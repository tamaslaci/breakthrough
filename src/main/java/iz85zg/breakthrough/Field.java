/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iz85zg.breakthrough;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

/**
 *
 * @author iz85zg
 */
public abstract class Field extends JButton{
    protected int row;
    protected int column;
    protected FieldTypes type;
    protected Board board;
    
    public Field(Board board, int row, int column){
        super();
        this.row = row;
        this.column = column;
        this.type = FieldTypes.EMPTY;
        this.board = board;
        this.board.setBoard(row, column, type);
        this.setPreferredSize(new Dimension(70, 70));
        this.setBackground(Color.WHITE);
        this.setBorder(new LineBorder(Color.BLACK, 2, true));
    }
    
    public int getRow(){
        return this.row;
    }
    
    public int getColumn(){
        return this.column;
    }
}
