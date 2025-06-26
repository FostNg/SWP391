package group5.SE1863.DPSS_backend.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
<<<<<<< HEAD

=======
>>>>>>> 894443c9ff4b67cf1ef6c3069f10aad3f5892c01
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private int code = 1010;
    private String message;
    private T result;
<<<<<<< HEAD

    public static <T> ApiResponse<T> success(T result) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(1010);
        response.setMessage("Success");
        response.setResult(result);
        return response;
    }

    public static <T> ApiResponse<T> error(int code, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }
=======
>>>>>>> 894443c9ff4b67cf1ef6c3069f10aad3f5892c01
}
