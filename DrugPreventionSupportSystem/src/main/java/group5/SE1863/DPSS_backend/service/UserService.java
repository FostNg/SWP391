package group5.SE1863.DPSS_backend.service;

import group5.SE1863.DPSS_backend.dto.request.UserDTO;
import group5.SE1863.DPSS_backend.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse register(UserDTO userDTO, String userRoleChoice);
    UserResponse getUser(Long userId);
    UserResponse getMyInfo();
    UserResponse updateUser(Long userId, UserDTO newInfoUser);
    void deleteUser(Long userId);
    List<UserResponse> getAllUser();
    UserResponse setStatusAccount(Long userId, String decision);
    UserResponse setRole(Long userId, String role);
}
