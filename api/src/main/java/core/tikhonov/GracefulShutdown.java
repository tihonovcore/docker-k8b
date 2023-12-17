package core.tikhonov;

import io.micronaut.context.annotation.Context;
import io.micronaut.runtime.event.ApplicationShutdownEvent;
import io.micronaut.runtime.event.annotation.EventListener;

@Context
public class GracefulShutdown {

    @EventListener
    public void onShutdown(ApplicationShutdownEvent ignore) {
        System.out.println("graceful shutdown started");

        try {
            Thread.sleep(15_000);
        } catch (InterruptedException e) {
            System.out.println("graceful shutdown FAILED");
            throw new RuntimeException(e);
        }

        //долгий shutdown, если не выставить достаточно
        //большой terminationGracePeriodSeconds, то это
        //graceful shutdown не завершится успешно и это
        //сообщение не будет видно
        System.out.println("graceful shutdown finished");
    }
}
