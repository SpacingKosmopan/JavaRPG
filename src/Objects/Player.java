package Objects;

import Classes.CharacterCreator;
import Classes.ConsoleFormatter;
import Classes.Labels;
import Classes.SkillLevelManager;

import java.util.Scanner;

public final class Player extends Entity {
    public SkillLevelManager skillManager;
    public CharacterCreator.Character character;

    public Player(String name, int health, int protection, int baseDamage) {
        super(name, health, protection, baseDamage);
        this.skillManager = new SkillLevelManager();
        this.character = new CharacterCreator.Character();
    }

    public Player(String name, int health, int protection, int baseDamage, SkillLevelManager skillManager, CharacterCreator.Character character) {
        super(name, health, protection, baseDamage);
        this.skillManager = skillManager;
        this.character = character;
    }

    public Player() {
        super();
        this.skillManager = null;
        this.character = null;
    }

    @Override
    public String toString() {
        return ConsoleFormatter.deregex("/B" + name + "/0 - <hp:/r" + health + "/0> <prot:/b" + protection + "/0> <bdmg:/y" + baseDamage + "/0> <lvl:/g" + skillManager.getHeroLevel() + "/0>");
    }

    public static Player PlayerCreation() {
        Labels labelsCreator = new Labels();
        Scanner scanner = new Scanner(System.in);
        Player p = new Player();
        String input = "";
        labelsCreator.printLabel(ConsoleFormatter.deregex("/bTworzenie postaci/0"));

        System.out.println(ConsoleFormatter.deregex("/y(i)/0 Nazwij swoją postać: "));
        input = scanner.nextLine();
        if (input.isEmpty()) {
            System.out.println("Wystąpił problem. Default: Roman");
            p.name = "Roman";
        }

        CharacterCreator.Character c = new CharacterCreator.Character();

        System.out.println(ConsoleFormatter.deregex("/y(i)/0 Wybierz płeć /b(m//k)/0: "));
        input = scanner.nextLine();
        if (input.equals("m")) {
            c.heroGender = CharacterCreator.Gender.Male;
        } else if (input.equals("k")) {
            c.heroGender = CharacterCreator.Gender.Female;
        } else {
            System.out.println("Wystąpił problem. Default: Mężczyzna");
            c.heroGender = CharacterCreator.Gender.Male;
        }

        System.out.println(ConsoleFormatter.deregex("/y(i)/0 Wybierz klasę: /b\n" +
                "\t(r) Rycerz: /rmało życia/b, /gdużo obrony/b i /gdużo obrażeń/b, /rwolno zbiera punkty doświadczenia/b\n" +
                "\t(l) Łucznik: /rmało życia/b, /rmało obrony/b i /gdużo obrażeń/b, /gszybko zbiera punkty doświadczenia/b\n" +
                "\t(m) Mag: /gdużo życia/b, /rmało obrony/b i /rmało obrażeń/b, /rwolno zbiera punkty doświadczenia/b, /gposiada dodatkowe umiejętności/b\n" +
                "\t(domyślnie) Rycerz" +
                "/0"));
        input = scanner.nextLine();
        c.heroClass = switch (input) {
            case "r" -> CharacterCreator.Class.Knight;
            case "l" -> CharacterCreator.Class.Archer;
            case "m" -> CharacterCreator.Class.Mage;
            default -> CharacterCreator.Class.Knight;
        };

        return p;
    }
}