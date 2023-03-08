package com.example.chessprim;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.chessprim.ChessColor.*;

public class King extends Figure {

    private final int [] initialCoord = new int[2];
    public King(ChessColor color, int[] coord) {
        super();
        this.color = color;
        this.coord = coord;

        System.arraycopy(coord, 0, initialCoord, 0, 2);

        if (color == LIGHT) figSym = "\uF06B";
        if (color == DARK) figSym = "\uF06C";

        castlingPotential = true;
    } //  constructor

    @Override
    public void updateCP() {
        if (coord[0] != initialCoord[0]) castlingPotential = false;
        if (coord[1] != initialCoord[1]) castlingPotential = false;
        if (!inGame) castlingPotential = false;
    } // > updateCP
    @Override
    public void findAvailable(Button[][] board, ArrayList available, boolean isFirstMove) {
        try {
            fieldCheck(available, board[coord[0] + 1][coord[1]]);
        } catch (IndexOutOfBoundsException ignored) {}
        try {
            fieldCheck(available, board[coord[0] - 1][coord[1]]);
        } catch (IndexOutOfBoundsException ignored) {}
        try {
            fieldCheck(available, board[coord[0]][coord[1] + 1]);
        } catch (IndexOutOfBoundsException ignored) {}
        try {
            fieldCheck(available, board[coord[0]][coord[1] - 1]);
        } catch (IndexOutOfBoundsException ignored) {}
        try {
            fieldCheck(available, board[coord[0] - 1][coord[1] - 1]);
        } catch (IndexOutOfBoundsException ignored) {}
        try {
            fieldCheck(available, board[coord[0] + 1][coord[1] - 1]);
        } catch (IndexOutOfBoundsException ignored) {}
        try {
            fieldCheck(available, board[coord[0] - 1][coord[1] + 1]);
        } catch (IndexOutOfBoundsException ignored) {}
        try {
            fieldCheck(available, board[coord[0] + 1][coord[1] + 1]);
        } catch (IndexOutOfBoundsException ignored) {}
    } // > findAvailable

    private void fieldCheck(ArrayList available, Button b) {
        try{
            if (Objects.equals(b.getText(), "")){
                available.add(b);
            } else if (colorCheck(b) != color) {
                available.add(b);
            }
        }catch (IndexOutOfBoundsException ignored) {}
    } // > fieldCheck

    @Override
    public String toString() {
        return "King";
    } // > toString
} // > King
