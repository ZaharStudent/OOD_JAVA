package characters;
import client.Battle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VampirTest {

    @Test
    @DisplayName("Smoke test for Vampire task")
    void smokeTest4() {
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();
        var mark = new Warrior();
        var bob = new Defender();
        var mike = new Knight();
        var rog = new Warrior();
        var lancelot = new Defender();
        var eric = new Vampire();
        var adam = new Vampire();
        var richard = new Defender();
        var ogre = new Warrior();

        assertTrue(Battle.fight(chuck, bruce));
        assertFalse(Battle.fight(dave, carl));

        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
        assertTrue(carl.isAlive());
        assertFalse(dave.isAlive());

        assertFalse(Battle.fight(carl, mark));
        assertFalse(carl.isAlive());

        assertFalse(Battle.fight(bob, mike));
        assertTrue(Battle.fight(lancelot, rog));
        assertFalse(Battle.fight(eric, richard));
        assertTrue(Battle.fight(ogre, adam));

        var myArmy = new Army()
                .addUnits(Defender::new, 2)
                .addUnits(Vampire::new, 2)
                .addUnits(Warrior::new, 1);

        var enemyArmy = new Army()
                .addUnits(Warrior::new, 2)
                .addUnits(Defender::new, 2)
                .addUnits(Vampire::new, 3);

        var army3 = new Army()
                .addUnits(Warrior::new, 1)
                .addUnits(Defender::new, 4);

        var army4 = new Army()
                .addUnits(Vampire::new, 3)
                .addUnits(Warrior::new, 2);

        assertFalse(Battle.fight(myArmy, enemyArmy));
        assertTrue(Battle.fight(army3, army4));
    }
}