package com.example.chessprim;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.chessprim.ChessColor.DARK;
import static com.example.chessprim.ChessColor.LIGHT;

public class ChecQueen extends Figure{

    public ChecQueen(ChessColor color, int[] coord) {
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
        for (int i = 1; i < 8; i++) {
            try {
                if (Objects.equals(board[coord[0] + i][coord[1] + i].getText(), "")) {
                    available.add(board[coord[0] + i][coord[1] + i]);
                } else if (colorCheck(board[coord[0] + i][coord[1] + i]) != this.color
                        && Objects.equals(board[coord[0] + (i + 1)][coord[1] + (i + 1)].getText(), "")) {
                    available.add(board[coord[0] + (i + 1)][coord[1] + (i + 1)]);
                    killPoints.add(board[coord[0] + (i + 1)][coord[1] + (i + 1)]);
                    inDanger.add(board[coord[0] + i][coord[1] + i]);
                    break;
                } else break;
            } catch (IndexOutOfBoundsException e) { break; }
        } // > for
        for (int i = 1; i < 8; i++) {
            try {
                if (Objects.equals(board[coord[0] - i][coord[1] + i].getText(), "")) {
                    available.add(board[coord[0] - i][coord[1] + i]);
                } else if (colorCheck(board[coord[0] - i][coord[1] + i]) != this.color
                        && Objects.equals(board[coord[0] - (i + 1)][coord[1] + (i + 1)].getText(), "")){
                    available.add(board[coord[0] - (i + 1)][coord[1] + (i + 1)]);
                    killPoints.add(board[coord[0] - (i + 1)][coord[1] + (i + 1)]);
                    inDanger.add(board[coord[0] - i][coord[1] + i]);
                    break;
                } else break;
            } catch (IndexOutOfBoundsException e) { break; }
        } // > for
        for (int i = 1; i < 8; i++) {
            try {
                if (Objects.equals(board[coord[0] + i][coord[1] - i].getText(), "")) {
                    available.add(board[coord[0] + i][coord[1] - i]);
                } else if (colorCheck(board[coord[0] + i][coord[1] - i]) != this.color
                        && Objects.equals(board[coord[0] + (i + 1)][coord[1] - (i + 1)].getText(), "")){
                    available.add(board[coord[0] + (i + 1)][coord[1] - (i + 1)]);
                    killPoints.add(board[coord[0] + (i + 1)][coord[1] - (i + 1)]);
                    inDanger.add(board[coord[0] + i][coord[1] - i]);
                    break;
                } else break;
            } catch (IndexOutOfBoundsException e) { break; }
        } // > for
        for (int i = 1; i < 8; i++) {
            try {
                if (Objects.equals(board[coord[0] - i][coord[1] - i].getText(), "")) {
                    available.add(board[coord[0] - i][coord[1] - i]);
                } else if (colorCheck(board[coord[0] - i][coord[1] - i]) != this.color
                        && Objects.equals(board[coord[0] - (i + 1)][coord[1] - (i + 1)].getText(), "")){
                    available.add(board[coord[0] - (i + 1)][coord[1] - (i + 1)]);
                    killPoints.add(board[coord[0] - (i + 1)][coord[1] - (i + 1)]);
                    inDanger.add(board[coord[0] - i][coord[1] - i]);
                    break;
                } else break;
            } catch (IndexOutOfBoundsException e) { break; }
        } // > for
    } // > findAvailable
} // > ChcQueen
