package org.bryantinsurance.client;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Data
@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid")
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "quote_date")
    private String quoteDate;

    @Column(name = "date_sold")
    private String dateSold;

    @Column(name = "latest_contact_date")
    private String latestContactDate;

    @Column(name = "expiry_date")
    private String expiryDate;

    @Column(name = "quote_status")
    private String quoteStatus;

    @Column(name = "commission_amount")
    private int commissionAmount;

    @Column(name = "has_review")
    private boolean hasReview;

    @Column(name = "referral")
    private String referral;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "join_client_carrier",
            joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "cid", foreignKey = @ForeignKey(name = "FK_client")),
            inverseJoinColumns = @JoinColumn(name = "carrier_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_carrier")),
            uniqueConstraints = @UniqueConstraint(columnNames = {"client_id", "carrier_id"})
    )
    private Set<Carrier> carriers = new HashSet<>();
}

