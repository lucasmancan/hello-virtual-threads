package dev.danvega.hellovirtualthreads;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/httpbin")
public class HttpBinController {

    private static final Logger log = LoggerFactory.getLogger(HttpBinController.class);
    private final RestClient restClient;

    public HttpBinController(RestClient.Builder restClientBuilder) {
        restClient = restClientBuilder.baseUrl("https://httpbin.org/").build();
    }

    @GetMapping("/block/sync/{seconds}")
    public String delay2(@PathVariable int seconds) {
        ResponseEntity<Void> result = getVoidResponseEntity(seconds);
        log.info("{} on {}", result.getStatusCode(), Thread.currentThread());
        return Thread.currentThread().toString();
    }


    @GetMapping("/block/{seconds}")
    public CompletableFuture<String> delay(@PathVariable int seconds) {
        return CompletableFuture.supplyAsync(() -> {
            ResponseEntity<Void> result = getVoidResponseEntity(seconds);
            log.info("{} on {}", result.getStatusCode(), Thread.currentThread());
            return Thread.currentThread().toString();
        });
    }


    private ResponseEntity<Void> getVoidResponseEntity(int seconds) {
        return restClient.get()
                .uri("/delay/" + seconds)
                .retrieve()
                .toBodilessEntity();
    }

}
