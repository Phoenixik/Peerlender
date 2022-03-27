package service;

import domain.model.User;
import org.springframework.stereotype.Service;

@Service
public interface TokenValidationService {

    User validateTokenAndGetUser(final String token);

}
