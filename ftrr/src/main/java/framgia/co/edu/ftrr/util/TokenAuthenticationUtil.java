package framgia.co.edu.ftrr.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import framgia.co.edu.ftrr.config.CustomPrincipal;
import framgia.co.edu.ftrr.service.UserService;
import io.jsonwebtoken.Claims;
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
    static final String CUSTOM_USER_KEY = "CustomUser";
    private static UserService userService;

    public static void addAuthentication(HttpServletResponse res, Authentication authentication) throws JsonProcessingException {
//        CustomPrincipal customPrincipal = userService.loadCustomPrincipal(username);
        ObjectMapper mapper = new ObjectMapper();
        String customPrincipalJson = mapper.writeValueAsString(authentication.getPrincipal());

        String JWT = Jwts.builder()
                .claim(CUSTOM_USER_KEY, customPrincipalJson)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    public static Authentication getAuthentication(HttpServletRequest request) throws IOException {
        String token = request.getHeader(HEADER_STRING);
        if (token == null) {
            return null;
        }
        Claims claims = Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
        ObjectMapper mapper = new ObjectMapper();

        CustomPrincipal customPrincipal = mapper.readValue(claims.get(CUSTOM_USER_KEY).toString(), CustomPrincipal.class);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(String.valueOf(customPrincipal.getRole())));
        return customPrincipal == null
                ? null : new UsernamePasswordAuthenticationToken(customPrincipal, null, grantedAuthorities);
    }

    @Autowired
    public void setUserRepository(UserService userService) {
        TokenAuthenticationUtil.userService = userService;
    }
}