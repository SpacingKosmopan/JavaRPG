import Classes.*;
import Objects.Player;

//   C:\Users\Komputer\IdeaProjects\JavaRPG

void main() {
    // initialize game
    System.out.println("Initializing...");
    MainGame game = new MainGame();
    Scanner sc = new Scanner(System.in);
    Player p = null;
    try {
        game.MainMenu(sc);
        p = Player.PlayerCreation(sc);
        FilesManager.SavePlayer(p);
    } catch (Exception e) {
        e.printStackTrace();
    }

    game.WaitForEnter(sc);
    System.out.println(ConsoleFormatter.deregex("/y(i)/0 Twoja postać jest gotowa. Możesz teraz wyruszać w świat. Miażdż przeciwników, podbijaj twierdze, odkrywaj zaklęte przedmioty.\nPamiętaj, twoim zadaniem jest dostanie się do Smoka Gewuncha, zabicie go i uwolnienie mieszkańców królestwa od strachu i koszmarów."));
    game.WaitForEnter(sc);

    try {
        game.GameLoop(sc);
    } catch (Exception e) {
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