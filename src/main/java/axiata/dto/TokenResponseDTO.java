package axiata.dto;

import java.io.Serializable;

public class TokenResponseDTO implements Serializable {
    private String token;
    private String code;
    private String message;

    public TokenResponseDTO(String token, String code, String message) {
        this.token = token;
        this.code = code;
        this.message = message;
    }

    public TokenResponseDTO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
