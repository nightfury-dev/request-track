package framgia.co.edu.ftrr.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.netty.util.internal.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import framgia.co.edu.ftrr.dto.request.UserDTO;
import framgia.co.edu.ftrr.repository.UserRepository;
import framgia.co.edu.ftrr.service.UserService;
import framgia.co.edu.ftrr.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TokenAuthenticationUtil {
	static final long EXPIRATIONTIME = 864_000_000; // 10 days

	static final String SECRET = "ThisIsASecret";

	static final String TOKEN_PREFIX = "Bearer";

	static final String HEADER_STRING = "Authorization";

	static final String AUTHORITIES_KEY = "Authorities";
	
	public static void addAuthentication(HttpServletResponse res, String username, Authentication authentication) {
		// List authority of authentication
		String authorities = authentication.getAuthorities().stream()
									.map(GrantedAuthority::getAuthority)
									.collect(Collectors.joining(","));
		String JWT = Jwts.builder().setSubject(username)
				.claim(AUTHORITIES_KEY, authorities)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	}

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token == null) {
			return null;
		}
		// parse the token.
		Claims claims = Jwts.parser().setSigningKey(SECRET)
				.parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
		// get username
		String user = claims.getSubject();
		// get authorities
		List<GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
				.map(auth -> new SimpleGrantedAuthority("ROLE_"+auth)).collect(Collectors.toList());
		return StringUtil.isNullOrEmpty(user)
				? null : new UsernamePasswordAuthenticationToken(user, null, authorities);

	}
}