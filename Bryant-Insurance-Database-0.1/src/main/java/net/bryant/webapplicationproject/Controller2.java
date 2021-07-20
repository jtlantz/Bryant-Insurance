package net.bryant.webapplicationproject;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller2 {

    // Return to the "Home" page of the application
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public String showRegisterPage(Model model) {
        return "this is admin Page";
    }

}
