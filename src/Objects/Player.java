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

    public static Player PlayerCreation() throws InterruptedException {
        Labels labelsCreator = new Labels();
        Scanner scanner = new Scanner(System.in);
        Player p = new Player();
        String input = "";
        labelsCreator.printLabel(ConsoleFormatter.deregex("/bTworzenie postaci/0"));
        Thread.sleep(2000);

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
                "/0Dzierży potężny miecz i epicką zbroję, które umożliwiają mu łatwe zadawanie obrażeń i wykańczanie przeciwników.\nWyszkolony w środku kontynentu, gdzie wiele skłóconych królestw toczyło boje, a rycerze do ostatniej kropli krwi bronili swojego kawałka.\n/b" +
                "\t(l) Łucznik: /rmało życia/b, /rmało obrony/b i /gdużo obrażeń/b, /gszybko zbiera punkty doświadczenia/b\n" +
                "/0Może i nie jest najsilniejszy, ale za to dobrze wytrenowany. Szkolony na północy, gdzie uczył się od podstaw jak składać łuk,\npo zaawansowane techniki, które umożliwiaja mu precyzyjne zadawanie krytycznych ciosów.\n/b" +
                "\t(m) Mag: /gdużo życia/b, /rmało obrony/b i /rmało obrażeń/b, /rwolno zbiera punkty doświadczenia/b, /gposiada dodatkowe umiejętności/b\n" +
                "/0Tylko na pierwszy rzut oka wygląda na przeciętnego przeciwnika, a tak na prawdę ma przysobie najpotężniejsze bronie, jakich świat jeszcze nie zna.\nRazem z magią wychowywał się na południu, pośród drzew i licznych roślin. Poznał naturę na wylot i teraz wie, jak ją wykorzystać.\n/b" +
                "\t(domyślnie) Rycerz" +
                "/0"));
        input = scanner.nextLine();
        c.heroClass = switch (input) {
            case "r" -> CharacterCreator.Class.Knight;
            case "l" -> CharacterCreator.Class.Archer;
            case "m" -> CharacterCreator.Class.Mage;
            default -> CharacterCreator.Class.Knight;
        };

        switch (input) {
            case "l" -> {
                System.out.println(ConsoleFormatter.deregex("/0Poczułeś lekkość i zwinność, teraz możesz łatwo poruszać się po terenie i zwinnie strzelać z łuku.\nRękojeść idealnie dopasowała się do linii twoich dłoni, a nić przestała uwierać ci w palce."));
            }
            case "m" -> {
                System.out.println(ConsoleFormatter.deregex("/0Poczułeś, jak twoja głowa urosła do niewyobrażalnych rozmiarów, znasz teraz wiele tajemnic i sekretów,\nktóre możesz obrócić przeciwko każdemu."));
            }
            default -> {
                System.out.println(ConsoleFormatter.deregex("/0Poczułeś, jak przybywa ci mięśni i rośnie twoja siła. Miecz w twojej ręce stał się znacznie lżejszy,\ndzięki czemu łatwiej ci będzie go wbijać w serca, a tarcza jakby sama uniosła się, by cię chronić."));
            }
        }

        return p;
    }
}