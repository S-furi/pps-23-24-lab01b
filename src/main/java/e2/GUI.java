package e2;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import e2.cell.Cell;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton,Pair<Integer,Integer>> buttons = new HashMap<>();
    private final Logics logics;
    
    public GUI(final int size, final int numberOfMines) {
        this.logics = new LogicsImpl(size, numberOfMines);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,panel);
        
        ActionListener onClick = (e)->{
            final JButton bt = (JButton)e.getSource();
            final Pair<Integer,Integer> pos = buttons.get(bt);
            boolean aMineWasFound = logics.hasMine(pos);
            if (aMineWasFound) {
                quitGame();
                JOptionPane.showMessageDialog(this, "You lost!!");
            } else {
                this.logics.click(pos);
                drawBoard();            	
            }
            boolean isThereVictory = this.logics.isThereVictory(); // call the logic here to ask if there is victory
            if (isThereVictory){
                quitGame();
                JOptionPane.showMessageDialog(this, "You won!!");
                System.exit(0);
            }
        };

        MouseInputListener onRightClick = new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final JButton bt = (JButton)e.getSource();
                if (bt.isEnabled()){
                    final Pair<Integer,Integer> pos = buttons.get(bt);
                    // call the logic here to put/remove a flag
                    logics.toggleFlag(pos);
                    if (logics.getCellAtPosition(pos).get().hasFlag()) {
                        bt.setText("X");
                    } else {
                        bt.setText("");
                    }
                }
                drawBoard(); 
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                jb.addActionListener(onClick);
                jb.addMouseListener(onRightClick);
                this.buttons.put(jb,new Pair<>(i,j));
                panel.add(jb);
            }
        }
        this.drawBoard();
        this.setVisible(true);
    }
    
    private void quitGame() {
        this.drawBoard();
        final List<Pair<Integer, Integer>> mines = this.logics.getMinesPositions();
    	for (final Map.Entry<JButton, Pair<Integer, Integer>> entry: this.buttons.entrySet()) {
            // call the logic here
            // if this button is a mine, draw it "*"
            // disable the button
            entry.getKey().setEnabled(false);
            if (mines.contains(entry.getValue())) {
                entry.getKey().setText("*");
            }
    	}
    }

    private void drawBoard() {
        for (final Map.Entry<JButton, Pair<Integer, Integer>> entry: this.buttons.entrySet()) {
            // call the logic here
            // if this button is a cell with counter, put the number
            // if this button has a flag, put the flag
            final Cell cell = this.logics.getCellAtPosition(entry.getValue()).orElseThrow(() ->
                new IllegalArgumentException("Cannot find a cell in position: " + entry.getValue())
            );

            if (cell.isDisabled()) {
                continue;
            }

            if (cell.isClicked()) {
                final JButton button = entry.getKey();
                button.setEnabled(false);
                final int adjacentMinesNumber = this.logics.getNumberOfAdjacentMines(cell.getPosition());
                button.setText(adjacentMinesNumber == 0 ? "" : "" + adjacentMinesNumber);
            }
    	}
    }
}
