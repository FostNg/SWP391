package group5.SE1863.DPSS_backend.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException {
    private ErrorCode errorCode;

    public AppException(ErrorCode errorCode) {

        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
<<<<<<< HEAD

    public AppException(com.google.firebase.ErrorCode errorCode) {
    }
=======
>>>>>>> 894443c9ff4b67cf1ef6c3069f10aad3f5892c01
}
