package lk.project.taskhub.secuirty.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.project.taskhub.secuirty.JwtUtills;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtills jwtUtills;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtills jwtUtills, UserDetailsService userDetailsService) {
        this.jwtUtills = jwtUtills;
        this.userDetailsService = userDetailsService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
              try {
                  String jwt = jwtUtills.extractJwtToken(request);
                  if(jwt != null && jwtUtills.validateToken(jwt)){
                      String userName = jwtUtills.getUserNameFromJwtToken(jwt);
                      UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                      if(userDetails!=null){
                          UsernamePasswordAuthenticationToken
                                  authentication = new UsernamePasswordAuthenticationToken(userDetails,
                                  null,userDetails.getAuthorities());
                          authentication.setDetails(userDetails);
                          SecurityContextHolder.getContext().setAuthentication(authentication);
                      }
                  }

              }catch (Exception e){
                  e.printStackTrace();
              }
              filterChain.doFilter(request,response);

    }

}
