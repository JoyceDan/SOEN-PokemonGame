package unitTest;

import model.Attack;
import model.Card;
import model.CardCollection;
import model.Pokemon;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Fish on 2017/5/23.
 */
public class CardCollectionTest {

    CardCollection cardCollection;
    Pokemon pokemon1, pokemon2;
    Card.PokmonType pType;
    ArrayList<Attack> attacks;
    Attack attack;

    @Before
    public void before(){
        cardCollection = new CardCollection();
        pType = Card.PokmonType.LEVEL1;
        attack = new Attack("Quick Attack", 20, 2);
        attacks = new ArrayList<Attack>();
        attacks.add(attack);
        pokemon1 = new Pokemon("pokemon1", 50, pType, attacks);
        pokemon2 = new Pokemon("pokemon2", 40, pType, attacks);
        cardCollection.addCard(pokemon1);
        cardCollection.addCard(pokemon2);
    }

    @Test
    public void testGetCard(){
        assertEquals(pokemon2, cardCollection.getCard(1));
    }

    @Test
    public void testRemoveCard(){
        cardCollection.removeCard(0);
        assertFalse(cardCollection.getCard(0) == pokemon1);
    }
}
