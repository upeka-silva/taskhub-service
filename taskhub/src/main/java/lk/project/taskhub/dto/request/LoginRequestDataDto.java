package lk.project.taskhub.dto.request;

public class LoginRequestDataDto {

    private String email;

    private String password;

    public LoginRequestDataDto() {
    }

    public LoginRequestDataDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
