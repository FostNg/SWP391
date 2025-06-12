package group5.SE1863.DPSS_backend.controller;

import group5.SE1863.DPSS_backend.dto.request.UserDTO;
import group5.SE1863.DPSS_backend.dto.response.UserResponse;
import group5.SE1863.DPSS_backend.entity.ApiResponse;
import group5.SE1863.DPSS_backend.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {
    private UserService userService;

    @PostMapping("/register")
    public ApiResponse<UserResponse> register(@RequestBody @Valid UserDTO userDTO, @RequestParam String userRoleChoice) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.register(userDTO, userRoleChoice));
        return apiResponse;
    }
    @GetMapping("/getUsers")
    public ApiResponse<List<UserResponse>> getAllUser() {
        ApiResponse<List<UserResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getAllUser());
        return apiResponse;
    }
    @GetMapping("{id}")
    public ApiResponse<UserResponse> getUser(@PathVariable("id") Long userId) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUser(userId));
        return apiResponse;
    }
    @GetMapping("/myInfo")
    public ApiResponse<UserResponse> getMyInfo() {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getMyInfo());
        return apiResponse;
    }
}
