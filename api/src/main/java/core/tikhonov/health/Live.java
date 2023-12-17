package core.tikhonov.health;

import io.micronaut.health.HealthStatus;
import io.micronaut.management.health.indicator.HealthIndicator;
import io.micronaut.management.health.indicator.HealthResult;
import io.micronaut.management.health.indicator.annotation.Liveness;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;

@Liveness
public class Live implements HealthIndicator {

    private static final Instant start = Instant.now();

    private static final String NAME = "live-hc";

    @Override
    public Publisher<HealthResult> getResult() {
        return Mono.just(HealthResult.builder(NAME, status()).build());
    }

    //2 минуты OK, потом DOWN
    private HealthStatus status() {
        if (start.plus(Duration.ofMinutes(2)).isBefore(Instant.now())) {
            return HealthStatus.DOWN;
        }

        return HealthStatus.UP;
    }
}
