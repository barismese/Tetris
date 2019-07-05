import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

//This class represent the playing field of tetris. It initilazes the board with empty Box objects with its constructor
//and then it uses its several method to manipulate the board state.
public class Grid 
{
  //---------SCREEN DIMENSIONS-----------------
  final int FRAME_WIDTH = 1400;
  final int FRAME_HEIGHT = 800;
  final int FIELD_WIDTH = 500;
  final int FIELD_HEIGHT = 650;
  final int FIELD_X = FRAME_WIDTH/2-FIELD_WIDTH/2;
  final int FIELD_Y = 20;
  //--------------------------------------------
  //Arraylist that holds the boxes of the playing field.
  Box[][] grid = new Box[13][10];
  
  //Constructor that initilazes the playing field.
  public Grid()
  {
    for(int i = 0; i < 13 ; i++)
    {
      for(int j = 0; j < 10; j++)
      {
        grid[i][j] = new Box(FIELD_X + j*50, FIELD_Y + i*50,false);
        
      }  
    }
  }
  //Sets the piece to the grid
  public void setGrid(int x,int y,TetrisPieces piece)
  {
    for(int i = y; i < piece.getRowLength()+y; i++)
    {
      for(int j = x; j < piece.getColLength(0)+x; j++)
      {
        if(i >= 0 && j >= 0 && i<13 && j<10)
        {
          if(grid[i][j].isBoxFill() && (piece.getPiece(i-y,j-x) == false))
          {
            
          }
          else{
            grid[i][j].fill(piece.getPiece(i-y,j-x));
            grid[i][j].setBoxColor(piece.getColor());
          }
          
          grid[i][j].setBoxFalling(piece.getPiece(i-y,j-x));
        }
      } 
    }
  }
  //Deletes all the boxes with falling attribute so it will look like they have moved in the next loop.
  public void clearGrid()
  {
    for(int i = 0; i < 13 ; i++)
    {
      for(int j = 0; j < 10; j++)
      {
        if(grid[i][j].isFalling() == true)
        {
          grid[i][j].fill(false);
        }
      }  
    }
  }
  //I use this method to make a piece not falling, so it won't get deleted in the next cycle of repaint loop
  public void setStable(int x,int y,TetrisPieces piece)
  {
    for(int i = y; i < piece.getRowLength()+y; i++)
    {
      for(int j = x; j < piece.getColLength(i-y)+x; j++)
      {
        if(i >= 0 && j >= 0 && i<20 && j<10)
        {
          if(piece.getPiece(i-y,j-x))
          {
            grid[i][j].setBoxFalling(false);
            grid[i][j].fill(true);
          }
        }
      } 
    }
  }
  //Returns the Box object so other classes can use Box class methods as well. 
  public Box getBox(int x,int y)
  {
    return grid[y][x];
  }
  
}