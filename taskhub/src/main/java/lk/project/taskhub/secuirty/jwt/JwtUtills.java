package lk.project.taskhub.secuirty.jwt;


import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lk.project.taskhub.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

public class JwtUtills {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private int jwtExpiration;

    public String extractJwtToken(HttpServletRequest request){
          String bearerToken = request.getHeader("Authorization");
          if(!bearerToken.isEmpty() && bearerToken.startsWith("Bearer")){
              return bearerToken.substring(7);
        }
          return null;

    }

    public String getUserNameFromJwtToken(String token){

        return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(token).getPayload().getSubject();
    }


    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }


    public boolean validateKey(String authToken){
        try {
            Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(authToken);
        }catch (JwtException e){
           throw new RuntimeException(e);
        }catch (IllegalArgumentException e){
            throw new RuntimeException(e);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return true;
    }



    public String generateToken(UserDetailsImpl userDetails){
        String userName = userDetails.getUsername();
        String roles = userDetails.getAuthorities()
                .stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.joining(","));

        return Jwts.builder().
                subject(userName).
                claim("roles",roles).
                issuedAt(new Date()).
                expiration(new Date((new Date().getTime() + jwtExpiration))).signWith(key()).compact();

    }










}
