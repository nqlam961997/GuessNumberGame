package cybersoft.java18.backend.guessnumber.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java18.backend.guessnumber.model.Player;
import cybersoft.java18.backend.guessnumber.service.GameService;
import cybersoft.java18.backend.guessnumber.utils.JspUtils;
import cybersoft.java18.backend.guessnumber.utils.UrlUtils;

@WebServlet(name = "authServlet", urlPatterns = { UrlUtils.SIGN_IN, UrlUtils.SIGN_UP })
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
		}
	}

	private void processLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		GameService service = GameService.getInstance();

		Player player = service.signIn(username, password);
		
		if (player != null) {
			req.getSession().removeAttribute("messages");
			req.getSession().setAttribute("currentUser", player);
			resp.sendRedirect(req.getContextPath() + UrlUtils.GAME);
		} else {
			req.getSession().setAttribute("messages", "User info is incorrect");
			resp.sendRedirect(req.getContextPath() + UrlUtils.SIGN_IN);
		}
	}

	private void processRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String name = req.getParameter("name");

		GameService service = GameService.getInstance();

		Player player = service.addPlayer(username, password, name);

		if (player != null) {
			req.getSession().removeAttribute("messages");
			req.getSession().setAttribute("currentUser", player);
			resp.sendRedirect(req.getContextPath() + UrlUtils.GAME);
		} else {
			req.getSession().setAttribute("messages", "User info is incorrect");
			resp.sendRedirect(req.getContextPath() + UrlUtils.SIGN_UP);
		}
	}

}
