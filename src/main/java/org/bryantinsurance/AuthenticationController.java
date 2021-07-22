package org.bryantinsurance;

import org.bryantinsurance.dto.SimpleResponseDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @PostMapping("/login")
    public SimpleResponseDTO login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(principal instanceof CustomUserDetails){
                request.logout();
            }
            request.login(username, password);
            return SimpleResponseDTO
                    .builder()
                    .success(true)
                    .message("Login successful")
                    .build();
        } catch (ServletException e){
            return SimpleResponseDTO
                    .builder()
                    .success(false)
                    .message("Incorrect username or password")
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
                    .message("Logout successful")
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
