package cz.jm.coder.game.repository.model;

import org.springframework.data.annotation.Id;

public record BoardUnit(
        @Id int id,
        String playerId
) {

}
