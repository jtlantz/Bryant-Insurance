package org.bryantinsurance;

import org.bryantinsurance.util.AjaxUtils;
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
            return AjaxUtils.convertToString(SimpleResponseDTO
                    .builder()
                    .success(true)
                    .message("Successfully login")
                    .build()
            );
        } catch (ServletException e){
            return AjaxUtils.convertToString(SimpleResponseDTO
                    .builder()
                    .success(true)
                    .message("Failed to login")
                    .build()
            );
        }
    }

    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        try {
            request.logout();
            return AjaxUtils.convertToString(SimpleResponseDTO
                    .builder()
                    .success(true)
                    .message("Successfully logout")
                    .build()
            );

        } catch (ServletException e) {
            return AjaxUtils.convertToString(SimpleResponseDTO
                    .builder()
                    .success(true)
                    .message("Failed to logout")
                    .build()
            );
        }
    }
}