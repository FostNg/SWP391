package group5.SE1863.DPSS_backend.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long userId;
    private String userName;
    private String email;
    private String fullName;
<<<<<<< HEAD
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
=======
<<<<<<< HEAD
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
=======
    @JsonFormat(pattern = "dd/MM/yyyy")
>>>>>>> 894443c9ff4b67cf1ef6c3069f10aad3f5892c01
>>>>>>> c13caca1fbbda71aeabe2ca4a58351d1d892b42e
    LocalDate dayOfBirth;
    private boolean status;
    private Set<RoleResponse> roles;
}
