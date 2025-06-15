package group5.SE1863.DPSS_backend.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long userId;
    private String userName;
    @Size(min = 8, message = "INVALID_PASSWORD")
    private String password;
    private String email;
    private String fullName;
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dayOfBirth;
    private boolean status;


}
