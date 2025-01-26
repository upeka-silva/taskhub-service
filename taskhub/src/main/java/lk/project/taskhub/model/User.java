package lk.project.taskhub.model;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String  lastName;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(length = 1000)
    private String password;
    private String role;
    @Column(name = "verification_code")
    private String verificationCode;
    @Column(name = "verification_expireration")
    private LocalDateTime verificationCodeExpireAt;
    private boolean isEnable =false;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password,
                String role, String verificationCode,
                LocalDateTime verificationCodeExpireAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.verificationCode = verificationCode;
        this.verificationCodeExpireAt = verificationCodeExpireAt;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public LocalDateTime getVerificationCodeExpireAt() {
        return verificationCodeExpireAt;
    }

    public void setVerificationCodeExpireAt(LocalDateTime verificationCodeExpireAt) {
        this.verificationCodeExpireAt = verificationCodeExpireAt;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }
}
