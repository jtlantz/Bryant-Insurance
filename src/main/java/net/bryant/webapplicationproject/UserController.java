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
    public SimpleResponseDTO createUser(@RequestBody User request) {
        return userService.createUser(request);
    }

    @PatchMapping("/user/{username}")
    public SimpleResponseDTO updateUser(@RequestBody User request, @PathVariable("username") String username) {
        return userService.updateUser(username, request);
    }

    @DeleteMapping("/user/{username}")
    public SimpleResponseDTO deleteUser(@PathVariable("username") String username) {
        return userService.deleteUser(username);
    }

    @GetMapping("/client")
    public ResponseEntity<List<Client>> findAllClients() {
        return ResponseEntity.ok(userService.findAllClients());
    }

    @PostMapping("/client")
    public SimpleResponseDTO createClient(@RequestBody Client request) {
        return userService.createClient(request);
    }

    @PatchMapping("/client/{cid}")
    public SimpleResponseDTO updateClient(@RequestBody Client request, @PathVariable("cid") Long cid) {
        return userService.updateClient(cid, request);
    }

    @DeleteMapping("/client/{cid}")
    public SimpleResponseDTO deleteClient(@PathVariable("cid") Long cid) {
        return userService.deleteClient(cid);
    }
}
