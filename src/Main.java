import Classes.ConsoleFormatter;
import Classes.Labels;

//   C:\Users\Komputer\IdeaProjects\JavaRPG

import static Classes.ConsoleFormatter.*;
void main() {
    String formatted1 = ConsoleFormatter.create().red().text("This is red").reset().text(" | and this is not").toString();
    System.out.println(formatted1);
    String formatted2 = RED + "This is red" + RESET + " | and this is not" + RESET;
    System.out.println(formatted2);

    Labels labelsCreator = new Labels();
    System.out.println(labelsCreator.getLabel("center", 0, 5, '+', '-', '|', "START", "Settings", "Credits", "Quit"));
}