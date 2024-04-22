package hasanalmunawr.Dev.Emailvalidation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestRegis {

    @NotBlank
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    @Size(min = 8)
    private String password;

    @NotBlank
    private String firstName;
    private String lastName;

}
