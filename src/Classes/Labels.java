package Classes;

public class Labels {
    public enum Align {LEFT, CENTER, RIGHT}

    public boolean printLabel(String text, Align align, int fixedWidth, int fixedHeight, int padding, char corner, char horizontalSide, char verticalSide) {
        System.out.println(getLabel(text, align, fixedWidth, fixedHeight, padding, corner, horizontalSide, verticalSide));
        return true;
    }

    public boolean printLabel(String text) {
        String textW= text.replaceAll("\\u001B\\[[;\\d]*m", "");
        return printLabel(text, Align.CENTER, textW.length() + 2, 1,
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

        String textW = text.replaceAll("\\u001B\\[[;\\d]*m", "");
        int stringLength = textW.length();

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
                ? minWidth - textW.length() - padding
                : (int) Math.floor((minWidth - textW.length()) / 2.0) + padding));
        sb.append(text);
        sb.append(ConsoleFormatter.getSpace(align.equals(Align.RIGHT)
                ? minWidth - textW.length() - padding
                : align.equals(Align.LEFT)
                ? padding
                : (int) Math.ceil((minWidth - textW.length()) / 2.0) + padding));
        sb.append(verticalSide);
        sb.append("\n");

        for (int i = 0; i < (int) Math.ceil((double) (fixedHeight - 1) / 2); i++) {
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
            String s = string.replaceAll("\\u001B\\[[;\\d]*m", "");
            if (s.length() > max) {
                max = s.length();
            }
        }
        return max;
    }

    public String getLabel(Align align, int minWidth, int extraHeight, int leftPadding, int rightPadding, char corner, char horizontalSide, char verticalSide, String... multitext) {
        StringBuilder sb = new StringBuilder();
        int longestStringLength = getLongestString(multitext);
        if (longestStringLength < 0 || extraHeight < 0)
            return "";

        // top +-+
        sb.append(corner);
        // if align to left, then 1 padding on the left and padding on the right
        // if align to center, then 2*padding
        int borderLength = Math.max(minWidth, longestStringLength + leftPadding + rightPadding);
        sb.append(ConsoleFormatter.getChars(horizontalSide, borderLength));
        sb.append(corner);
        sb.append("\n");

        System.out.println("Drawing: " + borderLength + " of border length and " + longestStringLength + " of longest string");

        for (int i = 0; i < (int) Math.floor((double) (extraHeight - 1) / 2); i++) {
            sb.append(verticalSide);
            sb.append(ConsoleFormatter.getSpace(borderLength));
            sb.append(verticalSide);
            sb.append("\n");
        }

        for (String line : multitext) {
            String lineW = line.replaceAll("\\u001B\\[[;\\d]*m", "");
            // left | and space
            sb.append(verticalSide);
            sb.append(ConsoleFormatter.getSpace(align.equals(Align.LEFT)
                    ? leftPadding
                    : align.equals(Align.RIGHT)
                    ? borderLength - lineW.length() + leftPadding
                    : (int) Math.floor((double) (longestStringLength - lineW.length()) / 2) + leftPadding));

            // text
            sb.append(line);

            // right space and |
            sb.append(ConsoleFormatter.getSpace(align.equals(Align.RIGHT)
                    ? rightPadding
                    : align.equals(Align.LEFT)
                    ? borderLength - lineW.length() + rightPadding
                    : (int) Math.ceil((double) (longestStringLength - lineW.length()) / 2) + rightPadding));
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