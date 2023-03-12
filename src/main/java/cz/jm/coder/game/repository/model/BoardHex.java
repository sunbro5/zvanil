package cz.jm.coder.game.repository.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;

@Builder
public record BoardHex(
        @Id int id,
        int positionX,
        int positionY,
        int controlPlayerId,
        BoardUnit unit) {
}
