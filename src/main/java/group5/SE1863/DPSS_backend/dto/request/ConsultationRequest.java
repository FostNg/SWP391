package group5.SE1863.DPSS_backend.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationRequest {
    private LocalDateTime appointmentTime;
    private String topic;
    private Long staffId;
}
