package com.bachar.recipes.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class LoginControllerTest {

    private static final String API_0_1_LOGIN = "/api/1.0/login";

    @Autowired
    TestRestTemplate testRestTemplate;


    @Test
    public void postLogin_withoutUserCredentials_returnUnauthorized() {
        ResponseEntity<Object> response = postLogin(Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    public void postLogin_withUnCorrecttUserCredentials_returnUnauthorized() {
        authenticate();
        ResponseEntity<Object> response = postLogin(Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    private void authenticate() {
        testRestTemplate.getRestTemplate().getInterceptors().add(new BasicAuthenticationInterceptor("test-user","P4ssword"));
    }

    public <T> ResponseEntity<T> postLogin(Class<T> responseType) {
        return testRestTemplate.postForEntity(API_0_1_LOGIN, null, responseType);
    }
}
