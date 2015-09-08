package main.security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationInterceptor implements HandlerInterceptor  {
    private static Logger LOGGER = LoggerFactory.getLogger(AuthenticationInterceptor.class);

    @Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("authentication") == null) {
            LOGGER.debug("Not authenticated, redirecting to login");
            //response.sendRedirect("/account/login");
            response.sendError(401, "Not authenticated, please authenticate via /account/login");
            return false;
        }
        LOGGER.debug("Authenticating, proceed as normal");
        return true;
	}

    @Override
	public void postHandle(	HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {
	}
}