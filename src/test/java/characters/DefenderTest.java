package characters;
import client.Battle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefenderTest {

    @Test
    @DisplayName("Smoke test for Defender task")
    void smokeTest3() {
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();
        var mark = new Warrior();
        var bob = new Defender();
        var mike = new Knight();
        var rog = new Warrior();
        var lancelot = new Defender();

        assertTrue(Battle.fight(chuck, bruce));
        assertFalse(Battle.fight(dave, carl));
        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
        assertTrue(carl.isAlive());
        assertFalse(dave.isAlive());
        assertFalse(Battle.fight(carl, mark));
        assertFalse(carl.isAlive());
        System.out.println(bob.getHealth());
        assertFalse(Battle.fight(bob, mike));
        assertTrue(Battle.fight(lancelot, rog));

        var myArmy = new Army();
        myArmy.addUnits(Defender::new, 1);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Warrior::new, 2);

        var army3 = new Army();
        army3.addUnits(Warrior::new, 1);
        army3.addUnits(Defender::new, 1);

        var army4 = new Army();
        army4.addUnits(Warrior::new, 2);

        assertFalse(Battle.fight(myArmy, enemyArmy));
        assertTrue(Battle.fight(army3, army4));
    }
}