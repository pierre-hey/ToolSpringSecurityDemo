package fr.hey.toolspringsecuritydemo.dto;

import fr.hey.toolspringsecuritydemo.validation.PasswordMatches;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatches(message = "Les mots de passe ne correspondent pas")
public class UserDto
{
    private Long id;
    @NotBlank(message = "Ne peut pas être vide")
    private String login;
    @NotBlank(message = "Ne peut pas être vide")
    private String password;
    @NotBlank(message = "Ne peut pas être vide")
    private String matchingPassword;

    @AssertTrue(message = "Values are invalid")
    private boolean isValid() {
        return password.equals(matchingPassword);
    }
}
