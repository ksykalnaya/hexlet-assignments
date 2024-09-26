package exercise.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

// BEGIN
@Getter
@Setter
public class GuestCreateDTO {

    @NotBlank(message = "name is null")
    private String name;
    @Email
    private String email;
    @Pattern(regexp = "^\\+[\\d\\- ]{11,13}$")
    private String phoneNumber;
//    @Digits(integer = 4, fraction = 0)
//    @Size(min = 4, max = 4)
    @Pattern(regexp = "^[\\d\\-]{4}$")
    private String clubCard;
    @Future
    private LocalDate cardValidUntil;
}
// END
