package lk.project.taskhub.dto.response;

public class VerifyUserDto {


    private String verificationCode;

    public VerifyUserDto() {
    }

    public VerifyUserDto(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
