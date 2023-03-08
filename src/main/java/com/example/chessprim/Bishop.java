package com.example.chessprim;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.chessprim.ChessColor.*;

public class Bishop extends Figure{

    public Bishop(ChessColor color, int[] coord) {
        super();
        this.color = color;
        this.coord = coord;

        if (color == LIGHT) figSym = "\uF0A5";
        if (color == DARK) figSym = "\uF076";
    } // > constructor

    @Override
    public void updateCP() {}

    @Override
    public void findAvailable(Button[][] board, ArrayList available, boolean isFirstMove) {
        for (int i = 1; i < 8; i++) {
            try {
                if (Objects.equals(board[coord[0] + i][coord[1] + i].getText(), "")) {
                    available.add(board[coord[0] + i][coord[1] + i]);
                } else if (colorCheck(board[coord[0] + i][coord[1] + i]) != this.color){
                    available.add(board[coord[0] + i][coord[1] + i]);
                    break;
                } else break;
            } catch (IndexOutOfBoundsException e) { break; }
        } // > for
        for (int i = 1; i < 8; i++) {
            try {
                if (Objects.equals(board[coord[0] - i][coord[1] + i].getText(), "")) {
                    available.add(board[coord[0] - i][coord[1] + i]);
                } else if (colorCheck(board[coord[0] - i][coord[1] + i]) != this.color){
                    available.add(board[coord[0] - i][coord[1] + i]);
                    break;
                } else break;
            } catch (IndexOutOfBoundsException e) { break; }
        } // > for
        for (int i = 1; i < 8; i++) {
            try {
                if (Objects.equals(board[coord[0] + i][coord[1] - i].getText(), "")) {
                    available.add(board[coord[0] + i][coord[1] - i]);
                } else if (colorCheck(board[coord[0] + i][coord[1] - i]) != this.color){
                    available.add(board[coord[0] + i][coord[1] - i]);
                    break;
                } else break;
            } catch (IndexOutOfBoundsException e) { break; }
        } // > for
        for (int i = 1; i < 8; i++) {
            try {
                if (Objects.equals(board[coord[0] - i][coord[1] - i].getText(), "")) {
                    available.add(board[coord[0] - i][coord[1] - i]);
                } else if (colorCheck(board[coord[0] - i][coord[1] - i]) != this.color){
                    available.add(board[coord[0] - i][coord[1] - i]);
                    break;
                } else break;
            } catch (IndexOutOfBoundsException e) { break; }
        } // > for
    } // > findAvailable

    @Override
    public String toString() {
        return "Bishop";
    } // > toString
} // > Bishop
