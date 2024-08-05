package etecc.cac.cac_api.service;

import etecc.cac.cac_api.auth.ApiKeyAuthentication;
import etecc.cac.cac_api.controller.CAC_Controller;
import etecc.cac.cac_api.error.LogFile;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

public class AuthenticationService {
    private static final String AUTH_TOKEN_HEADER_NAME = System.getenv("AUTH_TOKEN_HEADER");
    private static final String AUTH_TOKEN = System.getenv("AUTH_TOKEN_KEY");

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        if (token == null || !token.equals(AUTH_TOKEN)) {
            BadCredentialsException badCredentialsException = new BadCredentialsException("INVALID TOKEN <<>> ACCESS DENIED");

            LogFile.writeLogError(badCredentialsException, " FROM \n " + CAC_Controller.getClientMetaData(request));

            throw badCredentialsException;
        }
        return new ApiKeyAuthentication(token, AuthorityUtils.NO_AUTHORITIES);
    }
}
