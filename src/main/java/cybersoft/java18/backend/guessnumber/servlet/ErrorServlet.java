package cybersoft.java18.backend.guessnumber.servlet;


import cybersoft.java18.backend.guessnumber.utils.JspUtils;
import cybersoft.java18.backend.guessnumber.utils.UrlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {
        UrlUtils.NOT_FOUND,
        UrlUtils.INTERNAL_ERROR
})
public class ErrorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtils.NOT_FOUND:
                req.getRequestDispatcher(req.getContextPath() + JspUtils.NOT_FOUND)
                        .forward(req, resp);
            case UrlUtils.INTERNAL_ERROR:
                req.getRequestDispatcher(req.getContextPath() + JspUtils.INTERNAL_ERROR)
                        .forward(req, resp);
            default:
                resp.sendRedirect(req.getContextPath() + UrlUtils.NOT_FOUND);
        }
    }
}
