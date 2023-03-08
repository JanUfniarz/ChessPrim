package com.example.chessprim;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//* Klasa abstrakcyjna, po której dziedziczą wszystkie figury
public abstract class Figure {
    // Kolor figury
    ChessColor color;

    // Koordynaty figury na planszy
    int[] coord = new int[2];

    // Symbol wyświetlany na planszy
    String figSym = "";

    // Określa czy figura jest w grze
    boolean inGame = true;

    // Określa czy figura spełnia jeden z warunków do wykonania roszady
    public boolean castlingPotential;

    // Lista pól, które może atakować figura warcabowa
    public List<Button> inDanger = new ArrayList<>();

    // Lista pól, na które figura warcabowa może się poruszyć bijąc
    public List<Button> killPoints = new ArrayList<>();

    // Określa czy figura warcabowa właśnie biła
    public boolean jumped;

    // Wyświetla figurę na planszy
    public void takePlace(Button[][] board){
        if(inGame) board[coord[0]][coord[1]].setText(figSym);
    } // takePlace

    // Uaktualnia castlingPotential
    public abstract void updateCP();

    // Znajduje dostępne dla figury pola
    public abstract void findAvailable(Button[][] board, ArrayList available, boolean isFirstMove);

    // Określa kolor figury na danym polu
    public ChessColor colorCheck(Button field) {
        if (Objects.equals(field.getText(), "\uF072")
                || Objects.equals(field.getText(), "\uF0A5")
                || Objects.equals(field.getText(), "\uF070")
                || Objects.equals(field.getText(), "\uF06E")
                || Objects.equals(field.getText(), "\uF0A3")
                || Objects.equals(field.getText(), "\uF06B"))
            return ChessColor.LIGHT;

        if (Objects.equals(field.getText(), "\uF074")
                || Objects.equals(field.getText(), "\uF076")
                || Objects.equals(field.getText(), "\uF06F")
                || Objects.equals(field.getText(), "\uF06D")
                || Objects.equals(field.getText(), "\uF077")
                || Objects.equals(field.getText(), "\uF06C"))
            return ChessColor.DARK;
        return null;
    } // > colorCheck
} // > Figure
