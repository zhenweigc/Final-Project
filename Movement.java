import java.util.Scanner;

public class Movement{
  public static void main(String[] args){
    int[] CurrentCoordinate= {0,0};
    System.out.println("Input U, D, L, and R to move, input 'bye' to end.");
    System.out.println("Current Position:[0,0]");
    Scanner scanner = new Scanner(System.in);
    String move =" ";

    while(!move.equals("bye")){
      move = scanner.nextLine();

      if (CurrentCoordinate[0]==0 && move.equals("L")){
        System.out.println("You are at the most left, cannot go left. Input again:");
      } else if (CurrentCoordinate[0]==10 && move.equals("R")){
        System.out.println("You are at the most right, cannot go right. Input again:");
      } else if (CurrentCoordinate[1]==0 && move.equals("D")){
        System.out.println("You are at the most bottom, cannot go down. Input again:");
      } else if (CurrentCoordinate[1]==14 && move.equals("U")){
        System.out.println("You are at the most top, cannot go up. Input again:");
      } else {
        if (move.equals("U")){
          CurrentCoordinate[1]+=1;
        }else if(move.equals("D")){
          CurrentCoordinate[1]-=1;
        }else if(move.equals("L")){
          CurrentCoordinate[0]-=1;
        }else if(move.equals("R")){
          CurrentCoordinate[0]+=1;
        }else {
          System.out.println("Illegal input. Input again:");
        }
      }
      System.out.printf("Current Position:[%d,%d]",CurrentCoordinate[0],CurrentCoordinate[1]);
      System.out.println();
    }
  }
}
