package etecc.cac.cac_api.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class ApiKeyAuthentication extends AbstractAuthenticationToken {
    private final String key;

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param key The secret key to get authenticated
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public ApiKeyAuthentication(String key, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.key = key;
        setAuthenticated(true);
    }


    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return key;
    }
}
