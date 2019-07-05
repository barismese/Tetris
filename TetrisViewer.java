import javax.swing.*;
import java.util.*;
import java.awt.*;

public class TetrisViewer extends JPanel
{
  public static void main(String[] args) 
  {
    JFrame frame = new JFrame();
    
    //sizes of the frame
    final int FRAME_WIDTH = 1400;
    final int FRAME_HEIGHT = 800;

    frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    
    //name of the frame
    frame.setTitle("Tetris The Game");
    
    //closes the program when frame is closed
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
    //sets the location of the frame
    frame.setLocation(0,0);
    
    //sets the frame visible
    frame.setVisible(true);
    
    //sets the background color for the app
    frame.setBackground(Color.GRAY);
    
    //Add the component to the frame
    frame.add(new TetrisComponent());
    
    
  }
}

