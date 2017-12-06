import java.util.Scanner;
import java.util.Random;
//This is the final project program
public class FinalProject{

  public static int day=1;
  public static int hp=100;
  public static int food = 10;
  public static Boolean pistol = false;
  public static Boolean AutomaticRifle=false;
  public static int ammo = 0;
  public static int medicine = 5;
  public static int bandage=3;
  public static int valuables = 2;
  public static int Moral=100;
  public static Boolean sick=false;
  public static int hungry=10;
  public static Boolean FastRecover=false;
  public static Boolean injured=false;
  public static Boolean spyID=false;
  public static Boolean Cipher=false;
  public static Boolean Badge=false;
  public static Boolean TryEscape=false;

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

  //All 12 types of buildings
  public static final String[] Btype={"T","M","A","F","W","S","O","R","P","H","D","G"};

  /**
  CurrentCoordinate represents the current coordinate of the player,
  with starting point at (14,0)
  */
  public static int[] CurrentCoordinate= {14,0};
  //{Y,X} This is the starting point of the player
  public static void main(String[] args){
    Introduction();
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

    do{
      Scanner s = new Scanner(System.in);
      PlayerStatus();
      PositionReport();
      System.out.println("Please enter a char to move. U for upwards, D for downwards, L for Leftwards and R for rightwards.");
      Boolean ValidMove=true;
      do{
      //  char M=s.nextLine().charAt(0);
        ValidMove=Move(s.nextLine().charAt(0));
      }while(!ValidMove);
      PositionReport();
      PlayerStatus();
      action();
      PlayerStatus();
      sleep();
      PlayerStatus();
    }while(!win());
    End();
  }

  /**
  This subroutine is used for player movement.
  @param M the player's input, if valid it will change current CurrentCoordinate
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

/**
  This method is used to check whether current building type matches one of those inputed by the array
  @param CurrentBtype A String that contains the building type of Reznov's current position.
  @return A boolean that will be used in an if conditional to judge whether following codes will be executed
*/
  public static Boolean CheckPosition(String[] input){
    String CurrentBtype=String.valueOf((Map[CurrentCoordinate[0]][CurrentCoordinate[1]]).charAt(0));
    for(int i=0;i<input.length;i++){
      if(CurrentBtype.equals(input[i])){
        return true;
      }
    }
    return false;
  }

/**
  This method is used to check whether current building status matches one of those inputed by the array
  @param CurrentBstatus A String that contains the building status of Reznov's current position.
  @return A boolean that will be used in an if conditional to judge whether following codes will be executed
*/
  public static Boolean CheckStatus(String[] input){
    String CurrentBstatus=String.valueOf((Map[CurrentCoordinate[0]][CurrentCoordinate[1]]).charAt(1));
    for(int i=0;i<input.length;i++){
      if(CurrentBstatus.equals(input[i])){
        return true;
      }
    }
    return false;
  }

/**
  This method will print out all actions available at Reznov's current position.
  @return Return an array of available choice No.
*/
  public static int[] PrintAction(){
    String available="";
    if(CheckPosition("T A F W S O R P H D".split("\\s+"))){
      System.out.println("1.Scavenge");
      available+="1 ";
    }
    if(CheckPosition("T A F W S O R H D".split("\\s+"))&&CheckStatus("r".split("\\s+"))){
      System.out.println("2.Steal");
      available+="2 ";
    }
    if(CheckStatus("t".split("\\s+"))){
      System.out.println("3.Trade");
      available+="3 ";
    }
    if(CheckPosition("P".split("\\s+"))){
      System.out.println("4.Fast Travel");
      available+="4 ";
    }
    if(true){
      System.out.println("5.Do Nothing");
      available+="5 ";
    }
    if((CheckPosition("T A F W S O R H D".split("\\s+"))&&CheckStatus("b".split("\\s+")))||(CheckPosition("M".split("\\s+"))&&CheckStatus("r".split("\\s+")))){
      System.out.println("6.Stealth in and tried to find supplies.");
      available+="6 ";
    }
    if(CheckPosition("H".split("\\s+"))&&CheckStatus("r".split("\\s+"))&&(injured||sick)){
      System.out.println("7.Ask doctors inside to cure your disease and bandage your injuries to make you recover.");
      available+="7 ";
    }
    if(CheckPosition("D".split("\\s+"))&&CheckStatus("r".split("\\s+"))){
      System.out.println("8.Ask priests for material relief.");
      available+="8 ";
    }
    if(CheckPosition("D".split("\\s+"))&&CheckStatus("r".split("\\s+"))){
      System.out.println("9.Ask priests for mental relief.");
      available+="9 ";
    }
    if(CheckPosition("G".split("\\s+"))){
      System.out.println("10.Tried to cheat rebel checkpoints and sneak out of the city");
      available+="10 ";
    }
    String[] Raw1=available.split("\\s+");
    int[] output=new int[Raw1.length];
    for(int j=0;j<output.length;j++){
      output[j]=Integer.parseInt(Raw1[j]);
    }
    return output;
  }

/**
  This method will check whether the No. input by players is among those available choices.
  @param decision Choice made by Players.
  @param PossibleDecision The integer array produced by PrintAction().
  @return A Boolean value of true or false.
*/
  public static Boolean CheckAvailable(int decision,int[] PossibleDecision){
    for(int k=0;k<PossibleDecision.length;k++){
      if(decision==PossibleDecision[k]){
        return true;
      }
    }
    return false;
  }
/**
  This method is used for the action stage in game.
  @return This method does not need to return.
*/
  public static void action(){
    System.out.printf("Time to do something....%n");
    System.out.printf("You need to make a choice among actions available in your current position. If you have decided, enter the number before the choice to choose. Choose wisely.%n");
    Scanner y=new Scanner(System.in);
    int[] Available=PrintAction();
    int choice=0;
    while(!CheckAvailable(choice,Available)){
      System.out.println("Please input the No. you decide which is in font of available choices.");
      choice=TextIO.getlnInt();
    }
    switch(choice){
      case 1:
        Scavenge();
        break;
      case 2:
        Steal();
        break;
      case 3:
        Trade();
        break;
      case 4:
        Teleport();
        break;
      case 5:
        break;
      case 6:
        Stealth();
        break;
      case 7:
        DocHeal();
        break;
      case 8:
        AskR();
        break;
      case 9:
        AskM();
        break;
      default:
        Escape();
        break;
    }
  }

/**
  Method to run when players choose to scavenge.
  T A F W S O R P H D
  @return No returns
*/
  public static void Scavenge(){
    int foodbuff=0;
    int medicinebuff=0;
    int weaponbuff=0;
    int bandagebuff=0;
    int valuablesbuff=0;
    int ammobuff=0;
    int IDbuff=0;
    int DiseaseChance=0;
    int BadgeBuff=0;
    switch(String.valueOf((Map[CurrentCoordinate[0]][CurrentCoordinate[1]]).charAt(0))){
      case "T":
        foodbuff+=5;
        medicine+=2;
        bandagebuff+=2;
        weaponbuff+=0;
        valuablesbuff+=1;
        ammobuff+=0;
        IDbuff+=0;
        break;
      case "A":
        foodbuff+=3;
        medicine+=2;
        bandagebuff+=2;
        weaponbuff+=0;
        valuablesbuff+=2;
        ammobuff+=1;
        IDbuff+=0;
        break;
      case "F":
        foodbuff-=5;
        medicine+=3;
        bandagebuff+=4;
        weaponbuff+=0;
        valuablesbuff-=5;
        ammobuff-=3;
        IDbuff+=0;
        break;
      case "W":
        foodbuff+=2;
        medicine+=6;
        bandagebuff+=6;
        weaponbuff+=3;
        valuablesbuff+=3;
        ammobuff+=2;
        IDbuff+=0;
        break;
      case "S":
        foodbuff+=0;
        medicine+=3;
        bandagebuff+=3;
        weaponbuff-=5;
        valuablesbuff+=1;
        ammobuff-=5;
        IDbuff+=0;
        break;
      case "O":
        foodbuff+=1;
        medicine+=0;
        bandagebuff+=0;
        weaponbuff-=5;
        valuablesbuff+=5;
        ammobuff-=5;
        IDbuff+=4;
        break;
      case "R":
        foodbuff-=7;
        medicine-=7;
        bandagebuff-=7;
        weaponbuff-=7;
        valuablesbuff-=7;
        ammobuff-=7;
        IDbuff+=6;
        DiseaseChance-=6;
        break;
      case "P":
        foodbuff-=7;
        medicine-=7;
        bandagebuff-=7;
        weaponbuff-=5;
        valuablesbuff+=3;
        ammobuff-=5;
        IDbuff+=2;
        BadgeBuff+=3;
        DiseaseChance-=4;
        break;
      case "H":
        foodbuff+=1;
        medicine+=5;
        bandagebuff+=5;
        weaponbuff-=5;
        valuablesbuff-=3;
        ammobuff-=5;
        IDbuff+=0;
        DiseaseChance+=3;
        break;
      default:
        foodbuff+=1;
        medicine+=5;
        bandagebuff+=5;
        weaponbuff-=5;
        valuablesbuff-=3;
        ammobuff-=5;
        IDbuff+=0;
        DiseaseChance+=3;
        break;
    }
    switch(String.valueOf((Map[CurrentCoordinate[0]][CurrentCoordinate[1]]).charAt(1))){
      case "w":
        foodbuff-=1;
        medicine-=1;
        bandagebuff-=1;
        weaponbuff-=1;
        valuablesbuff-=1;
        ammobuff-=1;
        IDbuff+=0;
        DiseaseChance+=0;
        break;
      case "r":
        foodbuff-=2;
        medicine-=2;
        bandagebuff-=2;
        weaponbuff-=2;
        valuablesbuff-=2;
        ammobuff-=2;
        IDbuff+=0;
        DiseaseChance-=2;
        break;
      case "n":
        foodbuff+=0;
        medicine+=0;
        bandagebuff+=0;
        weaponbuff+=0;
        valuablesbuff+=1;
        ammobuff+=0;
        IDbuff+=0;
        DiseaseChance+=0;
        break;
      default:
        foodbuff-=2;
        medicine-=2;
        bandagebuff-=2;
        weaponbuff-=2;
        valuablesbuff-=2;
        ammobuff-=2;
        IDbuff+=0;
        DiseaseChance-=2;
        break;
    }
    System.out.printf("After searching for a long time, you stopped scavenging and started to see what you got.%n");
    AddFood(foodbuff);
    AddMedicine(medicinebuff);
    AddBandage(bandagebuff);
    AddValuables(valuablesbuff);
    AddAmmo(ammobuff);
    AddWeapon(weaponbuff);
    getDisease(DiseaseChance);
    getID(IDbuff);
    getBadge(BadgeBuff);
  }

