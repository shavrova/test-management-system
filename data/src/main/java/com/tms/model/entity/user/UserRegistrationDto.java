package com.tms.model.entity.user;

import com.tms.model.constrains.FieldMatch;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@ToString
@Getter
@Setter
@FieldMatch.List({
        @FieldMatch(field = "password", fieldMatch = "confirmPassword", message = "Passwords do not match!"),
        @FieldMatch(field = "email", fieldMatch = "confirmEmail", message = "Email addresses do not match!")
})
public class UserRegistrationDto {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    @Email
    @NotEmpty
    private String email;

    @Email
    @NotEmpty
    private String confirmEmail;

    @AssertTrue
    private Boolean terms;
}