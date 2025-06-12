package group5.SE1863.DPSS_backend.service;

import group5.SE1863.DPSS_backend.dto.request.UserDTO;
import group5.SE1863.DPSS_backend.dto.response.UserResponse;
import group5.SE1863.DPSS_backend.entity.RoleDetail;
import group5.SE1863.DPSS_backend.entity.User;
import group5.SE1863.DPSS_backend.enums.Role;
import group5.SE1863.DPSS_backend.exception.AppException;
import group5.SE1863.DPSS_backend.exception.ErrorCode;
import group5.SE1863.DPSS_backend.mapper.UserMapper;
import group5.SE1863.DPSS_backend.repository.RoleRepository;
import group5.SE1863.DPSS_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class UserImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(UserDTO userRequest, String userRoleChoice) {
        if (userRepository.existsByUserName(userRequest.getUserName()) || userRepository.existsByEmail(userRequest.getEmail())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        User user = UserMapper.mapToUser(userRequest);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        HashSet<RoleDetail> roles = new HashSet<>();
        try {
            Role roleEnum = Role.valueOf(userRoleChoice.toUpperCase());
            RoleDetail role = roleRepository.findByRoleType(roleEnum.name())
                    .orElseGet(() -> {
                        RoleDetail newRole = new RoleDetail();
                        newRole.setRoleType(roleEnum.name());
                        return roleRepository.save(newRole);
                    });
            roles.add(role);
            user.setRoles(roles);
        }catch (IllegalArgumentException e) {
            throw new AppException(ErrorCode.INVALID_ROLE);
        }
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserResponse(savedUser);
    }

    @Override
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public UserResponse getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return UserMapper.mapToUserResponse(user);
    }


}
