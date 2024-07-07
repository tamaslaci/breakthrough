/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iz85zg.breakthrough;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author iz85zg
 */
public class BoardGUI {
    private JFrame frame;
    private JPanel panel;
    private final JButton[][] fields;
    private final Board board;
    private JLabel label;
    private FieldTypes stepLock;
    
    public BoardGUI(JFrame frame, int size){
        this.frame = frame;
        this.panel = new JPanel(new GridLayout(size, size));
        this.fields = new JButton[size][size];
        if(size == 6){
            this.board = new Board(BoardTypes.SMALL, this);
        }else if(size == 10){
            this.board = new Board(BoardTypes.LARGE, this);
        }else{
            this.board = new Board(BoardTypes.MEDIUM, this);
        }
        for(int i=0; i<this.fields.length; ++i){
            for(int j=0; j<this.fields[0].length; ++j){
                JButton button = createField(i, j);
                this.fields[i][j] = button;
                this.panel.add(this.fields[i][j]);
            }
        }
        this.stepLock = FieldTypes.WHITE;
        this.label = new JLabel("  Welcome to the Break Through Game! White player starts!");
        this.label.setHorizontalAlignment(JLabel.LEFT);
    }
    
    /**
     * Creating a new Field element for the GUI Board
     * @param row
     * @param column
     * @return Field type element
     */
    private Field createField(int row, int column){
        switch (this.board.getBoard()[row][column]) {
            case EMPTY -> {
                return new EmptyField(this.board, row, column);
            }
            case WHITE -> {
                return new WhitePawn(this.board, row, column);
            }
            case BLACK -> {
                return new BlackPawn(this.board, row, column);
            }
            case WHITE_STEP -> {
                return new WhitePawnStep(this.board, row, column);
            }
            case BLACK_STEP -> {
                return new BlackPawnStep(this.board, row, column);
            }
            case EMPTY_STEP -> {
                return new EmptyStepField(this.board, row, column);
            }
            case ATTACKED_WHITE -> {
                return new WhitePawnAttacked(this.board, row, column);
            }
            case ATTACKED_BLACK -> {
                return new BlackPawnAttacked(this.board, row, column);
            }
            default -> {
                return new EmptyField(this.board, row, column);
            }
        }
    }
    
    /**
     * Updateing the graphical board
     */
    private void updateBoard(){
        this.frame.getContentPane().remove(this.getPanel());
        this.panel.removeAll();
        this.panel = new JPanel(new GridLayout(this.fields.length, this.fields[0].length));
        for(int i=0; i<this.fields.length; ++i){
            for(int j=0; j<this.fields[0].length; ++j){
                JButton button = createField(i, j);
                this.fields[i][j] = button;
                this.panel.add(this.fields[i][j]);
            }
        }
        this.frame.getContentPane().add(this.getPanel());
        this.frame.pack();
    }
    
    public JPanel getPanel(){
        return this.panel;
    }
    
    public JLabel getLabel(){
        return this.label;
    }
    
