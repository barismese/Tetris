import javax.swing.*;
import java.util.*;
import java.awt.*;

public class TetrisPieces
{
  Random rgen = new Random();//Random number generator
  private boolean[][] currentPiece = new boolean[4][4];//This 2d boolean array stores the values that show where piece is filled 
  private boolean[][] newPiece = new boolean[4][4];//The piece preset I use to see if rotated object would collide with anything.
  private int rint;//Random number generator stores it's output in this variable.
  private int pieceTag;//index numbers of the piece presets. This class uses this value to recognize which piece is on board
  private Color scolor;//color of the piece

  
  //-----------PIECE PRESETS---------------------------
  //I-Pieces
  //  Original I piece
  public boolean i_Piece[][] = { 
    {true,true,true,true},
  };
  //  90 degree rotated I piece
  public boolean rotated_i_Piece[][] = { 
    {true},
    {true},
    {true},
    {true},
  };
  
  //J-Pieces
  //  Original J piece
  public boolean j_Piece[][] = {
    {false, true},
    {false, true},
    {true, true},
  };
  //  90 degree rotated J piece
  public boolean rotated_j_Piece[][] = {
    {true, false, false},
    {true, true, true},
  };
  //  180 degree rotated J piece
  public boolean rotated2_j_Piece[][] = {
    {true, true},
    {true, false},
    {true, false},
  };
  //  270 degree rotated J piece 
  public boolean rotated3_j_Piece[][] = {
    {true, true, true},
    {false, false, true},
  };
  
  //L-Pieces
  //  Original L piece
  public boolean l_Piece[][] = {
    {true, false},
    {true, false},
    {true, true},
  };
  // 90 degree rotated L piece
  public boolean rotated_l_Piece[][] = {
    {true, true, true},
    {true, false, false},
  };
  // 180 degree rotated L piece
  public boolean rotated2_l_Piece[][] = {
    {true, true},
    {false, true},
    {false, true},
  };
  //  270 degree rotated piecce
  public boolean rotated3_l_Piece[][] = {
    {false, false, true},
    {true, true, true},
  };
  
  //O-Pieces
  //  Original 0 piece
  public boolean o_Piece[][] = {
    {true, true},
    {true, true},
    
  };

  //S-Pieces
  //  Original S piece
  public boolean s_Piece[][] = {
    {false, true, true},
    {true, true, false},
  };
  //  90 degree rotated S piece
  public boolean rotated_s_Piece[][] = {
    {true, false},
    {true, true},
    {false, true},
  };
  
  //Z-Pieces
  // Original Z piece
  public boolean z_Piece[][] = {
    {true, true, false},
    {false, true, true},
  };
  //  90 degree rotated Z piece
  public boolean rotated_z_Piece[][] = {
    {false, true},
    {true, true},
    {true, false},
  };
  //T-Pieces
  //  Original T piece
  public boolean t_Piece[][] = {
    {false, true, false},
    {true, true, true},
  };
  //  90 degree rotated T piece
  public boolean rotated_t_Piece[][] = {
    {true, false},
    {true, true},
    {true, false},
  };
  //  180 degree rotated T piece
  public boolean rotated2_t_Piece[][] = {
    {true, true, true},
    {false, true, false},
  };
  //  270 degree rotated T piece
  public boolean rotated3_t_Piece[][] = {
    {false, true},
    {true, true},
    {false, true},
  };
  
  //  Empty piece preset
  public boolean empty_Piece[][] = {
    {false, false, false, false},
    {false, false, false, false},
    {false, false, false, false},
    {false, false, false, false},
  };
  
  //This class has 3 different constructors
  
