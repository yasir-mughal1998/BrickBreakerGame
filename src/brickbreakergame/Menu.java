package brickbreakergame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.JComponent;
import javax.swing.JFrame;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Menu implements KeyListener,ActionListener {
    
    volatile boolean can_exit;
    JFrame f;    
    public Menu() {
	can_exit = false;
	
	f = new JFrame();
	Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
	f.setBounds(((int)d.getWidth()-700)/2,((int)d.getHeight()-600)/2,700,600);
	f.setTitle("BrickBreakerGame");
	f.setResizable(false);
	f.setVisible(true);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.addKeyListener(this);
	
	Component comp = new Component();
        Container contentPane = (Container) f.getContentPane().add(comp);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
	if(e.getKeyCode()== KeyEvent.VK_ENTER){
	    can_exit = true;
	    f.dispose();
	}
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
        
}

class Component extends JComponent {

    @Override
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	g.setColor(Color.black);	
	g.fillRect(0,0,700,600);	
	g.setColor(Color.white);
	g.setFont(new Font("Verdana",Font.PLAIN,40));
	g.drawString("PRESS ENTER TO PLAY", 130, 300);
        playAudio("start.wav");
     }
    
    public void playAudio(String filePath)
    {
        InputStream music;
        try {
            music = new FileInputStream(new File(filePath));
            AudioStream audio = new AudioStream(music);
            AudioPlayer.player.start(audio);
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}