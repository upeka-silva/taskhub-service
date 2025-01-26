package lk.project.taskhub.service.impl;
import jakarta.transaction.Transactional;
import lk.project.taskhub.model.User;
import lk.project.taskhub.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> loadUser = userRepository.findByEmail(userName);
        System.out.println(userName);
        System.out.println(loadUser.get().getFirstName());
        if (loadUser.isPresent()){
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(loadUser.get().getRole());
            UserDetailsImpl userDetails = new UserDetailsImpl(
                     loadUser.get().getFirstName(),
                     loadUser.get().getFirstName(),
                     loadUser.get().getEmail(),
                     loadUser.get().getPassword(),
                     loadUser.get().getVerificationCode(),
                     loadUser.get().getVerificationCodeExpireAt(), loadUser.get().isEnable(),
                      Collections.singletonList(grantedAuthority)
            );
           return userDetails;
        }
        throw  new UsernameNotFoundException("this user not found!"+userName);
    }
}
