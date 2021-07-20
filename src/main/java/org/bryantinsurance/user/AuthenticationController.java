package org.bryantinsurance.user;

import org.bryantinsurance.SimpleResponseDTO;
import org.bryantinsurance.util.AjaxUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class AuthenticationController{

    @PostMapping("/login")
    public SimpleResponseDTO login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(principal != null && principal instanceof CustomUserDetails){
                request.logout();
            }
            request.login(username, password);
            request.setAttribute("role", "ADMIN");
            return SimpleResponseDTO
                    .builder()
                    .success(true)
                    .message("Successfully login")
                    .build();

        } catch (ServletException e){
            return SimpleResponseDTO
                    .builder()
                    .success(false)
                    .message("Invalid password or username")
                    .build();
        }
    }

    @GetMapping("logout")
    public SimpleResponseDTO logout(HttpServletRequest request) {
        try {
            request.logout();
            return SimpleResponseDTO
                    .builder()
                    .success(true)
                    .message("Successfully logout")
                    .build();

        } catch (ServletException e) {
            return SimpleResponseDTO
                    .builder()
                    .success(false)
                    .message("Failed to logout")
                    .build();

        }
    }
}