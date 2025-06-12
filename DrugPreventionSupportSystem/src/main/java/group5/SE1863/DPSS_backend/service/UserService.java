package group5.SE1863.DPSS_backend.service;

import group5.SE1863.DPSS_backend.dto.request.UserDTO;
import group5.SE1863.DPSS_backend.dto.response.UserResponse;

public interface UserService {
    UserResponse register(UserDTO userDTO, String userRoleChoice);
    UserResponse getUser(Long userId);
}
