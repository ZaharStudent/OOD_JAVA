package characters;

import client.Battle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BattleTest {

    @Test
    @DisplayName("Smoke test for straight fight task")
    void smokeTestStraightFigth()
    {
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
        var freelancer = new Lancer();
        var vampire = new Vampire();
        var priest = new Healer();

        assertEquals(true,Battle.fight(chuck, bruce));
        assertEquals(false,Battle.fight(dave, carl));
        assertEquals(true,chuck.isAlive());
        assertEquals(false,bruce.isAlive());

        assertEquals(true,carl.isAlive());
        assertEquals(false,dave.isAlive());
        assertEquals(false,Battle.fight(carl, mark));
        assertEquals(false,carl.isAlive());
        assertEquals(false,Battle.fight(bob, mike));

        assertEquals(true,Battle.fight(lancelot, rog));
        assertEquals(false,Battle.fight(eric, richard));
        assertEquals(true,Battle.fight(ogre, adam));
        assertEquals(true,Battle.fight(freelancer, vampire));
        assertEquals(true,freelancer.isAlive());
        assertEquals(14,freelancer.getHealth());
        priest.heal(freelancer);
        assertEquals(16,freelancer.getHealth());

        var my_army = new Army();
        my_army.addUnits(Defender::new, 2);
        my_army.addUnits(Healer::new, 1);
        my_army.addUnits(Vampire::new, 2);
        my_army.addUnits(Lancer::new, 2);
        my_army.addUnits(Healer::new, 1);
        my_army.addUnits(Warrior::new, 1);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Warrior::new, 2);
        enemyArmy.addUnits(Lancer::new, 4);
        enemyArmy.addUnits(Healer::new, 1);
        enemyArmy.addUnits(Defender::new, 2);
        enemyArmy.addUnits(Vampire::new, 3);
        enemyArmy.addUnits(Healer::new, 1);

        var army3 = new Army();
        army3.addUnits(Warrior::new, 1);
        army3.addUnits(Lancer::new, 1);
        army3.addUnits(Healer::new, 1);
        army3.addUnits(Defender::new, 2);

        var army4 = new Army();
        army4.addUnits(Vampire::new, 3);
        army4.addUnits(Warrior::new, 1);
        army4.addUnits(Healer::new, 1);
        army4.addUnits(Lancer::new, 2);

        var army5 = new Army();
        army5.addUnits(Warrior::new, 10);

        var army6 = new Army();
        army6.addUnits(Warrior::new, 6);
        army6.addUnits(Lancer::new, 5);



        assertEquals(false,Battle.fight(my_army, enemyArmy));
        System.out.println("CURR");
        assertEquals(true,Battle.fight(army3, army4)); // ???
        System.out.println("END");
        assertEquals(false,Battle.straightFight(army5, army6));

    }
    @Test
    @DisplayName("Smoke test for characters.Army task")
    void smokeTest1()
    {
        Warrior chuck = new Warrior();
        Warrior bruce = new Warrior();
        Warrior carl = new Knight();
        Warrior dave = new Warrior();
        Warrior mark = new Warrior();

        assertEquals(true,Battle.fight(chuck, bruce));
        assertEquals(false,Battle.fight(dave, carl));
        assertEquals(true,chuck.isAlive());
        assertEquals(false,bruce.isAlive());
        assertEquals(true,carl.isAlive());
        assertEquals(false,dave.isAlive());
        assertEquals(false,Battle.fight(carl, mark));
        assertEquals(false,carl.isAlive());

        Army myArmy = new Army();
        myArmy.addUnits(Knight::new, 3);

        Army enemyArmy = new Army();
        enemyArmy.addUnits(Warrior::new, 3);

        Army army3 = new Army();
        army3.addUnits(Warrior::new, 20);
        army3.addUnits(Knight::new, 5);

        Army army4 = new Army();
        army4.addUnits(Warrior::new, 30);

        assertEquals(true, Battle.fight(myArmy, enemyArmy));
        assertEquals(false,Battle.fight(army3, army4));
    }

    @Test
    @DisplayName("1. Battle")
    void TestBattleNum1()
    {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Warrior::new, 1);
        army2.addUnits(Warrior::new, 2);
        boolean res = Battle.fight(army1,army2);
        assertEquals(false,res);
    }

    @Test
    @DisplayName("2. Battle")
    void TestBattleNum2()
    {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Warrior::new, 2);
        army2.addUnits(Warrior::new, 3);
        boolean res = Battle.fight(army1,army2);
        assertEquals(false,res);
    }
    @Test
    @DisplayName("3. Battle")
    void TestBattleNum3()
    {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Warrior::new, 5);
        army2.addUnits(Warrior::new, 7);
        boolean res = Battle.fight(army1,army2);
        assertEquals(false,res);
    }
    @Test
    @DisplayName("4. Battle")
    void TestBattleNum4()
    {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Warrior::new, 20);
        army2.addUnits(Warrior::new, 21);
        boolean res = Battle.fight(army1,army2);
        assertEquals(true,res);
    }
    @Test
    @DisplayName("5. Battle")
    void TestBattleNum5()
    {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Warrior::new, 10);
        army2.addUnits(Warrior::new, 11);
        boolean res = Battle.fight(army1,army2);
        assertEquals(true,res);
    }
    @Test
    @DisplayName("6. Battle")
    void TestBattleNum6()
    {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Warrior::new, 11);
        army2.addUnits(Warrior::new, 7);
        boolean res = Battle.fight(army1,army2);
        assertEquals(true,res);
    }

    @Test
    @DisplayName("7. Battle")
    void TestBattleNum7()
    {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Warrior::new, 5);
        army1.addUnits(Defender::new, 4);
        army1.addUnits(Defender::new, 5);
        army2.addUnits(Warrior::new, 4);
        assertEquals(true,Battle.fight(army1,army2));
    }

    @Test
    @DisplayName("8. Battle")
    void TestBattleNum8()
    {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Defender::new, 5);
        army1.addUnits(Warrior::new, 20);
        army2.addUnits(Warrior::new, 21);
        army1.addUnits(Defender::new, 4);
        assertEquals(true,Battle.fight(army1,army2));
    }
    @Test
    @DisplayName("9. Battle")
    void TestBattleNum9()
    {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Warrior::new, 10);
        army1.addUnits(Defender::new, 5);
        army2.addUnits(Warrior::new, 5);
        army1.addUnits(Defender::new, 10);
        assertEquals(true,Battle.fight(army1,army2));
    }
    @Test
    @DisplayName("10. Battle")
    void TestBattleNum10()
    {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Defender::new, 2);
        army1.addUnits(Warrior::new, 1);
        army1.addUnits(Defender::new, 1);
        army2.addUnits(Warrior::new, 5);
        assertEquals(false,Battle.fight(army1,army2));
    }
    @Test
    @DisplayName("11. Battle")
    void TestBattleNum11()
    {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Defender::new, 5);
        army1.addUnits(Vampire::new, 6);
        army1.addUnits(Warrior::new, 7);
        army2.addUnits(Warrior::new, 6);
        army2.addUnits(Defender::new, 6);
        army2.addUnits(Vampire::new, 6);
        assertEquals(false,Battle.fight(army1,army2));
    }
    @Test
    @DisplayName("12. Battle")
    void TestBattleNum12()
    {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Defender::new, 2);
        army1.addUnits(Vampire::new, 3);
        army1.addUnits(Warrior::new, 4);
        army2.addUnits(Warrior::new, 4);
        army2.addUnits(Defender::new, 4);
        army2.addUnits(Vampire::new, 3);
        assertEquals(false,Battle.fight(army1,army2));
    }
    @Test
    @DisplayName("13. Battle")
    void TestBattleNum13()
    {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Defender::new, 11);
        army1.addUnits(Vampire::new, 3);
        army1.addUnits(Warrior::new, 4);
        army2.addUnits(Warrior::new, 4);
        army2.addUnits(Defender::new, 4);
        army2.addUnits(Vampire::new, 13);
        assertEquals(true,Battle.fight(army1,army2));
    }
    @Test
    @DisplayName("14. Battle")
    void TestBattleNum14()
    {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Defender::new, 9);
        army1.addUnits(Vampire::new, 3);
        army1.addUnits(Warrior::new, 8);
        army2.addUnits(Warrior::new, 4);
        army2.addUnits(Defender::new, 4);
        army2.addUnits(Vampire::new, 13);
        assertEquals(true,Battle.fight(army1,army2));
    }

    @Test
    @DisplayName("15. Battle")
    void TestBattleNum15()
    {
        Army army1 = new Army();
        Army army2 = new Army();
        army1.addUnits(Lancer::new, 5);
        army1.addUnits(Vampire::new, 3);
        army1.addUnits(Warrior::new, 4);
        army1.addUnits(Defender::new, 2);
        army2.addUnits(Warrior::new, 4);
        army2.addUnits(Defender::new, 4);
        army2.addUnits(Vampire::new, 6);
        army2.addUnits(Lancer::new, 5);
        assertEquals(false,Battle.fight(army1,army2));
    }

    @Test
    @DisplayName("16. Battle")
    void TestBattleNum16()
    {
        Army army1 = new Army();
        Army army2 = new Army();

        army1.addUnits(Lancer::new, 7);
        army1.addUnits(Vampire::new, 3);
        army1.addUnits(Warrior::new, 4);
        army1.addUnits(Defender::new, 2);
        army2.addUnits(Warrior::new, 4);
        army2.addUnits(Defender::new, 4);
        army2.addUnits(Vampire::new, 6);
        army2.addUnits(Lancer::new, 4);
        assertEquals(true,Battle.fight(army1,army2));
    }

    @Test
    @DisplayName("17. Battle")
    void TestBattleNum17()
    {
        Army army_lancer = new Army();
        Army army_warrior= new Army();

        army_warrior.addUnits(Warrior::new, 2);
        army_lancer.addUnits(Lancer::new, 1);
        army_lancer.addUnits(Warrior::new, 1);
        assertEquals(false,Battle.fight(army_warrior,army_lancer));
    }

    @Test
    @DisplayName("18. Battle")
    void TestBattleNum18()
    {
        Army army1 = new Army();
        Army army2= new Army();

        army1.addUnits(Lancer::new, 1);
        army1.addUnits(Warrior::new, 3);
        army1.addUnits(Healer::new, 1);
        army1.addUnits(Warrior::new, 4);
        army1.addUnits(Healer::new, 1);
        army1.addUnits(Knight::new, 2);
        army2.addUnits(Warrior::new, 4);
        army2.addUnits(Defender::new, 4);
        army2.addUnits(Healer::new, 1);
        army2.addUnits(Vampire::new, 6);
        army2.addUnits(Lancer::new, 4);
        assertEquals(false,Battle.fight(army1,army2));
    }

    @Test
    @DisplayName("19. Battle")
    void TestBattleNum19()
    {
        Army army1 = new Army();
        Army army2= new Army();

        army1 = new Army();
        army2 = new Army();

        army1.addUnits(Lancer::new, 5);
        army1.addUnits(Vampire::new, 3);
        army1.addUnits(Warrior::new, 4);
        army1.addUnits(Defender::new, 2);
        army2.addUnits(Warrior::new, 4);
        army2.addUnits(Defender::new, 4);
        army2.addUnits(Vampire::new, 6);
        army2.addUnits(Lancer::new, 5);
        assertEquals(false,Battle.straightFight(army1,army2));

    }

    @Test
    @DisplayName("20. Battle")
    void TestBattleNum20()
    {
        Army army1 = new Army();
        Army army2= new Army();

        army1 = new Army();
        army2 = new Army();

        army1.addUnits(Lancer::new, 7);
        army1.addUnits(Vampire::new, 3);
        army1.addUnits(Warrior::new, 4);
        army1.addUnits(Defender::new, 2);
        army2.addUnits(Warrior::new, 4);
        army2.addUnits(Defender::new, 4);
        army2.addUnits(Vampire::new, 6);
        army2.addUnits(Lancer::new, 4);
        assertEquals(true,Battle.straightFight(army1,army2));

    }

    @Test
    @DisplayName("21. Battle")
    void TestBattleNum21()
    {
        Army army1 = new Army();
        Army army2= new Army();

        army1 = new Army();
        army2 = new Army();

        army1.addUnits(Lancer::new, 7);
        army1.addUnits(Vampire::new, 3);
        army1.addUnits(Healer::new, 1);
        army1.addUnits(Warrior::new, 4);
        army1.addUnits(Healer::new, 1);
        army1.addUnits(Defender::new, 2);

        army2.addUnits(Warrior::new, 4);
        army2.addUnits(Defender::new, 4);
        army1.addUnits(Healer::new, 1);
        army2.addUnits(Vampire::new, 6);
        army2.addUnits(Lancer::new, 4);
        assertEquals(false,Battle.straightFight(army1,army2));

    }

    @Test
    @DisplayName("22. Battle")
    void TestBattleNum22()
    {
        Army army1 = new Army();
        Army army2= new Army();

        army1 = new Army();
        army2 = new Army();

        army1.addUnits(Lancer::new, 4);
        army1.addUnits(Vampire::new, 3);
        army1.addUnits(Healer::new, 1);
        army1.addUnits(Warrior::new, 4);
        army1.addUnits(Healer::new, 1);
        army1.addUnits(Knight::new, 2);
        army2.addUnits(Warrior::new, 4);
        army1.addUnits(Healer::new, 1);
        army2.addUnits(Vampire::new, 2);
        army2.addUnits(Lancer::new, 4);
        assertEquals(true,Battle.straightFight(army1,army2));

    }
    @ParameterizedTest
    @MethodSource
    @DisplayName("Duel for characters.Army's")
    void testTwoArmiesDuel(Army army1,Army army2,boolean expected)
    {
        assertEquals(expected,Battle.fight(army1,army2));
    }

    static Stream<Arguments> testTwoArmiesDuel() {
        return Stream.of(Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 1)
                                .addUnits(Knight::new, 1),
                        new Army()
                                .addUnits(Knight::new, 1)
                                .addUnits(Warrior::new, 1),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 1)
                                .addUnits(Knight::new, 1),
                        new Army()
                                .addUnits(Knight::new, 1)
                                .addUnits(Warrior::new, 2),
                        false));
    }
}