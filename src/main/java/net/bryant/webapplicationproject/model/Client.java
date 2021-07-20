package net.bryant.webapplicationproject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Data
@Entity
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

    @Column(name = "number_of_policy")
    private int numberOfPolicy;

    @Column(name = "commission_amount")
    private int commissionAmount;

    @Column(name = "has_review")
    private boolean hasReview;

    @Column(name = "referral")
    private String referral;

    @JsonManagedReference
    @OneToMany(mappedBy = "carrier")
    private Set<Carrier> carrierSet = new HashSet<>();
}
