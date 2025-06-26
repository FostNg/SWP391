package group5.SE1863.DPSS_backend.service;

import group5.SE1863.DPSS_backend.dto.request.ConsultationRequest;
import group5.SE1863.DPSS_backend.dto.response.ConsultantScheduleResponse;
import group5.SE1863.DPSS_backend.dto.response.ConsultationResponse;
import group5.SE1863.DPSS_backend.entity.ConsultationAppointment;
import group5.SE1863.DPSS_backend.entity.Staff;
import group5.SE1863.DPSS_backend.entity.User;
import group5.SE1863.DPSS_backend.exception.AppException;
import group5.SE1863.DPSS_backend.exception.ErrorCode;
import group5.SE1863.DPSS_backend.repository.ConsultantScheduleRepository;
import group5.SE1863.DPSS_backend.repository.ConsultationRepository;
import group5.SE1863.DPSS_backend.repository.StaffRepository;
import group5.SE1863.DPSS_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsultationService {
    private final ConsultationRepository consultationRepository;
    private final ConsultantScheduleRepository consultantScheduleRepository;
    private final UserRepository userRepository;
    private final StaffRepository staffRepository;

    public ConsultationResponse scheduleAppointment(Long userId, ConsultationRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_EXISTED));
        Staff staff = staffRepository.findById(request.getStaffId()).orElseThrow(() ->
                new AppException(ErrorCode.INVALID_USERID));
        ConsultationAppointment appointment = new ConsultationAppointment();
        appointment.setUser(user);
        appointment.setStaff(staff);
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointment.setTopic(request.getTopic());
        appointment.setStatus("PENDING");
        appointment = consultationRepository.save(appointment);
        return new ConsultationResponse(
                appointment.getAppointmentId(),
                appointment.getTopic(),
                appointment.getAppointmentTime(),
                appointment.getStatus(),
                staff.getUser().getFullName()
        );
    }

    public List<ConsultationResponse> getUserAppointments(Long userId) {
        return consultationRepository.findByUser_UserId(userId).stream()
                .map(appointment -> new ConsultationResponse(
                        appointment.getAppointmentId(),
                        appointment.getTopic(),
                        appointment.getAppointmentTime(),
                        appointment.getStatus(),
                        appointment.getStaff().getUser().getFullName()
                )).toList();
    }

    public List<ConsultationResponse> getStaffAppointments(Long staffId) {
        return consultationRepository.findByStaff_StaffId(staffId).stream()
                .map(appointment -> new ConsultationResponse(
                        appointment.getAppointmentId(),
                        appointment.getTopic(),
                        appointment.getAppointmentTime(),
                        appointment.getStatus(),
                        appointment.getUser().getFullName()
                )).toList();
    }

    public void updateAppointmentStatus(Long appointmentId, String status) {
        ConsultationAppointment appointment = consultationRepository.findById(appointmentId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        appointment.setStatus(status);
        consultationRepository.save(appointment);
    }

    public void cancelAppointment(Long appointmentId) {
        ConsultationAppointment appointment = consultationRepository.findById(appointmentId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        consultationRepository.delete(appointment);
    }

    public List<ConsultantScheduleResponse> getAvailableAppointments(LocalDate date) {
        return consultantScheduleRepository.findByDate(date).stream()
                .map(schedule -> new ConsultantScheduleResponse(
                        schedule.getScheduleId(),
                        schedule.getDayOfWeek(),
                        schedule.getDate(),
                        schedule.getStartTime(),
                        schedule.getEndTime(),
                        schedule.isAvailable(),
                        schedule.getNotes()
                )).toList();
    }
}
