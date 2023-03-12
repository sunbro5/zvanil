package cz.jm.coder.game;

import cz.jm.coder.exception.ResourceNotFoundException;
import cz.jm.coder.game.repository.BoardGameRepository;
import cz.jm.coder.game.repository.model.GameEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GameService {

    private final BoardGameRepository boardGameRepository;

    public GameEntity generateGame() {
        GameEntity game = GameFactory.createGame10x10();
        return boardGameRepository.save(game);
    }

    public GameEntity getGameById(String id){
        return boardGameRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Game not found"));
    }

}
