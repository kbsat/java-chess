package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlankTest {

    @Test
    @DisplayName("해당 위치가 비어있으면 이동 불가")
    void canMove() {
        final Piece blank = Blank.getInstance();
        final Position start = new Position("e", "3");
        final Position end = new Position("e", "4");
        assertThatThrownBy(() -> blank.canMove(start, end, new Queen(Team.WHITE)))
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessage("비어 있는 칸입니다.");
    }
}
