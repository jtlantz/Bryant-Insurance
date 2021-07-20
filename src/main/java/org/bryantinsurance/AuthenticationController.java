package org.bryantinsurance;

import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class AuthenticationController{

    @PostMapping("/login")
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            request.login(username, password);
            return "Login";
        } catch (ServletException e){
            return "Failed to Login";
        }
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        try {
            request.logout();
            return "Logout";
        } catch (ServletException e) {
            return "Failed to Logout";
        }
    }
}