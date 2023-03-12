package cz.jm.coder.game.repository.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("game")
public record GameEntity(
        @Id String id,
        BoardGame boardGame) {
}
