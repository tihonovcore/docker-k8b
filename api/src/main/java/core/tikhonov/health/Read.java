package core.tikhonov.health;

import io.micronaut.health.HealthStatus;
import io.micronaut.management.health.indicator.HealthIndicator;
import io.micronaut.management.health.indicator.HealthResult;
import io.micronaut.management.health.indicator.annotation.Readiness;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

@Readiness
public class Read implements HealthIndicator {

    private static final Instant start = Instant.now();

    private static final String NAME = "read-hc";

    @Override
    public Publisher<HealthResult> getResult() {
        return Mono.just(HealthResult.builder(NAME, status()).build());
    }

    //2 минуты OK, 2 минуты DOWN, потом OK
    private HealthStatus status() {
        if (start.plus(Duration.ofMinutes(2)).isBefore(Instant.now())) {

            if (start.plus(Duration.ofMinutes(4)).isAfter(Instant.now())) {
                return HealthStatus.DOWN;
            }

        }

        return HealthStatus.UP;
    }
}
