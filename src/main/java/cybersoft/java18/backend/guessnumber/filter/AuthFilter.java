package cybersoft.java18.backend.guessnumber.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java18.backend.guessnumber.utils.UrlUtils;

@WebFilter(urlPatterns = {UrlUtils.ALL})
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		if (isLoginUser(req) || isAuthUrl(req)) {
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(req.getContextPath() + UrlUtils.SIGN_IN);
		}
	}

	private boolean isAuthUrl(HttpServletRequest req) {
		var path = req.getServletPath();
		return path.startsWith(UrlUtils.SIGN_UP) || path.startsWith(UrlUtils.SIGN_IN);
	}

	private boolean isLoginUser(HttpServletRequest req) {
		return req.getSession().getAttribute("currentUser") != null;
	}
	
}
