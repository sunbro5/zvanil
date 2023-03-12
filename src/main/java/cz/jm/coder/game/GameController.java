package cz.jm.coder.game;

import cz.jm.coder.game.repository.model.GameEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/game")
@RestController
public class GameController {

    private final GameService gameService;

    @PostMapping
    public GameEntity createGame() {
        return gameService.generateGame();
    }

    @GetMapping("/{id}")
    public GameEntity getGame(@PathVariable String id) {
        return gameService.getGameById(id);
    }

}
