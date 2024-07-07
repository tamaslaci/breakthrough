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
public class EmptyStepField extends Field {
    private ImageIcon image = new ImageIcon("icon/pawn-step.jpg");
    public EmptyStepField(Board board, int row, int column){
        super(board, row, column);
        this.type = FieldTypes.EMPTY_STEP;
        this.board.setBoard(row, column, this.type);
        this.setIcon(image);
        this.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e){
                board.getBoardGui().handleFieldEvent(row, column);
            }
        });
    }
}
