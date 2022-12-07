package characters;

public class Defender extends Warrior{
    private static final int ATTACK = 3;
    private static final int DEFENSE = 2;
    public static final int BASIC_HEALTH = 60;
    Defender()
    {
        super(BASIC_HEALTH);
    }
    @Override
    public int getAttack()
    {
        return ATTACK;
    }

    public int getDefense()
    {
        return DEFENSE;
    }
    @Override
    public int receiveDamage(int damage)
    {

        return super.receiveDamage(Math.max(0,damage - getDefense()));
    }

}
