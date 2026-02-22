package Classes;

import java.util.Scanner;
import java.util.Random;
import Objects.Player;
import Objects.Enemy;

public class MainGame {
    public void GameLoop(Scanner sc, Player p) {
        Labels labelsCreator = new Labels();
        String input = "";

        while (true) {
            System.out.println(labelsCreator.getLabel(
                    Labels.Align.CENTER, 0, 0, 5, 5, '+', '-', '|',
                    "1. Mapa", "2. Ekwipunek", "3. Misje", "4. Wyjdź"));
            input = sc.nextLine();
            if (input.equals("1")) {
                Adventuring a = new Adventuring();
                while (true) {
                    String[] map = FilesManager.ReadFileContent(FilesManager.Files.MainMap);
                    for (String s : map) {
                        System.out.println(s);
                    }
                    map = FilesManager.ReadFileContent(FilesManager.Files.MainMapDescription);
                    for (String s : map) {
                        System.out.println(ConsoleFormatter.deregex(s));
                    }
                    input = sc.nextLine();
                    if (input.equals("4")) {
                        a.Adventure(sc, p);
                    } else if (input.equals("5")) {
                        break; // the while(true) loop
                    }
                }
            } else if (input.equals("4")) {
                System.out.println("Quit");
                break;
            }
        }
    }

    public void MainMenu(Scanner sc) throws InterruptedException {
        Labels labelsCreator = new Labels();
        String input = "";

        while (true) {
            Thread.sleep(2000);
            System.out.println(labelsCreator.getLabel(
                    Labels.Align.CENTER, 0, 0, 5, 5, '+', '-', '|',
                    "1. START", "2. Ustawienia", "3. Kredyty", "4. Wyjście"));
            System.out.print("Podaj wybór: ");
            input = sc.nextLine();
            if (input.equals("1")) {
                break; // the while(true)
            } else if (input.equals("2")) {
                System.out.println("Settings are unavailable yet");
            } else if (input.equals("3")) {
                System.out.println("Credits are unavailable yet");
            } else if (input.equals("4")) {
                System.out.println("Quit");
                System.exit(0);
            }
        }
    }

    public void WaitForEnter(Scanner sc) {
        System.out.println("\tNaciśnij ENTER, aby przejść dalej");
        sc.nextLine();
    }
}