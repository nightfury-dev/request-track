package framgia.co.edu.ftrr.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import framgia.co.edu.ftrr.common.LoginReponseStringMessages;
import framgia.co.edu.ftrr.dto.request.AccountCredentials;
import framgia.co.edu.ftrr.dto.response.LoginMessageReponse;
import framgia.co.edu.ftrr.util.TokenAuthenticationUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        AccountCredentials credentials = new AccountCredentials(request.getParameter("username"),
                request.getParameter("password"));
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                credentials.getUsername(), credentials.getPassword(), Collections.emptyList()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        TokenAuthenticationUtil.addAuthentication(response, authResult.getName());
        LoginMessageReponse messageReponse = new LoginMessageReponse();
        messageReponse.setCode("200");
        messageReponse.setMessage(LoginReponseStringMessages.LOGINSUCESS);

        String employeeJsonString = objectWriter.writeValueAsString(messageReponse);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
        out.flush();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        LoginMessageReponse messageReponse = new LoginMessageReponse();
        messageReponse.setCode("404");
        messageReponse.setMessage(LoginReponseStringMessages.LOGINFAIL);

        String employeeJsonString = objectWriter.writeValueAsString(messageReponse);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);
        out.flush();
    }
}
