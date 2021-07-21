package org.bryantinsurance.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name = "carrier")
public class Carrier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "join_client_carrier",
            inverseJoinColumns = @JoinColumn(name = "client_id", referencedColumnName = "cid", foreignKey = @ForeignKey(name = "FK_client")),
            joinColumns = @JoinColumn(name = "carrier_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_carrier")),
            uniqueConstraints = @UniqueConstraint(columnNames = {"client_id", "carrier_id"})
    )
    private Set<Client> clients = new HashSet<>();

}
