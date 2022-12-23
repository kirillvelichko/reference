package my.project.integration.rest.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.project.configuration.rest.RestClientProps;
import my.project.integration.rest.user.request.UserRequest;
import my.project.integration.rest.user.response.UserResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserClient {
    private final RestTemplate restTemplate;
    private final RestClientProps restProperties;
    private HttpHeaders headers;
    private String userListUrl;

    @PostConstruct
    private void init() {
        userListUrl = restProperties.getUserListUrl();
        headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
    }

    public Optional<UserResponse> getUsers(UserRequest requestBody) {
        HttpEntity<UserRequest> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<UserResponse> response;
        try {
            response = restTemplate.postForEntity(userListUrl, request, UserResponse.class);
        } catch (RestClientResponseException e) {
            log.error("Received an error code. Code: {} Body: {}", e.getRawStatusCode(), e.getResponseBodyAsString());
            return Optional.empty();
        }
        return Optional.ofNullable(response.getBody());
    }
}
