package org.bryantinsurance;

import org.bryantinsurance.model.User;
import org.bryantinsurance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class WebappApplicationRunner implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new  BCryptPasswordEncoder();

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User adminUser = userRepository.findByUsername(("admin"));
        if (adminUser == null) {
            adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("123456"));
            adminUser.setRole("ADMIN");
            userRepository.save(adminUser);
        }
    }
}
