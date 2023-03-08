package com.example.chessprim;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.chessprim.ChessColor.*;

public class Rook extends Figure {

    private final int [] initialCoord = new int[2];
    public Rook(ChessColor color, int[] coord) {
        super();
        this.color = color;
        this.coord = coord;

        System.arraycopy(coord, 0, initialCoord, 0, 2);

        if (color == LIGHT) figSym = "\uF072";
        if (color == DARK) figSym = "\uF074";

        castlingPotential = true;
    } // > constructor

    @Override
    public void updateCP() {
        if (coord[0] != initialCoord[0]) castlingPotential = false;
        if (coord[1] != initialCoord[1]) castlingPotential = false;
        if (!inGame) castlingPotential = false;
    } // > updateCP

    @Override
    public void findAvailable(Button[][] board, ArrayList available, boolean isFirstMove) {
        for (int i = coord[0] + 1; i < 8; i++) {
            if (Objects.equals(board[i][coord[1]].getText(), ""))
                available.add(board[i][coord[1]]);
            else if (colorCheck(board[i][coord[1]]) != this.color) {
                available.add(board[i][coord[1]]);
                break;
            } else break;
        } // > for
        for (int i = coord[0] - 1; i >= 0; i--) {
            if (Objects.equals(board[i][coord[1]].getText(), ""))
                available.add(board[i][coord[1]]);
            else if (colorCheck(board[i][coord[1]]) != this.color) {
                available.add(board[i][coord[1]]);
                break;
            } else break;
        } // > for
        for (int j = coord[1] + 1; j < 8; j++) {
            if (Objects.equals(board[coord[0]][j].getText(), ""))
                available.add(board[coord[0]][j]);
            else if (colorCheck(board[coord[0]][j]) != this.color) {
                available.add(board[coord[0]][j]);
                break;
            } else break;
        } // > for
        for (int j = coord[1] - 1; j >= 0; j--) {
            if (Objects.equals(board[coord[0]][j].getText(), ""))
                available.add(board[coord[0]][j]);
            else if (!Objects.equals(colorCheck(board[coord[0]][j]), this.color)) {
                available.add(board[coord[0]][j]);
                break;
            } else break;
        } // > for
    } // > findAvailable

    @Override
    public String toString() {
        return "Rook";
    } // > toString
} // > Rook
