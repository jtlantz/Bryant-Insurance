package net.bryant.webapplicationproject;

import net.bryant.webapplicationproject.model.Client;
import net.bryant.webapplicationproject.model.User;
import net.bryant.webapplicationproject.repository.ClientRepository;
import net.bryant.webapplicationproject.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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

    // CreateUserResponseDTO
    public User createUser(User request) {
        User user = new User();
        if (userRepository.findByUsername(request.getUsername()) != null) {
            throw new DuplicateKeyException("Username already exist");
        }
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        return userRepository.save(user);
    }

    public User updateUser(String username, User request) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new EntityNotFoundException("Cannot find user in database");
        } else if (userRepository.findByUsername(request.getUsername()) != null) {
            throw new DuplicateKeyException("Username already exist");
        }
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        return userRepository.save(user);
    }

    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.delete(user);
        }
    }

    public Client createClient(Client request) {
        Client firstname = clientRepository.findByFirstname(request.getFirstname());
        Client lastname = clientRepository.findByLastname(request.getLastname());
        if (firstname != null && lastname != null) {
            throw new DuplicateKeyException("Client already exist");
        }
        Client client = new Client();
        BeanUtils.copyProperties(request, client);
        return clientRepository.save(client);
    }

    public Client updateClient(Long cid, Client request) {
        Optional<Client> optionalClient = clientRepository.findById(cid);
        Client firstname = clientRepository.findByFirstname(request.getFirstname());
        Client lastname = clientRepository.findByLastname(request.getLastname());
        if (!optionalClient.isPresent()) {
            throw new EntityNotFoundException("Cannot find client in database");
        } else if (firstname != null && lastname != null) {
            throw new DuplicateKeyException("Client already exist");
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
        return clientRepository.save(client);
    }

    public void deleteClient(Long cid) {
        Optional<Client> client = clientRepository.findById(cid);
        if (client.isPresent()) {
            clientRepository.deleteById(cid);
        }
    }

}
