package net.bryant.webapplicationproject;

import net.bryant.webapplicationproject.model.Client;
import net.bryant.webapplicationproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUser());
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PatchMapping("/user/{username}")
    public ResponseEntity<User> updateUser(@RequestBody User request, @PathVariable("username") String username) {
        return ResponseEntity.ok(userService.updateUser(username, request));
    }

    @DeleteMapping("/user/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/client")
    public ResponseEntity<List<Client>> findAllClients () {
        return ResponseEntity.ok(userService.findAllClients());
    }

    @PostMapping("/client")
    public ResponseEntity<Client> createClient(@RequestBody Client request) {
        return ResponseEntity.ok(userService.createClient(request));
    }

    @GetMapping("/client/{cid}")
    public ResponseEntity<List<Client>> findClient(@PathVariable("cid") Long cid) {
        return ResponseEntity.ok(userService.findAllClients());
    }

    @PatchMapping("/client/{cid}")
    public ResponseEntity<Client> updateClient(@RequestBody Client request, @PathVariable("cid") Long cid) {
        return ResponseEntity.ok(userService.updateClient(cid, request));
    }

    @DeleteMapping("/client/{cid}")
    public ResponseEntity<Void> deleteClient(@PathVariable("cid") Long cid) {
        userService.deleteClient(cid);
        return ResponseEntity.ok().build();
    }
}
