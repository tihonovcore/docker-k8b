package core.tikhonov;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpMethod;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.HttpClient;
import jakarta.inject.Inject;
import org.slf4j.helpers.MessageFormatter;
import reactor.core.publisher.Mono;

@io.micronaut.http.annotation.Controller
public class Controller {

    @Inject
    HttpClient httpClient;

    @Property(name = "rest.db-crud.url")
    String url;

    @Get("user/{id}")
    void getUser(@PathVariable("id") String id) {
        log("in:  get(id={})", id);

        var response = exchange(
            HttpRequest.create(HttpMethod.GET, url + "/crud/user/" + id)
        );

        log("out: get(id={}) -> {}", id, response.body());
    }

    @Post("user")
    void addUser(@QueryValue("name") String name) {
        log("in:  add(name={})", name);

        var response = exchange(
            HttpRequest.create(HttpMethod.POST, url + "/crud/user?name=" + name)
        );

        log("out: add(name={}) -> {}", name, response.body());
    }

    private HttpResponse<String> exchange(HttpRequest<?> request) {
        return Mono.from(httpClient.exchange(request, String.class)).block();
    }

    private static void log(String message, Object... arguments) {
        var formatted = MessageFormatter.arrayFormat(message, arguments).getMessage();
        System.out.println(formatted);
    }
}
