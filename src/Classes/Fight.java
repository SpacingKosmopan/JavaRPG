package Classes;

import Objects.Enemy;
import Objects.Player;

public class Fight {
    // static do usunięcia potem
    public static void BeginFight(Player player, Enemy enemy) {

        System.out.printf("Beware, Player! You are about to fight %s!%n", enemy.getHealth());

        int loops = 0;
        do {
            System.out.println(
                    "+" + ConsoleFormatter.getChars('=', 30) + "+" +
                            "\n>\t" + player.toString() +
                            "\n>\t" + enemy.toString() +
                            "\n+" + ConsoleFormatter.getChars('=', 30) + "+" +
                            "\n+\t" + ConsoleFormatter.deregex("/y(1)/0 Regular attack\n+\t/y(2)/0 Heal")
            );
            loops++;
        } while (enemy.getHealth() > 0 && player.getHealth() > 0 && loops < 100);
    }

    static void main() {
        Player p = new Player("John", 100, 10, 10);
        Enemy e = new Enemy("Goblin", 30, 0, 5);
        BeginFight(p, e);
    }
}