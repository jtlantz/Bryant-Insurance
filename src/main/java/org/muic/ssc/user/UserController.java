package org.muic.ssc.user;

import org.hibernate.type.StringNVarcharType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserRepository repo;

    @Autowired
    private UserService userService;

    // Return to the "Home" page of the application
    @GetMapping("")
    public String home(){
        return "home";
    }

    @GetMapping("/home")
    @ResponseBody
    public String showRegisterPage(Model model){
        model.addAttribute("user",new User());
        return "preventing circular error";
    }

    @PostMapping("/register_success")
    public String Register(User user){
        repo.save(user);
        return "Register success!";
    }
}
