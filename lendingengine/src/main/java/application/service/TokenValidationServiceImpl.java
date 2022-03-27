package application.service;

import Repository.UserRepository;
import domain.exception.UserNotFoundException;
import domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class TokenValidationServiceImpl {

    private UserRepository userRepository;
    private RestTemplate restTemplate = new RestTemplate();
    private final String securityContextBaseUrl;

    @Autowired
    public TokenValidationServiceImpl(UserRepository userRepository, @Value("${security.baseurl}")  final String securityContextBaseUrl) {
        this.userRepository = userRepository;
        this.securityContextBaseUrl = securityContextBaseUrl;
    }

    public User validateTokenAndGetUser(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        ResponseEntity<String> response = restTemplate
                .exchange(securityContextBaseUrl + "/user/validate", HttpMethod.POST, httpEntity, String.class);

        if(response.getStatusCode().equals(HttpStatus.OK)) {
            return userRepository.findById(response.getBody()).orElseThrow(() -> new UserNotFoundException(response.getBody()));
        } else
        {throw new RuntimeException("Invalid token");}
    }

}
