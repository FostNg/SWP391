package group5.SE1863.DPSS_backend.mapper;

import group5.SE1863.DPSS_backend.dto.request.UserDTO;
import group5.SE1863.DPSS_backend.dto.response.RoleResponse;
import group5.SE1863.DPSS_backend.dto.response.UserResponse;
import group5.SE1863.DPSS_backend.entity.User;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> 894443c9ff4b67cf1ef6c3069f10aad3f5892c01
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    @Builder
    public static UserDTO mapToUserDto(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.getFullName(),
                user.getDayOfBirth(),
                user.isStatus()

        );
    }

    public static User mapToUser(UserDTO userDto) {
<<<<<<< HEAD
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        user.setDayOfBirth(userDto.getDayOfBirth());
        user.setStatus(userDto.isStatus());
        return user;
=======
        return new User(
                userDto.getUserId(),
                userDto.getUserName(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.getFullName(),
                userDto.getDayOfBirth(),
                userDto.isStatus(),
                null

        );
>>>>>>> 894443c9ff4b67cf1ef6c3069f10aad3f5892c01
    }

    public static UserResponse mapToUserResponse(User user) {
        Set<RoleResponse> role = (user.getRoles() != null)
                ? user.getRoles().stream()
                .map(roleDetail -> new RoleResponse(roleDetail.getRoleType()))
                .collect(Collectors.toSet())
                : new HashSet<>();
        return new UserResponse(
                user.getUserId(),
                user.getUserName(),
                user.getFullName(),
                user.getEmail(),
                user.getDayOfBirth(),
                user.isStatus(),
                role
        );
    }
}
