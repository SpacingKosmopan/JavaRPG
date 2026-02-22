package Objects;

public abstract class Entity {
    protected int health;
    public int maxHealth;
    public int protection; // aka damage resistance
    protected int baseDamage;
    public String name;

    public Entity(String name, int health, int protection, int baseDamage) {
        this.maxHealth = health;
        this.health = health;
        this.protection = protection;
        this.baseDamage = baseDamage;
        this.name = name;
    }

    public Entity() {
        this.maxHealth = 0;
        this.health = 0;
        this.protection = 0;
        this.baseDamage = 0;
        this.name = "";
    }

    public void DealDamage(int amount) {
        this.health = Math.max(0, this.health - amount);
    }

    public int getHealth() {
        return this.health;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public int CalculateDamageAfterProtection(int rawDamage) {
        double reductionMultiplier = 1 - (protection / 100.0);
        int reduced = (int) Math.floor(rawDamage * reductionMultiplier);
        return Math.max(1, reduced);
    }

    public void setHealth(int health) {
        this.health = Math.abs(health);
    }

    public void Heal(int amount) {
        this.health = Math.min(this.maxHealth, this.health + amount);
    }

    public void Reborn() {
        this.health = this.maxHealth;
    }

    @Override
    public abstract String toString();

    public int getProtection() {
        return protection;
    }
}