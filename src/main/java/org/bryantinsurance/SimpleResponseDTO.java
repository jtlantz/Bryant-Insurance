package org.bryantinsurance;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SimpleResponseDTO {

    private boolean success;
    private String message;
}
