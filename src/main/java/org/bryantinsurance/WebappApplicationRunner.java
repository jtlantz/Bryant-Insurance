package org.bryantinsurance;

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
        User adminUser = userRepository.findByUsername(("Phill"));
        if (adminUser == null) {
            adminUser = new User();
            adminUser.setUsername("Phill");
            adminUser.setPassword(passwordEncoder.encode("qvctyi80"));
            adminUser.setAuthorized(true);
            adminUser.setRole("ADMIN");
            userRepository.save(adminUser);
        }
        else{
            return;
        }

    }

}
