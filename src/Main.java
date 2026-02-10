import Classes.ConsoleFormatter;
import Classes.Labels;
import Classes.PlayerMovement;
import Objects.Player;

//   C:\Users\Komputer\IdeaProjects\JavaRPG

import static Classes.ConsoleFormatter.*;
void main() {
    Player p = new Player("Janek", 100, 10, 10);
    System.out.println(p);

    String formatted1 = ConsoleFormatter.create().red().text("This is red").reset().text(" | and this is not").toString();
    System.out.println(formatted1);
    String formatted2 = RED + "This is red" + RESET + " | and this is not" + RESET;
    System.out.println(formatted2);

    Labels labelsCreator = new Labels();
    System.out.println(labelsCreator.getLabel(Labels.Align.CENTER, 0, 0, 5, 5, '+', '-', '|', "1. START", "2. Settings", "3. Credits", "4. Quit"));
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

    PlayerMovement.printMap();
}