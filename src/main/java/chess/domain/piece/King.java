package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class King extends FixedMovablePiece {

    public static final Position BLACK_INIT_LOCATION = Position.of("e8");
    public static final Position WHITE_INIT_LOCATION = Position.of("e1");

    private static final List<Direction> DIRECTIONS = List.of(
            Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH,
            Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST, Direction.SOUTH_WEST);
    private static final int KING_POINT = 0;

    public King(Color color) {
        super(color, PieceName.KING);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(Position position) {
        return super.getMovablePositionsByDirections(position, DIRECTIONS);
    }

    @Override
    public double getPoint() {
        return KING_POINT;
    }
}
