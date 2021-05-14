package ecma.ai.codingbatapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    @NotNull(message = "Email bo'sh bo'lmasin")
    private String email;
    @NotNull(message = "Password bo'sh bo'lmasin")
    private String password;
    @NotNull(message = "FullName bo'sh bo'lmasin")
    private String fullName;

    private Integer starBadgeId;
}
