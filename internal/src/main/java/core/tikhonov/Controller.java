package core.tikhonov;

import io.micronaut.http.annotation.*;
import jakarta.inject.Named;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.helpers.MessageFormatter;

import javax.sql.DataSource;

@io.micronaut.http.annotation.Controller
public class Controller {

    Jdbi jdbi;

    public Controller(@Named("pg") DataSource dataSource) {
        this.jdbi = Jdbi.create(dataSource);
    }

    @Get("crud/user/{id}")
    @Produces("application/text")
    String getUser(@PathVariable("id") String id) {
        log("crud in:  get(id={})", id);

        var name = jdbi.withHandle(handle -> handle.createQuery("select name from users where id = :id").bind("id", Integer.valueOf(id)).mapTo(String.class).one());

        log("crud out: add(name={}) -> {}", name, id);

        return name;
    }

    @Post("crud/user")
    @Produces("application/text")
    String addUser(@QueryValue("name") String name) {
        log("crud in:  add(name={})", name);

        var id = jdbi.withHandle(handle -> handle.createUpdate("insert into users (name) values (:name)").bind("name", name).executeAndReturnGeneratedKeys("id").mapTo(String.class).one());

        log("crud out: add(name={}) -> {}", name, id);

        return id;
    }

    private static void log(String message, Object... arguments) {
        var formatted = MessageFormatter.arrayFormat(message, arguments).getMessage();
        System.out.println(formatted);
    }
}
