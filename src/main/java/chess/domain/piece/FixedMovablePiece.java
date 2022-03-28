package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public abstract class FixedMovablePiece extends Piece {

    protected FixedMovablePiece(Color color, PieceName name) {
        super(color, name);
    }

    protected Map<Direction, List<Position>> getMovablePositionsByDirections(Position position,
                                                                             List<Direction> directions) {
        Map<Direction, List<Position>> movable = new EnumMap<>(Direction.class);
        for (Direction direction : directions) {
            movable.put(direction, new ArrayList<>());
            putMovablePositionsByDirection(movable, position, direction);
        }
        return movable;
    }

    private void putMovablePositionsByDirection(Map<Direction, List<Position>> movable, Position position,
                                                Direction direction) {
        Position nextPosition = position.toDirection(direction);
        if (nextPosition == position) {
            return;
        }
        movable.get(direction).add(nextPosition);
    }
}
