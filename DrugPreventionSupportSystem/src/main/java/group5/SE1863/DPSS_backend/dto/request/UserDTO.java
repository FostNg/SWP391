package group5.SE1863.DPSS_backend.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> c13caca1fbbda71aeabe2ca4a58351d1d892b42e
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

<<<<<<< HEAD
=======
=======

import java.time.LocalDate;
>>>>>>> 894443c9ff4b67cf1ef6c3069f10aad3f5892c01
>>>>>>> c13caca1fbbda71aeabe2ca4a58351d1d892b42e
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
<<<<<<< HEAD
    @DateTimeFormat(pattern = "dd-MM-yyyy")
=======
<<<<<<< HEAD
    @DateTimeFormat(pattern = "dd-MM-yyyy")
=======
    @JsonFormat(pattern = "dd/MM/yyyy")
>>>>>>> 894443c9ff4b67cf1ef6c3069f10aad3f5892c01
>>>>>>> c13caca1fbbda71aeabe2ca4a58351d1d892b42e
    LocalDate dayOfBirth;
    private boolean status;


}
