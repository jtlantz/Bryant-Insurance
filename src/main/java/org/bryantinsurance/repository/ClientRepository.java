package org.bryantinsurance.repository;

import org.bryantinsurance.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByFirstname(String firstname);
    Client findByLastname(String lastname);
}
