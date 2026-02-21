package Classes;

import java.io.File;
import java.util.List;
import java.util.Scanner;

/*
┌────┐
│    │
└────┘
█ ▓ ▒ ░
*/

public class PlayerMovement {
    private int playerXPosition;
    private int playerYPosition;

    public void setPlayerPosition(int playerXPosition, int playerYPosition) {
        this.playerXPosition = playerXPosition;
        this.playerYPosition = playerYPosition;
    }

    public static String[] getMap() {
        List<String> map = new java.util.ArrayList<>(List.of());
        try {
            File mapFile = new File("src/map.txt");
            Scanner fileScanner = new Scanner(mapFile);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                map.add(line);
            }

            fileScanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map.toArray(new String[map.size()]);
    }

    // max width 100 chars
    public static void printMap() {
        String[] map = getMap();
        Labels labelCreator = new Labels();
        System.out.println("\n");
        System.out.println(labelCreator.getLabel(Labels.Align.LEFT, 0, 0, 3, 3, '+', '─', '│', map));
    }
}