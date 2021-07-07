package net.bryant.webapplicationproject.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "carrier")
public class Carrier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Type")
    private String type;

//    @ManyToOne
//    @JoinColumn(name = "CID")
//    @JsonManagedReference
//    private Client client;

}
