/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreakergame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.JPanel;
import javax.swing.Timer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Hamza Razzaq
 */
public class PlayGame extends JPanel implements KeyListener,ActionListener{
    
    private boolean play = false;
    private Timer timer;
    
    private int speedDelay=1;    
    private int bricksTotal=32;
    private int score=0;
   
    private int ballposx=120;
    private int ballposy=350;
    
    private int paddlepos=310;
    
    private int balldirx=-1;
    private int balldiry=-1;
    private int level = 1;
    private BricksBoard board1;
    
    private static PlayGame obj = null;
    
    private PlayGame()
    {
        board1 = new BricksBoard(4,8);
      
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(speedDelay,this);
      
        timer.start();
    }
    
    public static PlayGame getInstance()
    {
        if(obj==null)
        {
            obj = new PlayGame();
        }
        return obj;
    }
    
    public void paint(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(1,1,692,592);
        
        board1.draw((Graphics2D)g);
       
        
        g.setColor(Color.GREEN);
        g.fillRect(0,0,3,592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691,0,3,592);
        
        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman",Font.BOLD,30));
        g.drawString("Score:"+score,540,30);
        g.drawString("Level:"+level, 30, 30 );
        
        g.setColor(Color.RED);
        g.fillRect(paddlepos, 550, 100, 8);
        
        g.setColor(Color.yellow);
        g.fillOval(ballposx,ballposy,20,20);
        
        if( bricksTotal <= 0)
        {
                play=false;
                balldirx = 0;
                balldiry = 0;
                g.setColor(Color.YELLOW);
                g.setFont(new Font("TimesRoman",Font.BOLD,30));
                g.drawString("Hii!!! You Won The Game XD ",150,300);
                g.drawString("Press (N) for  the Next Level Dude",130,350);
        }
        
        if(ballposy>570)
        {
            play=false;
            balldirx = 0;
            balldiry = 0;
            g.setColor(Color.YELLOW);
            g.setFont(new Font("TimesRoman",Font.BOLD,30));
            g.drawString("Game Over, Your Score Is:"+score,150,300);
            g.drawString("Press Enter to Restart the Game",130,350);
            playAudio("Game-Over.wav");
            timer.stop();
            
        }
        g.dispose();
    }
    
     @Override
    public void actionPerformed(ActionEvent ae) 
    {
        timer.start();
        if(play)
        {
            if(new Rectangle(ballposx,ballposy,20,20).intersects(new Rectangle(paddlepos,550,100,8)))
            {
                playAudio("ballPaddle.wav");
                balldiry = -balldiry;
            }
            for(int i=0;i<board1.board.length;i++)
            {
                for(int j=0;j<board1.board[0].length;j++)
                {
                    if(board1.board[i][j]>0)
                    {
                        int brickxpos=j*board1.brickWidth+80;
                        int brickypos=i*board1.brickHeight+50;
                        int brickWidth=board1.brickWidth;
                        int brickHeight=board1.brickHeight;
                        
                        Rectangle BrickRect = new Rectangle(brickxpos,brickypos,brickWidth,brickHeight);
                        Rectangle BallRect = new Rectangle (ballposx,ballposy,20,20);
                        if(BallRect.intersects(BrickRect))
                        {
                            playAudio("hit.wav");
                            board1.setValue(0, i, j);
                            bricksTotal--;
                            score+=10;
                            
                            if(ballposx+18 <= BrickRect.x || ballposx+1 >= BrickRect.x+brickWidth)
                            {
                               balldirx = -balldirx;
                               
                            }
                            else
                            {
                                balldiry = -balldiry;
                            }
                            
                        }
                    }
                }
            }
        ballposx+=balldirx;
        ballposy+=balldiry;
            if(ballposx <= 0)
            {
                balldirx = -balldirx;
            }
            if(ballposy <= 0)
            {
                balldiry = -balldiry;
            }
            if(ballposx >= 670)
            {
                balldirx = -balldirx;
            }
                    
        }
        repaint(); 
        
    }
    
    
    @Override
    public void keyTyped(KeyEvent ke) { }
    
      @Override
    public void keyReleased(KeyEvent ke) {}


    @Override
    public void keyPressed(KeyEvent ke) {
     if(ke.getKeyCode()== KeyEvent.VK_RIGHT){
         if(paddlepos>=590)
         {
             paddlepos=590;
         }
         else{
             moveRight();
         }
     }
     
     if(ke.getKeyCode()== KeyEvent.VK_LEFT){
         if(paddlepos<=20)
         {
             paddlepos=20;
         }
         else{
             moveLeft();
         }
     }
     
     if(ke.getKeyCode()== KeyEvent.VK_ENTER)
     {
         if(!play)
         {
             play = true;
             ballposx = 120;
             ballposy = 350;
             balldirx = -1;
             balldiry = -1;
             paddlepos = 310;
             score = 0;
             
             bricksTotal = 32;
             
             board1 = new BricksBoard(4,8);
             timer.start();
             
             repaint(); 
         }
     }
     if(ke.getKeyCode()== KeyEvent.VK_N)
     {
         if(!play)
         {
             play = true;
             ballposx = 120;
             ballposy = 350;
             balldirx = -1;
             balldiry = -1;
             paddlepos = 310;
             level++;
             score = 0;
             
             bricksTotal = 25;
             
             board1 = new BricksBoard(5,5);
             
             repaint(); 
         }
     }
    
    }
 public void moveRight()
     {
         play = true;
         paddlepos+=20;
         
     }
 public void moveLeft()
     {
         play = true;
         paddlepos-=20;
         
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
