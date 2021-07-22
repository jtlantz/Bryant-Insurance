package org.bryantinsurance.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserDTO {

    private int sid;

    private String username;

    private String role;
}
