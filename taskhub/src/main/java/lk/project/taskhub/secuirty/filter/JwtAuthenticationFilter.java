package lk.project.taskhub.secuirty.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.project.taskhub.secuirty.jwt.JwtUtills;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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

                final String authHeader = request.getHeader("Authorization");


          if( authHeader == null || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
           }

        try {
                  String jwt = jwtUtills.extractJwtToken(request);
                  if(jwt != null && jwtUtills.validateToken(jwt)){
                      String userName = jwtUtills.getUserNameFromJwtToken(jwt);
                      UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                      if(userDetails!=null){
                          UsernamePasswordAuthenticationToken
                                  authentication = new UsernamePasswordAuthenticationToken(userDetails,
                                  null,userDetails.getAuthorities());
                          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                          SecurityContextHolder.getContext().setAuthentication(authentication);
                      }
                  }

              }catch (Exception e){
                  e.printStackTrace();
              }
              filterChain.doFilter(request,response);

    }

}
