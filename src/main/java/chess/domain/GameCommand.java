package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class GameCommand {

    private static final int RAW_COMMAND_TYPE_INDEX = 0;
    private static final int FROM_PARAMETER_INDEX = 1;
    private static final int TO_PARAMETER_INDEX = 2;

    private static final int FROM_INDEX = 0;
    private static final int TO_INDEX = 1;

    private final CommandType command;
    private final List<Position> movePositions = new ArrayList<>();

    public GameCommand(String... rawCommand) {
        command = CommandType.of(rawCommand[RAW_COMMAND_TYPE_INDEX]);
        validateCommand(rawCommand);
        if (command == CommandType.MOVE) {
            movePositions.add(new Position(rawCommand[FROM_PARAMETER_INDEX]));
            movePositions.add(new Position(rawCommand[TO_PARAMETER_INDEX]));
        }
    }

    private void validateCommand(String[] rawCommand) {
        if (command == CommandType.MOVE && rawCommand.length != 3){
            throw new IllegalArgumentException("해당 타입의 커맨드는 인자가 3개여야합니다.");
        }
        if (CommandType.isSingleCommand(command) && rawCommand.length > 1) {
            throw new IllegalArgumentException("해당 타입의 커맨드는 인자가 하나여야합니다.");
        }
    }

    public boolean isStart() {
        return command == CommandType.START;
    }

    public boolean isEnd() {
        return command == CommandType.END;
    }

    public Position getFromPosition() {
        if (command == CommandType.MOVE) {
            return movePositions.get(FROM_INDEX);
        }
        throw new IllegalStateException("해당 커맨드는 이 작업을 할 수 없습니다.");
    }

    public Position getToPosition() {
        if (command == CommandType.MOVE) {
            return movePositions.get(TO_INDEX);
        }
        throw new IllegalStateException("해당 커맨드는 이 작업을 할 수 없습니다.");
    }
}
