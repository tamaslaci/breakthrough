/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iz85zg.breakthrough;

/**
 *
 * @author iz85zg
 */
public class Board {
    private final BoardGUI boardGui;
    private final FieldTypes[][] board;
    private final BoardTypes type;
    
    public Board(BoardTypes type, BoardGUI boardGui){
        this.boardGui = boardGui;
        this.type = type;
        if(this.type == BoardTypes.SMALL){
            this.board = new FieldTypes[6][6];
        }else if(this.type == BoardTypes.LARGE){
            this.board = new FieldTypes[10][10];
        }else{
            this.board = new FieldTypes[8][8];
        }
        initializeBoard();
    }
    
    private void initializeBoard(){
        for(int i=0; i<this.board.length; ++i){
            for(int j=0; j<this.board[i].length; ++j){
                if(i < 2){
                    board[i][j] = FieldTypes.BLACK;
                }else if(i > 1 && i < this.board.length-2){
                    board[i][j] = FieldTypes.EMPTY;
                }else{
                    board[i][j] = FieldTypes.WHITE;
                }
            }
        }
    }
    
    public final FieldTypes[][] getBoard(){
        return this.board;
    }
    
    public final BoardGUI getBoardGui(){
        return this.boardGui;
    }
    
    /**
     * Setting the modell board fields during operation
     * @param row
     * @param column
     * @param field 
     */
    public final void setBoard(int row, int column, FieldTypes field){
        this.board[row][column] = field;
    }
    
    /**
     * Deciding that a type of Field is present on the board
     * @param type
     * @return boolean
     */
    public final boolean find(FieldTypes type){
        for(int i=0; i<this.board.length; ++i){
            for(int j=0; j<this.board[i].length; ++j){
                if(this.board[i][j] == type){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Finding the position of stepping Fields
     * @return int[2]
     */
    public final int[] findStepField(){
        int[] result = new int[2];
        for(int i=0; i<this.board.length; ++i){
            for(int j=0; j<this.board[i].length; ++j){
                if(this.board[i][j] == FieldTypes.WHITE_STEP || this.board[i][j] == FieldTypes.BLACK_STEP){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        result[0] = -1;
        result[1] = -1;
        return result;
    }
    
    /**
     * Reseting the board for the next move
     */
    public final void cleanBoard(){
        for(int i=0; i<this.board.length; ++i){
            for(int j=0; j<this.board[i].length; ++j){
                switch(this.board[i][j]){
                    case EMPTY_STEP -> {
                        this.board[i][j] = FieldTypes.EMPTY;
                    }
                    case WHITE_STEP -> {
                        this.board[i][j] = FieldTypes.WHITE;
                    }
                    case BLACK_STEP -> {
                        this.board[i][j] = FieldTypes.BLACK;
                    }
                    case ATTACKED_WHITE -> {
                        this.board[i][j] = FieldTypes.WHITE;
                    }
                    case ATTACKED_BLACK -> {
                        this.board[i][j] = FieldTypes.BLACK;
                    }
                }
            }
        }
    }
}
