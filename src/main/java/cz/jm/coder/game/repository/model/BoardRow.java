package cz.jm.coder.game.repository.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;

import java.util.List;

@Builder
public record BoardRow(
        @Id int id,
        int positionY,
        List<BoardHex> columns) {
}
