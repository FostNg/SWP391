package group5.SE1863.DPSS_backend.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ConsultantScheduleResponse {
    private Long scheduleId;
    private String dayOfWeek;
    private LocalDateTime date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isAvailable;
    private String notes;
}
