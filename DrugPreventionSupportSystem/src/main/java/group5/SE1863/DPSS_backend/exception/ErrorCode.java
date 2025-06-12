package group5.SE1863.DPSS_backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    INVALID_KEY(1000, "Invalid message key", HttpStatus.BAD_REQUEST),
    UNCATAGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(1001, "User existed(please check your username or your email)", HttpStatus.BAD_REQUEST),
    INVALID_ROLE(1002, "Invalid role", HttpStatus.BAD_REQUEST),
    INVALID_USERID(1003, "This userID is not existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1004, "User invalid(please check your username or your password)", HttpStatus.NOT_FOUND),
    UNAUTHENDICATED(1005, "Unauthenticated", HttpStatus.UNAUTHORIZED),


    INVALID_INFOMATION(1007, "You need to check your information that it do not duplicated", HttpStatus.BAD_REQUEST),
    ;


    private int code;
    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    private HttpStatusCode statusCode;
}
