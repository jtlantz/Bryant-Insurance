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
        User adminUser = userRepository.findByUsername(("nan"));
        if (adminUser == null) {
            adminUser = new User();
            adminUser.setUsername("nan");
            adminUser.setPassword(passwordEncoder.encode("123456"));
            adminUser.setRole("ROLE_USER");
            userRepository.save(adminUser);
        }
    }
}
