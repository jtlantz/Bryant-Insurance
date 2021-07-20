package org.bryantinsurance.user;

import org.bryantinsurance.ClientService;
import org.bryantinsurance.SimpleResponseDTO;
import org.bryantinsurance.UserService;
import org.bryantinsurance.client.Carrier;
import org.bryantinsurance.client.Client;
import org.bryantinsurance.util.AjaxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
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
    public SimpleResponseDTO updateUser(@RequestBody User request, @PathVariable("username") String username) {
        return userService.updateUser(username, request);
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
    public SimpleResponseDTO createClient(@RequestBody Client request) {
        return clientService.createClient(request);
    }

    @PatchMapping("/client/{cid}")
    public SimpleResponseDTO updateClient(@RequestBody Client request, @PathVariable("cid") Long cid) {
        return clientService.updateClient(cid, request);
    }

    @DeleteMapping("/client/{cid}")
    public SimpleResponseDTO deleteClient(@PathVariable("cid") Long cid) {
        return clientService.deleteClient(cid);
    }

    @GetMapping("/carrier/{cid}")
    public List<Carrier> getCarriers(@PathVariable("cid") Long cid) {
        return clientService.findAllCarriers(cid);
    }

    @PostMapping("/carrier/create/{cid}")
    public SimpleResponseDTO createCarrier(@RequestBody Carrier request, @PathVariable("cid") Long cid) {
        return clientService.createCarrier(cid, request);
    }

    @DeleteMapping("/carrier/delete/{id}")
    public SimpleResponseDTO deleteCarrier(@PathVariable("id") Long id) {
        return clientService.deleteCarrier(id);
    }
}
