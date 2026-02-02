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

    public String getLabel(String text, Align align, int minWidth, int fixedHeight, int padding, char corner, char horizontalSide, char verticalSide) {
        StringBuilder sb = new StringBuilder();

        if ((!text.isEmpty() && fixedHeight <= 0)
                || (text.isEmpty() && fixedHeight < 0))
            return "";

        int stringLength = text.length();

        sb.append(corner);
        int borderLength = Math.max(minWidth, stringLength + (align.equals(Align.CENTER) ? 2 * padding : padding));
        sb.append(ConsoleFormatter.getChars(horizontalSide, borderLength));
        sb.append(corner);
        sb.append("\n");
        for (int i = 0; i < (int) Math.floor((double) (fixedHeight - 1) / 2); i++) {
            sb.append(verticalSide);
            sb.append(ConsoleFormatter.getSpace(minWidth));
            sb.append(verticalSide);
            sb.append("\n");
        }

        sb.append(verticalSide);
        sb.append(ConsoleFormatter.getSpace(align.equals(Align.RIGHT)
                ? padding
                : align.equals(Align.LEFT)
                ? minWidth - text.length() - padding
                : (int) Math.floor((minWidth - text.length()) / 2.0) + padding));
        sb.append(text);
        sb.append(ConsoleFormatter.getSpace(align.equals(Align.RIGHT)
                ? minWidth - text.length() - padding
                : align.equals(Align.LEFT)
                ? padding
                : (int) Math.ceil((minWidth - text.length()) / 2.0) + padding));
        sb.append(verticalSide);
        sb.append("\n");

        for (int i = 0; i < (int) Math.ceil((double) (fixedHeight) / 2); i++) {
            sb.append(verticalSide);
            sb.append(ConsoleFormatter.getSpace(minWidth));
            sb.append(verticalSide);
            sb.append("\n");
        }

        sb.append(corner);
        sb.append(ConsoleFormatter.getChars(horizontalSide, borderLength));
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

    public String getLabel(Align align, int extraHeight, int leftPadding, int rightPadding, char corner, char horizontalSide, char verticalSide, String... multitext) {
        StringBuilder sb = new StringBuilder();
        int longestStringLength = getLongestString(multitext);
        if (longestStringLength < 0 || extraHeight < 0)
            return "";

        // top +-+
        sb.append(corner);
        // if align to left, then 1 padding on the left and padding on the right
        // if align to center, then 2*padding
        int borderLength = longestStringLength + leftPadding + rightPadding;
        sb.append(ConsoleFormatter.getChars(horizontalSide, borderLength));
        sb.append(corner);
        sb.append("\n");

        for (int i = 0; i < (int) Math.floor((double) (extraHeight - 1) / 2); i++) {
            sb.append(verticalSide);
            sb.append(ConsoleFormatter.getSpace(borderLength));
            sb.append(verticalSide);
            sb.append("\n");
        }

        for (String line : multitext) {
            // left | and space
            sb.append(verticalSide);
            sb.append(ConsoleFormatter.getSpace(align.equals(Align.LEFT)
                    ? leftPadding
                    : align.equals(Align.RIGHT)
                    ?longestStringLength - line.length() + leftPadding
                    : (int) Math.floor((double) (longestStringLength - line.length()) / 2) + leftPadding));

            // text
            sb.append(line);

            // right space and |
            sb.append(ConsoleFormatter.getSpace(align.equals(Align.RIGHT)
                    ? rightPadding
                    : align.equals(Align.LEFT)
                    ?longestStringLength - line.length() + rightPadding
                    : (int) Math.ceil((double) (longestStringLength - line.length()) / 2) + rightPadding));
            sb.append(verticalSide);
            sb.append("\n");
        }

        for (int i = 0; i < (int) Math.ceil((double) extraHeight / 2); i++) {
            sb.append(verticalSide);
            sb.append(ConsoleFormatter.getSpace(borderLength));
            sb.append(verticalSide);
            sb.append("\n");
        }

        // bottom +-+
        sb.append(corner);
        sb.append(ConsoleFormatter.getChars(horizontalSide, borderLength));
        sb.append(corner);

        return sb.toString();
    }
}