package unitTest;

import control.GameEngine;
import model.Attack;
import model.Card;
import model.Pokemon;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Fish on 2017/5/24.
 */
public class AttackTest {
    Card.PokmonType pType;
    Attack attack;
    ArrayList<Attack> attacks;
    Pokemon pokemon1, pokemon2;

    static GameEngine gameEngine;

    @BeforeClass
    public static void beforeClass(){
        gameEngine = new GameEngine();
    }
    @Before
    public void before(){
        //gameEngine = new GameEngine();
        pType = Card.PokmonType.LEVEL1;
        attack = new Attack("Quick Attack", 20, 2);
        attacks = new ArrayList<Attack>();
        attacks.add(attack);
        pokemon1 = new Pokemon("pokemon1", 50, pType, attacks);
        pokemon2 = new Pokemon("pokemon2", 20, pType, attacks);
    }

    @Test
    public void testAttack(){
       // gameEngine = new GameEngine();
        assertTrue(gameEngine.attack(pokemon1, attack, true));
    }

    @After
    public void after(){
        gameEngine = null;
    }
}
