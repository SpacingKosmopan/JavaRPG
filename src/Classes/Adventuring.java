package Classes;

import Objects.Enemy;
import Objects.Player;

import java.util.Random;
import java.util.Scanner;

public class Adventuring {
    private java.util.Random random = new java.util.Random();
    private int dungeonDepth = 1;

    public void Adventure(Scanner sc, Player player) {

        dungeonDepth = 1;

        System.out.println(ConsoleFormatter.deregex("/yWchodzisz do lochu.../0"));

        while (player.getHealth() > 0) {

            System.out.println("\n===============================");
            System.out.println("Głębokość: " + dungeonDepth);
            System.out.println(player);

            boolean stayInDungeon = GenerateDungeonRoom(player, sc);
            if (!stayInDungeon) return; // wychodzimy do mapy

            if (player.getHealth() <= 0) {
                System.out.println(ConsoleFormatter.deregex("/rZginąłeś w lochu.../0"));
                dungeonDepth = 1;
                return;
            }
        }
    }

    public boolean GenerateDungeonRoom(Player player, Scanner sc) {

        System.out.println("""
                1. Idź głębiej (wysokie ryzyko, lepsze nagrody)
                2. Przeszukaj pomieszczenie (umiarkowane ryzyko)
                3. Odpocznij (leczenie, możliwa zasadzka)
                4. Ucieknij z lochu
                """);

        String input = sc.nextLine();

        switch (input) {

            // ===== IŚCIE GŁĘBIEJ =====
            case "1" -> {

                int roll = random.nextInt(100);

                if (roll < 65) { // 65% wróg
                    System.out.println(ConsoleFormatter.deregex("/rZ ciemności wyłania się przeciwnik!/0"));
                    Enemy enemy = GenerateScaledEnemy();
                    StartFight(player, enemy, sc);

                } else if (roll < 85) { // 20% pułapka
                    int trapDamage = dungeonDepth * 5 + random.nextInt(6);
                    trapDamage = ApplyDefenseReduction(player, trapDamage);

                    System.out.println("Wpadasz w pułapkę! Otrzymujesz " + trapDamage + " obrażeń.");
                    player.DealDamage(trapDamage);

                } else { // 15% nagroda
                    int heal = 15 + dungeonDepth * 3;
                    System.out.println(ConsoleFormatter.deregex("/gZnajdujesz źródło energii!/0 +" + heal + " HP"));
                    player.Heal(heal);
                }

                dungeonDepth++;
            }

            // ===== PRZESZUKIWANIE =====
            case "2" -> {

                int roll = random.nextInt(100);

                if (roll < 50) {
                    System.out.println("Nic nie znalazłeś.");

                } else if (roll < 80) {
                    int heal = 10 + dungeonDepth;
                    player.Heal(heal);
                    System.out.println("Znalazłeś zioła. +" + heal + " HP");

                } else {
                    int damage = 8 + dungeonDepth;
                    damage = ApplyDefenseReduction(player, damage);

                    System.out.println("Ukryta pułapka! -" + damage + " HP");
                    player.DealDamage(damage);
                }
            }

            // ===== ODPOCZYNEK =====
            case "3" -> {

                System.out.println("Próbujesz odpocząć...");

                if (random.nextInt(100) < 40) {
                    System.out.println(ConsoleFormatter.deregex("/rZasadzka!/0"));
                    Enemy ambush = GenerateScaledEnemy();
                    ambush.setBaseDamage(ambush.getBaseDamage() + 3);
                    StartFight(player, ambush, sc);
                } else {
                    int heal = 20;
                    player.Heal(heal);
                    System.out.println("Odpoczynek udany. +" + heal + " HP");
                }
            }

            // ===== UCIECZKA =====
            case "4" -> {
                System.out.println(ConsoleFormatter.deregex(
                        "/yOpuszczasz loch z głębokości " + dungeonDepth + "./0"));
                dungeonDepth = 1;
                player.Heal((int) (player.maxHealth * 0.3));
                return false;
            }

            default -> System.out.println("Niezdecydowanie kosztuje cię turę...");
        }
        return true;
    }

