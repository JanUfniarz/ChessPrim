package com.example.chessprim;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.chessprim.ChessColor.*;

public class Knigth extends Figure {

    public Knigth(ChessColor color, int[] coord) {
        super();
        this.color = color;
        this.coord = coord;

        if (color == LIGHT) figSym = "\uF06E";
        if (color == DARK) figSym = "\uF06D";
    } // > constructor

    @Override
    public void updateCP() {}

    @Override
    public void findAvailable(Button[][] board, ArrayList available, boolean isFirstMove) {
        try {
            fieldCheck(available, board[coord[0] + 2][coord[1] + 1]);
        } catch (IndexOutOfBoundsException ignored) {}
        try {
            fieldCheck(available, board[coord[0] + 2][coord[1] - 1]);
        } catch (IndexOutOfBoundsException ignored) {}
        try {
            fieldCheck(available, board[coord[0] - 2][coord[1] + 1]);
        } catch (IndexOutOfBoundsException ignored) {}
        try {
            fieldCheck(available, board[coord[0] - 2][coord[1] - 1]);
        } catch (IndexOutOfBoundsException ignored) {}
        try {
            fieldCheck(available, board[coord[0] + 1][coord[1] + 2]);
        } catch (IndexOutOfBoundsException ignored) {}
        try {
            fieldCheck(available, board[coord[0] - 1][coord[1] + 2]);
        } catch (IndexOutOfBoundsException ignored) {}
        try {
            fieldCheck(available, board[coord[0] + 1][coord[1] - 2]);
        } catch (IndexOutOfBoundsException ignored) {}
        try {
            fieldCheck(available, board[coord[0] - 1][coord[1] - 2]);
        } catch (IndexOutOfBoundsException ignored) {}
    } // findAvailable
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
        return "Knigth";
    } // > toString
} // > Knigth
