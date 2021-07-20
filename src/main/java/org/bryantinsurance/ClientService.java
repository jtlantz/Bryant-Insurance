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

    public SimpleResponseDTO createCarrier(Long cid, Carrier request) {
        Carrier carrier = new Carrier();
        BeanUtils.copyProperties(request, carrier);
        carrier.setClient(clientRepository.getById(cid));
        carrierRepository.save(carrier);
        return SimpleResponseDTO
                .builder()
                .success(true)
                .message("You created carrier successfully.")
                .build();
    }
}
