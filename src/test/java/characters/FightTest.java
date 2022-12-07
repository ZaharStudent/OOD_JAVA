package characters;

import client.Battle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class FightTest {
    @Test
    @DisplayName("Smoke test for Warrior and Knight task")
    void smokeTest1() {
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();

        assertTrue(Battle.fight(chuck, bruce));
        assertFalse(Battle.fight(dave, carl));
        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
        assertTrue(carl.isAlive());
        assertFalse(dave.isAlive());
    }

    class Rookie extends Warrior
    {
        @Override
        public int getAttack()
        {
            return 1;
        }
    }

    @Test
    @DisplayName("1. Figth")
    void TestFigthNum1()
    {
        Warrior carl = new Warrior();
        Warrior jim = new Knight();
        boolean res = Battle.fight(carl,jim);
        assertEquals(false,res);
    }

    @Test
    @DisplayName("2. Figth")
    void TestFigthNum2()
    {
        Warrior ramon = new Knight();
        Warrior slevin = new Warrior();
        boolean res = Battle.fight(ramon,slevin);
        assertEquals(true,res);
    }

    @Test
    @DisplayName("3. Figth")
    void TestFigthNum3()
    {
        Warrior bob = new Warrior();
        Warrior mars = new Warrior();
        Battle.fight(bob,mars);
        boolean res = bob.isAlive();
        assertEquals(true,res);
    }

    @Test
    @DisplayName("4. Figth")
    void TestFigthNum4()
    {
        Warrior zeus = new Knight();
        Warrior godkiller = new Warrior();
        Battle.fight(zeus,godkiller);
        boolean res = zeus.isAlive();
        assertEquals(true,res);
    }

    @Test
    @DisplayName("5. Figth")
    void TestFigthNum5()
    {
        Warrior wife = new Warrior();
        Warrior husband = new Warrior();
        Battle.fight(husband,wife);
        boolean res = wife.isAlive();
        assertEquals(false,res);
    }

    @Test
    @DisplayName("6. Figth")
    void TestFigthNum6()
    {
        Warrior dragon = new Warrior();
        Warrior knight = new Knight();
        Battle.fight(dragon,knight);
        boolean res = knight.isAlive();
        assertEquals(true,res);
    }
    @Test
    @DisplayName("7. Figth")
    void TestFigthNum7()
    {
        Warrior unit_1 = new Warrior();
        Warrior unit_2 = new Knight();
        Warrior unit_3 = new Warrior();
        Battle.fight(unit_1,unit_2);
        boolean res = Battle.fight(unit_2,unit_3);
        assertEquals(false,res);
    }
    @Test
    @DisplayName("8. Figth")
    void TestFigthNum8()
    {
        Warrior unit1 = new Defender();
        Warrior unit2 = new Rookie();
        Battle.fight(unit1,unit2);
        assertEquals(60,unit1.getHealth());
    }

    @Test
    @DisplayName("9. Figth")
    void TestFigthNum9()
    {
        Warrior unit1 = new Defender();
        Warrior unit2 = new Rookie();
        Warrior unit3 = new Warrior();
        Battle.fight(unit1,unit2);
        assertEquals(true,Battle.fight(unit1,unit3));
    }
    @ParameterizedTest
    @MethodSource
    @DisplayName("Duel for two warriors")
    void testTwoWarriorsDuel(Warrior warrior1,Warrior warrior2,boolean expected)
    {
        assertEquals(expected,Battle.fight(warrior1,warrior2));
    }

    static Stream<Arguments> testTwoWarriorsDuel()
    {
        return Stream.of(Arguments.of(new Warrior(),new Warrior(),true),
                Arguments.of(new Warrior(),new Knight(),false),
                Arguments.of(new Knight(),new Warrior(),true),
                Arguments.of(new Knight(),new Knight(),true)
        );
    }

}