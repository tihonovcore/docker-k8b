package core.tikhonov;

import io.micronaut.context.annotation.Context;
import io.micronaut.context.annotation.Property;
import io.micronaut.runtime.event.ApplicationStartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

@Context
public class GreedyService {

    @Property(name = "greedy.mode")
    private String mode;

    @EventListener
    public void onStart(ApplicationStartupEvent ignored) {
        switch (mode) {
            case "CPU" -> cpu();
            case "MEM" -> mem();
        }
    }

    private static void cpu() {
        System.out.println("eat cpu");

        long x = 2;
        next : while (true) {
            for (int i = 2; i < x; i++) {
                if (x % i == 0) {
                    x++;
                    continue next;
                }
            }

            System.out.println(x++);
        }
    }

    public static void main(String[] args) {
        mem();
    }

    private static void mem() {
        System.out.println("eat mem");

        var random = new Random();
        var dates = new ArrayList<String>();
        dates.add(Instant.now().toString());

        while (true) {
            for (int i = 0; i < 5000; i++) {
                var prev = random.nextInt(dates.size());
                dates.add(Instant.now().toString() + dates.get(prev));
            }

            var diff = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            System.out.println(diff / 1024 / 1024 + " mb");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
