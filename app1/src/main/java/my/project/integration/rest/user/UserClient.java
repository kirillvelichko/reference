package my.project.integration.rest.user;

import lombok.RequiredArgsConstructor;
import my.project.configuration.rest.RestClientProps;
import my.project.integration.rest.user.request.UserRequest;
import my.project.integration.rest.user.response.UserResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class UserClient {
    private final RestTemplate restTemplate;
    private final RestClientProps restProperties;
    private HttpHeaders headers;
    private String url;

    @PostConstruct
    private void init() {
        url = restProperties.getUserListUrl();
        headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
    }

    public Optional<UserResponse> getUsers(UserRequest requestBody) {
        HttpEntity<UserRequest> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<UserResponse> response = restTemplate.postForEntity(url, request, UserResponse.class);
        return Optional.ofNullable(response.getBody());
    }
}
