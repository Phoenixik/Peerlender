package service;

import Repository.UserRepository;
import domain.exception.UserNotFoundException;
import domain.model.Money;
import domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BalanceService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void topUpBalance(Money money, String authToken) {
        User user = findUser(userRepository, authToken);
        user.topUp(money);
    }

    @Transactional
    public void withdrawFromBalance(Money money, String authToken) {
        User user= findUser(userRepository, authToken);
        user.withdraw(money);
    }

    private User findUser(UserRepository userRepository, String authToken) {
        User user = userRepository.findById(authToken).orElseThrow(() -> new UserNotFoundException(authToken));
        return user;
    }
}
