package Objects;

import Classes.ConsoleFormatter;

public final class Enemy extends Entity {
    public Enemy(String name, int health, int protection, int baseDamage) {
        super(name, health, protection, baseDamage);
    }

    @Override
    public String toString() {
        return ConsoleFormatter.deregex("/B" + name + "/0 - <hp:/r" + health + "/0> <prot:/b" + protection + "/0> <bdmg:/y" + baseDamage + "/0>");
    }
}