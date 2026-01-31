package Classes;

public class Labels {
    public boolean printLabel(String text, String align, int fixedWidth, int fixedHeight, int padding, char corner, char horizontalSide, char verticalSide) {
        System.out.println(getLabel(text, align, fixedWidth, fixedHeight, padding, corner, horizontalSide, verticalSide));
        return true;
    }

    public boolean printLabel(String text) {
        return printLabel(text, "center", text.length() + 2, 1,
                0, '+', '-', '|');
    }

    public boolean printLabel(String text, String align, int fixedWidth, int fixedHeight, int padding) {
        return printLabel(text, align, fixedWidth, fixedHeight, padding, '+', '-', '|');
    }

    public boolean printLabel(String text, String align, int fixedWidth, int fixedHeight) {
        return printLabel(text, align, fixedWidth, fixedHeight, 1, '+', '-', '|');
    }

    public String getLabel(String text, String align, int fixedWidth, int fixedHeight, int padding, char corner, char horizontalSide, char verticalSide) {
        if ((!text.isEmpty() && (fixedWidth < text.length() + padding || fixedHeight <= 0))
                || (text.isEmpty() && (fixedWidth < 0 || fixedHeight < 0))) {
            return "";
        }
        if (!align.equals("right") && !align.equals("center") && !align.equals("left"))
            return "";
        if (corner == ' ' || verticalSide == ' ' || horizontalSide == ' ') return "";

        String output = "";

        output += corner + ConsoleFormatter.getChars(horizontalSide, fixedWidth) + corner + "\n";
        for (int i = 0; i < (int) Math.floor(fixedHeight - 1) / 2; i++) {
            output += verticalSide + ConsoleFormatter.getSpace(fixedWidth) + verticalSide + "\n";
        }

        output += verticalSide;
        output += ConsoleFormatter.getSpace(align.equals("left")
                ? padding
                : align.equals("right")
                ? fixedWidth - text.length() - padding
                : (int) Math.floor((fixedWidth - text.length()) / 2.0) + padding);
        output += text;
        output += ConsoleFormatter.getSpace(align.equals("left")
                ? fixedWidth - text.length() - padding
                : align.equals("right")
                ? padding
                : (int) Math.ceil((fixedWidth - text.length()) / 2.0) + padding);
        output += verticalSide + "\n";

        for (int i = 0; i < (int) Math.ceil(fixedHeight) / 2; i++) {
            output += verticalSide + ConsoleFormatter.getSpace(fixedWidth) + verticalSide + "\n";
        }

        output += corner + ConsoleFormatter.getChars(horizontalSide, fixedWidth) + corner;

        return output;
    }

    private int getLongestString(String... strings) {
        int max = 0;
        for (String string : strings) {
            if (string.length() > max) {
                max = string.length();
            }
        }
        return max;
    }

    public String getLabel(String align, int extraHeight, int padding, char corner, char horizontalSide, char verticalSide, String... multitext) {
        extraHeight++;
        int maxWidth = getLongestString(multitext);
        if (maxWidth < 0 || extraHeight < 0)
            return "";

        if (!align.equals("right") && !align.equals("center") && !align.equals("left"))
            return "";
        if (corner == ' ' || verticalSide == ' ' || horizontalSide == ' ') return "";

        String output = "";

        output += corner + ConsoleFormatter.getChars(horizontalSide, maxWidth + (align.equals("center") ? 2 * padding : maxWidth-padding)) + corner + "\n";

        for (int i = 0; i < (int) Math.floor(extraHeight - 1) / 2; i++) {
            output += verticalSide + ConsoleFormatter.getSpace(maxWidth) + verticalSide + "\n";
        }

        for (String line : multitext) {
            output += verticalSide;
            output += ConsoleFormatter.getSpace(align.equals("left")
                    ? padding
                    : align.equals("right")
                    ? maxWidth - line.length() - padding
                    : (int) Math.floor((maxWidth - line.length()) / 2.0) + padding);
            output += line;
            output += ConsoleFormatter.getSpace(align.equals("left")
                    ? maxWidth - line.length() - padding
                    : align.equals("right")
                    ? padding
                    : (int) Math.ceil((maxWidth - line.length()) / 2.0) + padding);
            output += verticalSide + "\n";
        }

        for (int i = 0; i < (int) Math.ceil(extraHeight) / 2; i++) {
            output += verticalSide + ConsoleFormatter.getSpace(maxWidth) + verticalSide + "\n";
        }

        output += corner + ConsoleFormatter.getChars(horizontalSide, maxWidth + (align.equals("center") ? 2 * padding : padding)) + corner;

        return output;
    }
}