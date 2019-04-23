package framgia.co.edu.ftrr.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import framgia.co.edu.ftrr.common.StringsCommon;
import framgia.co.edu.ftrr.config.CustomPrincipal;
import framgia.co.edu.ftrr.dto.response.LoginMessageReponse;
import framgia.co.edu.ftrr.dto.response.UserWsmResponse;
import framgia.co.edu.ftrr.service.UserService;
import framgia.co.edu.ftrr.service.impl.UserServiceImpl;
import framgia.co.edu.ftrr.util.TokenAuthenticationUtil;
import framgia.co.edu.ftrr.util.WsmTokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
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

public class WsmLoginFilter extends AbstractAuthenticationProcessingFilter {

    private UserService userService;

    private ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

    public WsmLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        //init user service
        if (userService == null) {
            ServletContext servletContext = request.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            userService = webApplicationContext.getBean(UserServiceImpl.class);
        }

        //Check token from client
        String token = request.getParameter("token");
        if (StringUtils.isBlank(token))
            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(null, null, null));

        //get user info from wsm sever and check
        UserWsmResponse userWsmResponse = WsmTokenUtils.getUserInfo(token);
        if (userWsmResponse.getId() == null)
            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(null, null, null));

        //load and update user from wsm to db
        CustomPrincipal customPrincipal = userService.loadOrUpdateUser(userWsmResponse);

        Authentication authentication = new UsernamePasswordAuthenticationToken(customPrincipal, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        //Thêm token khi login sucess vào response header khi login thành công
        TokenAuthenticationUtil.addAuthentication(response, authResult);

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

    private void builtResponse(HttpServletResponse response, LoginMessageReponse loginMessageReponse) throws IOException {
        String resultString = objectWriter.writeValueAsString(loginMessageReponse);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(resultString);
        out.flush();
    }
}
