package org.bryantinsurance.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bryantinsurance.model.Carrier;

import javax.persistence.Column;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Builder
public class ClientDTO {

    private Long id;

    private String firstname;

    private String lastname;

    private String phoneNumber;

    private String email;

    private String quoteDate;

    private String dateSold;

    private String latestContactDate;

    private String expiryDate;

    private String quoteStatus;

    private int commissionAmount;

    private boolean hasReview;

    private String referral;

    private Set<Carrier> carriers;
}
