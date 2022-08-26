package cybersoft.java18.backend.guessnumber.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java18.backend.guessnumber.model.Player;
import cybersoft.java18.backend.guessnumber.service.GameService;
import cybersoft.java18.backend.guessnumber.service.PlayerService;
import cybersoft.java18.backend.guessnumber.utils.JspUtils;
import cybersoft.java18.backend.guessnumber.utils.UrlUtils;

@WebServlet(name = "authServlet", urlPatterns = {UrlUtils.SIGN_IN, UrlUtils.SIGN_UP, UrlUtils.SIGN_OUT})
public class AuthServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtils.SIGN_IN:
                req.getRequestDispatcher(JspUtils.SIGN_IN).forward(req, resp);
                break;

            case UrlUtils.SIGN_UP:
                req.getRequestDispatcher(JspUtils.SIGN_UP).forward(req, resp);
                break;
            case UrlUtils.SIGN_OUT:
                req.getSession().invalidate();
                resp.sendRedirect(req.getContextPath() + UrlUtils.SIGN_IN);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + UrlUtils.NOT_FOUND);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtils.SIGN_IN:
                processLogin(req, resp);
                break;

            case UrlUtils.SIGN_UP:
                processRegister(req, resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + UrlUtils.NOT_FOUND);
        }
    }

    private void processLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        var player = PlayerService.getInstance().signIn(
                req.getParameter("username"),
                req.getParameter("password")
        );

        if (player == null) {
            req.setAttribute("errors", "Username or password is incorrect!");
            this.doGet(req, resp);
        } else {
            req.getSession().setAttribute("currentUser", player);
            resp.sendRedirect(req.getContextPath() + UrlUtils.GAME);
        }
    }

    private void processRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");

        var newPlayer = PlayerService.getInstance().register(username, password, name);

        if (newPlayer != null) {
            req.getSession().setAttribute("currentUser", newPlayer);
            resp.sendRedirect(req.getContextPath() + UrlUtils.GAME);
        } else {
            req.setAttribute("errors", "Thông tin người dùng không hợp lệ hoặc đã được sử dụng.");
            doGet(req, resp);
        }
    }
}
