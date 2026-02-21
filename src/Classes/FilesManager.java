package Classes;

import Objects.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FilesManager {
    public enum Files {
        PlayerCharacterData,
        MainMap,
        MainMapDescription
    }

    public static Map<Files, String> FILES = Map.of(
            Files.PlayerCharacterData, "Files/player_character_data.txt",
            Files.MainMap, "Files/Map.txt",
            Files.MainMapDescription, "Files/MapDesc.txt"
    );

    public static String[] ReadFileContent(Files file) {
        if (!FILES.containsKey(file)) {
            throw new IllegalArgumentException("<Unrecognized File>");
        }
        List<String> content = new java.util.ArrayList<>();
        String path = "src/" + FILES.get(file);

        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                content.add(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toArray(new String[0]);
    }

    public static void SaveContentToFile(List<String> content, Files file) {
        if (!FILES.containsKey(file)) {
            throw new IllegalArgumentException("<Unrecognized File>");
        }

        String path = "src/" + FILES.get(file);

        try (FileWriter fw = new FileWriter(path)) {
            for (String line : content) {
                fw.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void AppendLineToFile(String line, Files file) {
        if (!FILES.containsKey(file)) {
            throw new IllegalArgumentException("<Unrecognized File>");
        }

        String path = "src/" + FILES.get(file);

        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(line + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    Marek,100,10,5       <- Player: name, health, protection, baseDamage
    0,0,0.0              <- SkillLevelManager: heroLevel, skillPoints, experience
    Mage,Male            <- CharacterCreator.Character: heroClass, heroGender
    */
    public static void SavePlayer(Player player) {
        List<String> lines = new ArrayList<>();

        // Entity
        lines.add(player.name + "," + player.getHealth() + "," + player.protection + "," + player.getBaseDamage());

        // SkillLevelManager
        SkillLevelManager skillManager = player.skillManager != null ? player.skillManager : new SkillLevelManager();
        lines.add(skillManager.getHeroLevel() + "," + skillManager.getSkillPoints() + "," + skillManager.getExperience());

        // Character
        CharacterCreator.Character c = player.character != null ? player.character : new CharacterCreator.Character();
        lines.add(
                (c.heroClass != null ? c.heroClass : CharacterCreator.Class.Knight) + "," +
                        (c.heroGender != null ? c.heroGender : CharacterCreator.Gender.Male)
        );

        SaveContentToFile(lines, Files.PlayerCharacterData);
    }

    public static Player LoadPlayer() {
        String[] lines = ReadFileContent(Files.PlayerCharacterData);
        if (lines.length < 3) return null; // brak danych

        // Entity
        String[] entityParts = lines[0].split(",");
        String name = entityParts[0];
        int health = Integer.parseInt(entityParts[1]);
        int protection = Integer.parseInt(entityParts[2]);
        int baseDamage = Integer.parseInt(entityParts[3]);

        // SkillLevelManager
        String[] skillParts = lines[1].split(",");
        SkillLevelManager skillManager = new SkillLevelManager();
        skillManager.setHeroLevel(Integer.parseInt(skillParts[0]));
        skillManager.setSkillPoints(Integer.parseInt(skillParts[1]));
        skillManager.setExperience(Float.parseFloat(skillParts[2]));

        // Character
        String[] charParts = lines[2].split(",");
        CharacterCreator.Character character = new CharacterCreator.Character(
                CharacterCreator.Class.valueOf(charParts[0]),
                CharacterCreator.Gender.valueOf(charParts[1])
        );

        return new Player(name, health, protection, baseDamage, skillManager, character);
    }
}