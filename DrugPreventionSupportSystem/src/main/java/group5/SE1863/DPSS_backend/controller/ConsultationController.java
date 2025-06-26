package group5.SE1863.DPSS_backend.controller;

import group5.SE1863.DPSS_backend.dto.request.ConsultationRequest;
import group5.SE1863.DPSS_backend.dto.request.ConsultationStatusUpdateRequest;
import group5.SE1863.DPSS_backend.dto.response.ConsultationResponse;
import group5.SE1863.DPSS_backend.entity.ApiResponse;
import group5.SE1863.DPSS_backend.service.ConsultationService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/consultation")
public class ConsultationController {
    private final ConsultationService consultationService;

    @PostMapping("/schedule")
    public ApiResponse<ConsultationResponse> scheduleAppointment(@RequestBody ConsultationRequest request) {
        Long userId = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
        ConsultationResponse response = consultationService.scheduleAppointment(userId, request);
        return ApiResponse.success(response);
    }

    @GetMapping("/user")
    public ApiResponse<List<ConsultationResponse>> getUserAppointments() {
        Long userId = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
        return ApiResponse.success(consultationService.getUserAppointments(userId));
    }

    @GetMapping("/staff")
    @PreAuthorize("hasRole('STAFF')")
    public ApiResponse<List<ConsultationResponse>> getStaffAppointments() {
        Long staffId = Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getName());
        return ApiResponse.success(consultationService.getStaffAppointments(staffId));
    }

    @PutMapping("/{appointmentId}/status")
    @PreAuthorize("hasRole('STAFF')")
    public ApiResponse<String> updateStatus(@PathVariable Long appointmentId,
                                            @RequestBody ConsultationStatusUpdateRequest request) {
        consultationService.updateAppointmentStatus(appointmentId, request.getStatus());
        return ApiResponse.success("Appointment status updated successfully");
    }
}
