package characters;

public class Healer extends Warrior implements Army.Processable{
    private static final int HEAL_POWER = 2;
    private static final int BASIC_HEALTH = 60;
    private static final int ATTACK = 0;



    @Override
    public int hit(Warrior warrior)
    {
        return 0;
    }

    boolean isCooldown = false; // Healer can't heal more than one time at stroke
    Healer()
    {
        super(BASIC_HEALTH);
    }
    @Override
    public int getAttack() { return ATTACK;}

    @Override
    public void processCommand(Command command,Warrior sender)
    {
        if(command instanceof OurChampionHasHit && sender != null && !isCooldown && sender.isAlive() && sender.getMaxHealth() != sender.getHealth()) {
            heal(sender);
            isCooldown = true;
        }
        else if(command instanceof EndOfFight)
            isCooldown = false;

    }

    public int getHealPower()
    {
        return HEAL_POWER;
    }
    public void heal(Warrior warrior)
    {
        warrior.healBy(getHealPower());
    }
}