  /**
    pick a random element of a list of Strings
    @param list the array of Strings
    @return a randomly selected member of the list
  */
  public static String pickRandom(String[] list){
    Random f = new Random();
    int k = f.nextInt(list.length);
    return list[k];
  }

/**
  The method that will be executed when players choose to steal.
  @return no returns
*/
  public static void Steal(){
    int foodbuff=0;
    int medicinebuff=0;
    int weaponbuff=0;
    int bandagebuff=0;
    int valuablesbuff=0;
    int ammobuff=0;
    int stealBuff=2;
    String[] steal={
      "'Stealing, of course, is a crime, and a very impolite thing to do. But like most impolite things, it is excusable under certain circumstances.' --Lemony Snicket",
      "'If you don't get caught, you deserve everything you steal.' --Daniel Nayeri",
      "'Stealers, keepers.' --Ilona Andrews",
      "'Clothes make the man. Especially the pockets.' --Ljupka Cvetanova",
      "'Taking books can't be counted as stealing...for a scholar...can't be counted as stealing.' --Yiji Kong"
    };
    System.out.printf("%s%n",pickRandom(steal));
    Moral-=5;
    int L=RollDice();
    if(L>7){
      switch(String.valueOf((Map[CurrentCoordinate[0]][CurrentCoordinate[1]]).charAt(0))){
        case "T":
          foodbuff+=5;
          medicine+=2;
          bandagebuff+=2;
          weaponbuff+=0;
          valuablesbuff+=1;
          ammobuff+=0;
          break;
        case "A":
          foodbuff+=3;
          medicine+=2;
          bandagebuff+=2;
          weaponbuff+=0;
          valuablesbuff+=2;
          ammobuff+=1;
          break;
        case "F":
          foodbuff-=5;
          medicine+=3;
          bandagebuff+=4;
          weaponbuff+=0;
          valuablesbuff-=5;
          ammobuff-=3;
          break;
        case "W":
          foodbuff+=2;
          medicine+=6;
          bandagebuff+=6;
          weaponbuff+=3;
          valuablesbuff+=3;
          ammobuff+=2;
          break;
        case "S":
          foodbuff+=0;
          medicine+=3;
          bandagebuff+=3;
          weaponbuff-=5;
          valuablesbuff+=1;
          ammobuff-=5;
          break;
        case "O":
          foodbuff+=1;
          medicine+=0;
          bandagebuff+=0;
          weaponbuff-=5;
          valuablesbuff+=5;
          ammobuff-=5;
          break;
        case "R":
          foodbuff-=7;
          medicine-=7;
          bandagebuff-=7;
          weaponbuff-=7;
          valuablesbuff-=7;
          ammobuff-=7;
          break;
        case "H":
          foodbuff+=1;
          medicine+=5;
          bandagebuff+=5;
          weaponbuff-=5;
          valuablesbuff-=3;
          ammobuff-=5;
          Moral-=5;
          break;
        default:
          foodbuff+=1;
          medicine+=5;
          bandagebuff+=5;
          weaponbuff-=5;
          valuablesbuff-=3;
          ammobuff-=5;
          Moral-=5;
          break;
      }
        foodbuff-=2;
        medicine-=2;
        bandagebuff-=2;
        weaponbuff-=2;
        valuablesbuff-=2;
        ammobuff-=2;
        System.out.printf("You sneaked in, evade attentions, grabbed a bag and ran out.%nAfter you are sure that you are safe, you open the bag and begin counting what you steal.%n");
        AddFood(foodbuff);
        AddMedicine(medicinebuff);
        AddBandage(bandagebuff);
        AddValuables(valuablesbuff);
        AddAmmo(ammobuff);
        AddWeapon(weaponbuff);
    }else{
      System.out.printf("Your attempt to crime is discovered,you hear noises and sounds of loading guns.%n");
      if((pistol||AutomaticRifle)&&ammo>=1){
        System.out.printf("You jumped out of window, shot some bullets back.%nIt sounds that you hit some civilians there and they stop chasing you.%n");
        Moral-=15;
        ammo-=1;
      }else{
        System.out.printf("You began to run away.%nWhile you are running away, people inside this house shoot you. You feel that a bullet hits you.%n");
        injured=true;
        hp-=10;
        Defeat();
      }
    }
  }

/**
  This is the method that will be used when players choose to trade.
  @return No return needed.
*/
  public static void Trade(){
    System.out.printf("You walked into the building, which is now a trading spot.%n'Maybe I can find a good deal.', you tell youself.%n");
    Boolean chance=false;
    if(RollDice()>13){
      chance=true;
    }
    System.out.printf("%n%n-----------------------------------------------------------------------------------------------------------------------%nHere is the trade price...%n");
    System.out.printf("1.One unit of valuable can exchange three units of food, or one unit of medicine, or one unit of bandage, or two units of ammos.%n2.One unit of medicine can exchange three units of food, or one unit of bandage, or two units of ammos.%n3.One unit of bandage can exchange three units of food, or one unit of medicine, or two units of ammos.%n4.Four units of food can exchange one unit of medicines or one unit of bandage.%n");
    if(chance){
      System.out.printf("5.Five units of valuables can exchange one pistol.%n6.Ten units of valuables can exchange one automatic rifle.%n7.Ten units of valuables can exchange a spy ID or a badge or cipher of rebels spies.%n");
    }
    Boolean continueTrade=false;
    PlayerItemStatus();
    System.out.printf("%n%nDo you want to trade with them? Input 'yes' to trade, else to leave.%n");
    String a=TextIO.getln();
    if(!((a.toUpperCase()).equals("YES"))){
      return;
    }
    String[] Choice1={"valuables","food","medicine","bandage","ammo"};
    String[] Choice2={"medicine","food","bandage","ammo"};
    String[] Choice3={"bandage","food","medicine","ammo"};
    String[] Choice4={"food","medicine","bandage"};
    String[] Choice7={"valuables","spyID","Badge","cipher"};
    do{
      String StringInput;
      int typeInput;
      System.out.printf("Input the number before each type to select what kind of item you are going to use.%n");
      typeInput=TextIO.getlnInt();
      while((typeInput>7||typeInput<1)||(typeInput>4&&(!chance))){
        System.out.printf("Invalid choices, input again.%n");
        typeInput=TextIO.getlnInt();
      }
      switch(typeInput){
        case 1:
          Boolean ValidInput=true;
          int input=0;
          System.out.printf("Now enter name of the item you want to exchange to.%n");
          StringInput=(TextIO.getln()).toUpperCase();
          for(int k=1;k<Choice1.length;k++){
            if(StringInput.equals(Choice1[k].toUpperCase())){
              //Valid Input
              input=k;
              ValidInput=true;
              break;
            }else{
              ValidInput=false;
            }
          }
          while(!ValidInput){
            System.out.printf("Please make sure you input a valid name. Please re-input.%n");
            StringInput=(TextIO.getln()).toUpperCase();
            for(int k=1;k<Choice1.length;k++){
              if(StringInput.equals(Choice1[k].toUpperCase())){
                //Valid Input
                input=k;
                ValidInput=true;
                break;
              }else{
                ValidInput=false;
              }
            }
          }
          if(valuables<1){
            System.out.printf("You do not have enough item to trade!.%n");
          }else{
            System.out.printf("Trade Completed.%n");
            valuables-=1;
            if(input==1){
              food+=3;
            }else if(input==2){
              medicine+=1;
            }else if(input==3){
              bandage+=1;
            }else if(input==4){
              ammo+=2;
            }else{
              System.out.printf("Error!");
            }
          }
          break;
        case 2:
          System.out.printf("Now enter name of the item you want to exchange to.%n");
          Boolean ValidInput1=true;
          int input1=0;
          System.out.printf("Now enter name of the item you want to exchange to.%n");
          StringInput=(TextIO.getln()).toUpperCase();
          for(int k=1;k<Choice2.length;k++){
            if(StringInput.equals(Choice2[k].toUpperCase())){
              //Valid Input
              input1=k;
              ValidInput1=true;
              break;
            }else{
              ValidInput1=false;
            }
          }
          while(!ValidInput1){
            System.out.printf("Please make sure you input a valid name. Please re-input.%n");
            StringInput=(TextIO.getln()).toUpperCase();
            for(int k=1;k<Choice2.length;k++){
              if(StringInput.equals(Choice2[k].toUpperCase())){
                //Valid Input
                input1=k;
                ValidInput1=true;
                break;
              }else{
                ValidInput1=false;
              }
            }
          }
          if(medicine<1){
            System.out.printf("You do not have enough item to trade!.%n");
          }else{
            System.out.printf("Trade Completed.%n");
            medicine-=1;
            if(input1==1){
              food+=3;
            }else if(input1==2){
              bandage+=1;
            }else if(input1==3){
              ammo+=2;
            }else{
              System.out.printf("Error!");
            }
          }
          break;
        case 3:
          System.out.printf("Now enter name of the item you want to exchange to.%n");
          Boolean ValidInput2=true;
          int input2=0;
          System.out.printf("Now enter name of the item you want to exchange to.%n");
          StringInput=(TextIO.getln()).toUpperCase();
          for(int k=1;k<Choice3.length;k++){
            if(StringInput.equals(Choice3[k].toUpperCase())){
              //Valid Input
              input2=k;
              ValidInput2=true;
              break;
            }else{
              ValidInput2=false;
            }
          }
          while(!ValidInput2){
            System.out.printf("Please make sure you input a valid name. Please re-input.%n");
            StringInput=(TextIO.getln()).toUpperCase();
            for(int k=1;k<Choice3.length;k++){
              if(StringInput.equals(Choice3[k].toUpperCase())){
                //Valid Input
                input2=k;
                ValidInput2=true;
                break;
              }else{
                ValidInput2=false;
              }
            }
          }
          if(bandage<1){
            System.out.printf("You do not have enough item to trade!.%n");
          }else{
            System.out.printf("Trade Completed.%n");
            bandage-=1;
            if(input2==1){
              food+=3;
            }else if(input2==2){
              medicine+=1;
            }else if(input2==3){
              ammo+=2;
            }else{
              System.out.printf("Error!");
            }
          }
          break;
        case 4:
          System.out.printf("Now enter name of the item you want to exchange to.%n");
          Boolean ValidInput3=true;
          int input3=0;
          System.out.printf("Now enter name of the item you want to exchange to.%n");
          StringInput=(TextIO.getln()).toUpperCase();
          for(int k=1;k<Choice4.length;k++){
            if(StringInput.equals(Choice4[k].toUpperCase())){
              //Valid Input
              input3=k;
              ValidInput3=true;
              break;
            }else{
              ValidInput3=false;
            }
          }
          while(!ValidInput3){
            System.out.printf("Please make sure you input a valid name. Please re-input.%n");
            StringInput=(TextIO.getln()).toUpperCase();
            for(int k=1;k<Choice4.length;k++){
              if(StringInput.equals(Choice4[k].toUpperCase())){
                //Valid Input
                input3=k;
                ValidInput3=true;
                break;
              }else{
                ValidInput3=false;
              }
            }
          }
          if(food<4){
            System.out.printf("You do not have enough item to trade!.%n");
          }else{
            System.out.printf("Trade Completed.%n");
            food-=4;
            if(input3==1){
              medicine+=1;
            }else if(input3==2){
              bandage+=1;
            }else{
              System.out.printf("Error!");
            }
          }
          break;
        case 5:
          if(valuables<5){
            System.out.printf("You do not have enough items to trade.%n");
          }else if(pistol){
            System.out.printf("You already have a pistol, you do not need to trade for a new one.%n");
          }else{
            System.out.printf("That guy gave you a pistol.%n");
            valuables-=5;
            pistol=true;
          }
          break;
        case 6:
          if(valuables<10){
            System.out.printf("You do not have enough items to trade.%n");
          }else if(AutomaticRifle){
              System.out.printf("You already have an automatic rifle, you do not need to trade for a new one.%n");
          }else{
            System.out.printf("After putting all valuable things you give him, that guy pick up an automatic rifle from the safe.%n");
            valuables-=10;
            AutomaticRifle=true;
          }
          break;
        default:
          Boolean ValidInput4=true;
          int input4=0;
          System.out.printf("Now enter name of the item you want to exchange to.%n");
          StringInput=(TextIO.getln()).toUpperCase();
          for(int k=1;k<Choice7.length;k++){
            if(StringInput.equals(Choice7[k].toUpperCase())){
              //Valid Input
              input4=k;
              ValidInput4=true;
              break;
            }else{
              ValidInput4=false;
            }
          }
          while(!ValidInput4){
            System.out.printf("Please make sure you input a valid name. Please re-input.%n");
            StringInput=(TextIO.getln()).toUpperCase();
            for(int k=1;k<Choice7.length;k++){
              if(StringInput.equals(Choice7[k].toUpperCase())){
                //Valid Input
                input4=k;
                ValidInput4=true;
                break;
              }else{
                ValidInput4=false;
              }
            }
          }
          if(valuables<10){
            System.out.printf("You do not have enough item to trade!.%n");
          }else{
            if(input4==1){
              if(spyID){
                System.out.printf("You do not need that anymore.");
              }else{
                System.out.printf("That guy claims that he had something interesting. You buy it.%nYou find out that it is maybe a rebel spy ID.%n");
                valuables-=10;
                spyID=true;
              }
            }else if(input4==2){
              if(Badge){
                System.out.printf("You do not need that anymore.");
              }else{
                System.out.printf("That guy claims that he had something interesting. You buy it.%nYou find out that it is maybe a rebel spy's badge.%n");
                valuables-=10;
                Badge=true;
              }
            }else if(input4==3){
              if(Cipher){
                System.out.printf("You do not need that anymore.");
              }else{
                System.out.printf("That guy claims that he had something interesting. You buy it.%nYou find out that it is a booklet of words and sentences and symbols, and it is a cipher of rebels spies.%n");
                valuables-=10;
                Cipher=true;
              }
            }else{
              System.out.printf("Error!");
            }
          }
          break;
        }
      PlayerItemStatus();
      System.out.printf("Do you want to continue trading with them? Input 'yes' to continue, else to quit.%n");
      String b=TextIO.getln();
      if(!((a.toUpperCase()).equals("YES"))){
        continueTrade=true;
      }
    }while(continueTrade);
  }

/**
  This is the method that will be used when players choose let Reznov to stealth in.
  @return No return needed.
*/
  public static void Stealth(){
    int foodbuff=0;
    int medicinebuff=0;
    int weaponbuff=0;
    int bandagebuff=0;
    int valuablesbuff=0;
    int ammobuff=0;
    int stealBuff=2;
    int L=RollDice();
    if(L>10){
      switch(String.valueOf((Map[CurrentCoordinate[0]][CurrentCoordinate[1]]).charAt(0))){
        case "T":
          foodbuff+=5;
          medicine+=2;
          bandagebuff+=2;
          weaponbuff+=0;
          valuablesbuff+=1;
          ammobuff+=0;
          break;
        case "A":
          foodbuff+=3;
          medicine+=2;
          bandagebuff+=2;
          weaponbuff+=0;
          valuablesbuff+=2;
          ammobuff+=1;
          break;
        case "F":
          foodbuff-=5;
          medicine+=3;
          bandagebuff+=4;
          weaponbuff+=0;
          valuablesbuff-=5;
          ammobuff-=3;
          break;
        case "W":
          foodbuff+=2;
          medicine+=6;
          bandagebuff+=6;
          weaponbuff+=3;
          valuablesbuff+=3;
          ammobuff+=2;
          break;
        case "S":
          foodbuff+=0;
          medicine+=3;
          bandagebuff+=3;
          weaponbuff-=5;
          valuablesbuff+=1;
          ammobuff-=5;
          break;
        case "O":
          foodbuff+=1;
          medicine+=0;
          bandagebuff+=0;
          weaponbuff-=5;
          valuablesbuff+=5;
          ammobuff-=5;
          break;
        case "R":
          foodbuff-=7;
          medicine-=7;
          bandagebuff-=7;
          weaponbuff-=7;
          valuablesbuff-=7;
          ammobuff-=7;
          break;
        case "H":
          foodbuff+=1;
          medicine+=5;
          bandagebuff+=5;
          weaponbuff-=5;
          valuablesbuff-=3;
          ammobuff-=5;
          break;
        case "M":
          foodbuff+=5;
          medicine+=6;
          bandagebuff+=6;
          weaponbuff+=7;
          valuablesbuff+=3;
          ammobuff+=7;
          break;
        default:
          foodbuff+=1;
          medicine+=5;
          bandagebuff+=5;
          weaponbuff-=5;
          valuablesbuff-=3;
          ammobuff-=5;
          break;
      }
      if(CheckStatus("b".split("\\s+"))){
        foodbuff+=3;
        medicine+=3;
        bandagebuff+=2;
        weaponbuff+=4;
        valuablesbuff+=3;
        ammobuff+=4;
      }
        System.out.printf("You sneaked in, evade attentions, grabbed a bag and ran out.%nAfter you are sure that you are safe, you open the bag and begin counting what you get.%n");
        AddFood(foodbuff);
        AddMedicine(medicinebuff);
        AddBandage(bandagebuff);
        AddValuables(valuablesbuff);
        AddAmmo(ammobuff);
        AddWeapon(weaponbuff);
    }else{
      System.out.printf("You are noticed by armed guards, they raise alarms that even deaf people feel it.%n");
      if((pistol||AutomaticRifle)&&ammo>=5){
        System.out.printf("You jumped out of window, shot some bullets back.%nIt sounds that you hit some armed guards there and they stop chasing you.%n");
        ammo-=5;
      }else{
        System.out.printf("You began to run away.%nWhile you are running away, people inside this house shot you. You feel that a bullet hits you.%n");
        injured=true;
        hp-=30;
        Defeat();
      }
    }
  }

/**
  The method that will be executed when players use to ask docters for help.
  @return No return needed.
*/
  public static void DocHeal(){
    System.out.printf("You decided to ask doctors for help and walked in.%n");
    int L=RollDice();
    if(L>8&&Moral>=45){
      System.out.printf("A docter responses to your demand and help you.%n");
      sick=false;
      injured=false;
    }else if(L<=8&&Moral>=45){
      System.out.printf("All doctors are busy helping other refugees, they can not help you.%n");
    }else{
      System.out.printf("No doctor wants to help you because of your bad reputation.%n");
    }
  }

/**
  This method is used when players choose to ask priests for material relief
  @return No return
*/
  public static void AskR(){
    System.out.printf("You decided to go into the church and ask priests for help.%n");
    int L=RollDice();
    if(L>11&&hungry<=5){
      System.out.printf("A priest noticed that you are suffering from starving, so he gives you some food.(food+3)%n");
      food+=3;
    }else if(L>11&&hungry>5){
      System.out.printf("Priests think that they need to help other people first, not you.%n");
    }else{
      System.out.println("All priests are busy helping other refugees. You are omitted.");
    }
  }

/**
  This method is used when players choose to ask priests for mental help
  @return No return needed
*/
  public static void AskM(){
    System.out.printf("Tortured by the unpleasant experience of war and pressure of trying to survive, you think you need some mental help.%nYou wait in line for sometime and walk in a small chamber.%nAfter telling all your pressure and crime you commited, the priest helped you and you feel better now.%n");
    Moral+=10;
    if(Moral>100){
      Moral=100;
    }
  }

/**
  The method is used when Reznov tries to get out of the city.
  @return No returns.
*/
  public static void Escape(){
    if(Cipher&&spyID&&Badge){
      System.out.printf("After sneaked out of the gate, as expected, you are noticed by some rebels.%nYou shout out cipher you learned, one of them orders other rebels to hold fire.%nThen you show him your ID and your badge.%nHe then let you pass and tell you to meet with the General ten miles away.%nOf course you do not follow his words.%nYou directly get out of this region, catch a boat--of course you paid the captain with some valuables--and reach France.%n");
      TryEscape=true;
    }else{
      System.out.printf("After sneaked out of the gate, as expected, you are noticed by some rebels.%nBut you failed you prove your identity as one of their spies, you are exposed and then executed.%n");
      System.exit(1);
    }
  }

/**
  This method is used to add resources
  @param Input FoodBuff value.
  @return No return
*/
  public static void AddFood(int Input){
    int rolled=AffectRollDice(Input);
    if(rolled>14){
      food+=5;
      System.out.printf("You are lucky, you find a lot of food.%n'No need to worry about eating for a few days', you think.%n(food+6)%n");
    }else if(rolled>11&&rolled<=14){
      food+=4;
      System.out.printf("You find six cans of beans.%n'Not plenty,' you think, 'but still good.'%n(food+4)%n");
    }else if(rolled>8&&rolled<=11){
      food+=3;
      System.out.printf("You find three cans of meat.%n'At least I have more food now.', you think.%n(food+3)%n");
    }else if(rolled>6&&rolled<=8){
      food+=1;
      System.out.printf("After a long search, you find a little food, but better than nothing.%n(food+1)%n");
    }else{
      food+=0;
      System.out.printf("So disappionting: there is no trace of food.%n(food+0)%n");
    }
  }

/**
  This method is used to add resources
  @param Input medicineBuff value.
  @return No return
*/
  public static void AddMedicine(int Input){
    int rolled=AffectRollDice(Input);
    if(rolled>15){
      medicine+=3;
      System.out.printf("You find three unused Antibiotic pills, which are extremely useful.%n(medicine+3)%n");
    }else if(rolled>12&&rolled<=15){
      medicine+=2;
      System.out.printf("Two pills of cold medicine...can be savior at this difficult times.%n(medicine+2)%n");
    }else if(rolled>9&&rolled<=12){
      medicine+=1;
      System.out.printf("You find one pill of medicine.%n'Can be my life-saving straw.', you think.%n(medicine+1)%n");
    }else{
      medicine+=0;
      System.out.printf("You did not find any medicine.%nYou try to comfort yourself:'Medicine is always hard to find, take it easy.'%n(medicine+0)%n");
    }
  }

/**
  This method is used to add resources
  @param Input bandageBuff value.
  @return No return
*/
  public static void AddBandage(int Input){
    int rolled=AffectRollDice(Input);
    if(rolled>15){
      bandage+=3;
      System.out.printf("You find an first-aid kit, which contains bandages that can be used to treat wounds.%n(bandage+3)%n");
    }else if(rolled>14&&rolled<=15){
      bandage+=1;
      System.out.printf("You find some clean gauze and alcohol which can be used together as simple bandage.%n(bandage+1)%n");
    }else{
      bandage+=0;
      System.out.printf("You did not find any bandages.'%n(bandage+0)%n");
    }
  }

/**
  This method is used to add resources
  @param Input valuablesbuff value.
  @return No return
*/
  public static void AddValuables(int Input){
    int rolled=AffectRollDice(Input);
    if(rolled>=16){
      valuables+=2;
      System.out.printf("You find a gold ring. War still has not wipe out value of gold yet.%n'Maybe people at trade station will like it?',you think%n(valuables+2)%n");
    }else if(rolled>13&&rolled<16){
      valuables+=1;
      System.out.printf("Two boxes of cigarettes...Cigarettes gradually became hard currency after war began.%n(valuables+1)%n");
    }else{
      valuables+=0;
      System.out.printf("Nothing unexpected.'%n(valuables+0)%n");
    }
  }

/**
  This method is used to add resources
  @param Input ammobuff value.
  @return No return
*/
  public static void AddAmmo(int Input){
    int rolled=AffectRollDice(Input);
    if(rolled>=15){
      ammo+=3;
      System.out.printf("You find some cartridge cases and a box of bullets, seems that there was a fight here.%n(ammo+3)%n");
    }else if(rolled>11&&rolled<15){
      ammo+=2;
      System.out.printf("You find some bullets.%n(ammo+2)%n");
    }else{
      ammo+=0;
    }
  }

/**
  This method is used to add resources
  @param Input weaponbuff value.
  @return No return
*/
  public static void AddWeapon(int Input){
    int rolled=AffectRollDice(Input);
    if(rolled==16){
      System.out.printf("You see something in a box...%n");
      if(RollDice()>14){
        System.out.printf("...And it is an automatic rifle.%nFor a civilian, this is a weapon that can protect you well and may even transform into one of those crminals.%n");
        AutomaticRifle=true;
      }else{
        pistol=true;
        System.out.printf("...And it is a pistol, a guarantee of safety.%n");
      }
    }
  }

/**
  This method is used to add resources
  @param Input DiseaseChance value.
  @return No return
*/
  public static void getDisease(int Input){
    int rolled=AffectRollDice(Input);
    if(rolled>2){
    }else{
      sick=true;
      System.out.printf("You are not feeling well, maybe you touched something that you should not touch?%n(You are sick.)%n");
    }
  }

/**
  This method is used to add resources
  @param Input IDbuff value.
  @return No return
*/
  public static void getID(int Input){
    int rolled=AffectRollDice(Input);
    if(rolled>15){
      spyID=true;
      System.out.printf("You find an ID card. It looks wired...'Possible once owned by a spy in this city', you think.%n(You now have a spy's ID, which can help you to cheat rebels.)%n");
    }else{}
  }

/**
  This method is used to add resources
  @param Input weaponbuff value.
  @return No return
*/
  public static void getBadge(int Input){
    int rolled=AffectRollDice(Input);
    if(rolled>15){
      Badge=true;
      System.out.printf("You find a badge with wired symbols and pattern.'Government newspaper once reported that this was a symbol of rebel spy...', you think.%n(You now have a spy's badge, which can help you to cheat rebels.)%n");
    }else{}
  }

/**
  This method is used when Reznov is inside a sewer and tries to go to another sewer entrance.
  @param SewerCoordinate It stores coordinates of Sewer entrance in the map.(Y,X)
  @param input No. of the row in SewerCoordinate that the player want to teleport to.
  @param o No. of the row in SewerCoordinate that Reznov is currently at.
  @return There is no return needed.
*/
  public static void Teleport(){
    int[][] SewerCoordinate={
      {11,1},
      {2,3},
      {5,5},
      {14,5},
      {10,7},
      {5,8},
      {7,9},
      {13,9},
      {11,10}
    };
    int o=0;
    for(int i=0;i<9;i++){
      if((SewerCoordinate[i][0]==CurrentCoordinate[0])&&(SewerCoordinate[i][1]==CurrentCoordinate[1])){
        o=i+1;
      }else{
        System.out.printf("%1d.{%1d,%1d}%n",(i+1),SewerCoordinate[i][1],SewerCoordinate[i][0]);
      }
    }
    System.out.printf("Please choose and input one of those No. in front of the coordinate you want to travel to.%n");
    int input=0;
    while(input==o||input>9||input<1){
      System.out.printf("Invalid input, input again.%n");
        input=TextIO.getlnInt();
    }
    System.out.printf("Enduring stench of sewers, you finally reached your destination.%n");
    CurrentCoordinate[0]=SewerCoordinate[input-1][0];
    CurrentCoordinate[1]=SewerCoordinate[input-1][1];
  }

/**
  This method is used for sleeping stage in game.
*/
  public static void sleep(){
    if(food>=2){
      food-=2;
      System.out.printf("You eat enough food.%n");
      hungry+=4;
      if(hungry>10){
        hungry=10;
      }
    }else if(food==1){
      food-=1;
      System.out.printf("You do not have enough food.%n");
      hungry-=2;
    }else{
      System.out.printf("You do not have any food, so you need to go to bed hungry.%n");
      hungry-=3;
    }
    if(injured&&(bandage>0)){
      System.out.printf("You are injured, do you want to use bandage to treat yourself? Input 'yes' if you want to.%n");
      String Bdecision=TextIO.getln();
      if((Bdecision.toUpperCase()).equals("YES")){
        bandage-=1;
        FastRecover=true;
      }
    }
    if(sick&&(medicine>0)){
      System.out.printf("You are sick, do you want to use medicine to treat yourself? Input 'yes' if you want to.%n");
      String Mdecision=TextIO.getln();
      if((Mdecision.toUpperCase()).equals("YES")){
        medicine-=1;
        sick=false;
      }
    }
    if(sick){
      hp-=5;
      System.out.printf("Due to disease, you lose 5 hp.%n");
    }
    if(FastRecover){
      if(hungry>7){
        hp+=30;
        System.out.println("Treatment on injuries combines with a good dinner makes you recover fastly.");
      }else if(hungry>=5&&hungry<=7){
        hp+=10;
        System.out.println("Treatment on wounds makes you feel better--Even make you forget that you are not full.");
      }
    }else if(hungry>7){
      hp+=10;
      System.out.println("Keeping your stomach being filled by food makes you recover.");
    }else if(hungry<5){
      System.out.println("Due to starving, your hp is minused by 10.")
      hp-=10;
    }else{
      System.out.println("You begin to feel hungry. If you still do not eat food tomorrow, you may have trouble.");
    }
    Defeat();
    System.out.printf("You go to bed and sleep.%n'Things will be better tomorrow.',you told yourself.%n----------------------------------------------------------%n%n%n");
    if(hp==100){
      injured=false;
    }
    if(hp>100){
      hp=100;
    }
    day+=1;
    FastRecover=false;
  }

/**
*/
  public static void End(){
    if()
  }

/**
  the first condition to win: if Reznov could survive 60 days, then win
  @param day surviving days of Reznov
  @return true if surviving days equal 60
*/
  public static Boolean windays(){
    if(day==60){
      return true;
    } else{
      return false;
    }
  }

/**
the second condition to win: escaping from the city (i.e. arriving (10,14))
@param CurrentCoordinate the position of the player
@return true if the player escapes from the city
*/
  public static Boolean winout(){
    Boolean winout;
    if(CurrentCoordinate[0]==0 && CurrentCoordinate[1]==10){
      if(TryEscape){
        winout = true;
      }else{
        winout=false;
      }
    }else{
      winout = false;
    }
    return winout;
  }

