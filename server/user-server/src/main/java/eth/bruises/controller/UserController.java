package eth.bruises.controller;

import eth.bruises.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Value("${server.port}")
    private String port;
    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return new User(1L, "bruises", "test --port=" + port);
    }

}