    /**
     * Main method for operating the board.
     * Using Field action event to modify the board modell before updateing the graphical board.
     * @param row
     * @param column 
     */
    public void handleFieldEvent(int row, int column){
        if(this.stepLock == FieldTypes.EMPTY){
            return;
        }
        switch (this.board.getBoard()[row][column]) {
            case EMPTY -> {
            }
            case WHITE -> {
                if(!this.board.find(FieldTypes.WHITE_STEP) && !this.board.find(FieldTypes.BLACK_STEP) &&
                        this.stepLock == FieldTypes.WHITE){
                    if(column == 0 && 
                            (this.board.getBoard()[row-1][column] != FieldTypes.WHITE ||
                            this.board.getBoard()[row-1][column+1] != FieldTypes.WHITE)){
                        this.board.setBoard(row, column, FieldTypes.WHITE_STEP);
                    }else if(column == this.fields[row-1].length-1 && 
                            (this.board.getBoard()[row-1][column] != FieldTypes.WHITE ||
                            this.board.getBoard()[row-1][column-1] != FieldTypes.WHITE)){
                        this.board.setBoard(row, column, FieldTypes.WHITE_STEP);
                    }else if(column > 0 && column < this.fields[row-1].length-1 &&
                            (this.board.getBoard()[row-1][column-1] != FieldTypes.WHITE ||
                            this.board.getBoard()[row-1][column] != FieldTypes.WHITE ||
                            this.board.getBoard()[row-1][column+1] != FieldTypes.WHITE)){
                        this.board.setBoard(row, column, FieldTypes.WHITE_STEP);
                    }
                    if(this.board.getBoard()[row][column] == FieldTypes.WHITE_STEP){
                        if(this.board.getBoard()[row-1][column] == FieldTypes.EMPTY){
                            this.board.setBoard(row-1, column, FieldTypes.EMPTY_STEP);
                        }
                        if(column-1 >= 0 && this.board.getBoard()[row-1][column-1] == FieldTypes.EMPTY){
                            this.board.setBoard(row-1, column-1, FieldTypes.EMPTY_STEP);
                        }else if(column-1 >= 0 && this.board.getBoard()[row-1][column-1] == FieldTypes.BLACK){
                            this.board.setBoard(row-1, column-1, FieldTypes.ATTACKED_BLACK);
                        }
                        if(column+1 < this.fields[row-1].length && this.board.getBoard()[row-1][column+1] == FieldTypes.EMPTY){
                            this.board.setBoard(row-1, column+1, FieldTypes.EMPTY_STEP);
                        }else if(column+1 < this.fields[row-1].length && this.board.getBoard()[row-1][column+1] == FieldTypes.BLACK){
                            this.board.setBoard(row-1, column+1, FieldTypes.ATTACKED_BLACK);
                        }
                        this.stepLock = FieldTypes.BLACK;
                    }
                }
            }
            case BLACK -> {
                if(!this.board.find(FieldTypes.WHITE_STEP) && !this.board.find(FieldTypes.BLACK_STEP) &&
                        this.stepLock == FieldTypes.BLACK){
                    if(column == this.fields[row+1].length-1 &&
                            (this.board.getBoard()[row+1][column-1] != FieldTypes.BLACK ||
                            this.board.getBoard()[row+1][column] != FieldTypes.BLACK)){
                        this.board.setBoard(row, column, FieldTypes.BLACK_STEP);
                    }else if(column == 0 &&
                            (this.board.getBoard()[row+1][column] != FieldTypes.BLACK ||
                            this.board.getBoard()[row+1][column+1] != FieldTypes.BLACK)){
                        this.board.setBoard(row, column, FieldTypes.BLACK_STEP);
                    }else if(column < this.fields[row+1].length-1 && column > 0 &&
                            (this.board.getBoard()[row+1][column-1] != FieldTypes.BLACK ||
                            this.board.getBoard()[row+1][column] != FieldTypes.BLACK ||
                            this.board.getBoard()[row+1][column+1] != FieldTypes.BLACK)){
                        this.board.setBoard(row, column, FieldTypes.BLACK_STEP);
                    }
                    if(this.board.getBoard()[row][column] == FieldTypes.BLACK_STEP){
                        if(this.board.getBoard()[row+1][column] == FieldTypes.EMPTY){
                            this.board.setBoard(row+1, column, FieldTypes.EMPTY_STEP);
                        }
                        if(column+1 < this.fields[row+1].length && this.board.getBoard()[row+1][column+1] == FieldTypes.EMPTY){
                            this.board.setBoard(row+1, column+1, FieldTypes.EMPTY_STEP);
                        }else if(column+1 < this.fields[row+1].length && this.board.getBoard()[row+1][column+1] == FieldTypes.WHITE){
                            this.board.setBoard(row+1, column+1, FieldTypes.ATTACKED_WHITE);
                        }
                        if(column-1 >= 0 && this.board.getBoard()[row+1][column-1] == FieldTypes.EMPTY){
                            this.board.setBoard(row+1, column-1, FieldTypes.EMPTY_STEP);
                        }else if(column-1 >= 0 && this.board.getBoard()[row+1][column-1] == FieldTypes.WHITE){
                            this.board.setBoard(row+1, column-1, FieldTypes.ATTACKED_WHITE);
                        }
                        this.stepLock = FieldTypes.WHITE;
                    }
                }
            }
            case WHITE_STEP -> {
                this.board.setBoard(row, column, FieldTypes.WHITE);
                if(this.board.getBoard()[row-1][column] == FieldTypes.EMPTY_STEP){
                    this.board.setBoard(row-1, column, FieldTypes.EMPTY);
                }
                if(column-1 >= 0 && this.board.getBoard()[row-1][column-1] == FieldTypes.EMPTY_STEP){
                    this.board.setBoard(row-1, column-1, FieldTypes.EMPTY);
                }else if(column-1 >= 0 && this.board.getBoard()[row-1][column-1] == FieldTypes.ATTACKED_BLACK){
                    this.board.setBoard(row-1, column-1, FieldTypes.BLACK);
                }
                if(column+1 < this.fields[row-1].length && this.board.getBoard()[row-1][column+1] == FieldTypes.EMPTY_STEP){
                    this.board.setBoard(row-1, column+1, FieldTypes.EMPTY);
                }else if(column+1 < this.fields[row-1].length && this.board.getBoard()[row-1][column+1] == FieldTypes.ATTACKED_BLACK){
                    this.board.setBoard(row-1, column+1, FieldTypes.BLACK);
                }
                this.stepLock = FieldTypes.WHITE;
            }
            case BLACK_STEP -> {
                this.board.setBoard(row, column, FieldTypes.BLACK);
                if(this.board.getBoard()[row+1][column] == FieldTypes.EMPTY_STEP){
                    this.board.setBoard(row+1, column, FieldTypes.EMPTY);
                }
                if(column+1 < this.fields[row+1].length && this.board.getBoard()[row+1][column+1] == FieldTypes.EMPTY_STEP){
                    this.board.setBoard(row+1, column+1, FieldTypes.EMPTY);
                }else if(column+1 < this.fields[row+1].length && this.board.getBoard()[row+1][column+1] == FieldTypes.ATTACKED_WHITE){
                    this.board.setBoard(row+1, column+1, FieldTypes.WHITE);
                }
                if(column-1 >= 0 && this.board.getBoard()[row+1][column-1] == FieldTypes.EMPTY_STEP){
                    this.board.setBoard(row+1, column-1, FieldTypes.EMPTY);
                }else if(column-1 >= 0 && this.board.getBoard()[row+1][column-1] == FieldTypes.ATTACKED_WHITE){
                    this.board.setBoard(row+1, column-1, FieldTypes.WHITE);
                }
                this.stepLock = FieldTypes.BLACK;
            }
            case EMPTY_STEP -> {
                if(this.board.findStepField()[0] != -1 && this.board.findStepField()[1] != -1 &&
                        this.board.getBoard()[board.findStepField()[0]][board.findStepField()[1]] == FieldTypes.WHITE_STEP){
                    this.board.setBoard(row, column, FieldTypes.WHITE);
                    this.board.setBoard(board.findStepField()[0], board.findStepField()[1], FieldTypes.EMPTY);
                    this.board.cleanBoard();
                    this.stepLock = FieldTypes.BLACK;
                }else if(this.board.findStepField()[0] != -1 && this.board.findStepField()[1] != -1 &&
                        this.board.getBoard()[board.findStepField()[0]][board.findStepField()[1]] == FieldTypes.BLACK_STEP){
                    this.board.setBoard(row, column, FieldTypes.BLACK);
                    this.board.setBoard(board.findStepField()[0], board.findStepField()[1], FieldTypes.EMPTY);
                    this.board.cleanBoard();
                    this.stepLock = FieldTypes.WHITE;
                }
                this.checkWinner(row);
                if(this.stepLock != FieldTypes.EMPTY){
                    this.label.setText("  " + this.stepLock.name() + " player steps");
                }else{
                    this.label.setText("  Click on the Menu and start a new game or exit!");
                }
            }
            case ATTACKED_WHITE -> {
                if(this.board.findStepField()[0] != -1 && this.board.findStepField()[1] != -1 &&
                        this.board.getBoard()[board.findStepField()[0]][board.findStepField()[1]] == FieldTypes.BLACK_STEP){
                    this.board.setBoard(row, column, FieldTypes.BLACK);
                    this.board.setBoard(board.findStepField()[0], board.findStepField()[1], FieldTypes.EMPTY);
                    this.board.cleanBoard();
                    this.stepLock = FieldTypes.WHITE;
                }
                this.checkWinner(row);
                if(this.stepLock != FieldTypes.EMPTY){
                    this.label.setText("  " + this.stepLock.name() + " player steps");
                }else{
                    this.label.setText("  Click on the Menu and start a new game or exit!");
                }
            }
            case ATTACKED_BLACK -> {
                if(this.board.findStepField()[0] != -1 && this.board.findStepField()[1] != -1 &&
                        this.board.getBoard()[board.findStepField()[0]][board.findStepField()[1]] == FieldTypes.WHITE_STEP){
                    this.board.setBoard(row, column, FieldTypes.WHITE);
                    this.board.setBoard(board.findStepField()[0], board.findStepField()[1], FieldTypes.EMPTY);
                    this.board.cleanBoard();
                    this.stepLock = FieldTypes.BLACK;
                }
                this.checkWinner(row);
                if(this.stepLock != FieldTypes.EMPTY){
                    this.label.setText("  " + this.stepLock.name() + " player steps");
                }else{
                    this.label.setText("  Click on the Menu and start a new game or exit!");
                }
            }
        }
        this.updateBoard();
    }
    
    /**
     * Checking end of game
     * @param row 
     */
    private final void checkWinner(int row){
        if(row == this.fields.length-1 && this.stepLock == FieldTypes.WHITE){
            this.updateBoard();
            JOptionPane.showMessageDialog(this.panel, "Black Player have won the game!", 
                    "Black Player Victory", JOptionPane.PLAIN_MESSAGE);
            this.stepLock = FieldTypes.EMPTY; // End of Game
        }else if(row == 0 && this.stepLock == FieldTypes.BLACK){
            this.updateBoard();
            JOptionPane.showMessageDialog(this.panel, "White Player have won the game!", 
                    "White Player Victory", JOptionPane.PLAIN_MESSAGE);
            this.stepLock = FieldTypes.EMPTY; // Endof Game
        }
    }
}
