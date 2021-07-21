package org.bryantinsurance;

import org.bryantinsurance.model.Carrier;
import org.bryantinsurance.model.Client;
import org.bryantinsurance.repository.CarrierRepository;
import org.bryantinsurance.repository.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CarrierRepository carrierRepository;

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public SimpleResponseDTO createClient(Client request) {
        Client firstname = clientRepository.findByFirstname(request.getFirstname());
        Client lastname = clientRepository.findByLastname(request.getLastname());
        if (firstname != null && lastname != null) {
            throw new DuplicateKeyException("Client already exist");
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

    public SimpleResponseDTO addCarrierToClient(Long cid, Carrier request) {
        Client client = clientRepository.getById(cid);
        Carrier carrier = carrierRepository.findByType(request.getType());
        client.getCarriers().add(carrier);
        clientRepository.save(client);
        return SimpleResponseDTO
                .builder()
                .success(true)
                .message("You created carrier successfully.")
                .build();
    }

    public List<Carrier> findAllCarriers(Long cid) {
        Client client = clientRepository.getById(cid);
        return carrierRepository.findByClients(client);
    }

    public SimpleResponseDTO deleteCarrierFromClient(Long cid, Long id) {
        Client client = clientRepository.getById(cid);
        Optional<Carrier> carrier = carrierRepository.findById(id);
        if (carrier.isPresent()) {
            client.getCarriers().remove(carrier.get());
            clientRepository.save(client);
            return SimpleResponseDTO
                    .builder()
                    .success(true)
                    .message("You deleted carrier successfully.")
                    .build();
        } else {
            return SimpleResponseDTO
                    .builder()
                    .success(false)
                    .message("You deleted carrier unsuccessfully.")
                    .build();
        }

    }
}
