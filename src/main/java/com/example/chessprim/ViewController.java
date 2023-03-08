package com.example.chessprim;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.InnerShadow;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.*;

import static com.example.chessprim.ChessColor.*;
import static javafx.scene.paint.Color.*;

//* Klasa zawiera kod działania gry w warcaby i w szachy
public class ViewController implements Initializable {

    // *===== WARTOŚCI =====*

    // Przypisanie pól planszy
    @FXML
    Button
            A8,B8,C8,D8,E8,F8,G8,H8,
            A7,B7,C7,D7,E7,F7,G7,H7,
            A6,B6,C6,D6,E6,F6,G6,H6,
            A5,B5,C5,D5,E5,F5,G5,H5,
            A4,B4,C4,D4,E4,F4,G4,H4,
            A3,B3,C3,D3,E3,F3,G3,H3,
            A2,B2,C2,D2,E2,F2,G2,H2,
            A1,B1,C1,D1,E1,F1,G1,H1= new Button();

    // Pole tekstowe wyświetlające informacje o szachu i obowiązkowym biciu
    @FXML
    public Label statusLabel;

    // Pola tekstowe wyświetlające informacje o turze
    public Label bwLabel1, bwLabel2;

    // Pole wyboru używane do awansu pionka w szachach (pawnOnEnd())
    @FXML
    public ChoiceBox<Figure> choiceBox;

    // Pole wyboru używane do wyboru szachów lub warcabów
    @FXML
    public ChoiceBox<String> gameBox;

    // Przycisk wyboru Easy Mode
    @FXML
    public RadioButton easyModeBu;

    // Pole, z którego figura się porusza
    private Button clickedField;

    // Poruszająca się figura
    private Figure clickedFigure;

    // Reprezentacja tablicy szachowej (initialize())
    private Button[][] board;

    // Wszystkie figury
    private Figure[] allFigures;

    // Określa czy pole zostało kliknięte
    private boolean isClicked = false;

    // Określa turę
    private boolean isWhiteMove = true;

    // Określa czy jest pierwszy ruch danego gracza
    private boolean whiteFM = true, darkFM = true;

    // Określa czy Easy Mode jest aktywny
    private boolean easyMode;

    // Określa czy gra jest w warcaby, czy w szachy
    private boolean checkers;

    // Pola dostępne dla danej figury
    private final ArrayList<Button> available = new ArrayList<>();

    // *===== FUNKCJE STARTOWE =====*

    // Funkcja uruchamiana od razu po uruchomieniu
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Przypisanie wartości do gameBox
        gameBox.getItems().add("Chess");
        gameBox.getItems().add("Checkers");
        gameBox.setValue("Chess");

