package org.bryantinsurance.repository;

import org.bryantinsurance.model.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrierRepository extends JpaRepository<Carrier, Long> {
}
