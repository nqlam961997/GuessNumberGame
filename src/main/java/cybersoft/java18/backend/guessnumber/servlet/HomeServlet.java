package cybersoft.java18.backend.guessnumber.servlet;

import cybersoft.java18.backend.guessnumber.utils.JspUtils;
import cybersoft.java18.backend.guessnumber.utils.UrlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = UrlUtils.ROOT)
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(req.getContextPath() + JspUtils.HOME).forward(req, resp);
    }
}
