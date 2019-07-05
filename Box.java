import javax.swing.*;
import java.util.*;
import java.awt.*;

//This class is used to create the object that is the building block of the whole game.
//The object is a simple rectangle with fixed size and location  but
//other classes can change the boxes other attributes to manipulate the field.
public class Box
{
  //Attributes
  private Rectangle box1;//The rectangle itself
  private Color scolor; //Color of the filled rectangle
  private int xpos; //X position of the rectangle
  private int ypos; //Y position of the rectangle
  private boolean isBoxFilled; //whatever the box is filled or not
  private boolean isBoxFalling;//whatever the box belongs to a falling piece or not
  
  //Constructor of the class
  //It requires the x and y  position of the rectangle and also whatever it is filled or not
  public Box(int x,int y,boolean boxFilled)
  {
    xpos = x;
    ypos = y;
    isBoxFilled = boxFilled;
    scolor = Color.BLACK;
    isBoxFalling = false;
  }
  //Returns the rectangle object so it can be drawn in another class.
  public Rectangle draw()
  {
    box1 = new Rectangle(xpos,ypos,50,50);
    return box1;
  }
  //I use this method to set the box filled or empty
  public void fill(boolean filled)
  {
    isBoxFilled = filled;
  }
  //Returns whatever the box is filled or not
  public boolean isBoxFill()
  {
    return isBoxFilled;
  }
  //Sets the color of the box
  public void setBoxColor(Color boxcolor)
  {
    scolor = boxcolor;
  }
  //Returns the color of box
  public Color getBoxColor()
  {
    return scolor;
  }
  //Changes whatever the box is falling or not
  public void setBoxFalling(boolean falling)
  {
     isBoxFalling = falling;
  }
  //Returns whatever the box is falling or not
  public boolean isFalling()
  {
    return isBoxFalling;
  }
}