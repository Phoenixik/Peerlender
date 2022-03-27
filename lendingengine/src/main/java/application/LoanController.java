package application;

import Repository.LoanApplicationRepsitory;
import Repository.UserRepository;
import application.model.LoanRepaymentRequest;
import application.model.LoanRequest;
import application.service.TokenValidationServiceImpl;
import service.TokenValidationService;
import domain.model.Loan;
import domain.model.LoanApplication;
import domain.model.Status;
import domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import service.LoanApplicationAdapter;
import service.LoanService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class LoanController {


    @Autowired
    private LoanApplicationRepsitory loanApplicationRepsitory;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private LoanApplicationAdapter loanApplicationAdapter;
    @Autowired
    private LoanService loanService;
    @Autowired
    private TokenValidationServiceImpl tokenValidationServiceImpl;


    //request a loan
    @PostMapping(value = "/loan/request")
    public void  requestLoan(@RequestBody final LoanRequest loanrequest, HttpServletRequest request) {

       User borrwer= tokenValidationServiceImpl.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        LoanApplication loanApplication= loanApplicationAdapter.transform(loanrequest, borrwer);
        loanApplicationRepsitory.save(loanApplication);
        System.out.println(loanrequest);
    }

    //returns all user details
    @GetMapping("/loan/{status}/borrowed")
    public List<Loan> findBorrowedLoans(@RequestHeader String authorization,
                                        @PathVariable Status status) {

        User user = tokenValidationServiceImpl.validateTokenAndGetUser(authorization);
        return loanService.findAllBorrowedLoans(user, status);
    }

    @PostMapping("/loan/repay")
    public void repayLoan(@RequestBody LoanRepaymentRequest request, @RequestHeader String authorization) {
        User borrower = tokenValidationServiceImpl.validateTokenAndGetUser(authorization);
        loanService.repayLoan(request.getAmount(), request.getLoanId(), borrower);
    }

    @GetMapping("/loan/{status}/lent")
    public List<Loan> findLentLoans(@RequestHeader String authorization,
                                    @PathVariable Status status) {
        User user = tokenValidationServiceImpl.validateTokenAndGetUser(authorization);
        return loanService.findAllByLender(user, status);

    }

    //find all loan requests
    @GetMapping("/loan/requests")
    public List<LoanApplication> findallLoanApplication(HttpServletRequest request) {

        tokenValidationServiceImpl.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        return loanApplicationRepsitory.findAllByStatus(Status.ONGOING);
    }

   // accept loan request
    @PostMapping(value = "/loan/accept/{loanApplicationId}")
    public void acceptLoan(@PathVariable String loanApplicationId, HttpServletRequest request) {
        User lender= tokenValidationServiceImpl.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        loanService.acceptLoan(Long.parseLong(loanApplicationId), lender.getUsername());
    }
    @GetMapping(value = "/loans")
    public List<Loan> getLoanss() {
        return loanService.getLoans();
    }
}
