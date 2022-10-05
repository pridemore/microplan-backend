//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package zw.co.creative.microplanbackend.configs;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class MySimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public MySimpleUrlAuthenticationSuccessHandler() {
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        this.handle(request, response, authentication);
        this.clearAuthenticationAttributes(request);
    }

    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = this.determineTargetUrl(authentication);
        if (!response.isCommitted()) {
            this.redirectStrategy.sendRedirect(request, response, targetUrl);
        }
    }

    protected String determineTargetUrl(Authentication authentication) {
        boolean isUser = false;
        boolean isAdmin = false;

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator var10 = authorities.iterator();

        while(var10.hasNext()) {
            GrantedAuthority grantedAuthority = (GrantedAuthority)var10.next();
            if (grantedAuthority.getAuthority().equals("USER")) {
                isUser = true;
                break;
            }

            if (grantedAuthority.getAuthority().equals("admin")) {
                isAdmin = true;
                break;
            }

            System.out.println( grantedAuthority.getAuthority());
        }

        if (isUser) {
            return "/dashboard";
        } else if (isAdmin) {
            return "/dashboard";

    } else {
            return "/loginerror";
        }
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("SPRING_SECURITY_LAST_EXCEPTION");
        }
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return this.redirectStrategy;
    }
}