  /**
use the 2 conditions above to determine whether the user has won
windays: method to show whether Reznov has survived 60 days
winout: method to show whether Reznov has escaped
@return true if at least one of the 2 conditions is satisfied, meaning Reznov(and players) has won
  */
  public static Boolean win(){
    Boolean win;
    if(winout()||windays()){
      win = true;
    }else{
      win = false;
    }
    return win;
  }

  /**
  This method is used to tell players where is Reznov now and what is the building&status of his position
  @return No returns
  */
  public static void PositionReport(){
    System.out.printf("------------------------------------------------%n");
    System.out.printf("Your current coordinate is (%1d,%1d), which is a (x,y) coordinate.%n",CurrentCoordinate[1],CurrentCoordinate[0]);
    System.out.printf("------------------------------------------------%n");
    String raw=Map[CurrentCoordinate[0]][CurrentCoordinate[1]];
    String status;
    status=PositionStatusReport(raw.charAt(1));
    switch(raw.charAt(0)){
      case 'A':
        System.out.printf("You are at the front of an apartment, "+status+"%n");
        break;
      case 'T':
        System.out.printf("You are at the front of a supermarket, "+status+"%n");
        break;
      case 'M':
        System.out.printf("You are at the front of a Military base. 'A sign of government power and a guarantee of safety.', said by the president. 'Yeah, definitely.' You tell youself.%nIt is a place "+status+"%n");
        break;
      case 'F':
        System.out.printf("You are at the front of a construction field. 'Leader of builders promised to finish this building, but seems that he could not fulfill his promise.' You tell youself. 'This place may not be completed forever...' But now, it is a place "+status+"%n");
        break;
      case 'W':
        System.out.printf("You are at the front of a warehouse, place to store food, medicine, etc. It now stores hope, too.%nThis warehouse is a place "+status+"%n");
        break;
      case 'S':
        System.out.printf("You are at the front of a school. '......' You began to remember your time in school. But then you tell yourself it is not a time for memories.%nThe school in front of you is a place "+status+"%n");
        break;
      case 'O':
        System.out.printf("An office building...You used to be one of those white-collars worked inside. You are so familiar to this place, but war changed it.%nThere is no people working inside it, it is a place "+status+"%n");
        break;
      case 'R':
        System.out.printf("You are at the front of a slum, home of bottom class people.---But war changed everything. Displaced person, refugees whose homes were destroyed all lived here now.%nIt is a place "+status+"%n");
        break;
      case 'P':
        System.out.printf("You are at the front of an sewer entrance. You can smell it before see it---War does not change this.%n This place, full of disease, is now a safe and fast way for you to move to different parts of the city.%n");
        break;
      case 'H':
        System.out.printf("You are at the front of a hospital, "+status+"%n");
        break;
      case 'D':
        System.out.printf("You are at the front of a church "+status+"%n");
        break;
      default:
        System.out.printf("You are now at the gate of this city. If you can sneak out and cheat all those rebels that you are one of their spies in this city.%nBut they are not fools, so you need to prove yourself.%n");
        break;
    }
  }

