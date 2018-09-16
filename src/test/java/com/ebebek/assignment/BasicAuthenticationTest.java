package com.ebebek.assignment;

import static org.junit.Assert.assertEquals;

import com.ebebek.assignment.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BasicAuthenticationTest {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;

    @Test
    public void basicAuth() {
        String url = "http://localhost:" + port + "/login";
        String username = "mustafa.ergin";
        String password = "Mustafa@ebebek";

        ResponseEntity<String> responseEntity = restTemplate.withBasicAuth(username, password)
                                                            .getForEntity(url, String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void basicAuthInvalidCredentials() {
        String url = "http://localhost:" + port + "/login";
        String username = "invalid-user";
        String password = "invalid-password";

        ResponseEntity<String> response = restTemplate.withBasicAuth(username, password)
                                                      .getForEntity(url, String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

}
