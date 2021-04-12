package chess.domain.piece;

import chess.domain.position.Position;

public class Knight extends Division {
    public static final int FIRST_POSITION = 2;
    public static final int SECOND_POSITION = 1;
    public static final String KNIGHT_MOVE_ERROR = "나이트가 이동할 수 없는 위치입니다";
    private static final String KNIGHT_DISPLAYNAME = "n";
    private static final double KNIGHT_SCORE = 2.5;

    public Knight(final Color color, final Position position) {
        super(color, KNIGHT_DISPLAYNAME, position);
    }

    @Override
    public void moveToEmpty(final Position to, final Pieces pieces) {
        final int diffRow = Math.abs(position.diffRow(to));
        final int diffColumn = Math.abs(position.diffColumn(to));

        if ((diffRow == FIRST_POSITION && diffColumn == SECOND_POSITION) ||
                (diffRow == SECOND_POSITION && diffColumn == FIRST_POSITION)) {
            position = to;
            return;
        }
        throw new IllegalArgumentException(KNIGHT_MOVE_ERROR);
    }

    @Override
    public void moveForKill(final Position to, final Pieces pieces) {
        this.moveToEmpty(to, pieces);
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return KNIGHT_SCORE;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
