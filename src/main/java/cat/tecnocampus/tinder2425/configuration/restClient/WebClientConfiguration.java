package cat.tecnocampus.tinder2425.configuration.restClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Bean
    public WebClient createWebClient() {
        return WebClient.create("http://localhost/api");
    }
}
