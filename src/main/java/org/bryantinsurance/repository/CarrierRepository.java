package org.bryantinsurance.repository;


import org.bryantinsurance.client.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.bryantinsurance.client.Client;

import java.util.List;
import java.util.Optional;

public interface CarrierRepository extends JpaRepository<Carrier, Long> {
    List<Carrier> findByClients(Client client);
    Carrier findByType(String type);
}
