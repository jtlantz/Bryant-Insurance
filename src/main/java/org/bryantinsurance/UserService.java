package net.bryant.webapplicationproject;

import net.bryant.webapplicationproject.model.Client;
import net.bryant.webapplicationproject.model.User;
import net.bryant.webapplicationproject.repository.ClientRepository;
import net.bryant.webapplicationproject.repository.UserRepository;
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

    public SimpleResponseDTO createClient(Client request) {
        Client firstname = clientRepository.findByFirstname(request.getFirstname());
        Client lastname = clientRepository.findByLastname(request.getLastname());
        if (firstname != null && lastname != null) {
            return SimpleResponseDTO
                    .builder()
                    .success(false)
                    .message("Client already exist.")
                    .build();
        }
        Client client = new Client();
        BeanUtils.copyProperties(request, client);
        clientRepository.save(client);
        return SimpleResponseDTO
                .builder()
                .success(true)
                .message("You created client successfully.")
                .build();
    }

    public SimpleResponseDTO updateClient(Long cid, Client request) {
        Optional<Client> optionalClient = clientRepository.findById(cid);
        Client firstname = clientRepository.findByFirstname(request.getFirstname());
        Client lastname = clientRepository.findByLastname(request.getLastname());
        if (!optionalClient.isPresent()) {
            return SimpleResponseDTO
                    .builder()
                    .success(false)
                    .message("Cannot find client in database.")
                    .build();
        } else if (firstname != null && lastname != null) {
            return SimpleResponseDTO
                    .builder()
                    .success(false)
                    .message("Client already exist.")
                    .build();
        }
        Client client = optionalClient.get();
        client.setFirstname(request.getFirstname());
        client.setLastname(request.getLastname());
        client.setPhoneNumber(request.getPhoneNumber());
        client.setEmail(request.getEmail());
        client.setQuoteDate(request.getQuoteDate());
        client.setDateSold(request.getDateSold());
        client.setLatestContactDate(request.getLatestContactDate());
        client.setExpiryDate(request.getExpiryDate());
        client.setQuoteStatus(request.getQuoteStatus());
        client.setNumberOfPolicy(request.getNumberOfPolicy());
        client.setCommissionAmount(request.getCommissionAmount());
        client.setHasReview(request.isHasReview());
        client.setReferral(request.getReferral());
        clientRepository.save(client);
        return SimpleResponseDTO
                .builder()
                .success(true)
                .message("You updated client successfully.")
                .build();
    }

    public SimpleResponseDTO deleteClient(Long cid) {
        Optional<Client> client = clientRepository.findById(cid);
        if (client.isPresent()) {
            clientRepository.deleteById(cid);
            return SimpleResponseDTO
                    .builder()
                    .success(true)
                    .message("You deleted client successfully.")
                    .build();
        } else {
            return SimpleResponseDTO
                    .builder()
                    .success(false)
                    .message("Cannot find client in database.")
                    .build();
        }
    }

}
