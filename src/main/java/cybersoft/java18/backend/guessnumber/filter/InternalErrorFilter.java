package cybersoft.java18.backend.guessnumber.filter;

import cybersoft.java18.backend.guessnumber.utils.UrlUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = UrlUtils.ALL)
public class InternalErrorFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if(resp.getStatus() == 500){
            resp.sendRedirect(req.getContextPath() + UrlUtils.INTERNAL_ERROR);
        } else {
            filterChain.doFilter(req, resp);
        }
    }
}