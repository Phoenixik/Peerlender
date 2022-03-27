package service;

import Repository.LoanApplicationRepsitory;
import Repository.LoanRepository;
import Repository.UserRepository;
import domain.exception.LoanApplicationNotFoundException;
import domain.exception.LoanNotFoundException;
import domain.exception.UserNotFoundException;
import domain.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanApplicationRepsitory loanApplicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Transactional
    public void acceptLoan(Long loanApplicationId, String lenderUsername) {
        User lender = findUser(lenderUsername);
        LoanApplication loanApplication = findLoanApplication (loanApplicationId);
//        User borrower = loanApplication.getBorrower();
//        Money money = loanApplication.getAmount();
//        lender.withdraw(money);
//        borrower.topUp(money);
        loanRepository.save(loanApplication.acceptLoanApplication(lender));
    }

    private LoanApplication findLoanApplication(Long loanApplicationId) {
        return loanApplicationRepository.findById(loanApplicationId).
                orElseThrow(() -> new LoanApplicationNotFoundException(loanApplicationId));
    }

    private User findUser(String lenderUsername) {
        return userRepository.findById(lenderUsername).orElseThrow(() -> new UserNotFoundException(lenderUsername));
    }

    public List<Loan> getLoans() {
        return loanRepository.findAll();
    }

    public List<Loan> findAllBorrowedLoans(User borrower, Status status) {
        return loanRepository.findAllByBorrowerAndStatus(borrower, status);
    }

    public List<Loan> findAllByLender(User lender, Status status) {
        return loanRepository.findAllByLenderAndStatus(lender, status);
    }

    @Transactional
    public void repayLoan(final Money amountToRepay, final Long loanId, final User borrower) {
        Loan loan = loanRepository.findOneByIdAndBorrower(loanId, borrower)
                .orElseThrow(()-> new LoanNotFoundException());
        Money actualPaidAmount = amountToRepay.getAmount() > loan.getAmountOwed().getAmount() ?
                loan.getAmountOwed() : amountToRepay;
        loan.repay(actualPaidAmount);
    }

}