        // Przypisanie wartości do board
        board = new Button[][]{
                { A8,B8,C8,D8,E8,F8,G8,H8 },
                { A7,B7,C7,D7,E7,F7,G7,H7 },
                { A6,B6,C6,D6,E6,F6,G6,H6 },
                { A5,B5,C5,D5,E5,F5,G5,H5 },
                { A4,B4,C4,D4,E4,F4,G4,H4 },
                { A3,B3,C3,D3,E3,F3,G3,H3 },
                { A2,B2,C2,D2,E2,F2,G2,H2 },
                { A1,B1,C1,D1,E1,F1,G1,H1 }};
    } // > initialize

    // Funkcja po naciśnięciu przycisku start
    @FXML
    public void start(){
        // Przypisuje wartości nowej gry
        easyMode = easyModeBu.isSelected();
        available.clear();
        isClicked = false;
        isWhiteMove = true;
        showMove();
        whiteFM = true;
        darkFM = true;
        checkers = Objects.equals(gameBox.getValue(), "Checkers");
        assign();
        showFigures();
    } // > start

    // Funkcja opisująca startowe ułożenie figur
    private void assign() {
        if (checkers) {
            // Dla warcabów
            allFigures = new Figure[]{
                    new ChecPawn(DARK, new int[] {0, 1}),
                    new ChecPawn(DARK, new int[] {0, 3}),
                    new ChecPawn(DARK, new int[] {0, 5}),
                    new ChecPawn(DARK, new int[] {0, 7}),
                    new ChecPawn(DARK, new int[] {1, 0}),
                    new ChecPawn(DARK, new int[] {1, 2}),
                    new ChecPawn(DARK, new int[] {1, 4}),
                    new ChecPawn(DARK, new int[] {1, 6}),
                    new ChecPawn(DARK, new int[] {2, 1}),
                    new ChecPawn(DARK, new int[] {2, 3}),
                    new ChecPawn(DARK, new int[] {2, 5}),
                    new ChecPawn(DARK, new int[] {2, 7}),
                    new ChecPawn(LIGHT, new int[] {5, 0}),
                    new ChecPawn(LIGHT, new int[] {5, 2}),
                    new ChecPawn(LIGHT, new int[] {5, 4}),
                    new ChecPawn(LIGHT, new int[] {5, 6}),
                    new ChecPawn(LIGHT, new int[] {6, 1}),
                    new ChecPawn(LIGHT, new int[] {6, 3}),
                    new ChecPawn(LIGHT, new int[] {6, 5}),
                    new ChecPawn(LIGHT, new int[] {6, 7}),
                    new ChecPawn(LIGHT, new int[] {7, 0}),
                    new ChecPawn(LIGHT, new int[] {7, 2}),
                    new ChecPawn(LIGHT, new int[] {7, 4}),
                    new ChecPawn(LIGHT, new int[] {7, 6})};
        } else {
            // Dla szachów
            allFigures = new Figure[]{
                    new Queen(DARK, new int[] {0, 3}),
                    new Queen(LIGHT, new int[] {7, 3}),
                    new King(DARK, new int[] {0, 4}),
                    new King(LIGHT, new int[] {7, 4}),
                    new Rook(DARK, new int[] {0, 0}),
                    new Rook(DARK, new int[] {0, 7}),
                    new Rook(LIGHT, new int[] {7, 0}),
                    new Rook(LIGHT, new int[] {7, 7}),
                    new Bishop(DARK, new int[] {0, 2}),
                    new Bishop(DARK, new int[] {0, 5}),
                    new Bishop(LIGHT, new int[] {7, 2}),
                    new Bishop(LIGHT, new int[] {7, 5}),
                    new Knigth(DARK, new int[] {0, 1}),
                    new Knigth(DARK, new int[] {0, 6}),
                    new Knigth(LIGHT, new int[] {7, 1}),
                    new Knigth(LIGHT, new int[] {7, 6}),
                    new Pawn(DARK, new int[] {1, 0}),
                    new Pawn(DARK, new int[] {1, 1}),
                    new Pawn(DARK, new int[] {1, 2}),
                    new Pawn(DARK, new int[] {1, 3}),
                    new Pawn(DARK, new int[] {1, 4}),
                    new Pawn(DARK, new int[] {1, 5}),
                    new Pawn(DARK, new int[] {1, 6}),
                    new Pawn(DARK, new int[] {1, 7}),
                    new Pawn(LIGHT, new int[] {6, 0}),
                    new Pawn(LIGHT, new int[] {6, 1}),
                    new Pawn(LIGHT, new int[] {6, 2}),
                    new Pawn(LIGHT, new int[] {6, 3}),
                    new Pawn(LIGHT, new int[] {6, 4}),
                    new Pawn(LIGHT, new int[] {6, 5}),
                    new Pawn(LIGHT, new int[] {6, 6}),
                    new Pawn(LIGHT, new int[] {6, 7})};
        } // > if checkers or not
    } // > assign

    // Funkcja wywoływana naciśnięciem dowolnego pola planszy
    @FXML
    public void onClick(ActionEvent e){
        try {
            // Pole, na którym została wywołana funkcja
            Button field = (Button) e.getSource();

            // Określenie czy kliknięcie oznacza wybór figury czy ruch nią
            if (!isClicked) firstStep(field);
            else secondStep(field);
        } catch (NullPointerException | NoSuchElementException ignored) {}
    } // > onClick

    // Przypisuje wartości dla wszystkich podświetleń
    private void initializeInSha(InnerShadow innerShadow, Color color) {
        innerShadow.setBlurType(BlurType.THREE_PASS_BOX);
        innerShadow.setChoke(0.3);
        innerShadow.setColor(color);
        innerShadow.setHeight(100);
        innerShadow.setWidth(100);
        innerShadow.setRadius(50);
    } // > initializeInSha

    // *===== FUNKCJE GŁÓWNE =====*
    /*
    * Zawierają pomieszany kod szachowy i warcabowy
    * Jest on rozgraniczany tak:
    ? <|Chess   |<

    ? >|Chess   |>
    ! <|Checkers|<

    ! >|Checkers|>
    * <|Both    |<

    * >|Both    |>

    * Dla pojedynczej linijki:
    ? Chess
    * lub:
    ! Checkers
    *  lub:
    * Both
     */

    // Odpowiada za wybranie figury, którą gracz się rusza i dostępnych dla niej pól
    private void firstStep(Button field){
//? <|Chess   |<
        // Sprawdza i wyświetla informację o szachu
        if (check()) statusLabel.setText("CHECK!");
        else statusLabel.setText("");
//? >|Chess   |>
//* <|Both    |<
        // Określa kolory podświetleń
        InnerShadow basicShadow = new InnerShadow(); // Pola wybranego
        initializeInSha(basicShadow, rgb(86, 116, 86));

        InnerShadow easyShadow = new InnerShadow(); // Pola dostępnego
        initializeInSha(easyShadow, rgb(75, 75, 65));

        InnerShadow attackShadow = new InnerShadow(); // Pola dostępnego do ataku
        initializeInSha(attackShadow, rgb(139, 22, 22));

        InnerShadow castlingShadow = new InnerShadow(); // Pola wieży, jeśli możliwa jest roszada
        initializeInSha(castlingShadow, rgb(32, 141, 236));

        // Wyklucza pola, na których nie znajduje się żadna figura
        if (!Objects.equals(field.getText(), "")) {
//* >|Both    |>
//! <|Checkers|<
            // Lista figur które mają możliwość atakowania
            List<Figure> raged = new ArrayList<>();
            Arrays.stream(allFigures)
                    .filter(figure -> figure.color == (isWhiteMove ? LIGHT : DARK))
                    .filter(figure -> figure.inGame)
                    .filter(figure -> inDangerSum(figure) > 0)
                    .forEach(raged::add);

            // Sprawdza i wyświetla informację o obowiązkowym biciu
            if (raged.size() > 0) statusLabel.setText("Mandatory Capturing");
            else statusLabel.setText("");

            // Podświetla pole z obowiązkowym biciu
            raged.forEach(f -> board[f.coord[0]][f.coord[1]].setEffect(castlingShadow));
//! >|Checkers|>
//* <|Both    |<
            // Pozwala wykonać ruch tylko w odpowiedniej turze
            if (figOn(field).color == (isWhiteMove ? LIGHT : DARK)) {

                // Wymusza wykonanie obowiązkowego bicia
                if (raged.size() == 0 || raged.contains(figOn(field))) { //! Checkers

                    field.setEffect(basicShadow);
                    clickedField = field;
                    isClicked = !isClicked;

                    // Czyści listę killPoints dla wszystkich figur
                    Arrays.stream(allFigures).forEach(f -> f.killPoints.clear()); //! Checkers

                    // Określa czy jest pierwszy ruch dla danego gracza
                    boolean isFirstMove = isWhiteMove ? whiteFM : darkFM; //? Chess

                    // Znajduje dostępne pola dla danej figury
                    figOn(field).findAvailable(board, available, isFirstMove);
//* >|Both    |>
//! <|Checkers|<
                    // Usuwa ze zbioru dostępnych pól te, które nie prowadzą do bicia
                    if (raged.contains(figOn(field))) {
                        available.clear();
                        available.addAll(figOn(field).killPoints);
                    } // > if in raged
//! >|Checkers|>
//* <|Both    |<
                    // Przypisuje kliknięte pole do zmiennej
                    clickedFigure = figOn(field);

                    // Ustala podświetlenia
                    if (easyMode) {
                        for (Button f : available) {
                            // Dla dostępnych
                            if (Objects.equals(f.getText(), ""))
                                f.setEffect(easyShadow);
                            // Dla dostępnych do ataku
                            else //? Chess
                                f.setEffect(attackShadow);
                        } // > for f : available

                        // Dla dostępnych do ataku
                        clickedFigure.inDanger //! Checkers
                                .forEach(f -> f.setEffect(attackShadow));
//* >|Both    |>
//? <|Chess   |<
                        // Dla roszady
                        if (longCastling(board[0][0])) {
                            board[0][0].setEffect(castlingShadow);
                        } else if (longCastling(board[7][0])) {
                            board[7][0].setEffect(castlingShadow);
                        } else if (shortCastling(board[0][7])) {
                            board[0][7].setEffect(castlingShadow);
                        } else if (shortCastling(board[7][7])) {
                            board[7][7].setEffect(castlingShadow);
                        } // > if castling
                    } // > if easyMode
                } // > if raged
//? >|Chess   |>
//! <|Checkers|<
                // Czyści listę raged
                raged.clear();
            } // > if move = color
        } // > if empty field
//! >|Checkers|>
    } // > firstStep

    // Określa przemieszczenie się pionka i bicie
    private void secondStep(Button field) {
        Figure temp = null; //? Chess
        // Ustawia domyślną wartość jumped każdej figurze
        Arrays.stream(allFigures).forEach(f -> f.jumped = false); //! Checkers
//* <|Both    |<
        // Cofnięcie firstStep() po ponownym kliknięciu na to samo pole
        if (field == clickedField) {
            isClicked = !isClicked;
            for (Button[] row : board)
                for (Button f : row)
                    f.setEffect(null);
            clickedField = null;
            available.clear();
            // Pozwala poruszyć się tylko na dostępne dla figury miejsce
        } else if (available.contains(field)){
//* >|Both    |>
//! <|Checkers|<
            // Dla Warcabów
            if (checkers) {
                // Sprawdza, czy jest to bicie
                if (clickedFigure.killPoints.contains(field)) {
                    // Sprawdza, w którą stronę następuje bice
                    // Zmienia inGame w zbitej figurze
                    if (fieldCoord(field)[0] > clickedFigure.coord[0]
                            && fieldCoord(field)[1] > clickedFigure.coord[1]) {
                        figOn(board[fieldCoord(field)[0] - 1][fieldCoord(field)[1] - 1]).inGame = false;
                        clickedFigure.jumped = true;
                    } // > if 1
                    if (fieldCoord(field)[0] > clickedFigure.coord[0]
                            && fieldCoord(field)[1] < clickedFigure.coord[1]) {
                        figOn(board[fieldCoord(field)[0] - 1][fieldCoord(field)[1] + 1]).inGame = false;
                        clickedFigure.jumped = true;
                    } // > if 2
                    if (fieldCoord(field)[0] < clickedFigure.coord[0]
                            && fieldCoord(field)[1] > clickedFigure.coord[1]) {
                        figOn(board[fieldCoord(field)[0] + 1][fieldCoord(field)[1] - 1]).inGame = false;
                        clickedFigure.jumped = true;
                    } // > if 3
                    if (fieldCoord(field)[0] < clickedFigure.coord[0]
                            && fieldCoord(field)[1] < clickedFigure.coord[1]) {
                        figOn(board[fieldCoord(field)[0] + 1][fieldCoord(field)[1] + 1]).inGame = false;
                        clickedFigure.jumped = true;
                    } // > if 4
                } // > if in killPoints
            } // > if checkers
//! >|Checkers|>
//? <|Chess   |<
            // Zmienia wartość inGame zbitej figury szachowej
            if (!Objects.equals(field.getText(), "")) {
                temp = figOn(field);
                temp.inGame = false;
            } // > if not empty field
//? >|Chess   |>
//* <|Both    |<
            // Zmienia koordynaty figury po ruchu
            clickedFigure.coord[0] = fieldCoord(field)[0];
            clickedFigure.coord[1] = fieldCoord(field)[1];
//* >|Both    |>
//? <|Chess   |<
            // Odnotowuje kiedy minął pierwszy ruch
            if (isWhiteMove) whiteFM = false;
            else darkFM = false;
//? >|Chess   |>
            // Pokazuje figury na planszy
            showFigures(); //* Both
//! <|Checkers|<
            // Czyści wewnętrzne listy figur
            Arrays.stream(allFigures).forEach(x -> {
                x.inDanger.clear();
                x.killPoints.clear();
            });
            // Sprawdza, czy nastąpiło bicie
            if (clickedFigure.jumped) {
                // Sprawdza, czy nie ma podwójnego bicia
                if (inDangerSum(clickedFigure) == 0) isWhiteMove = !isWhiteMove;
            }
//! >|Checkers|>
//? <|Chess   |<
            // Sprawdza, czy wystąpił szach
            else if (check()) {
                // Cofa zmianę pozycji figury
                clickedFigure.coord[0] = fieldCoord(clickedField)[0];
                clickedFigure.coord[1] = fieldCoord(clickedField)[1];
                // Cofa zmianę inGame
                try {
                    assert temp != null;
                    temp.inGame = true;
                } catch (NullPointerException ignored) {}
            } else isWhiteMove = !isWhiteMove;

            // Pokazuje Figury na planszy
            showFigures(); //* Both

            // Uaktualnia warunek potrzebny do wykonania roszady
            Arrays.stream(allFigures).forEach(Figure::updateCP);
//? >|Chess   |>
//* <|Both    |<
            // Sprawdza, czy na rzędzie 8 znalazł się biały pionek
            for (Button field_ : board[0]) {
                if (Objects.equals(field_.getText(), "\uF070"))
                    pawnOnEnd(field_, LIGHT);
            } // > for board[0]
            // Sprawdza, czy na rzędzie 1 znalazł się czarny pionek
            for (Button field_ : board[7]) {
                if (Objects.equals(field_.getText(), "\uF06F"))
                    pawnOnEnd(field_, DARK);
            } // > for board[7]

            // Kończy ruch
            clickedField = null;
            isClicked = !isClicked;
            available.clear();
        }
//* >|Both    |>
//? <|Chess   |<
        // Sprawdza, czy następuje długa roszada
        else if (longCastling(field)) {
            // Przenosi figury na odpowiednie pola
            clickedFigure.coord[1] = 2;
            figOn(field).coord[1] = 3;

            // Kończy ruch
            showFigures();
            clickedField = null;
            isClicked = !isClicked;
            available.clear();
            isWhiteMove = !isWhiteMove;
        }
        // Sprawdza, czy następuje krótka roszada
        else if (shortCastling(field)) {
            // Przenosi figury na odpowiednie pola
            clickedFigure.coord[1] = 6;
            figOn(field).coord[1] = 5;

            // Kończy ruch
            showFigures();
            clickedField = null;
            isClicked = !isClicked;
            available.clear();
            isWhiteMove = !isWhiteMove;
        }// > if
//? >|Chess   |>
        showMove(); //* Both
    } // > secondStep

    // *===== FUNKCJE DODATKOWE =====*

    // Określa koordynaty danego pola
    private int[] fieldCoord(Button field){
        int[] res = new int[2];
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (board[i][j] == field){
                    res[0] = i;
                    res[1] = j;
                }
        return res;
    } // > fieldCoord

    // Znajduje figure na danym polu
    private Figure figOn(Button field){
        return Arrays.stream(allFigures)
                .filter(figure
                        -> figure.coord[0] == fieldCoord(field)[0]
                        && figure.coord[1] == fieldCoord(field)[1]
                        && figure.inGame)
                .findFirst().orElseThrow();
    } // > figOn

    // Liczy dostępne ataki dla figury warcabowej
    private int inDangerSum(Figure figure) {
        Arrays.stream(allFigures).forEach(f -> f.inDanger.clear());
        figure.findAvailable(board, new ArrayList<>(), false);
        int sum = figure.inDanger.size();
        figure.inDanger.clear();
        return sum;
    } // > inDangerSum

    // Sprawdza, czy występuje szach
    private boolean check() {
        ArrayList<Button> allAvaL = new ArrayList<>();
        ArrayList<Button> allAvaD = new ArrayList<>();

        Arrays.stream(allFigures)
                .filter(figure -> figure.inGame)
                .filter(figure -> figure.color == LIGHT)
                .forEach(figure -> figure.findAvailable(board, allAvaL, false));

        Arrays.stream(allFigures)
                .filter(figure -> figure.inGame)
                .filter(figure -> figure.color == DARK)
                .forEach(figure -> figure.findAvailable(board, allAvaD, false));


        if (isWhiteMove)
            return allAvaD.stream()
                    .map(Labeled::getText)
                    .anyMatch(x -> Objects.equals(x, "\uF06B"));
        else return allAvaL.stream()
                .map(Labeled::getText)
                .anyMatch(x -> Objects.equals(x, "\uF06C"));
    } // > check

    // Sprawdza, czy występuje długa roszada
    private boolean longCastling(Button field) {
        int x = isWhiteMove ? 7 : 0;
        return clickedField == board[x][4]
                && field == board[x][0] && !Objects.equals(field.getText(), "")
                && clickedFigure.castlingPotential && figOn(field).castlingPotential
                && Objects.equals(board[x][1].getText(), "")
                && Objects.equals(board[x][2].getText(), "")
                && Objects.equals(board[x][3].getText(), "")
                && !check() && isSafe(board[x][2]) && isSafe(board[x][3]);
    } // > longCastling

    // Sprawdza, czy występuje krótka roszada
    private boolean shortCastling(Button field) {
        int x = isWhiteMove ? 7 : 0;
        return clickedField == board[x][4]
                && field == board[x][7] && !Objects.equals(field.getText(), "")
                && clickedFigure.castlingPotential && figOn(field).castlingPotential
                && Objects.equals(board[x][5].getText(), "")
                && Objects.equals(board[x][6].getText(), "")
                && !check() && isSafe(board[x][5]) && isSafe(board[x][6]);
    } // > shortCastling

    // Sprawdza, czy dane pole jest atakowane
    private boolean isSafe(Button field) {
        ArrayList<Button> allAva = new ArrayList<>();
        Arrays.stream(allFigures)
                .filter(figure -> figure.inGame)
                .filter(figure -> figure.color == (isWhiteMove ? DARK : LIGHT))
                .forEach(figure -> figure.findAvailable(board, allAva, false));
        return !allAva.contains(field);
    } // > isAttacked

    // Pokazuje figury na planszy
    private void showFigures() {
        for (Button[] row : board)
            for (Button field : row) {
                field.setText("");
                field.setEffect(null);
            } // > for row
        for (Figure figure : allFigures)
            figure.takePlace(board);
    } // showFigures

    // Przypisuje tekst do pól tekstowych w zależności od tury
    private void showMove() {
        if (isWhiteMove) {
            bwLabel1.setText("WHITE");
            bwLabel1.setTextFill(WHITE);
            bwLabel2.setText("WHITE");
            bwLabel2.setTextFill(WHITE);
        } else {
            bwLabel1.setText("BLACK");
            bwLabel1.setTextFill(BLACK);
            bwLabel2.setText("BLACK");
            bwLabel2.setTextFill(BLACK);
        } // > if isWhiteMove
    }

    // Określa promocje pionka
    private void pawnOnEnd(Button field, ChessColor color){
        // Dla warcabów
        if (checkers) {
            // Pętla przechodzi przez allFigures
            for (int i = 0; i < allFigures.length; i++) {
                //  Znajduje figurę na nadym polu
                if (allFigures[i].coord[0] == fieldCoord(field)[0]
                        && allFigures[i].coord[1] == fieldCoord(field)[1]) {

                    // Przypisuje nową figurę w miejsce awansowanego pionka
                    allFigures[i] = new ChecQueen(color, fieldCoord(field));
                    showFigures();
                } // > if
            } // > for allFigures
        } // > if checkers

        // Dla szachów
        else {
            // Pętla przechodzi przez allFigures
            for (Figure figure : allFigures) {
                // Znajduje zbite figury
                if (!figure.inGame) {
                    // Znajduje figury o tym samym kolorze co awansowany pionek
                    if (figure.color == color) {
                        // Dodaje do pola wyboru
                        choiceBox.getItems().add(figure);
                    } // > if same color
                } // > if not in game
            } // > for allFigures

            // Wyświetla pole wyboru
            choiceBox.setVisible(true);

            // Zamienia pionek na wybraną figurę
            choiceBox.setOnAction((event) -> {
                try {
                    Figure resurrected = choiceBox.getValue();
                    resurrected.coord = figOn(field).coord;
                    figOn(field).inGame = false;
                    resurrected.inGame = true;
                    showFigures();
                    choiceBox.setVisible(false);
                    choiceBox.getItems().clear();
                } catch (NullPointerException ignored) {}
            }); // > onAction
        } // > if checkers or not
    } // > pawnOnEnd
} // > ViewController