package lk.project.taskhub.dto.response;


import java.time.LocalDateTime;

public class LoginResponseDto {

      private String token;
      private LocalDateTime expirationTime;

    public LoginResponseDto() {
    }

    public LoginResponseDto(String token, LocalDateTime expirationTime) {
        this.token = token;
        this.expirationTime = expirationTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }
}
