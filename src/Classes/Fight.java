package Classes;

public class Fight {
    // static do usunięcia potem
    public static void BeginFight() {
        int enemyHealth = 100;
        int playerHealth = 100;
        String enemyName = null;

        System.out.printf("Beware, Player! You are about to fight %s!%n", enemyName);

        int loops = 0;
        do {
            System.out.println(
                    "+" + ConsoleFormatter.getChars('=', 30) + "+" +
                            "\n>\t" + ConsoleFormatter.deregex("/0Player health: /g" + playerHealth + "/0") +
                            "\n>\t" + ConsoleFormatter.deregex("/0Enemy health: /r" + playerHealth + "/0")
            );
            loops++;
        } while (enemyHealth > 0 && playerHealth > 0 && loops < 100);
    }

    static void main() {
        BeginFight();
    }
}