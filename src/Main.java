import Classes.ConsoleFormatter;
import Classes.FilesManager;
import Classes.Labels;
import Classes.PlayerMovement;
import Objects.Player;

//   C:\Users\Komputer\IdeaProjects\JavaRPG

void main() {
    // initialize game
    System.out.println("Initializing...");
    Scanner sc = new Scanner(System.in);
    Player p = null;
   try {
       MainMenu(sc);
       p = Player.PlayerCreation(sc);
       FilesManager.SavePlayer(p);
   }
   catch (Exception e) {
       e.printStackTrace();
   }

    WaitForEnter(sc);
    System.out.println(ConsoleFormatter.deregex("/y(i)/0 Twoja postać jest gotowa. Możesz teraz wyruszać w świat. Miażdż przeciwników, podbijaj twierdze, odkrywaj zaklęte przedmioty.\nPamiętaj, twoim zadaniem jest dostanie się do Smoka Gewuncha, zabicie go i uwolnienie mieszkańców królestwa od strachu i koszmarów."));
    WaitForEnter(sc);

    try {
        GameLoop(sc);
    }
    catch (Exception e) {
        e.printStackTrace();
    }

    /*String formatted1 = ConsoleFormatter.create().red().text("This is red").reset().text(" | and this is not").toString();
    System.out.println(formatted1);
    String formatted2 = RED + "This is red" + RESET + " | and this is not" + RESET;
    System.out.println(formatted2);

     System.out.println(labelsCreator.getLabel(Labels.Align.LEFT, 0, 0, 2, 5, '+', '-', '|', "CHARACTERS", "1. Create", "2. Change", "3. Edit", "4. Delete", "5. Go back"));
    for (int i = 1; i < 180; i++) {
        System.out.print(i % 10 == 0 ? "." : i % 10);
    }

    System.out.println(deregex("\nthis will be red: /rit is red/0 and now not\n/gkolorowe kredki/0 i /nblack background/0"));
    try {
        for (int i = 0; i <= 100; i++) {
            System.out.print("\rWczytywanie: " + i + "%");
            Thread.sleep(30);
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    PlayerMovement.printMap();*/
}

public static void WaitForEnter(Scanner sc) {
    System.out.println("\tNaciśnij ENTER, aby przejść dalej");
    sc.nextLine();
}

void MainMenu(Scanner sc) throws InterruptedException {
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

void GameLoop(Scanner sc) throws InterruptedException {
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