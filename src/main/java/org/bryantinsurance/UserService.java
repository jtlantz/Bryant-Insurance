package org.bryantinsurance;

import org.bryantinsurance.model.Client;
import org.bryantinsurance.model.User;
import org.bryantinsurance.repository.ClientRepository;
import org.bryantinsurance.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        }
        throw new EntityNotFoundException();
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public SimpleResponseDTO createUser(User request) {
        User user = new User();
        if (userRepository.findByUsername(request.getUsername()) != null) {
            return SimpleResponseDTO
                    .builder()
                    .success(false)
                    .message("Username already exist.")
                    .build();
        }
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);
        return SimpleResponseDTO
                .builder()
                .success(true)
                .message("You created user successfully.")
                .build();
    }

    public SimpleResponseDTO updateUser(String username, User request) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return SimpleResponseDTO
                    .builder()
                    .success(false)
                    .message("Cannot find user in database.")
                    .build();
        } else if (userRepository.findByUsername(request.getUsername()) != null) {
            return SimpleResponseDTO
                    .builder()
                    .success(false)
                    .message("Username already exist.")
                    .build();
        }
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);
        return SimpleResponseDTO
                .builder()
                .success(true)
                .message("You updated user successfully.")
                .build();
    }

    public SimpleResponseDTO deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.delete(user);
            return SimpleResponseDTO
                    .builder()
                    .success(true)
                    .message("You deleted user successfully.")
                    .build();
        } else {
            return SimpleResponseDTO
                    .builder()
                    .success(false)
                    .message("Cannot find user in database.")
                    .build();
        }
    }

}
