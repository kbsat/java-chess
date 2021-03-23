package chess.domain.piece;

import chess.domain.position.Difference;
import chess.domain.position.Position;
import chess.domain.position.Vertical;
import java.util.Arrays;
import java.util.List;

public final class Pawn extends Piece {

    public static final double EXTRA_SCORE = 0.5;
    private static final List<Integer> INITIAL_VERTICALS = Arrays.asList(2, 7);
    private static final List<Direction> POSSIBLE_DIRECTIONS = Arrays.asList(Direction.NORTH, Direction.NORTHEAST,
        Direction.NORTHWEST, Direction.INITIAL_PAWN_NORTH);
    private static final String INITIAL_NAME = "P";
    private static final double SCORE = 1;

    public Pawn(final Team team) {
        super(team, INITIAL_NAME);
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Piece piece) {
        final List<Integer> result = subtractByTeam(source, target);
        final Difference difference = new Difference(result);
        final Direction direction = POSSIBLE_DIRECTIONS.stream()
            .filter(possibleDirection -> possibleDirection.isSameDirection(difference))
            .findAny()
            .orElse(Direction.NOTHING);

        return direction != Direction.NOTHING && checkPossible(direction, piece, source.vertical());
    }

    @Override
    public boolean hasMiddlePath() {
        return true;
    }

    @Override
    public double score() {
        return SCORE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    private List<Integer> subtractByTeam(final Position source, final Position target) {
        if (team().isBlackTeam()) {
            return source.subtract(target);
        }
        return target.subtract(source);
    }

    private boolean checkPossible(final Direction direction, final Piece piece, final Vertical vertical) {
        if (direction.isNorth()) {
            return piece.isBlank();
        }
        if (direction.isNorthWest() || direction.isNorthEast()) {
            return piece.isOpponent(this);
        }
        if (direction.isInitialPawnNorth()) {
            return INITIAL_VERTICALS.contains(vertical.value()) && piece.isBlank();
        }
        return false;
    }
}