  /**
  This method will get the building status of current coordinate.
  @return return a string that can be used in PositionReport to out print.
  */
  public static String PositionStatusReport(char input){
    switch(input){
      case 'w':
       return  "which is a wreckage now because of artilleries attacks.";
      case 'r':
        return "which still has people living inside.";
      case 'b':
        return "which is now captured by a group of bandits.";
      case 'n':
        return "which has no one inside now.";
      default:
        return "which is used as a trading spot for residents nearby.";
    }
  }

  /**
  Introduce this game to player.
  @return No returns needed
  */
  public static void Introduction(){
    System.out.println("Welcome to the game! In this game, you played as a civilian, Dimitri Reznov, who lived inside a city that have been surrounded rebels.");
    Delay();
    System.out.println("What you need to do was to survive by scavenging. And maybe stealing or even kill someone---You decide.");
    Delay();
    System.out.println("Are you ready for the game? Type 'yes' to start, else to quit.");
    Delay();
    Scanner s = new Scanner(System.in);
    String input=s.nextLine();
    if((input.toUpperCase()).equals("YES")){
      System.out.println("Good luck, game starts now.");
      System.out.println("----------------------------------------------------------");
      System.out.println();
    }else{
      System.exit(1);
    }
    System.out.printf("'In modern war there is nothing sweet nor fitting in your dying. You will die like a dog for no good reason.'%n                                                                                                   ---Ernest Miller Hemingway%n");
    Delay();
    System.out.printf("It is December 31st now. And the rebels are still outside of this city.%nNo one can go out and no one get in.%nYou, Dimitri Reznov, is one of those civilians who trapped here.%nThe conditions are getting worse. People are running out of food, medicines and everything they need to live.%n");
    Delay();
    System.out.printf("Shadow of the war shrouded the city for such a long time that almost no one celebrated the Christmas.%nYou planed to stay inside your apartment, but a shelling rain razed your apartment to flat ground.%n");
    System.out.printf("So you can no longer stay in your house, you need to move inside the city and tried to survive.%n");
    Delay();
    System.out.printf("You heard that UN is trying to make rebels and government army ceasefire, and it will come true soon. But it is still 'soon',so you still need to survive through the period of mediation.%n");
    Delay();
    System.out.printf("But you also have a brave idea: You heard that there are spies of rebels in this city...If you can collect their IDs, cipher and badges, maybe you have a chance to pass through rebel's Roadblocks directly.%n");
    System.out.printf("But whatever your plan is, you need to move now....%n");
    LongDelay();
    System.out.printf("---------------------------------------------------------------------------------------------------------------------------------%nHere is a brief tutorial:%n%n");
    Delay();
    System.out.printf("Everyday, you have three stages:move,act and sleep.%nDifferent buildings and status of buildings decide what choice you can have and possiblities for each result.%nSewer entrance can make you fast travel to the another sewer entrance.%n");
    Delay();
    System.out.printf("If you get injured in a battle, do not be scared: As long as you feel full, or rate of hungry is above 7, you can recover 10hp during sleep.%nBut when you are suffering from starving, you will lose hp during sleeping.%n");
    System.out.printf("You will automatically consume two units of food everyday, if you do not have any food, then your hungry rate will drop by 3. Drop to zero equals to death and end of game. If you find food after being hungury for sometime, each day you eat food will add hungry rate by 4 (maximum is 10).%n");
    Delay();
    System.out.printf("When you are injured, using bandage can help you recover faster and remove the 'injured' debuff.%nEach time you will consume 1 units and it will make you recover 200 percent faster when you are full, and even recover 10hp when hungry rate is between 5 to 7([5,7]).%nThis buff will be removed as long as one day later(So the boost is just one night long).%n");
    Delay();
    System.out.printf("You may caught serious disease when you enter some untidy places. This status can be cured by having one unit of medicine.%nIf you do not do it or run out of medicine, then you can not recover HP and will lose 5HP eveyday.%nIt's the end of tutorial. Good Luck!%n%n%n%n");
  }

/**
  Delay the program for 2s, make the program show texts slowly.
  @return No returns needed.
*/
  public static void Delay(){
      try{
        Thread thread = Thread.currentThread();
        thread.sleep(2000);//2S
      }catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
  }

/**
  This method presents Reznov's current status.
  @param sick A Boolean value that shows whether Reznov is sick or not.
  @param injured A Boolean value that shows whether Reznov is injured or not.
  @param pistol A Boolean value that shows whether Reznov has a pistol or not.
  @param AutomaticRifle A Boolean value that shows whether Reznov has an AR or not.
  @param FastRecover A Boolean value that shows whether Reznov bandaged himselves or not.
  @param food An int type data that shows how many units of food Reznov has now.
  @param medicine An int type data that shows how many units of medicine Reznov has now.
  @param bandage An int type data that shows how many units of bandages Reznov has now.
  @param ammo An int type data that shows how many units of ammo Reznov has now.
  @param valuables An int type data that shows how many units of valuables Reznov has now.
  @param spyID A Boolean value that shows whether Reznov has a spy ID or not.
  @param Cipher A Boolean value that shows whether Reznov has a cipher or not.
  @param Badge  A Boolean value that shows whether Reznov has a badge or not.
  @return No return needed.
*/
  public static void PlayerStatus(){
    System.out.println("--------------------------------------------------------------------------------------------------------");
    System.out.printf("It is the No.%1d day since your shelter was destroyed.%n",day);
    System.out.printf("Your HP is %1d, hungry rate is %1d.%n",hp,hungry);
    if(sick){
      System.out.printf("You are sick now.%n");
    }else{
      System.out.printf("You are not sick.%n");
    }
    if(injured){
      System.out.printf("You are injured now.%n");
    }else{
      System.out.printf("You are not injured.%n");
    }
    if(pistol&&AutomaticRifle){
      System.out.printf("You have weapons.%n");
    }else if(pistol||AutomaticRifle){
      System.out.printf("You have a weapon.%n");
    }else{
      System.out.printf("You do not have any weapons.%n");
    }
    System.out.printf("You have %1d food, %1d medicine, %1d bandage, 1%d ammos and %1d valuables.%n",food,medicine,bandage,ammo,valuables);
    if(FastRecover){
      System.out.printf("You have bandaged yourself.%n");
    }
    if(spyID){
      System.out.printf("You have a spy ID that may help you to cheat rebels.%n");
    }
    if(Badge){
      System.out.printf("You have a badge of rebel spies.%n");
    }
    if(Cipher){
      System.out.printf("You had and learned cipher of a spy.%n");
    }
  }

/**
  This method is used in Trade(), to print out item owned by Reznov now.
  @param pistol A Boolean value that shows whether Reznov has a pistol or not.
  @param AutomaticRifle A Boolean value that shows whether Reznov has an AR or not.
  @param food An int type data that shows how many units of food Reznov has now.
  @param medicine An int type data that shows how many units of medicine Reznov has now.
  @param bandage An int type data that shows how many units of bandages Reznov has now.
  @param ammo An int type data that shows how many units of ammo Reznov has now.
  @param valuables An int type data that shows how many units of valuables Reznov has now.
  @return No returns needed.
*/
  public static void PlayerItemStatus(){
    System.out.printf("You have %1d food, %1d medicine, %1d bandage, 1%d ammos and %1d valuables.%n",food,medicine,bandage,ammo,valuables);
    if(pistol&&AutomaticRifle){
      System.out.printf("You have weapons.%n");
    }else if(pistol||AutomaticRifle){
      System.out.printf("You have a weapon.%n");
    }else{
      System.out.printf("You do not have any weapons.%n");
    }
    if(spyID){
      System.out.printf("You have a spy ID that may help you to cheat rebels.%n");
    }
    if(Badge){
      System.out.printf("You have a badge of rebel spies.%n");
    }
    if(Cipher){
      System.out.printf("You had and learned cipher of a spy.%n");
    }
  }

/**
Delay the program for 6.5s, make the program show texts slowly.
@return No returns needed.
*/
  public static void LongDelay(){
      try{
        Thread thread = Thread.currentThread();
        thread.sleep(6500);//6.5s
      }catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }


