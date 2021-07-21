package org.bryantinsurance;

import org.bryantinsurance.user.User;
import org.bryantinsurance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

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

    // CreateUserResponseDTO
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
            throw new EntityNotFoundException("Cannot find user in database");
        } else if (userRepository.findByUsername(request.getUsername()) != null) {
            throw new DuplicateKeyException("Username already exist");
        }
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        return SimpleResponseDTO
                .builder()
                .success(true)
                .message("You updated user successfully.")
                .build();
    }

    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.delete(user);
        }
    }
}
