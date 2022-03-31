package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @Test
    @DisplayName("나이트가 갈 수있는 방향으로 위치리스트를 반환한다.")
    void getMovablePositionsByRook() {
        Knight knight = new Knight(Color.BLACK);
        Map<Direction, List<Position>> positions = knight.getMovablePositions(Position.of("d4"));
        Map<Direction, List<Position>> expected = new HashMap<>(
                Map.ofEntries(
                        Map.entry(Direction.SOUTH_SOUTH_EAST, List.of(Position.of("e2"))),
                        Map.entry(Direction.SOUTH_SOUTH_WEST, List.of(Position.of("c2"))),
                        Map.entry(Direction.NORTH_NORTH_EAST, List.of(Position.of("e6"))),
                        Map.entry(Direction.NORTH_NORTH_WEST, List.of(Position.of("c6"))),
                        Map.entry(Direction.EAST_EAST_SOUTH, List.of(Position.of("f3"))),
                        Map.entry(Direction.EAST_EAST_NORTH, List.of(Position.of("f5"))),
                        Map.entry(Direction.WEST_WEST_SOUTH, List.of(Position.of("b3"))),
                        Map.entry(Direction.WEST_WEST_NORTH, List.of(Position.of("b5")))
                )
        );
        assertThat(positions).isEqualTo(expected);
    }

    @Test
    @DisplayName("나이트는 2.5점이다.")
    void getPoint() {
        Piece knight = new Knight(Color.BLACK);
        assertThat(knight.getPoint()).isEqualTo(2.5);
    }
}