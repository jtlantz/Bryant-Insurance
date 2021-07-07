package net.bryant.webapplicationproject.repository;

import net.bryant.webapplicationproject.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByFirstname(String firstname);
    Client findByLastname(String lastname);
}
