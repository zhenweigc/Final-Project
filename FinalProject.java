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
  public static int valuables = 3;
  public static int Moral=100;
  public static Boolean sick=false;
  public static int hungry=10;
  public static Boolean FastRecover=false;
  public static Boolean injured=false;
  public static Boolean spyID=false;
  public static Boolean Cipher=false;
  public static Boolean Badge=false;
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
    PositionReport();
    PlayerStatus();
    do{
      Scanner s = new Scanner(System.in);
      char M=s.nextLine().charAt(0);
      Move(M);
      PositionReport();
      PlayerStatus();
      action();
      sleep();
    }while(!win());
    End();
  }

  /**
  This subroutine is used for player movement.
  @param M the user's input, if valid it will change current CurrentCoordinate
  @return return true, if the movement is valid; otherwise, false
  */
  public static Boolean Move(char M){
    //Input a char M input this subroutine as command of movement. If the movement is valid, it will change current CurrentCoordinate and return true.
    System.out.println("Please enter a char to move. U for upwards, D for downwards, L for Leftwards and R for rightwards.");
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
  This method is used for the action stage in game.
  @return This method does not need to return.
*/
  public static void action(){
    System.out.printf("Time to do something....%n");
    System.out.printf("You need to make a choice among actions available in your current position. If you have decided, enter the number before the choice to choose. Choose wisely.%n");
  }

/**

*/
  //public static

/**
  This method is used for sleeping stage in game.
*/
  public static void sleep(){
    day+=1;
  }

/**
*/
  public static void End(){

  }

/**
  the first condition to win: if the player could survive 60 days, then win
  @param day surviving days of the player
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
      winout = true;
    }else{
      winout = false;
    }
    return winout;
  }

  /**
use the 2 conditions above to determine whether the user has won
windays: method to show whether the user has survived 60 days
winout: method to show whether the user has escaped
@return true if at least one of the 2 conditions is satisfied, meaning the player has won
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
  This method is used to tell players where are they now and what is the building&status of their position
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
    System.out.printf("When you are injured, using bandage can help you recover faster. Each time you will consume 2 units and it will make you recover 100 percent faster when you are full, and even recover 10hp when hungry rate is between 5 to 7.%nThis buff will be removed two days later or you are no longer injured.%n");
    Delay();
    System.out.printf("You may caught serious disease when you enter some untidy places. This status can be cured by having one unit of medicine.%nIf you do not do it or run out of medicine, then you can not recover HP and will lose 5HP eveyday.%nIt's the end of tutorial. Good Luck!%n");
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
  This method presents player's current status.
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
  This method is used to let players trade inside this game.
  @return No returns needed
*/
  public static void Trade(){

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
  This method will generate a random number in order to decide what results will happen when the player make a choice.
  The higher the number generated is, the better the result.
  @param i An integer that determine how many dices will be rolled at the same time. (Determine range of the random number.)
  @return Return the randomly generated integer.
  */
  public static int RollDice(int i){
    Random r=new Random();
    int output=(r.nextInt(6*i))+1;
    return output;
  }


}
