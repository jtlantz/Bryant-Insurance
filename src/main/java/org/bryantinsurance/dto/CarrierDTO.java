package org.bryantinsurance.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CarrierDTO {

    private Long id;

    private String type;
}
