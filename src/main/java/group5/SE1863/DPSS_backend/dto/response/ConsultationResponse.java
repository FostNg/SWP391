package group5.SE1863.DPSS_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationResponse {
    private Long appointmentId;
    private String topic;
    private LocalDateTime appointmentTime;
    private String status;
    private String staffName;
}
