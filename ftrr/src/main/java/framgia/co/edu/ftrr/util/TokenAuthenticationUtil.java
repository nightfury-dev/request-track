package framgia.co.edu.ftrr.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import framgia.co.edu.ftrr.entity.User;
import framgia.co.edu.ftrr.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class TokenAuthenticationUtil {
    static final long EXPIRATIONTIME = 864_000_000; // 10 days
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";
    private static UserRepository userRepository;

    public static void addAuthentication(HttpServletResponse res, String username) throws JsonProcessingException {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    public static Authentication getAuthentication(HttpServletRequest request) throws IOException {
        String token = request.getHeader(HEADER_STRING);

        if (token == null) {
            return null;
        }

        String userName = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();

        User user = userRepository.getOneByEmail(userName).orElse(null);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

        return user == null
                ? null : new UsernamePasswordAuthenticationToken(user.getEmail(), null, grantedAuthorities);
    }

    @Autowired
    public void setUserService(UserRepository userRepository) {
        TokenAuthenticationUtil.userRepository = userRepository;
    }
}