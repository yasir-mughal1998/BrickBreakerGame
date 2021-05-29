/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreakergame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Hamza Razzaq
 */
public class BricksBoard {
    public int board[][];
    public int brickWidth;
    public int brickHeight;
    
    public BricksBoard(int row,int col)
    {
       board = new int[row][col];
       for(int i=0;i<board.length;i++)
       {
           for(int j=0;j<board[0].length;j++)
           {
               board[i][j]=1;
           }
       }
      brickWidth=540/col;
       brickHeight=120/row;
    }
    
     
    public void draw(Graphics2D g)
    {
        for(int i=0;i<board.length;i++)
       {
           for(int j=0;j<board[0].length;j++)
           {
               if(board[i][j]>0)
               {
                   g.setColor(Color.GREEN);
                   g.fillRect(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight);
                   
                   g.setStroke(new BasicStroke(4));
                   g.setColor(Color.WHITE);
                   g.drawRect(j*brickWidth+80,i*brickHeight+50,brickWidth,brickHeight);
               }
           }
       }
    }
    public void setValue(int val,int row,int col)
    {
        board[row][col] = val;
    }
}
