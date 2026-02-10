package Objects;

import Classes.CharacterCreator;
import Classes.ConsoleFormatter;
import Classes.SkillLevelManager;

public final class Player extends Entity {
    public SkillLevelManager skillManager;
    public CharacterCreator.Character character;

    public Player(String name, int health, int protection, int baseDamage) {
        super(name, health, protection, baseDamage);
        this.skillManager = new SkillLevelManager();
        this.character = new CharacterCreator.Character();
    }

    public Player(String name, int health, int protection, int baseDamage, SkillLevelManager skillManager, CharacterCreator.Character character) {
        super(name, health, protection, baseDamage);
        this.skillManager = skillManager;
        this.character = character;
    }

    @Override
    public String toString() {
        return ConsoleFormatter.deregex("/B" + name + "/0 - <hp:/r" + health + "/0> <prot:/b" + protection + "/0> <bdmg:/y" + baseDamage + "/0> <lvl:/g" + skillManager.getHeroLevel() + "/0>");
    }
}