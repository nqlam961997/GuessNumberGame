package cybersoft.java18.backend.guessnumber.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java18.backend.guessnumber.utils.JspUtils;
import cybersoft.java18.backend.guessnumber.utils.UrlUtils;

@WebServlet(name = "gameServlet", urlPatterns = { UrlUtils.GAME, UrlUtils.RANKED })
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		switch (req.getServletPath()) {
		case UrlUtils.GAME:
			req.getRequestDispatcher(JspUtils.GAME).forward(req, resp);
			break;

		case UrlUtils.RANKED:
			req.getRequestDispatcher(JspUtils.RANKED).forward(req, resp);
			break;
		}
	}
}
