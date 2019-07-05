import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class TetrisComponent extends JComponent implements ActionListener
{
  //------------SCREEN CONSTANTS----------------------
  final int FRAME_WIDTH = 1400;
  final int FRAME_HEIGHT = 800;
  final int FIELD_WIDTH = 500;
  final int FIELD_HEIGHT = 650;
  final int FIELD_X = FRAME_WIDTH/2-FIELD_WIDTH/2;
  final int FIELD_Y = 20;
  //-----------------------------------------------
  
  
  //----------Calculation Variables----------------
  //Initilazes the grid that the game will be played on.
  Grid grid = new Grid();
  
  // Y-value variable
  int counter = -4;
  
  // X-value variable
  int xpiece = 3;
  
  //The integer value that stores user input for x values
  int dx = 0;
  
  //The integer value that stores user input for y values
  int dy = 0;
  
  //How many lines the player has eliminated
  int score = 0;
  
  //Initilazes the first piece
  private TetrisPieces piece = new TetrisPieces();
  
  //Initilazes the second piece(a.k.a next piece)
  TetrisPieces piece2 = new TetrisPieces(piece.getColor());
  
  //Timer is the thread that updates the component
  Timer timer;
  
  //Initilazes the grid that next piece will be showned on. 
  Box[][] nextGrid = new Box[4][4];
  
  //Variable that I use while checking if a row is filled
  boolean isRowFilled;
  
  //Variable that I use while checking if the place piece going to move is empty or not
  boolean canMove;
  
  //Variable that I use while checking if piece can fall
  boolean canFall;
  
  //This variable is used to check if piece is going to collide with anything if it were to turned.
  boolean canRotate;
  
  //Boolean that controls if the game is paused
  boolean isPaused = true;
  
  //Boolean that changes when the up key is pressed so that piece can rotate
  boolean isRotated = false;
  
  //It moves the piece down every other event so the user can go sideways faster that the actual piece falls.
  boolean fallTurn = true;
  
  //I use this object to see if the rotated piece would collide with anything. This object is not actually represented on the screen.
  TetrisPieces nextPiece;
  //-----------------------------------------------
  
  //In this constructor the timer is started and the key listener is activated.
  //Also initilaze the nextGrid where the following piece is shown.
  public TetrisComponent() 
  {
    setFocusable(true);
    addKeyListener(new TAdapter());
   // piece.setEmpty();
    timer = new Timer(150,this);
    timer.start();
    initilazeNextGrid();
  }
  //This is the code that is called whenever timer class fires an event.
  //It checks if the player pauses the game, if the piece has collided into borders or another piece.
  public void actionPerformed(ActionEvent ae) {
    if(!isPaused)
    {
    grid.clearGrid();
    //Calculations
    
    if(fallTurn)
    {
      counter++;   
    }
    //Collision of bottom 
    if(counter>(13-piece.getRowLength()))
    {
      newPiece(); 
    }
    
    tryMove();
    detectBottom();
    
    if(isRotated)
    {
      
      nextPiece = new TetrisPieces(piece.getPieceTag(),piece.getColor());
      canRotate = true;
      for(int i = counter; i < nextPiece.getRowLength()+counter; i++)
      {
        for(int j = xpiece; j < nextPiece.getColLength(0)+xpiece; j++)
        {
          if(i >= 0 && j >= 0 && i<13 && j<10)
          { 
            if(grid.getBox(j,i).isBoxFill() && nextPiece.getPiece(i-counter,j-xpiece))
            {
              canRotate = false;
            }          
          }
        } 
      }
      if(canRotate)
      {
        piece= new TetrisPieces(piece.getPieceTag(),piece.getColor());
      }
    }
    
    
    //Collison detection
    
    
    rowFilled();
    //Sets the pieces to the grid
    grid.setGrid(xpiece,counter,piece);
    setNextGrid();
    
    System.out.println(FIELD_HEIGHT);
    System.out.println(counter + "//" + xpiece);
    repaint(); 
    dx = 0;
    dy = 0;
    isRotated = false;
    if(fallTurn)
    {
      fallTurn = false;
    }
    else
    {
      fallTurn = true;
    }
    }
  }
  
  public void paintComponent(Graphics g)
  {
    
    
    //-------------------------SETUP----------------------------------------------
    //Constructs a new Graphics2D object(our brush in a way).
    Graphics2D g2 = (Graphics2D) g;
    //Constructs a black playing field with red outlines.
    g2.setColor(Color.RED);
    g2.fillRect((FRAME_WIDTH/2-FIELD_WIDTH/2)-5,15,FIELD_WIDTH+10,FIELD_HEIGHT+10);
    g2.setColor(Color.BLACK);
    g2.fillRect(FRAME_WIDTH/2-FIELD_WIDTH/2,20,FIELD_WIDTH,FIELD_HEIGHT);
    //Constructs the area where the next tile is displayed
    g2.setFont( new Font("Verdana", Font.PLAIN,30));
    g2.drawString("NEXT TILE",(FRAME_WIDTH/2+FIELD_WIDTH/2)+120,40);
    g2.setColor(Color.RED);
    g2.fillRect((FRAME_WIDTH/2+FIELD_WIDTH/2)+70,50,280,220);
    g2.setColor(Color.BLACK);
    g2.fillRect((FRAME_WIDTH/2+FIELD_WIDTH/2)+75,55,270,210);
    //Draws the name of the game and the names of the creators
    g2.setColor(Color.BLUE);
    g2.setFont( new Font("Verdana", Font.PLAIN,100));
    g2.drawString("TETRIS",20,200);
    g2.setFont( new Font("Verdana", Font.PLAIN,20));
    g2.drawString("The Game",150,250);
    g2.drawString("by",190,300);
    g2.drawString("Murat Baris Mese  and  Arda T�rkmen",20,340);
    //Draws the level player at and his/her score
    g2.setColor(Color.RED);
    g2.setFont( new Font("Verdana", Font.PLAIN,30));
    g2.drawString("SCORE:",(FRAME_WIDTH/2+FIELD_WIDTH/2)+70,350);
    g2.drawString(""+score,(FRAME_WIDTH/2+FIELD_WIDTH/2)+200,350);
    //----------------------------------SETUP-----------------------------------------  
    
    //Draws the current grids
    //Main grid
    for(int d = 0; d < 13; d++)
    {
      for(int j = 0; j < 10; j++)
      {
        
        g2.setColor(Color.BLACK);
        if(grid.getBox(j,d).isBoxFill())
        {
          g2.setColor(grid.getBox(j,d).getBoxColor());
        }
        
        g2.fill(grid.getBox(j,d).draw());
      }  
    }
    
    //Draws next tile grid
    for(int d = 0; d < 4; d++)
    {
      for(int j = 0; j < 4; j++)
      {
        
        g2.setColor(Color.BLACK);
        if(nextGrid[j][d].isBoxFill())
        {
          g2.setColor(nextGrid[j][d].getBoxColor());
        }
        
        g2.fill(nextGrid[j][d].draw());
      }  
    }
    
    //Draws the GAME OVER sign if game is over
    if(counter==-4)
    {
      if(grid.getBox(xpiece,counter+4).isBoxFill() || grid.getBox(xpiece+1,counter+4).isBoxFill()|| grid.getBox(xpiece+2,counter+4).isBoxFill() || grid.getBox(xpiece+3,counter+4).isBoxFill())
      {
        g2.setColor(Color.RED);
        g2.setFont( new Font("Verdana", Font.PLAIN,80));
        g2.drawString("GAME OVER",430,300); 
        System.out.println("ANAN");
        timer.stop();
      }
    }
    
   
    for(int d = 0; d < 13; d++)
    {
      g2.setColor(Color.BLACK);
      g2.drawLine(FRAME_WIDTH/2-FIELD_WIDTH/2,20+(d*50),FRAME_WIDTH/2+FIELD_WIDTH/2,20+(d*50));
    }
    for(int j = 0; j < 10; j++)
    {
      g2.setColor(Color.BLACK);
      g2.drawLine(FRAME_WIDTH/2-FIELD_WIDTH/2+(50*j),20,FRAME_WIDTH/2-FIELD_WIDTH/2+(50*j),20+FIELD_HEIGHT);
    }
    //Draws the paused label if the game is paused
     if(isPaused)
    {
      g2.setColor(Color.RED);
      g2.setFont( new Font("Verdana", Font.PLAIN,80));
      g2.drawString("PAUSED",530,300); 
      g2.setFont( new Font("Verdana", Font.PLAIN,40));
      g2.drawString("Press Space to Start",450,400);
    }
  }
  //Fill the NextGrid with empty objects
  public void initilazeNextGrid()
  {
    for(int i = 0; i < 4 ; i++)
    {
      for(int j = 0; j <4; j++)
      {
        nextGrid[i][j] = new Box((FRAME_WIDTH/2+FIELD_WIDTH/2)+95+j*50,60+i*50,false);
      }  
    }
  }
  
  //Sets the next grid
  public void setNextGrid()
  {
    
    for(int i = 0; i < 4; i++)
    {
      if(i<piece2.getRowLength())
      {
        for(int j = 0; j < 4; j++)
        {
          if(j<piece2.getColLength(i))
          {
            nextGrid[i][j].fill(piece2.getPiece(i,j));
            nextGrid[i][j].setBoxColor(piece2.getColor());
          }
          else
          {
            nextGrid[i][j].fill(false);
          }
        }
      }
      else
      {
        for(int j = 0; j < 4; j++)
        {
          nextGrid[i][j].fill(false);
        }
      }
    }
  }
  
  //The function that moves the piece
  public void tryMove()
  {
    //Checks the Y value to see if there is more place for piece to go
    if(counter>-1 && counter+dy<13)
    {
      counter = counter + dy;
      
    }
    //Checks the X value to see if there is more place for piece to go
    canMove = true;
    
    //This if checks to keep the piece is still in the playing field
    if(xpiece+dx >= 0 && (xpiece+dx <=10-piece.getColLength(0)))
    {
      for(int i = counter; i < piece.getRowLength()+counter; i++)
      {
        for(int j = xpiece+dx; j < piece.getColLength(0)+xpiece+dx; j++)
        {
          if(i >= 0 && j >= 0 && i<13 && j<10)
          { 
            if(grid.getBox(j,i).isBoxFill() && piece.getPiece(i-counter,j-xpiece-dx))
            {
              canMove = false;
            }          
          }
        } 
      }
      if(canMove)
      {
        xpiece = xpiece + dx;    
      }
    }
  }
  //This method checks if falling piece can fall more or will it collide with a stationary piece.
  public void detectBottom()
  {
    canFall = true;
    for(int j = xpiece; j < piece.getColLength(0)+xpiece; j++)
    {
      if(counter>-1)
      {
        if(grid.getBox(j,counter+piece.getRowLength()-1).isBoxFill() && piece.getPiece(piece.getRowLength()-1,j-xpiece))
        {
          canFall = false;
        }
      }
    }
    
    if(canFall == false)
    {
      newPiece();
    }
  }
  //This method checks if there are any rows filled, if there are it deletes them and updates the board.
  public void rowFilled()
  {
    for(int i = 12; i > -1 ; i--)
    {
      isRowFilled = true; 
      for(int j = 0; j < 10; j++)
      {
        if(grid.getBox(j,i).isBoxFill() == false)
        {
          isRowFilled = false;
        } 
      }
      if(isRowFilled)
      {
        score++;
        for(int j = 0; j < 10; j++)
        {
          grid.getBox(j,i).fill(false);
        }
        for(int d = i;d>0;d--)
        {
          for(int j = 0; j < 10; j++)
          {
            grid.getBox(j,d).fill(grid.getBox(j,d-1).isBoxFill());
          }
        }
        newPiece();
      }
    }
  }
  //I use this method to call for a new piece whenever game need one.
  public void newPiece()
  {
    grid.setStable(xpiece,counter-1,piece);
    counter = -4;
    xpiece = 3;
    piece = piece2;
    piece2 = new TetrisPieces(piece.getColor());  
  }
//KeyListener class where I set what each key does.
  class TAdapter extends KeyAdapter {
    
    public void keyPressed(KeyEvent e) {
      
      int keycode = e.getKeyCode();
      switch (keycode) {
        case KeyEvent.VK_LEFT:
          dx = -1;
          break;
        case KeyEvent.VK_RIGHT:
          dx = 1;
          break;
        case KeyEvent.VK_UP:
          isRotated = true;
          break;
        case 32:
          if(isPaused)
          {
          isPaused = false;
          }
          else
          {
            isPaused = true;
          }
          break;
      }
      System.out.println("Key Pressed: " + keycode);
    }
  }
}