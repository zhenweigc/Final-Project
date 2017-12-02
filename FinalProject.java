import java.util.Scanner;
//This is the final project program
public class FinalProject{
  /**
  This string array contains map information, with the capital
  letter denoting the type of buildings and small letter state
  of buildings
  */
  public static final String[][] Map=
  {
    {"Mw","Fn","Sn","Rr","Rr","Hw","Wt","Fr","Hr","Mr","Gr"},
    {"Mr","Mr","Dr","Tw","Rr","Fn","Tw","Rr","Fn","Aw","Wn"},
    {"Fn","Fn","Wr","Pr","Hr","Dr","Tw","Sw","Mr","Aw","Hw"},
    {"Tr","Hr","Fn","Fn","Fw","Rr","Fw","Or","Or","Dw","Ow"},
    {"Hn","Ar","Rr","Ar","Wt","Rr","Ww","Hr","Dn","Wn","Ow"},
    {"Wb","Ar","Rr","Wr","Ob","Pr","Wn","Fr","Pr","Wb","Mr"},
    {"An","Rr","Rr","Ow","Aw","On","Wt","Mr","Hr","Mr","Mr"},
    {"An","Rr","Rt","Sn","An","An","Dw","Ar","Tw","Pr","An"},
    {"Mr","On","On","Ow","An","On","Tw","Sn","Wr","Aw","An"},
    {"Hr","Sw","Wt","On","On","On","Tb","Aw","Dn","Fn","Sn"},
    {"Fn","Ar","Ar","Dn","Dr","An","Tn","Pr","Hr","Or","Wn"},
    {"An","Pr","Aw","Dw","Hr","At","Tn","Ar","Fw","Or","Pr"},
    {"An","An","At","Ar","Mr","Ft","On","Ar","Fw","Fw","Fb"},
    {"Ar","Ar","Sn","Ar","Rt","Ww","Wn","Ww","Fb","Pr","Fn"},
    {"Aw","Aw","Tn","Wt","Rr","Pr","Hr","Ww","Fn","Fn","Fw"}
  };
  //String[15][11]
  /**
  CurrentCoordinate represents the current coordinate of the player,
  with starting point at (14,0)
  */
  public static int[] CurrentCoordinate= {14,0};
  //{Y,X}
  public static void main(String[] args){
  /**
   for(int i=0;i<15;i++){
    for(int j=0;j<11;j++){
      System.out.print(Map[i][j]+" ");
    }
      System.out.println();
  }
*/
  /**
    Boolean Test=false;
    char M;
    while(Test!=true){
      System.out.printf("Please input a Char.%nL for moving leftward; R for moving rightward; U for moving upwards; and D for moving downwards.%n");
       Scanner s = new Scanner(System.in);
       M=s.nextLine().charAt(0);
       Test=Move(M);
    }
    System.out.println("After the move, current coordinate is ("+CurrentCoordinate[1]+","+CurrentCoordinate[0]+")");
    */
    //Previous Codes are used to test whether subroutine M works properly.
  }

  /**
  This subroutine is used for player movement.
  @param M the user's input, if valid it will change current CurrentCoordinate
  @return return true, if the movement is valid; otherwise, false
  */
  public static Boolean Move(char M){
    //Input a char M input this subroutine as command of movement. If the movement is valid, it will change current CurrentCoordinate and return true.
    if (CurrentCoordinate[1]==0 && M=='L'){
      System.out.println("You are at the most left, cannot go left. Input again:");
      return false;
    } else if (CurrentCoordinate[1]==10 && M=='R'){
      System.out.println("You are at the most right, cannot go right. Input again:");
      return false;
    } else if (CurrentCoordinate[0]==14 && M=='D'){
      System.out.println("You are at the most bottom, cannot go down. Input again:");
      return false;
    } else if (CurrentCoordinate[0]==0 && M=='U'){
      System.out.println("You are at the most top, cannot go up. Input again:");
      return false;
    } else {
      if (M=='U'){
        CurrentCoordinate[0]-=1;
        return true;
      }else if(M=='D'){
        CurrentCoordinate[0]+=1;
        return true;
      }else if(M=='L'){
        CurrentCoordinate[1]-=1;
        return true;
      }else if(M=='R'){
        CurrentCoordinate[1]+=1;
        return true;
      }else {
        System.out.println("Illegal input. Input again:");
        return false;
      }
    }
  }
  public static int day=0;
  public static int hp=100;
  public static int money=1000;
  public static int food = 10;
  public static int gun = 0;
  public static int ammo = 0;
  public static int medicine = 0;
  public static int gold = 0;

  public static void act(){
  //empty
  }

  public static void sleep(){
    day+=1;
  }

/**
  the first condition to win: if the player could survive 60 days, then win
  @param val1 surviving days of the player
  @return true if surviving days equal 60
*/
  public static Boolean windays(int val1){
    if(val1==60){
      return true;
    } else{
      return false;
    }
  }

/**
the second condition to win: escaping from the city (i.e. arriving (10,14))
@param coordinate the position of the player
@return true if the player escapes from the city
*/
  public static Boolean winout(int[] coordinate){
    if(coordinate[0]==10 && coordinate[1]==14){
      winout = true;
    }else{
      winout = false;
    }
  }

  /**
use the 2 conditions above to determine whether the user has won
@param windays boolean value to show whether the user has survived 60 days
@param winout boolean value to show whether the user has escaped
@return true if at least one of the 2 conditions is satisfied, meaning the player has won
  */
  public static Boolean win(boolean windays, boolean winout){
    if(winout||windays){
      win = true;
    }else{
      win = false;
    }
  }
}
