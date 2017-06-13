package unitTest;

import control.GameEngine;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Fish on 2017/5/24.
 */
public class GameEngineTest {
    static GameEngine gameEngine;

    @BeforeClass
    public static void beforeClass(){
        gameEngine = new GameEngine();
    }

    @Test
    public void testLoadAssignDeck() {
        assertNotNull(gameEngine.getPlayer_Deck());
        assertNotNull(gameEngine.getAI_Deck());
    }

    @Test
    public void testCreateView(){
        assertNotNull(gameEngine.getBaseWindo().getMainGameView().getHandBoard().getCardCollection());
    }

    @Test public void testPrepareHand(){
        assertEquals(7, gameEngine.getPlayer_Hand().getCardCount());
        assertEquals(7, gameEngine.getAI_Hand().getCardCount());
    }

    @Test public void testDealCardToPlayer(){
        gameEngine.dealCard(true);
        assertEquals(8, gameEngine.getPlayer_Hand().getCardCount());
    }

    @Test public void testDealCardToAI(){
        gameEngine.dealCard(false);
        assertEquals(7, gameEngine.getAI_Hand().getCardCount());
    }

    @Test public void testAddToTrash(){
        gameEngine.addToTrash(gameEngine.getPlayer_Hand().getCard(0), true);
        assertEquals(1, gameEngine.getPlayer_Trash().getCardCount());
    }

    @Test public void testAddToTrashAi(){
        gameEngine.addToTrash(gameEngine.getAI_Hand().getCard(0), false);
        assertEquals(1, gameEngine.getAI_Trash().getCardCount());
    }

    @Test public void testDeleteCard(){
        gameEngine.deleteCard(gameEngine.getPlayer_Hand().getCard(0), true, false, "Hand" );
        assertEquals(7, gameEngine.getPlayer_Hand().getCardCount());
    }

    @Test public void testDeleteCardAi(){
        gameEngine.deleteCard(gameEngine.getAI_Hand().getCard(0), false, false, "Hand");
        assertEquals(6, gameEngine.getAI_Hand().getCardCount());
    }


}
