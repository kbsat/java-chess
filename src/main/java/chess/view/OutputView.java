package chess.view;

import chess.domain.game.Player;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String NEWLINE = System.getProperty("line.separator");

    private OutputView() {
    }

    public static void printWillPlayGameMessage() {
        System.out.println("> 체스 게임을 시작합니다.\n" +
                "> 게임 시작 : start\n" +
                "> 게임 종료 : end\n");
    }

    public static void printWayToMove() {
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3\")");
    }

    public static void printScore(Map<Color, Double> scoreTable) {
        scoreTable.forEach(OutputView::printEachScore);
    }

    private static void printEachScore(Color color, Double score) {
        System.out.printf("%s : %.1f점" + NEWLINE, color.name(), score);
    }

    public static void printError(String errorMessage) {
        System.out.println("[ERROR] " + errorMessage);
    }

    public static void printCurrentPlayer(Player player) {
        System.out.println(NEWLINE + "현재 플레이어는 : " + player.color().name());
    }

    public static void display(Map<Position,Piece> pieces) {
        List<Row> rows = Arrays.asList(Row.values());
        Collections.reverse(rows);
        for (Row row : rows) {
            for (Column column : Column.values()) {
                System.out.print(pieces.get(Position.of(column, row))
                                       .display());
            }
            System.out.println();
        }
    }
}
