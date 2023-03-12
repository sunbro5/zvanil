package cz.jm.coder.game;

import cz.jm.coder.game.repository.model.BoardGame;
import cz.jm.coder.game.repository.model.BoardHex;
import cz.jm.coder.game.repository.model.BoardRow;
import cz.jm.coder.game.repository.model.GameEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.IntStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameFactory {

    public static GameEntity createGame10x10() {
        BoardGame boardGame = new BoardGame(createHexRow(10, 10));
        return new GameEntity(null, boardGame);
    }


    private static List<BoardRow> createHexRow(int sizeX, int sizeY) {
        return IntStream.range(0, sizeY)
                .mapToObj(index -> BoardRow.builder()
                        .positionY(index)
                        .columns(createHex(sizeX, index))
                        .build())
                .toList();
    }

    private static List<BoardHex> createHex(int sizeX, int offsetY) {
        return IntStream.range(0, sizeX)
                .mapToObj(index -> BoardHex.builder()
                        .positionX(sizeX)
                        .positionY(offsetY)
                        .build())
                .toList();
    }
}
