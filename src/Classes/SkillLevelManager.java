package Classes;

import java.util.List;

public class SkillLevelManager {
    // if hero wants to unlock a new ability, he must earn skill point first
    // hero can earn skill points by slaying enemies, doing quests or level upping
    // when hero has a skill point, he can start learning a new ability
    // at the beginning the ability will deal much less damage and will be much harder to cast (low success rate)
    // hero can increase success rate by training in training area
    // the higher hero level is, the easier it is for them to improve ability
    // some abilities require battle energy to be performed during battle

    private int heroLevel = 0;
    private int skillPoints = 0;
    private float experience = 0;

    public void increaseExperience(float amount) {
        this.experience += Math.abs(amount);
    }

    public int getHeroLevel() {
        return this.heroLevel;
    }

    public static final List<Integer> EXPERIENCE_REQUIRED = List.of(10, 20, 30, 40, 50,
            70, 90, 110, 130, 150,
            180, 210, 240, 270, 300,
            350, 400, 450, 500,
            600, 700, 800, 900, 1000,
            1250, 1500, 1750, 2000,
            2500, 3000, 3500, 4000, 4500, 5000,
            6000, 7000, 8000, 9000, 10000,
            15000, 20000, 25000, 30000, 35000, 40000, 45000, 50000,
            60000, 70000, 80000, 90000, 100000,
            200000, 300000, 400000, 500000, 1000000);
}