package Classes;

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
        String text = "";
        for (int i = 0; i < amount; i++) {
            text += " ";
        }
        return text;
    }

    public static void chars(char c, int amount) {
        for (int i = 0; i < amount; i++) {
            System.out.print(c);
        }
    }

    public static String getChars(char c, int amount) {
        String text = "";
        for (int i = 0; i < amount; i++) {
            text += c;
        }
        return text;
    }
}