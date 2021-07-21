package org.bryantinsurance;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CurrentUserDTO {

    private boolean isLoggedIn;
    private String username;
    private String role;
}
