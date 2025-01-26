package lk.project.taskhub.service.impl;
import lk.project.taskhub.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;


public class UserDetailsImpl implements UserDetails {

    private String firstName;
    private String  lastName;
    private String email;
    private String password;
    private String verificationCode;
    private LocalDateTime verificationCodeExpireAt;
    private boolean isEnable;
    private Collection<? extends  GrantedAuthority>authorities;

    public UserDetailsImpl(String firstName, String lastName, String email, String password,
                            String verificationCode, LocalDateTime verificationCodeExpireAt,
                           boolean isEnable, Collection<? extends GrantedAuthority> authorities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.verificationCode = verificationCode;
        this.verificationCodeExpireAt = verificationCodeExpireAt;
        this.isEnable = isEnable;
        this.authorities = authorities;
    }


    public static UserDetailsImpl build(User user){
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole());
        return new UserDetailsImpl(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getVerificationCode(),
                user.getVerificationCodeExpireAt(), user.isEnable(), Collections.singletonList(grantedAuthority));

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isEnabled(){
        return  isEnable;
    }


}
