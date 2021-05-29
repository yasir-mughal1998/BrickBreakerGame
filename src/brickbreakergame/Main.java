
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreakergame;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Hamza Razzaq
 */
public class Main {
    public static void main(String[] args) {
        
	Menu m = new Menu();
	while(!m.can_exit) {}
	
	
        JFrame f = new JFrame();		
        PlayGame play = PlayGame.getInstance();
          Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
          f.setBounds(((int)d.getWidth()-700)/2,((int)d.getHeight()-600)/2,700,600);
          f.setTitle("BrickBreakerGame");
          f.setResizable(false);
          f.setVisible(true);
	  f.addKeyListener(play);
          f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          f.add(play);
	  f.requestFocus();
    }
    
}
