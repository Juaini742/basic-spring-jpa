package com.core.jpatraining2.validation;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDto {

    @NotEmpty(message = "Username is required")
    @Size(min = 1, message = "Username is required")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username only can contain letter and number")
    private String username;

    @NotEmpty(message = "Password is required")
    @Size(min = 6, message = "Password must be with in 6 characters")
    private String password;

    public @NotEmpty(message = "Username is required") String getUsername() {
        return username;
    }

    public void setUsername(
            @NotEmpty(message = "Username is required")
            @Size(min = 1, message = "Username is required")
            @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Username only can contain letter and number")
            String username
    ) {
        this.username = username;
    }

    public @NotEmpty(message = "Password is required")
    @Size(min = 6, message = "Password must be with in 6 characters")
    String getPassword() {
        return password;
    }

    public void setPassword(
            @NotEmpty(message = "Password is required")
            @Size(min = 6, message = "Password must be with in 6 characters")
            String password
    ) {
        this.password = password;
    }
}
