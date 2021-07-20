package org.bryantinsurance;

import org.bryantinsurance.model.Carrier;
import org.bryantinsurance.model.Client;
import org.bryantinsurance.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    @GetMapping("/user/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUser());
    }

    @PostMapping("/user/create")
    public SimpleResponseDTO createUser(@RequestBody User request) {
        return userService.createUser(request);
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
        return ResponseEntity.ok(clientService.findAllClients());
    }

    @PostMapping("/client")
    public ResponseEntity<Client> createClient(@RequestBody Client request) {
        return ResponseEntity.ok(clientService.createClient(request));
    }

    @GetMapping("/client/{cid}")
    public ResponseEntity<List<Client>> findClient(@PathVariable("cid") Long cid) {
        return ResponseEntity.ok(clientService.findAllClients());
    }

    @PatchMapping("/client/{cid}")
    public ResponseEntity<Client> updateClient(@RequestBody Client request, @PathVariable("cid") Long cid) {
        return ResponseEntity.ok(clientService.updateClient(cid, request));
    }

    @DeleteMapping("/client/{cid}")
    public ResponseEntity<Void> deleteClient(@PathVariable("cid") Long cid) {
        clientService.deleteClient(cid);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/carrier/create/{cid}")
    public SimpleResponseDTO createCarrier(@RequestBody Carrier request, @PathVariable("cid") Long cid) {
        return clientService.createCarrier(cid, request);
    }
}
