package org.bryantinsurance.repository;

import org.bryantinsurance.model.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarrierRepository extends JpaRepository<Carrier, Long> {
    List<Carrier> findByClientId(Long cid);
}
