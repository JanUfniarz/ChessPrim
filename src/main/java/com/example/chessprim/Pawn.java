package com.example.chessprim;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.chessprim.ChessColor.*;

public class Pawn extends Figure{

    public Pawn(ChessColor color, int[] coord) {
        super();
        this.color = color;
        this.coord = coord;

        if (color == LIGHT) figSym = "\uF070";
        if (color == DARK) figSym = "\uF06F";
    } // > constructor

    @Override
    public void updateCP() {}

    @Override
    public void findAvailable(Button[][] board, ArrayList available, boolean isFirstMove) {
        if (color == LIGHT) {
            try {
                if (Objects.equals(board[coord[0] - 1][coord[1]].getText(), ""))
                    available.add(board[coord[0] - 1][coord[1]]);
            } catch (IndexOutOfBoundsException ignored) {}
            try {
                if (Objects.equals(board[coord[0] - 2][coord[1]].getText(), "")
                        && isFirstMove)
                    available.add(board[coord[0] - 2][coord[1]]);
            } catch (IndexOutOfBoundsException ignored){}
            try {
                if (!Objects.equals(board[coord[0] - 1][coord[1] - 1].getText(), ""))
                    if (colorCheck(board[coord[0] - 1][coord[1] - 1]) != color)
                        available.add(board[coord[0] - 1][coord[1] - 1]);
            } catch (IndexOutOfBoundsException ignored){}
            try {
                if (!Objects.equals(board[coord[0] - 1][coord[1] + 1].getText(), ""))
                    if (colorCheck(board[coord[0] - 1][coord[1] + 1]) != color)
                        available.add(board[coord[0] - 1][coord[1] + 1]);
            } catch (IndexOutOfBoundsException ignored){}
        } else if (color == DARK) {
            try {
                if (Objects.equals(board[coord[0] + 1][coord[1]].getText(), "")) {
                    available.add(board[coord[0] + 1][coord[1]]);
                }
            } catch (IndexOutOfBoundsException ignored) {}
            try {
                if (Objects.equals(board[coord[0] + 2][coord[1]].getText(), "")
                        && isFirstMove)
                    available.add(board[coord[0] + 2][coord[1]]);
            } catch (IndexOutOfBoundsException ignored){}
            try {
                if (!Objects.equals(board[coord[0] + 1][coord[1] - 1].getText(), ""))
                    if (colorCheck(board[coord[0] + 1][coord[1] - 1]) != color)
                        available.add(board[coord[0] + 1][coord[1] - 1]);
            } catch (IndexOutOfBoundsException ignored){}
            try {
                if (!Objects.equals(board[coord[0] + 1][coord[1] + 1].getText(), ""))
                    if (colorCheck(board[coord[0] + 1][coord[1] + 1]) != color)
                        available.add(board[coord[0] + 1][coord[1] + 1]);
            } catch (IndexOutOfBoundsException ignored){}
        } // > if
    } // > findAvailable

    @Override
    public String toString() {
        return "Pawn";
    } // > toString
} // > Pawn