  //First one is used only in the beginning of the game to select a random 
  //piece with a random color.
  public TetrisPieces()
  {

    rint = rgen.nextInt(7) + 1;
    switch (rint) {
      case 1:  
        currentPiece = i_Piece;
        pieceTag = 1;
        break;
      case 2:  
        currentPiece = j_Piece;
        pieceTag = 2;
        break;
      case 3: 
        currentPiece = l_Piece;
        pieceTag = 3;
        break;
      case 4:  
        currentPiece = o_Piece;
        pieceTag = 4;
        break;
      case 5:  
        currentPiece = s_Piece;
        pieceTag = 5;
        break;
      case 6:  
        currentPiece = z_Piece;
        pieceTag = 6;
        break;
      case 7:  
        currentPiece = t_Piece;
        pieceTag = 7;
        break;
    }
    int rcolor = rgen.nextInt(9) + 1;
    switch (rcolor) {
      case 1:  scolor = Color.RED;
      break;
      case 2:  scolor = Color.BLUE;
      break;
      case 3:  scolor = Color.GREEN;
      break;
      case 4:  scolor = Color.YELLOW;
      break;
      case 5:  scolor = Color.WHITE;
      break;
      case 6:  scolor = Color.PINK;
      break;
      case 7:  scolor = Color.ORANGE;
      break;
      case 8:  scolor = Color.CYAN;
      break;
      case 9:  scolor = Color.MAGENTA;
      break;
    }
  }
  //This constructor is used each time game needs a new piece. Differently from the first one this constructor requires the
  //previous piece's color so consequent pieces won't have the same color.
  public TetrisPieces(Color previousColor)
  {

    rint = rgen.nextInt(7) + 1;
    switch (rint) {
      case 1:  
        currentPiece = i_Piece;
        pieceTag = 1;
        break;
      case 2:  
        currentPiece = j_Piece;
        pieceTag = 2;
        break;
      case 3: 
        currentPiece = l_Piece;
        pieceTag = 3;
        break;
      case 4:  
        currentPiece = o_Piece;
        pieceTag = 4;
        break;
      case 5:  
        currentPiece = s_Piece;
        pieceTag = 5;
        break;
      case 6:  
        currentPiece = z_Piece;
        pieceTag = 6;
        break;
      case 7:  
        currentPiece = t_Piece;
        pieceTag = 7;
        break;
    }
    do{
      int rcolor = rgen.nextInt(9) + 1;
      switch (rcolor) {
        case 1:  scolor = Color.RED;
        break;
        case 2:  scolor = Color.BLUE;
        break;
        case 3:  scolor = Color.GREEN;
        break;
        case 4:  scolor = Color.YELLOW;
        break;
        case 5:  scolor = Color.WHITE;
        break;
        case 6:  scolor = Color.PINK;
        break;
        case 7:  scolor = Color.ORANGE;
        break;
        case 8:  scolor = Color.CYAN;
        break;
        case 9:  scolor = Color.MAGENTA;
        break;
      }
    }while(scolor == previousColor);
  }
  // This constructor is used to to rotate the piece. It recognizes what piece is on board and replaces it with
  //it's correct rotated version.
  public TetrisPieces(int tag,Color oldColor)
  {
    //I-Pieces
    if(tag == 1)
    {
      currentPiece = rotated_i_Piece;
      pieceTag = 8;
    }
    if(tag == 8)
    {
      currentPiece = i_Piece;
      pieceTag = 1;
    }
    
    //J-Pieces
    if(tag == 2)
    {
      currentPiece = rotated_j_Piece;
      pieceTag = 9;
    }
    if(tag == 9)
    {
      currentPiece = rotated2_j_Piece;
      pieceTag = 16;
    }
    if(tag == 16)
    {
      currentPiece = rotated3_j_Piece;
      pieceTag = 23;
    }
    if(tag == 23)
    {
      currentPiece = j_Piece;
      pieceTag = 2;
    }
    
    //L-Pieces
    if(tag == 3)
    {
      currentPiece = rotated_l_Piece;
      pieceTag = 10;
    }
    if(tag == 10)
    {
      currentPiece = rotated2_l_Piece;
      pieceTag = 17;
    }
    if(tag == 17)
    {
      currentPiece = rotated3_l_Piece;
      pieceTag = 24;
    }
    if(tag == 24)
    {
      currentPiece = l_Piece;
      pieceTag = 3;
    }
    
    //T-Pieces
    if(tag == 7)
    {
      currentPiece = rotated_t_Piece;
      pieceTag = 14;
    }
    if(tag == 14)
    {
      currentPiece = rotated2_t_Piece;
      pieceTag = 21;
    }
    if(tag == 21)
    {
      currentPiece = rotated3_t_Piece;
      pieceTag = 28;
    }
    if(tag == 28)
    {
      currentPiece = t_Piece;
      pieceTag = 7;
    }
    
    //S-Pieces
    if(tag == 5)
    {
      currentPiece = rotated_s_Piece;
      pieceTag = 12;
    }
    if(tag == 12)
    {
      currentPiece = s_Piece;
       pieceTag = 5;
    }
    
    //Z-Pieces
    if(tag == 6)
    {
      currentPiece = rotated_z_Piece;
      pieceTag = 13;
    }
    if(tag == 13)
    {
      currentPiece = z_Piece;
      pieceTag = 6;
    }
    
    //O-Pieces
    if(tag == 4)
    {
      currentPiece = o_Piece;
    }
    
    scolor = oldColor;
    
  }
  //This method is used to get the rotated version of the current piece ahead of time so program
  //can check if the rotated version will collide with anything.
  //It works similarly with the last constructor but this one returns the 2d boolean array instead of assinning it to the current piece
  public boolean[][] getNextPiece(int tag)
  {
    //I-Pieces
    if(tag == 1)
    {
      newPiece = rotated_i_Piece;
      pieceTag = 8;
    }
    if(tag == 8)
    {
      newPiece = i_Piece;
      pieceTag = 1;
    }
    
    //J-Pieces
    if(tag == 2)
    {
      newPiece = rotated_j_Piece;
      pieceTag = 9;
    }
    if(tag == 9)
    {
      newPiece = rotated2_j_Piece;
      pieceTag = 16;
    }
    if(tag == 16)
    {
      newPiece = rotated3_j_Piece;
      pieceTag = 23;
    }
    if(tag == 23)
    {
      newPiece = j_Piece;
      pieceTag = 2;
    }
    
    //L-Pieces
    if(tag == 3)
    {
      newPiece = rotated_l_Piece;
      pieceTag = 10;
    }
    if(tag == 10)
    {
      newPiece = rotated2_l_Piece;
      pieceTag = 17;
    }
    if(tag == 17)
    {
      newPiece = rotated3_l_Piece;
      pieceTag = 24;
    }
    if(tag == 24)
    {
      newPiece = l_Piece;
      pieceTag = 3;
    }
    
    //T-Pieces
    if(tag == 7)
    {
      newPiece = rotated_t_Piece;
      pieceTag = 14;
    }
    if(tag == 14)
    {
      newPiece = rotated2_t_Piece;
      pieceTag = 21;
    }
    if(tag == 21)
    {
      newPiece = rotated3_t_Piece;
      pieceTag = 28;
    }
    if(tag == 28)
    {
      newPiece = t_Piece;
      pieceTag = 7;
    }
    
    //S-Pieces
    if(tag == 5)
    {
      newPiece = rotated_s_Piece;
      pieceTag = 12;
    }
    if(tag == 12)
    {
      newPiece = s_Piece;
       pieceTag = 5;
    }
    
    //Z-Pieces
    if(tag == 6)
    {
      newPiece = rotated_z_Piece;
      pieceTag = 13;
    }
    if(tag == 13)
    {
      newPiece = z_Piece;
      pieceTag = 6;
    }
    
    //O-Pieces
    if(tag == 4)
    {
      newPiece = o_Piece;
    }
    return newPiece;//Returns the rotated version
  }
  //Sets the piece empty for the first piece so user have time to start
  public void setEmpty()
  {
    currentPiece = empty_Piece;
  }
  //Returns if the piece has a filled box in the inquired location
  public boolean getPiece(int row,int col)
  {
    return currentPiece[row][col];
  }
  //Returns the color of the piece
  public Color getColor()
  {
    return scolor;
  }
  //Returns how many rows does the piece have
  public int getRowLength()
  {
    return currentPiece.length;
  }
  //Returns how many columns does piece have in the inquired row.
  public int getColLength(int rownum)
  {
    return currentPiece[rownum].length;
  }
 //Returns the index tag of the piece so program can recognize which piece is on board.
  public int getPieceTag()
  {
    return pieceTag;
  }
}