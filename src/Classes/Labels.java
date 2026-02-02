package Classes;

public class Labels {
    public enum Align {LEFT, CENTER, RIGHT}

    ;

    public boolean printLabel(String text, Align align, int fixedWidth, int fixedHeight, int padding, char corner, char horizontalSide, char verticalSide) {
        System.out.println(getLabel(text, align, fixedWidth, fixedHeight, padding, corner, horizontalSide, verticalSide));
        return true;
    }

    public boolean printLabel(String text) {
        return printLabel(text, Align.CENTER, text.length() + 2, 1,
                0, '+', '-', '|');
    }

    public boolean printLabel(String text, Align align, int fixedWidth, int fixedHeight, int padding) {
        return printLabel(text, align, fixedWidth, fixedHeight, padding, '+', '-', '|');
    }

    public boolean printLabel(String text, Align align, int fixedWidth, int fixedHeight) {
        return printLabel(text, align, fixedWidth, fixedHeight, 1, '+', '-', '|');
    }

    public String getLabel(String text, Align align, int fixedWidth, int fixedHeight, int padding, char corner, char horizontalSide, char verticalSide) {
        StringBuilder sb = new StringBuilder();

        if ((!text.isEmpty() && (fixedWidth < text.length() + padding || fixedHeight <= 0))
                || (text.isEmpty() && (fixedWidth < 0 || fixedHeight < 0))) {
            return "";
        }
        if (corner == ' ' || verticalSide == ' ' || horizontalSide == ' ') return "";

        sb.append(corner);
        sb.append(ConsoleFormatter.getChars(horizontalSide, fixedWidth));
        sb.append(corner);
        sb.append("\n");
        for (int i = 0; i < (int) Math.floor((double) (fixedHeight - 1) / 2); i++) {
            sb.append(verticalSide);
            sb.append(ConsoleFormatter.getSpace(fixedWidth));
            sb.append(verticalSide);
            sb.append("\n");
        }

        sb.append(verticalSide);
        sb.append(ConsoleFormatter.getSpace(align.equals(Align.LEFT)
                ? padding
                : align.equals(Align.RIGHT)
                ? fixedWidth - text.length() - padding
                : (int) Math.floor((fixedWidth - text.length()) / 2.0) + padding));
        sb.append(text);
        sb.append(ConsoleFormatter.getSpace(align.equals(Align.LEFT)
                ? fixedWidth - text.length() - padding
                : align.equals(Align.RIGHT)
                ? padding
                : (int) Math.ceil((fixedWidth - text.length()) / 2.0) + padding));
        sb.append(verticalSide);
        sb.append("\n");

        for (int i = 0; i < (int) Math.ceil((double) (fixedHeight) / 2); i++) {
            sb.append(verticalSide);
            sb.append(ConsoleFormatter.getSpace(fixedWidth));
            sb.append(verticalSide);
            sb.append("\n");
        }

        sb.append(corner);
        sb.append(ConsoleFormatter.getChars(horizontalSide, fixedWidth));
        sb.append(corner);

        return sb.toString();
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

    public String getLabel(Align align, int extraHeight, int padding, char corner, char horizontalSide, char verticalSide, String... multitext) {
        StringBuilder sb = new StringBuilder();
        extraHeight++;
        int maxWidth = getLongestString(multitext);
        if (maxWidth < 0 || extraHeight < 0)
            return "";

        if (corner == ' ' || verticalSide == ' ' || horizontalSide == ' ') return "";

        // top +-+
        sb.append(corner);
        sb.append(ConsoleFormatter.getChars(horizontalSide, maxWidth + (align.equals(Align.CENTER) ? 2 * padding : maxWidth - padding)));
        sb.append(corner);
        sb.append("\n");

        for (int i = 0; i < (int) Math.floor((double) (extraHeight - 1) / 2); i++) {
            sb.append(verticalSide);
            sb.append(ConsoleFormatter.getSpace(maxWidth));
            sb.append(verticalSide);
            sb.append("\n");
        }

        for (String line : multitext) {
            // left | and space
            sb.append(verticalSide);
            sb.append(ConsoleFormatter.getSpace(align.equals(Align.LEFT)
                    ? padding
                    : align.equals(Align.RIGHT)
                    ? maxWidth - line.length() - padding
                    : (int) Math.floor((maxWidth - line.length()) / 2.0) + padding));

            // text
            sb.append(line);

            // right space and |
            sb.append(ConsoleFormatter.getSpace(align.equals(Align.LEFT)
                    ? maxWidth - line.length() + padding
                    : align.equals(Align.RIGHT)
                    ? padding
                    : (int) Math.ceil((maxWidth - line.length()) / 2.0) + padding));
            sb.append(verticalSide);
            sb.append("\n");
        }

        for (int i = 0; i < (int) Math.ceil((double) extraHeight / 2); i++) {
            sb.append(verticalSide);
            sb.append(ConsoleFormatter.getSpace(maxWidth));
            sb.append(verticalSide);
            sb.append("\n");
        }

        // bottom +-+
        sb.append(corner);
        sb.append(ConsoleFormatter.getChars(horizontalSide, maxWidth + (align.equals(Align.CENTER) ? 2 * padding : padding)));
        sb.append(corner);

        return sb.toString();
    }
}