  /**
  This method is used to check whether player lose the game.
  @param hp hp is health point of Reznov.
  @param hungry Hungry is rate of hunger.
  @param sick Sick is a boolean type value that shows whether Reznov is sick.
  @param injured Injured is a boolean type value that shows whether Reznov is injured.
  @return No returns needed.
  */
  public static void Defeat(){
    if(hp==0){
      if(sick&&injured&&(hungry<5)){
        System.out.printf("You suffered from starving, injury and disease and finally fail to survive.%nYou become one of those victims of war.%n");
        System.exit(1);
      }else if(sick&&injured){
        System.out.printf("In a war, lack of medical treatment is a lethal killer. And you are one of his victims.%n");
        System.exit(1);
      }else if(sick&&hungry<5){
        System.out.printf("Sickness and hunger tortured you and finally take your lives.%n");
        System.exit(1);
      }else if(injured&&(hungry<5)){
        System.out.printf("Suffered from injury and hunger, you died.%n");
        System.exit(1);
      }else if(injured){
        System.out.printf("You bleed out and become one of victims of violence.%n");
        System.exit(1);
      }else{
        System.out.printf("Disease corroded your health and finally killed you.%n");
        System.exit(1);
      }
    }else if(hungry==0){
      System.out.printf("For several days, you tried to find food. But you failed and finally starved to death.%nYour emaciated corpse is find by a UN rescue teammember who enter this city after the ceasefire order.%n");
      System.exit(1);
    }
  }

  /**
  This method will generate a random number[1,16] in order to decide fate of Reznov (what results will happen) when the player make a choice.
  The higher the number generated is, the better the result.
  @return Return the randomly generated integer.
  */
  public static int RollDice(){
    Random r=new Random();
    int output=r.nextInt(16)+1;
    return output;
  }

/**
  This method is used to change result of RollDice()
  For example, if Reznov choose to scavange in an appartment, he can find one unit of medicine when the RollDice() result is bigger than 10.
  But in a hospital, of course he will have a bigger chance. Then, AffectRollDice(3) will add 3 to result of RollDice().
  If he is in a wreckage, then it will be AffectRollDice(-3) because it is much harder to find medicine in wreckages.
  @param input An integer(can be either positive or negative) that influence the result and change the possiblity.
  @return Return an integer than have been affected.
*/
  public static int AffectRollDice(int input){
    int output=RollDice()+input;
    if(output>16){
      return 16;
    }else if(output<1){
      return 1;
    }else{
      return output;
    }
  }


}
