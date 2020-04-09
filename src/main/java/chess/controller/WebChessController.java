package chess.controller;

import static spark.Spark.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.ChessGame;
import chess.dao.GamesDao;
import chess.dao.MoveDao;
import chess.domain.position.Position;
import chess.dto.ScoreDto;
import chess.dto.TurnDto;
import chess.dto.UnitsDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {
	private final Gson gson;
	private final MoveDao moveDao;
	private final GamesDao gamesDao;

	public WebChessController(MoveDao moveDao, GamesDao gamesDao) {
		this.gson = new GsonBuilder().create();
		this.moveDao = moveDao;
		this.gamesDao = gamesDao;
	}

	public void route() {
		Spark.staticFileLocation("/templates");
		get("/", this::index);
		get("/init", this::init);
		get("/new_game/:id", this::createNewGame);
		get("/games", this::games);
		get("/turn/:id", this::turn);
		get("/board/:id", this::board);
		get("/score/:id", this::score);
		post("/move/:id", this::move);
		post("/users", this::users);
	}

	private String index(Request request, Response response) {
		Map<String, Object> model = new HashMap<>();
		return render(model, "index.html");
	}

	private String init(Request request, Response response) {
		return render(new HashMap<>(), "userNames.html");
	}

	private String createNewGame(Request request, Response response) {
		HashMap<String, Object> model = new HashMap<>();
		model.put("id", request.params(":id"));
		return render(model, "playingGame.html");
	}

	private String games(Request request, Response response) throws SQLException {
		return gson.toJson(gamesDao.everyGames());
	}

	private String turn(Request request, Response response) throws SQLException {
		int id = Integer.parseInt(request.params(":id"));
		ChessGame game = ChessGame.createGameByMoves(moveDao.findMovesByGameId(id));
		TurnDto turnDto = new TurnDto(game.turn());

		HashMap<String, Object> model = new HashMap<>();
		model.put("turn", turnDto);
		return gson.toJson(model);
	}

	private String board(Request request, Response response) throws SQLException {
		int id = Integer.parseInt(request.params(":id"));
		ChessGame game = ChessGame.createGameByMoves(moveDao.findMovesByGameId(id));
		UnitsDto units = new UnitsDto(game.board().getBoard());
		return gson.toJson(units);
	}

	private String score(Request request, Response response) throws SQLException {
		int id = Integer.parseInt(request.params(":id"));
		ChessGame game = ChessGame.createGameByMoves(moveDao.findMovesByGameId(id));
		ScoreDto score = new ScoreDto(game.status());
		HashMap<String, Object> model = new HashMap<>();
		model.put("score", score);
		return gson.toJson(model);
	}

	private String move(Request request, Response response) throws SQLException {
		int id = Integer.parseInt(request.params(":id"));
		ChessGame game = ChessGame.createGameByMoves(moveDao.findMovesByGameId(id));
		Map<String, Integer> map = gson.fromJson(request.body(), new TypeToken<Map<String, Integer>>() {}.getType());
		game.move(Position.of((map.get("sourceX")), (map.get("sourceY"))),
			Position.of((map.get("targetX")), (map.get("targetY"))));
		UnitsDto updateUnits = new UnitsDto(game.board().getBoard());
		saveMove(id, map);
		return gson.toJson(updateUnits);
	}

	private void saveMove(int id, Map<String, Integer> map) throws SQLException {
		int sourceX = map.get("sourceX") + 96;
		int targetX = map.get("targetX") + 96;
		moveDao.save((char)(sourceX) + String.valueOf(map.get("sourceY")),
			(char)(targetX) + String.valueOf(map.get("targetY")), id);
	}

	private String users(Request request, Response response) throws SQLException {
		Map<String, String> req = gson.fromJson(request.body(), new TypeToken<Map<String, String>>() {}.getType());
		int gameId = gamesDao.createGame(req.get("user1"), req.get("user2"));
		HashMap<String, Object> model = new HashMap<>();
		model.put("id", gameId);
		return gson.toJson(model);
	}

	private static String render(Map<String, Object> model, String templatePath) {
		return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
	}
}

