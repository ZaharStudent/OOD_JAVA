package characters;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.function.Supplier;


public class Army {
    private LinkedList<UnitInArmy> troops = new LinkedList<UnitInArmy>();

    public interface Processable
    {
        void processCommand(Command command,Warrior sender);
    }
    public interface HasWarriorBehind extends Processable
    {
        Warrior getWarriorBehind();
        void setWarriorBehind(Warrior behind);
    }
    public static class UnitInArmy extends Warrior implements HasWarriorBehind {
        private Warrior behind;
        private Warrior current;

        @Override
        public Warrior getWarriorBehind() {
            return behind;
        }
        @Override
        public void setWarriorBehind(Warrior behind)
        {
            this.behind = behind;
        }

        UnitInArmy(Warrior current,Warrior behind)
        {
            super(0);
            this.current = current;
            this.behind = behind;
        }
        @Override
        protected int receiveDamage(int damage)
        {
            int real_damage = current.receiveDamage(damage);
            processCommand(new OurChampionHasHit(){},this);
            return real_damage;

        }
        @Override
        public int getAttack() {
            return current.getAttack();
        }

        @Override
        public int getHealth() {
            return current.getHealth();
        }

        @Override
        protected void setHealth(int health) {
            current.setHealth(health);
        }

        @Override
        public boolean isAlive() {
            return current.isAlive();
        }

        public void processCommand(Command command, Warrior sender)
        {
                if(current instanceof Processable)
                    ((Processable) current).processCommand(command,sender);
                if (behind != null && behind instanceof Army.UnitInArmy unit) {
                    unit.processCommand(command, sender);
                }
        }
        private Warrior workWithCurrentWarrior()
        {
            return current;
        }

        @Override
        public int getMaxHealth() {
            return current.getMaxHealth();
        }

        public void healBy(int heal_power)
        {
            current.healBy(heal_power);
        }
        @Override
        public int hit(Warrior opponent)
        {
            return current.hit(opponent);
        }
    }

    public Iterator<Warrior> firstAlive()
    {
        return new FirstAliveIterator();
    }
    private class FirstAliveIterator implements Iterator<Warrior>
    {
        @Override
        public boolean hasNext()
        {
            while (peekFirst() != null && !peekFirst().isAlive())
            {
                removeFirst();
            }
            return peekFirst() != null;
        }

        @Override
        public Warrior next() {
            if(!hasNext())
                throw new NoSuchElementException();
            return peekFirst();
        }
    }

    private class PureFirstAliveIterator extends FirstAliveIterator
    {
        private int current_index = 0;
        @Override
        public boolean hasNext()
        {
            if(current_index == 0)
                super.hasNext(); // clear all death units;
            return troops.size() > current_index && !troops.isEmpty();
        }

        @Override
        public Warrior next() {
            if(!hasNext()) {
                current_index = 0;
                return null;
            }
            var result = troops.get(current_index);
            current_index++;
            return result.workWithCurrentWarrior();
        }

    }

    public Iterator<Warrior> pureFirstAlive() {return new PureFirstAliveIterator();}
    public Army addUnits(Warrior warrior)
    {
        UnitInArmy new_el = new UnitInArmy(warrior,null);
        if(!troops.isEmpty())
            troops.get(troops.size() - 1).setWarriorBehind(new_el);
        troops.add(new_el);
        return this;
    }

    public Army addUnits(Supplier<Warrior> factory, int count)
    {
        for(int i = 0; i < count;i++)
            addUnits(factory.get());
        return this;
    }

    private Warrior peekFirst()
    {
        return troops.peek();
    }

    private void removeFirst()
    {
        troops.poll();
    }


}
