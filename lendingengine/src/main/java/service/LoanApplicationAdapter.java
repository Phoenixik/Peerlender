package service;

import Repository.UserRepository;
import application.model.LoanRequest;
import domain.exception.UserNotFoundException;
import domain.model.LoanApplication;
import domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
public class LoanApplicationAdapter {

    @Autowired
    private UserRepository userRepository;

    public LoanApplication transform(LoanRequest request, User borrower) {
         Optional<User> userOptional = userRepository.findById(borrower.getUsername());
         if(userOptional.isPresent()) {
             return new LoanApplication(request.getAmount(),  userOptional.get(),
                     request.getDaysToRepay(), request.getInterestRate());
         }else {
             throw new UserNotFoundException(borrower.getUsername());
         }
    }
}
