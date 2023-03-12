package cz.jm.coder.game.repository;

import cz.jm.coder.game.repository.model.GameEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BoardGameRepository extends MongoRepository<GameEntity, String> {
}
