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
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dayOfBirth;
    private boolean status;
    private Set<RoleResponse> roles;
}
