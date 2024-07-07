/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iz85zg.breakthrough;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author iz85zg
 */
public class BreakThroughGUI {
    private JFrame frame;
    private BoardGUI board;
    
    public BreakThroughGUI(){
        this.frame = new JFrame("Break Through Game");
        this.frame.setLayout(new BorderLayout());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.board = new BoardGUI(this.frame, 8);
        this.frame.getContentPane().add(this.board.getPanel(), BorderLayout.CENTER);
        this.frame.getContentPane().add(this.board.getLabel(), BorderLayout.SOUTH);
        
        JMenuBar menuBar = new JMenuBar();
        this.frame.setJMenuBar(menuBar);
        JMenu menu = new JMenu("Menu");
        menuBar.add(menu);
        JMenu game = new JMenu("New game");
        menu.add(game);
        JMenuItem small = new JMenuItem("Small table");
        small.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e){
                frame.getContentPane().remove(board.getPanel());
                frame.getContentPane().remove(board.getLabel());
                board = new BoardGUI(frame, 6);
                frame.getContentPane().add(board.getPanel(), BorderLayout.CENTER);
                frame.getContentPane().add(board.getLabel(), BorderLayout.SOUTH);
                frame.pack();
                frame.setLocationRelativeTo(null);
            }
        });
        JMenuItem medium = new JMenuItem("Medium table");
        medium.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e){
                frame.getContentPane().remove(board.getPanel());
                frame.getContentPane().remove(board.getLabel());
                board = new BoardGUI(frame, 8);
                frame.getContentPane().add(board.getPanel(), BorderLayout.CENTER);
                frame.getContentPane().add(board.getLabel(), BorderLayout.SOUTH);
                frame.pack();
                frame.setLocationRelativeTo(null);
            }
        });
        JMenuItem large = new JMenuItem("Large table");
        large.addActionListener(new ActionListener(){
            @Override public void actionPerformed(ActionEvent e){
                frame.getContentPane().remove(board.getPanel());
                frame.getContentPane().remove(board.getLabel());
                board = new BoardGUI(frame, 10);
                frame.getContentPane().add(board.getPanel(), BorderLayout.CENTER);
                frame.getContentPane().add(board.getLabel(), BorderLayout.SOUTH);
                frame.pack();
                frame.setLocationRelativeTo(null);
            }
        });
        game.add(small);
        game.add(medium);
        game.add(large);
        JMenuItem exit = new JMenuItem(new AbstractAction(){
            @Override public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        exit.setText("Exit");
        menu.add(exit);
        
        ImageIcon icon = new ImageIcon("icon/icon.jpg");
        this.frame.setIconImage(icon.getImage());
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
        this.frame.setResizable(false);
    }
}
