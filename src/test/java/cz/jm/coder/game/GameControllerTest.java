package cz.jm.coder.game;

import cz.jm.coder.AbstractIntegrationTest;
import cz.jm.coder.game.repository.model.GameEntity;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class GameControllerTest extends AbstractIntegrationTest {

    @Test
    @WithMockUser(username = "spring")
    public void testCreateGame() throws Exception {
        GameEntity game = successfulCallForObject(post("/api/game"), GameEntity.class);
        String id = game.id();
        assertFalse(Strings.isBlank(id));
        GameEntity gameById = successfulCallForObject(get("/api/game/" + id), GameEntity.class);
        assertEquals(game, gameById);
    }
}