package org.bryantinsurance;

import org.bryantinsurance.dto.CarrierDTO;
import org.bryantinsurance.dto.ClientDTO;
import org.bryantinsurance.dto.SimpleResponseDTO;
import org.bryantinsurance.model.Carrier;
import org.bryantinsurance.model.Client;
import org.bryantinsurance.repository.CarrierRepository;
import org.bryantinsurance.repository.ClientRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CarrierRepository carrierRepository;

    public SimpleResponseDTO createSimpleResponseDTO(Boolean bool, String message) {
        return SimpleResponseDTO.builder()
                .success(bool)
                .message(message)
                .build();
    }

    public ClientDTO createClientDTO(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .firstname(client.getFirstname())
                .lastname(client.getLastname())
                .phoneNumber(client.getPhoneNumber())
                .email(client.getEmail())
                .quoteDate(client.getQuoteDate())
                .dateSold(client.getDateSold())
                .latestContactDate(client.getLatestContactDate())
                .expiryDate(client.getExpiryDate())
                .quoteStatus(client.getQuoteStatus())
                .commissionAmount(client.getCommissionAmount())
                .hasReview(client.isHasReview())
                .referral(client.getReferral())
                .carriers(client.getCarriers())
                .build();
    }

    public CarrierDTO createCarrierDTO(Carrier carrier) {
        return CarrierDTO.builder()
                .id(carrier.getId())
                .type(carrier.getType())
                .build();
    }

    public List<ClientDTO> findAllClients() {
        List<Client> clients = clientRepository.findAll();
        List<ClientDTO> clientDTOList = new ArrayList<>();
        for(Client client: clients) {
            clientDTOList.add(createClientDTO(client));
        }
        return clientDTOList;
    }

    public SimpleResponseDTO createClient(Client request) {
        Client firstname = clientRepository.findByFirstname(request.getFirstname());
        Client lastname = clientRepository.findByLastname(request.getLastname());
        if (firstname != null && lastname != null) {
            return createSimpleResponseDTO(false, "Client already exist");
        }
        Client client = new Client();
        BeanUtils.copyProperties(request, client);
        clientRepository.save(client);
        return createSimpleResponseDTO(true, "You created client successfully.");
    }

    public SimpleResponseDTO updateClient(Long cid, Client request) {
        Optional<Client> optionalClient = clientRepository.findById(cid);
        if (!optionalClient.isPresent()) {
            return createSimpleResponseDTO(false, "Cannot find client in database");
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
        return createSimpleResponseDTO(true, "You updated client successfully.");
    }

    public SimpleResponseDTO deleteClient(Long cid) {
        Optional<Client> client = clientRepository.findById(cid);
        if (client.isPresent()) {
            clientRepository.deleteById(cid);
            return createSimpleResponseDTO(true, "You deleted client successfully.");
        } else {
            return createSimpleResponseDTO(false, "Cannot find client in database.");
        }
    }

    public SimpleResponseDTO addCarrierToClient(Long cid, Carrier request) {
        Client client = clientRepository.getById(cid);
        Carrier carrier = carrierRepository.findByType(request.getType());
        client.getCarriers().add(carrier);
        carrier.getClients().add(client);
        carrierRepository.save(carrier);
        clientRepository.save(client);
        return createSimpleResponseDTO(true, "You created carrier successfully.");
    }

    public List<CarrierDTO> findAllCarriersOfClient(Long cid) {
        Client client = clientRepository.getById(cid);
        List<Carrier> carriers = carrierRepository.findByClients(client);
        List<CarrierDTO> carrierDTOList = new ArrayList<>();
        for(Carrier carrier: carriers) {
            carrierDTOList.add(createCarrierDTO(carrier));
        }
        return carrierDTOList;
    }

    public SimpleResponseDTO deleteCarrierFromClient(Long cid, Long id) {
        Client client = clientRepository.getById(cid);
        Optional<Carrier> carrier = carrierRepository.findById(id);
        if (carrier.isPresent()) {
            client.getCarriers().remove(carrier.get());
            clientRepository.save(client);
            return createSimpleResponseDTO(true, "You deleted carrier successfully.");
        } else {
            return createSimpleResponseDTO(false, "You deleted carrier unsuccessfully.");
        }
    }

    public List<CarrierDTO> getAllCarriers() {
        List<Carrier> carriers = carrierRepository.findAll();
        List<CarrierDTO> carrierDTOList = new ArrayList<>();
        for(Carrier carrier: carriers) {
            carrierDTOList.add(createCarrierDTO(carrier));
        }
        return carrierDTOList;
    }

    public SimpleResponseDTO createCarrier(Carrier request) {
        Carrier carrier = new Carrier();
        BeanUtils.copyProperties(request, carrier);
        carrierRepository.save(carrier);
        return createSimpleResponseDTO(true, "You created carrier successfully.");
    }

    public SimpleResponseDTO deleteCarrier(Long id) {
        Optional<Carrier> carrier = carrierRepository.findById(id);
        if (carrier.isPresent()) {
            carrierRepository.deleteById(id);
            return createSimpleResponseDTO(true, "You deleted carrier successfully.");
        } else {
            return createSimpleResponseDTO(false, "Cannot find carrier in database.");
        }
    }
}
