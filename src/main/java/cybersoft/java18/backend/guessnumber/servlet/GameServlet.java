package cybersoft.java18.backend.guessnumber.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cybersoft.java18.backend.guessnumber.model.Guess;
import cybersoft.java18.backend.guessnumber.model.Player;
import cybersoft.java18.backend.guessnumber.model.GameSession;
import cybersoft.java18.backend.guessnumber.service.GameService;
import cybersoft.java18.backend.guessnumber.utils.JspUtils;
import cybersoft.java18.backend.guessnumber.utils.UrlUtils;

@WebServlet(name = "gameServlet", urlPatterns = {UrlUtils.GAME, UrlUtils.NEW_GAME, UrlUtils.RANKED})
public class GameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GameService gameService;

    @Override
    public void init() throws ServletException {
        super.init();
        gameService = GameService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtils.GAME:
            case UrlUtils.NEW_GAME:
                loadGame(req, resp);
                break;
            case UrlUtils.RANKED:
                req.getRequestDispatcher(JspUtils.RANKED).forward(req, resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + UrlUtils.NOT_FOUND);
                break;
        }
    }

    private void loadGame(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var currentUser = (Player) req.getSession().getAttribute("currentUser");
        // create new game/get existed game
        GameSession game = gameService.getCurrentGame(currentUser.getUsername());
        // put in req
        req.setAttribute("game", game);
        req.getRequestDispatcher(JspUtils.GAME)
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case UrlUtils.GAME:
                processGame(req, resp);
                break;
            case UrlUtils.NEW_GAME:
                processNewGame(req, resp);
                break;
            case UrlUtils.RANKED:
                req.getRequestDispatcher(JspUtils.RANKED)
                        .forward(req, resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + UrlUtils.NOT_FOUND);
        }
    }

    private void processNewGame(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        var currentUser = (Player) req.getSession().getAttribute("currentUser");
        // create new game/get existed game
        gameService.skipAndPlayNewGame(currentUser.getUsername());

        doGet(req, resp);
    }

    private void processGame(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String gameSessionId = req.getParameter("game-session");
        int guessNumber = Integer.parseInt(req.getParameter("guess"));

        var gameSession = gameService.getGameSession(gameSessionId);

        if (gameSession == null) { // if the session is not existed, ask the player to sign in again
            req.getSession().invalidate();
            resp.sendRedirect(req.getContextPath() + UrlUtils.SIGN_IN);
            return;
        }

        gameSession.getGuess().add(createGuess(guessNumber, gameSession));

        if (guessNumber == gameSession.getTargetNumber()) {
            gameSession.setIsCompleted(true);
            gameService.completeGame(gameSession.getId());
            sendResultToGameUI(req, resp, gameSession);
            return;
        }

        sendResultToGameUI(req, resp, gameSession);
    }

    private Guess createGuess(int guessNumber, GameSession gameSession) {
        int result = gameSession.getTargetNumber() == guessNumber
                ? 0
                : guessNumber > gameSession.getTargetNumber()
                ? 1 : -1;

        Guess newGuess = new Guess(guessNumber, gameSession.getId(), result);
        gameService.saveGuess(newGuess);
        return newGuess;
    }

    private void sendResultToGameUI(HttpServletRequest req, HttpServletResponse resp, GameSession gameSession) throws
            ServletException, IOException {
        req.setAttribute("game", gameSession);
        doGet(req, resp);
    }
}
