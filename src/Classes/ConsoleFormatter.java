package Classes;

import java.util.Map;

public class ConsoleFormatter {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";

    public static final String RED_BCKG = "\u001B[41m";
    public static final String BLACK_BCKG = "\u001B[40m";

    public static final String BOLD = "\u001B[1m";

    public static void flush() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static final Map<Character, String> TOKENS=Map.of(
            '/', "/",
            'r', RED,
            'g', GREEN,
            'b', BLUE,
            'y', YELLOW,
            'f', RED_BCKG,
            'n', BLACK_BCKG,
            '0', RESET,
            'B',BOLD
    );

    public static String deregex(String textToFormat) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < textToFormat.length(); i++) {
            char ch = textToFormat.charAt(i);
            if (ch == '/' && i + 1 < textToFormat.length()) {
                ch = textToFormat.charAt(++i);
                output.append(TOKENS.getOrDefault(ch, ""));
            } else output.append(ch);
        }
        return output.toString();
    }

    String text;

    public ConsoleFormatter() {
        text = "";
    }

    public static ConsoleFormatter create() {
        return new ConsoleFormatter();
    }

    public ConsoleFormatter red() {
        this.text += RED;
        return this;
    }

    public ConsoleFormatter reset() {
        this.text += RESET;
        return this;
    }

    public ConsoleFormatter text(String text) {
        this.text += text;
        return this;
    }

    @Override
    public String toString() {
        return text;
    }

    public static void space(int amount) {
        for (int i = 0; i < amount; i++) {
            System.out.print(" ");
        }
    }

    public static String getSpace(int amount) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < amount; i++) {
            output.append(" ");
        }
        return output.toString();
    }

    public static void chars(char c, int amount) {
        for (int i = 0; i < amount; i++) {
            System.out.print(c);
        }
    }

    public static String getChars(char c, int amount) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < amount; i++) {
            output.append(c);
        }
        return output.toString();
    }
}