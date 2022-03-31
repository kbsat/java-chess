package chess.domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Direction {
    EAST(1, 0),
    WEST(-1, 0),
    SOUTH(0, -1),
    NORTH(0, 1),

    SOUTH_EAST(1, -1),
    SOUTH_WEST(-1, -1),
    NORTH_EAST(1, 1),
    NORTH_WEST(-1, 1),

    SOUTH_SOUTH_EAST(1, -2),
    SOUTH_SOUTH_WEST(-1, -2),
    NORTH_NORTH_EAST(1, 2),
    NORTH_NORTH_WEST(-1, 2),
    EAST_EAST_SOUTH(2, -1),
    EAST_EAST_NORTH(2, 1),
    WEST_WEST_SOUTH(-2, -1),
    WEST_WEST_NORTH(-2, 1);


    private final int columnValue;
    private final int rowValue;

    Direction(int columnValue, int rowValue) {
        this.columnValue = columnValue;
        this.rowValue = rowValue;
    }

    public List<Direction> getDiagonal() {
        List<Direction> directions = new ArrayList<>();
        if (this == NORTH || this == SOUTH) {
            directions.add(getDirectionByValues(-1, rowValue));
            directions.add(getDirectionByValues(1, rowValue));
            return directions;
        }
        if (this == EAST || this == WEST) {
            directions.add(getDirectionByValues(columnValue, -1));
            directions.add(getDirectionByValues(columnValue, 1));
            return directions;
        }

        throw new IllegalStateException("해당 디렉션의 대각선을 구할 수 없습니다");
    }

    public static Direction getDirectionByValues(int columnValue, int rowValue) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.columnValue == columnValue && direction.rowValue == rowValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 Direction이 없습니다."));
    }

    public int getColumnValue() {
        return columnValue;
    }

    public int getRowValue() {
        return rowValue;
    }
}
