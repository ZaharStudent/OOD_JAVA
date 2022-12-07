package characters;

public class Warrior {
    private static final int ATTACK = 5;
    private static final int BASIC_HEALTH = 50;
    private final int max_health;
    private int health ;

    Warrior() {this(BASIC_HEALTH);}

    protected Warrior(int health )
    {
        this.health = health;
        this.max_health = health;
    }

    public int getMaxHealth()
    {
        return max_health;
    }
    public int getAttack() { return ATTACK; }
    public int getHealth() { return health; }

    protected void setHealth(int health) {
        if(health < 0)
            this.health = 0;
        else this.health = health;
    }

    public boolean isAlive() {return health > 0;}

    public int hit(Warrior opponent)
    {
        int real_damage = opponent.receiveDamage(getAttack());

        return real_damage;
    }

    protected int receiveDamage(int damage)
    {
        if(damage < 0)
            damage = 0;
        setHealth(getHealth() - damage);
        return damage;
    }



    public void healBy(int heal_power)
    {
        setHealth(Math.min(getHealth() + heal_power,getMaxHealth()));
    }

    @Override
    public String toString()
    {
        return String.format("%s {health = %d, attack = %d}",getClass().getSimpleName(),getHealth(),getAttack());
    }
}
