package unitTest;

import com.sun.org.apache.xpath.internal.operations.Equals;
import model.Attack;
import model.Card;
import model.Pokemon;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

/**
 * Created by Fish on 2017/5/23.
 */
public class PokemonTest {

    Pokemon pokemon;
    Card.PokmonType pType;
    ArrayList<Attack> attacks;
    Attack attack;

    @Before
    public void before(){
        pType = Card.PokmonType.LEVEL1;
        attack = new Attack("Quick Attack", 20, 2);
        attacks = new ArrayList<Attack>();
        attacks.add(attack);
        pokemon = new Pokemon("Glameow", 60, pType, attacks);
    }

    @Test
    public void testGotHit(){
        pokemon.gotHit(40);
        int damage = pokemon.getDamage();

        assertTrue(damage == 20);
    }

    @Test
    public void testIsPokemonAlive(){
        pokemon.gotHit(60);

        assertFalse(pokemon.isPokmonAlive());
    }

}
