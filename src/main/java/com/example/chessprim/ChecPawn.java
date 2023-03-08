package com.example.chessprim;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.chessprim.ChessColor.DARK;
import static com.example.chessprim.ChessColor.LIGHT;

public class ChecPawn extends Figure{

    public ChecPawn(ChessColor color, int[] coord) {
        super();
        this.color = color;
        this.coord = coord;

        if (color == LIGHT) figSym = "\uF070";
        if (color == DARK) figSym = "\uF06F";
    } // > constructor

    @Override
    public void updateCP() {}

    @Override
    public void findAvailable(Button[][] b, ArrayList available, boolean isFirstMove) {
        int x = coord[0];
        int y = coord[1];
        try {
            if (Objects.equals(b[x - 1][y - 1].getText(), "")) {
                if (color == LIGHT)
                    available.add(b[x - 1][y - 1]);
            } else if (colorCheck(b[x - 1][y - 1]) != color
                    && Objects.equals(b[x - 2][y - 2].getText(), "")) {
                available.add(b[x - 2][y - 2]);
                killPoints.add(b[x - 2][y - 2]);
                inDanger.add(b[x - 1][y - 1]);
            }
        } catch (IndexOutOfBoundsException ignored) {}
        try {
            if (Objects.equals(b[x - 1][y + 1].getText(), "")) {
                if (color == LIGHT)
                    available.add(b[x - 1][y + 1]);
            } else if (colorCheck(b[x - 1][y + 1]) != color
                    && Objects.equals(b[x - 2][y + 2].getText(), "")) {
                available.add(b[x - 2][y + 2]);
                killPoints.add(b[x - 2][y + 2]);
                inDanger.add(b[x - 1][y + 1]);
            }
        } catch (IndexOutOfBoundsException ignored) {}
        try {
            if (Objects.equals(b[x + 1][y - 1].getText(), "")) {
                if (color == DARK)
                    available.add(b[x + 1][y - 1]);
            } else if (colorCheck(b[x + 1][y - 1]) != color
                    && Objects.equals(b[x + 2][y - 2].getText(), "")) {
                available.add(b[x + 2][y - 2]);
                killPoints.add(b[x + 2][y - 2]);
                inDanger.add(b[x + 1][y - 1]);
            }
        } catch (IndexOutOfBoundsException ignored) {}
        try {
            if (Objects.equals(b[x + 1][y + 1].getText(), "")) {
                if (color == DARK)
                    available.add(b[x + 1][y + 1]);
            } else if (colorCheck(b[x + 1][y + 1]) != color
                    && Objects.equals(b[x + 2][y + 2].getText(), "")) {
                available.add(b[x + 2][y + 2]);
                killPoints.add(b[x + 2][y + 2]);
                inDanger.add(b[x + 1][y + 1]);
            }
        } catch (IndexOutOfBoundsException ignored) {}

    }
}
