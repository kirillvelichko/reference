package my.project.integration.rest.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.project.IntegrationTest;
import my.project.integration.client.rest.user.UserClient;
import my.project.integration.client.rest.user.request.UserRequest;
import my.project.integration.client.rest.user.response.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

class UserRestClientTest extends IntegrationTest {
    @Autowired
    private UserClient userClient;
    @Autowired
    private RestTemplate restTemplate;
    private MockRestServiceServer mockServer;
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void getUser_testRequestMapping() throws JsonProcessingException {
        var requestBody = createRequestBody();
        UserRequest request = mapper.readValue(requestBody, UserRequest.class);
        var actualRequest = mapper.writeValueAsString(request);
        assertEquals(mapper.readTree(requestBody), mapper.readTree(actualRequest));
    }

    @Test
    void getUsers_testOkResponseMapping() throws URISyntaxException, JsonProcessingException {
        var responseBody = createOkResponseBody();
        mockServer.expect(ExpectedCount.once(), requestTo(new URI("http://localhost:8081/user/get/list")))
                .andExpect(method(POST))
                .andRespond(withStatus(OK)
                        .contentType(APPLICATION_JSON)
                        .body(responseBody));

        var requestBody = createRequestBody();
        UserRequest request = mapper.readValue(requestBody, UserRequest.class);
        UserResponse response = userClient.getUsers(request).orElseThrow();

        mockServer.verify();
        assertEquals(1, response.getUserList().size());
        var user = response.getUserList().get(0);
        assertEquals("Petr", user.getFirstName());
        assertEquals("Petrov", user.getLastName());
    }

    private String createOkResponseBody() {
        return """
                {
                  "userList": [
                   {
                    "firstName": "Petr",
                    "lastName": "Petrov"
                   }
                 ]
                }
                """;
    }

    private String createRequestBody() {
        return """
                {
                 "firstName" : "Petr",
                 "lastName" : "Petrov"
                }
                """;
    }
}