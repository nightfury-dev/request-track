package framgia.co.edu.ftrr.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import framgia.co.edu.ftrr.common.StringsCommon;
import framgia.co.edu.ftrr.dto.request.AccountCredentials;
import framgia.co.edu.ftrr.dto.response.LoginMessageReponse;
import framgia.co.edu.ftrr.repository.UserRepository;
import framgia.co.edu.ftrr.util.TokenAuthenticationUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

/**
 * Filter cho api /login dùng cho admin login
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private UserRepository userRepository;
    private ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        //init user service
        if (userRepository == null) {
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            userRepository = webApplicationContext.getBean(UserRepository.class);
        }

        // Bắt request đi qua và lấy ra 2 param là username và password
        AccountCredentials credentials = new AccountCredentials(request.getParameter("username"),
                request.getParameter("password"));

        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                credentials.getUsername(), credentials.getPassword(), Collections.emptyList()));
    }

    //Method handle kết quả login
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        //Thêm token khi login sucess vào response header khi login thành công
        TokenAuthenticationUtil.addAuthentication(response, authResult.getName());

        LoginMessageReponse messageReponse = new LoginMessageReponse("200", StringsCommon.LOGINSUCESS);
        builtResponse(response, messageReponse);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response, AuthenticationException failed)
            throws IOException, ServletException {
        LoginMessageReponse messageReponse = new LoginMessageReponse("404", StringsCommon.LOGINFAIL);
        builtResponse(response, messageReponse);

    }

    //Method add result body to response
    private void builtResponse(HttpServletResponse response, LoginMessageReponse loginMessageReponse) throws IOException {
        String resultString = objectWriter.writeValueAsString(loginMessageReponse);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(resultString);
        out.flush();
    }
}
