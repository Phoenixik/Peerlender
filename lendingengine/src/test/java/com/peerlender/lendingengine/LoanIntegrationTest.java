package com.peerlender.lendingengine;

import application.model.LoanApplicationDTO;
import application.model.LoanRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.model.Loan;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class LoanIntegrationTest {

    private static final String JOHN = "John";
    private static final Gson GSON = new Gson();

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int serverPort;

    @Test
    public void givenLoanRequestIsMadeLoanApplicationGetsCreated() throws Exception{
        final  String baseUrl = "http://localhost:" + serverPort +"/loan/";
        HttpHeaders httpHeaders = new HttpHeaders();
        getHttpHeaders();

        HttpEntity<LoanRequest> request = new HttpEntity<>(new LoanRequest(50, 10L,10), getHttpHeaders());
        restTemplate.postForEntity(baseUrl + "/request", request, String.class);


        HttpEntity<String> httpEntity = new HttpEntity<>(null, getHttpHeaders());
        ResponseEntity<String> response = restTemplate.
                exchange(baseUrl + "/requests", HttpMethod.GET, httpEntity, String.class);
        List<LoanApplicationDTO> loanApplicationDTOS =
                GSON.fromJson(response.getBody(), new TypeToken<List<LoanApplicationDTO>>(){}.getType());

        assertEquals(1, loanApplicationDTOS.size());
        assertEquals(loanApplicationDTOS.get(0).getBorrower().getUsername(), JOHN);
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, JOHN);
        return httpHeaders;
    }
}
