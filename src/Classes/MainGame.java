package Classes;

import java.util.Scanner;

public class MainGame {
    public void GameLoop(Scanner sc) throws InterruptedException {
        Thread.sleep(1000);
        Labels labelsCreator = new Labels();
        System.out.println(labelsCreator.getLabel(
                Labels.Align.CENTER, 0, 0, 5, 5, '+', '-', '|',
                "1. Mapa", "2. Ekwipunek", "3. Misje", "4. Wyjdź"));
        String input = "";

        while (true) {
            input = sc.nextLine();
            if (input.equals("1")) {
                PlayerMovement.printMap();
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
            System.out.print("Enter your choice: ");
            input = sc.nextLine();
            if (input.equals("1")) {
                break; // the while(true)
                // CHARACTER
                // you haven't created character yet
                // you have created character - do you wish to continue or create a new one?
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

    public static void WaitForEnter(Scanner sc) {
        System.out.println("\tNaciśnij ENTER, aby przejść dalej");
        sc.nextLine();
    }
}