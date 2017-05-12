package cloud.base.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.util.StringUtils;

import cloud.base.model.SessionUser;
import cloud.base.util.SecurityUtil;


public class SecuritySavedRequestAwareAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    protected final Log logger = LogFactory.getLog(this.getClass());

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
//        SavedRequest savedRequest = requestCache.getRequest(request, response);

//        if (savedRequest == null) {
//            super.onAuthenticationSuccess(request, response, authentication);
//
//            return;
//        }
//        String targetUrlParameter = getTargetUrlParameter();
//        if (isAlwaysUseDefaultTargetUrl() || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
//            requestCache.removeRequest(request, response);
//            super.onAuthenticationSuccess(request, response, authentication);
//
//            return;
//        }
//
//        clearAuthenticationAttributes(request);

        // Use the DefaultSavedRequest URL
//        String targetUrl = savedRequest.getRedirectUrl();
//        logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
//        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    	
    	//使用配置文件中的targetUrlParameter参数作为默认跳转的页面。  heza 2017-05-10 11:41:05
    	String targetUrlParameter = getTargetUrlParameter();
    	getRedirectStrategy().sendRedirect(request, response, targetUrlParameter);
    }

    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
}
