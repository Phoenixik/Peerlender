package application;

import Repository.UserRepository;
import application.service.TokenValidationServiceImpl;
import service.TokenValidationService;
import domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private TokenValidationServiceImpl tokenValidationServiceImpl;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> findUsers(HttpServletRequest request) {

        tokenValidationServiceImpl.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
        return userRepository.findAll();
    }
}
