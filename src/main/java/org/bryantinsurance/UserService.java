package org.bryantinsurance;

import org.bryantinsurance.dto.SimpleResponseDTO;
import org.bryantinsurance.dto.UserDTO;
import org.bryantinsurance.model.User;
import org.bryantinsurance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public SimpleResponseDTO createSimpleResponseDTO(Boolean bool, String message) {
        return SimpleResponseDTO.builder()
                .success(bool)
                .message(message)
                .build();
    }

    public UserDTO createUserDTO(User user) {
        return UserDTO.builder()
                .sid(user.getSid())
                .username(user.getUsername())
                .role(user.getRole())
                .build();
    }

    public UserDTO findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return createUserDTO(user);
        }
        return UserDTO.builder()
                .build();
    }

    public List<UserDTO> findAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for(User user: users) {
            userDTOList.add(createUserDTO(user));
        }
        return userDTOList;
    }

    public SimpleResponseDTO createUser(User request) {
        User user = new User();
        if (userRepository.findByUsername(request.getUsername()) != null) {
            return createSimpleResponseDTO(false, "Username already exist.");
        }
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);
        return createSimpleResponseDTO(true, "You created user successfully.");
    }

    public SimpleResponseDTO updateUser(String username, User request) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return createSimpleResponseDTO(false, "Cannot find user in database.");
        }
        user.setUsername(request.getUsername());
        user.setRole(request.getRole());
        userRepository.save(user);
        return createSimpleResponseDTO(true, "You updated user successfully.");
    }

    public SimpleResponseDTO deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.delete(user);
            return createSimpleResponseDTO(true, "You deleted user successfully.");
        } else {
            return createSimpleResponseDTO(false, "Cannot find user in database.");
        }
    }

    public SimpleResponseDTO changePassword(String username, User request) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            return createSimpleResponseDTO(false, "Cannot find user in database.");
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return createSimpleResponseDTO(true,"You changed password successfully.");
    }
}
