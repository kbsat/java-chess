package chess.domain.piece;

import java.util.Map;

import chess.domain.Team;
import chess.domain.position.Position;

public class BlankStrategy extends MovingStrategy {
	@Override
	protected void checkObstacle(Position source, Position target, Map<Position, Team> teamBoard) {

	}

	@Override
	protected void checkDirection(Position source, Position target) {

	}
}