    private int ApplyDefenseReduction(Player player, int damage) {

        int defensePercent = player.getProtection(); // np. 10 = 10%

        int reduced = damage - (damage * defensePercent / 100);

        return Math.max(1, reduced);
    }

    private Enemy GenerateScaledEnemy() {

        int hp = 30 + dungeonDepth * 5;
        int protection = 3 + dungeonDepth;
        int damage = (int) (6 + dungeonDepth * 1.3);

        return new Enemy("Bestia Głębin", hp, protection, damage);
    }

    public void StartFight(Player player, Enemy enemy, Scanner sc) {

        int potionsLeft = 2;
        Random random = new Random();

        System.out.println(ConsoleFormatter.deregex("/rRozpoczyna się walka!/0"));

        while (player.getHealth() > 0 && enemy.getHealth() > 0) {

            System.out.println("\n---------------------");
            System.out.println(player);
            System.out.println(enemy);

            System.out.println("""
                    1. Atak
                    2. Obrona (+20% redukcji na tę turę)
                    3. Silny atak (200% dmg, 40% szansy na kontrę)
                    4. Leczenie (""" + potionsLeft + ")");

            String input = sc.nextLine();

            boolean defending = false;

            // ===== TURA GRACZA =====
            switch (input) {

                case "1" -> {
                    int dmg = CalculateDamageAfterProtection(
                            player.getBaseDamage(),
                            enemy.getProtection()
                    );
                    enemy.DealDamage(dmg);
                    System.out.println("Zadajesz " + dmg + " obrażeń.");
                }

                case "2" -> {
                    defending = true;
                    System.out.println("Przygotowujesz się na atak.");
                }

                case "3" -> {
                    int base = (int) (player.getBaseDamage() * 2);
                    int dmg = CalculateDamageAfterProtection(base, enemy.getProtection());
                    enemy.DealDamage(dmg);

                    System.out.println("Zadajesz SILNY cios za " + dmg + " obrażeń!");

                    if (random.nextInt(100) < 40 && enemy.getHealth() > 0) {
                        int counter = CalculateDamageAfterProtection(
                                enemy.getBaseDamage(),
                                player.getProtection()
                        );
                        player.DealDamage(counter);
                        System.out.println("Przeciwnik kontruje! Otrzymujesz " + counter + " obrażeń.");
                    }
                }

                case "4" -> {
                    if (potionsLeft > 0) {
                        int heal = (int) (player.maxHealth * 0.25);
                        player.Heal(heal);
                        potionsLeft--;
                        System.out.println("Leczysz się za " + heal + " HP.");
                    } else {
                        System.out.println("Nie masz mikstur!");
                    }
                }

                default -> System.out.println("Tracisz turę przez niezdecydowanie...");
            }

            if (enemy.getHealth() <= 0)
                break;

            // ===== TURA PRZECIWNIKA =====
            int defenseBonus = defending ? 20 : 0;

            int totalDefense = player.getProtection() + defenseBonus;

            int enemyDamage = CalculateDamageAfterProtection(
                    enemy.getBaseDamage(),
                    totalDefense
            );

            player.DealDamage(enemyDamage);
            System.out.println("Przeciwnik zadaje " + enemyDamage + " obrażeń.");
        }

        // ===== KONIEC WALKI =====
        if (player.getHealth() > 0) {
            System.out.println(ConsoleFormatter.deregex("/gPokonałeś przeciwnika!/0"));
        } else {
            System.out.println(ConsoleFormatter.deregex("/rZostałeś pokonany.../0"));
        }
        FilesManager.SavePlayer(player);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            //
        }
    }

    private int CalculateDamageAfterProtection(int damage, int protectionPercent) {

        int reduced = damage - (damage * protectionPercent / 100);

        return Math.max(1, reduced);
    }
}