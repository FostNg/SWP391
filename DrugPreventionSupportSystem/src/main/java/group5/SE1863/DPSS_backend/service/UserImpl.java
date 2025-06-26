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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

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
            RoleDetail role = roleRepository.findByRoleType(roleEnum.name()).orElseGet(() -> {
                RoleDetail newRole = new RoleDetail();
                newRole.setRoleType(roleEnum.name());
                return roleRepository.save(newRole);
            });
            roles.add(role);
            user.setRoles(roles);
        } catch (IllegalArgumentException e) {
            throw new AppException(ErrorCode.INVALID_ROLE);
        }
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserResponse(savedUser);
    }

    @Override
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<UserResponse> getAllUser() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserResponse).toList();
    }

    @Override
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public UserResponse getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return UserMapper.mapToUserResponse(user);
    }

    @Override
    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        Long userId = Long.valueOf(context.getAuthentication().getName());

        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return UserMapper.mapToUserResponse(user);

    }

    @Override
    public UserResponse updateUser(Long userId, UserDTO newInfoUser) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        user.setUserName(newInfoUser.getUserName());
        user.setEmail(newInfoUser.getEmail());
        user.setFullName(newInfoUser.getFullName());
        user.setDayOfBirth(newInfoUser.getDayOfBirth());

        return UserMapper.mapToUserResponse(userRepository.save(user));
    }

    @Override
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        userRepository.deleteById(userId);
        log.info("User with ID {} has been deleted", userId);
    }

    @Override
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public UserResponse setStatusAccount(Long userId, String decision) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.INVALID_USERID));
        user.setStatus(!decision.equalsIgnoreCase("inactive"));
        return userMapper.mapToUserResponse(userRepository.save(user));
    }

    @Override
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public UserResponse setRole(Long userId, String role) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.INVALID_USERID));
        RoleDetail roleDetail;

        switch (role.toLowerCase()) {
            case "user":
            case "staff":
            case "consultant":
            case "manager":
                // Gán thêm role
                String roleName = "ROLE_" + role.toUpperCase();

                roleDetail = roleRepository.findByRoleType(roleName)
                        .orElseGet(() -> {
                            RoleDetail newRole = new RoleDetail();
                            newRole.setRoleType(roleName);
                            return roleRepository.save(newRole);
                        });

                user.getRoles().add(roleDetail);
                break;

            case "unuser":
            case "unstaff":
            case "unconsultant":
            case "unmanager":
                // Gỡ role
                String removeRole = "ROLE_" + role.replace("un", "").toUpperCase();

                user.getRoles().removeIf(r ->
                        r.getRoleType().equalsIgnoreCase(removeRole));
                break;

            default:
                throw new AppException(ErrorCode.INVALID_ROLE);
        }

        userRepository.save(user);
        return userMapper.mapToUserResponse(user);
    }

}
