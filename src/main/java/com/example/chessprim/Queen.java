package com.example.chessprim;

import javafx.scene.control.Button;

import java.util.ArrayList;

import static com.example.chessprim.ChessColor.*;

public class Queen extends Figure{
    public Queen(ChessColor color, int[] coord) {
        super();
        this.color = color;
        this.coord = coord;

        if (color == LIGHT) figSym = "\uF0A3";
        if (color == DARK) figSym = "\uF077";
    } // > constructor

    @Override
    public void updateCP() {}

    @Override
    public void findAvailable(Button[][] board, ArrayList available, boolean isFirstMove) {
        Bishop bishop = new Bishop(color, coord);
        Rook rook = new Rook(color, coord);
        bishop.findAvailable(board, available, isFirstMove);
        rook.findAvailable(board, available, isFirstMove);
    } // > findAvailable

    @Override
    public String toString() {
        return "Queen";
    } //  > toString
} // > Queen
