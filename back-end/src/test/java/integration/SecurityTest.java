package integration;

import com.evo.backend.BackEndApplication;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.security.Principal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackEndApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations="classpath:test.yml")
public class SecurityTest {
    TestRestTemplate restTemplate = new TestRestTemplate();
    String token;
    @Value("${local.server.port}")
    private int port;

    @Before
    public void setup(){
        String clientId = "clientId";
        String secret = "secret";
        String url = "http://localhost:9000/oauth/token";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("grant_type", "password")
                .queryParam("username", "user")
                .queryParam("password", "pass");

        HttpHeaders headers = getAuthorizationHeader(clientId, secret);

        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<OAuth2AccessToken> response =
                restTemplate.exchange(builder.toUriString(),
                        HttpMethod.POST, entity, OAuth2AccessToken.class);
        Assert.assertEquals(response.getStatusCodeValue(), 200);
        this.token = response.getBody().getValue();
    }

    @Test
    public void notAuthenticatedUserGetPrincipal() {
        HttpEntity<Object> entity = new HttpEntity<>(getTokenHeader());

        String resourceUrl = "http://localhost:" + port + "/me";
        ResponseEntity<String> response = restTemplate.exchange(
                resourceUrl, HttpMethod.GET,entity, String.class);
        Assert.assertEquals(response.getStatusCodeValue(), 200);
        Assert.assertTrue(response.getBody().contains( "user"));

    }

    private HttpHeaders getTokenHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization" , "Bearer " + token);
        return headers;
    }

    private HttpHeaders getAuthorizationHeader(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        return headers;
    }


}
