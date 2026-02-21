package Classes;

import java.io.File;
import java.util.List;
import java.util.Scanner;

/*
┌────┐
│    │
└────┘
█ ▓ ▒ ░
─ ━ │ ┃ ┌ ┐ └ ┘ ┏ ┓ ┗ ┛ ├ ┤ ┝ ┥ ┠ ┨ ┯ ┷ ┿
╭ ╮ ╰ ╯ ─ │ ┬ ┴ ┼
█ ▓ ▒ ░ ▉ ▊ ▋ ▌ ▍ ▎ ▏
▔ ▕ ▖ ▗ ▘ ▝ ▚ ▞ ▛ ▜ ▙ ▟
← ↑ → ↓ ↖ ↗ ↘ ↙ ⇐ ⇑ ⇒ ⇓ ⇖ ⇗ ⇘ ⇙
★ ☆ ☀ ☁ ☂ ☃ ☄ ☇ ☈ ☉ ☊ ☋ ☌ ☍
♥ ♦ ♣ ♠ ♤ ♧ ♡ ♢ ♤
│ ┃ ┆ ┇ ┊ ┋ ─ ━ ┄ ┅ ┈ ┉ ┌ ┐ └ ┘ ├ ┤ ┬ ┴ ┼
*/

public class PlayerMovement {
    private int playerXPosition;
    private int playerYPosition;

    public void setPlayerPosition(int playerXPosition, int playerYPosition) {
        this.playerXPosition = playerXPosition;
        this.playerYPosition = playerYPosition;
    }
}