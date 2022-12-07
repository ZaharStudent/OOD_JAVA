package client;

import characters.EndOfFight;
import characters.Warrior;
import characters.Army;
public class Battle {
    public static boolean fight(Warrior warrior1, Warrior warrior2) throws NullPointerException
    {


        while (warrior1.isAlive() && warrior2.isAlive())
        {
            int base_health_warrior_1 = warrior1.getHealth();
            int base_health_warrior_2 = warrior2.getHealth();

            warrior1.hit(warrior2);
            if(warrior2.isAlive())
                warrior2.hit(warrior1);
            // Output for tests
            // System.out.printf("%d %d\n",warrior1.getHealth(),warrior2.getHealth());

            // End of fight command to make cooldown of heal false
            if(warrior1 instanceof Army.HasWarriorBehind current1)
                current1.processCommand(new EndOfFight(){},null);
            if(warrior2 instanceof Army.HasWarriorBehind current2)
                current2.processCommand(new EndOfFight(){},null);
            // against loop
            if(base_health_warrior_1 == warrior1.getHealth() && base_health_warrior_2 == warrior2.getHealth())
                throw new NullPointerException("Loop");

        }
        // Output for tests
        // System.out.println("End of fight");
        return warrior1.isAlive();

    }

    public static boolean fight(Army army1, Army army2)
    {
        var it1 = army1.firstAlive();
        var it2 = army2.firstAlive();
        while (it1.hasNext() && it2.hasNext())
        {
            try {
                fight(it1.next(), it2.next());
            }catch (NullPointerException e)
            {
                return false;
            }
        }
        return it1.hasNext();
    }

    public static boolean straightFight(Army army1, Army army2)
    {
        var it1 = army1.pureFirstAlive();
        var it2 = army2.pureFirstAlive();
        while (it1.hasNext() && it2.hasNext()) {
            do  {
                fight(it1.next(), it2.next());
            }while (it1.hasNext() && it2.hasNext());
            while (it1.next() != null);
            while (it2.next() != null);
        }
        return it1.hasNext();
    }
}
