package org.bryantinsurance.repository;

import org.bryantinsurance.model.Carrier;
import org.bryantinsurance.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarrierRepository extends JpaRepository<Carrier, Long> {
    List<Carrier> findByClients(Client client);
    Carrier findByType(String type);
}
