/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iz85zg.breakthrough;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/**
 *
 * @author iz85zg
 */
public class WhitePawn extends Field{
    private ImageIcon pawn = new ImageIcon("icon/white-pawn.jpg");
    public WhitePawn(Board board, int row, int column){
        super(board, row, column);
        this.type = FieldTypes.WHITE;
        this.board.setBoard(row, column, this.type);
        this.setIcon(pawn);
        this.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e){
                board.getBoardGui().handleFieldEvent(row, column);
            }
        });
    }
}
