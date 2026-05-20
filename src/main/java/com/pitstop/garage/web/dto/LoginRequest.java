package com.pitstop.garage.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LoginRequest {

    @NotBlank
    @Size(min = 3,max = 20, message = "Username length must be between 3 and 20 symbols.")
    public String username;

    @NotBlank
    @Size(min = 4, max = 20, message = "Password must be between 4 and 20 characters")
    public String password;
}
