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
    System.out.println(labelsCreator.getLabel("center", 0, 5, '+', '-', '|', "1. START", "2. Settings", "3. Credits", "4. Quit"));
    System.out.println(labelsCreator.getLabel("left", 0, 5, '+', '-', '|', "CHARACTERS", "1. Create", "2. Change", "3. Edit", "4. Delete", "5. Go back"));
    for(int i =1;i<180;i++){
        System.out.print(i%10==0?".":i%10);
        // tu będzie prombel HAHA!
    }
}