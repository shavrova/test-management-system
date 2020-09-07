package com.tms.smoke;

import com.tms.util.AuthUtils;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;


//TODO this test will start a server on random port and sent http request. problem that can't authenticate correctly. redirect to login page
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void loginPageIsAvailable(){
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/login",
                String.class)).contains("Login page");
    }

    @Test
    public void registrationPageIsAvailable() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/registration",
                String.class)).contains("Registration");
    }

    //Fix authentication
    @Test
    public void mainPageIsAvailable() {
        HttpHeaders headers = AuthUtils.createHeaders("spring", "1234");
        ResponseEntity<String> response =restTemplate.exchange("http://localhost:" + port + "/myTests", HttpMethod.GET, new HttpEntity(null, headers), String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.toString()).contains("Add new test quickly");
    }
}