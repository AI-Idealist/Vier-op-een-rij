package vieropeenrij;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static vieropeenrij.Spel.EMPTY;
import static vieropeenrij.Spel.AGENT;
import static vieropeenrij.Spel.USER;
import static vieropeenrij.Spel.KOL;
import static vieropeenrij.Spel.RIJ;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AISpeler {
  private Bord bord;
  private int[][] lvelden = new int[RIJ][KOL];
      
public AISpeler(Bord pbord) {
  bord = pbord;
} 
 
public int vraagtZet(){
  int zet = -1;

  Scanner scan = new Scanner(System.in);
      
  while (!bord.isLegaleZet(zet)){
    System.out.print("Agent vraagt, welke kolom moet ik kiezen? ");
     zet = scan.nextInt();

  }
  return zet;
}
 
    // Agent kiest steeds een wilekeurige zet 
    public int KiestZetWill() {
    int zet = -1;
     
    List<Integer> mzetten = BepaalMogelijkeZetten();     
         
    int min = 0;
    int max = mzetten.size();
    Random random = new Random();
    random.setSeed(1);
    int rij = random.nextInt(max - min + 1) + min;
    zet = mzetten.get(rij);
    return zet;
    }
    
   private List<Integer> BepaalMogelijkeZetten() {
      List<Integer> nextMoves = new ArrayList<Integer>(); 
 
      // zoek kolommen waar nog wat bij kan, dus mogelijke zetten
        for (int j = 0; j <= KOL-1; j++) {
            if (lvelden[0][j] == EMPTY) {
               nextMoves.add(j);
            }
         }
       return nextMoves;
   }
   

  // Agent kiest zet alleen obv huidige opstelling. Dus kijkt NIET vooruit
public int KiestZetNaief() {
  int zet = -1;
  // kopieer bordcellen om neveneffecten te voorkomen.
  for(int i = 0; i <= RIJ-1; i++) {
    for (int j = 0; j <=KOL-1 ; j++) {
      lvelden[i][j] = bord.velden[i][j];
    }
  }
  List<Integer> mzetten = BepaalMogelijkeZetten();
  zet = BepaalBesteZet(mzetten);
  return zet; 
} 
   
   
   
   private int BepaalBesteZet(List<Integer>  opties) {
      int hiscore = Integer.MIN_VALUE;
      int index = -1; //op welke plaats in de lijst van mogelijke zetten staat de beste zet.
        
        for (int i = 0; i < opties.size(); i++) { // probeer ieder mogelijke zet
            int zet = opties.get(i);
            int kolom = zet;
            int rij = bord.bepaalTopRij(kolom);
            int tmp = lvelden[rij][kolom];
            lvelden[rij][kolom] = AGENT;
            
            int score = EvalueerOpstelling(); // bepaal hoe goed die is. 
            if (score >= hiscore) {
                   hiscore = score;
                   index = i;
            } 
            System.out.println("i :"+ i + " kolom : " + kolom + " rij : " + rij + " score : " + score + " hiscore : " + hiscore + " index : " + index);
            
             printbord();
            
             lvelden[rij][kolom] = tmp; // zet velden terug naar de uitgangssituatie.
        }
     int zet = opties.get(index);
     return zet;
   }
   
   
   public int scoreLine(int r1, int k1, int r2, int k2, int r3, int k3, int r4, int k4) {
            String lijn = "";
            int score = 0;
            
            if (lvelden[r1][k1] == -1 ) lijn.concat("-");
            if (lvelden[r1][k1] == 0 ) lijn.concat("x");
            if (lvelden[r1][k1] == 1 ) lijn.concat("o");
            
            if (lvelden[r2][k2] == -1 ) lijn.concat("-");
            if (lvelden[r2][k2] == 0 ) lijn.concat("x");
            if (lvelden[r2][k2] == 1 ) lijn.concat("o");
            
            if (lvelden[r3][k3] == -1 ) lijn.concat("-");
            if (lvelden[r3][k3] == 0 ) lijn.concat("x");
            if (lvelden[r3][k3] == 1 ) lijn.concat("o");
            
            if (lvelden[r4][k4] == -1 ) lijn.concat("-");
            if (lvelden[r4][k4] == 0 ) lijn.concat("x");
            if (lvelden[r4][k4] == 1 ) lijn.concat("o");
            
            String patternString0 = "(?=(xxxx))";
            String patternString1 = "(?=(xxx-|xx-x|x-xx|-xxx))";
            String patternString2 = "(?=(xx--|x-x-|-xx-|--xx|x--x|-x-x))";
            String patternString3 = "(?=(x---|-x--|--x-|---x|)";
    	
            Pattern patroon = Pattern.compile(patternString0);
    	    Matcher matcher = patroon.matcher(lijn);
            if(matcher.find()) {
                  score = Integer.MAX_VALUE;
            }
            
            patroon = Pattern.compile(patternString1);
    	    matcher = patroon.matcher(lijn);
            if(matcher.find()) {
                  score = 100;
            }
            
          patroon = Pattern.compile(patternString2);
    	    matcher = patroon.matcher(lijn);
            if(matcher.find()) {
                  score = 10;
            }  
            
               
          patroon = Pattern.compile(patternString3);
    	    matcher = patroon.matcher(lijn);
            if(matcher.find()) {
                  score = 1;
            }
        return score;    
        }

   
   
   
   
   
   
   
   
   
   
   private int EvalueerOpstelling() {
      int score = 0;
      ArrayList aivelden = new ArrayList();
      ArrayList uservelden = new ArrayList();
      
      
      
      
      // Evaluate score for each of the 8 lines (3 rows, 3 columns, 2 diagonals)
      score += evalueer4Rij(5, 0, 5, 1, 5, 2);  // row 0
      //score += evalueer4Rij(1, 0, 1, 1, 1, 2);  // row 1
      //score += evalueer4Rij(2, 0, 2, 1, 2, 2);  // row
     // score += evalueer4Rij(0, 0, 1, 0, 2, 0);  // kol 0
     // score += evalueer4Rij(0, 1, 1, 1, 2, 1);  // kol 1
     // score += evalueer4Rij(0, 2, 1, 2, 2, 2);  // kol 2
     // score += evalueer4Rij(0, 0, 1, 1, 2, 2);  // diagonal
     // score += evalueer4Rij(0, 2, 1, 1, 2, 0);  // alternate diagonal
      return score;
   }
 
  // The heuristic evaluation function for the given line of 3 cells
  //     @Return +100, +10, +1 for 3-, 2-, 1-in-a-line for computer.
   //            -100, -10, -1 for 3-, 2-, 1-in-a-line for opponent.
   //            0 otherwise 
   private int evalueer4Rij(int row1, int col1, int row2, int col2, int row3, int col3) {
      int score = 1; 
     
                 
    if ((lvelden[row1][col1] == AGENT) && (lvelden[row1][col1] == lvelden[row2][col2]) && (lvelden[row2][col2] == lvelden[row3][col3])  ) {
       score = 1000; 
    } 
     if ((lvelden[row1][col1] == EMPTY) && (lvelden[row1][col1] == lvelden[row2][col2]) && (lvelden[row2][col2] == lvelden[row3][col3])  ) {
       score = 100; 
    }  
      return score;
   } 
   
  
   
   private void printcel(int j, int i) {
      if (lvelden[i][j] == -1) System.out.print("-");
      if (lvelden[i][j] == 0) System.out.print("x");
      if (lvelden[i][j] == 1) System.out.print("o");
    }
        
    private void printbord() {
      for (int i=0;i <= RIJ-1;i++) {
        for (int j=0;j<=KOL-1;j++) {
          printcel(j,i);
        }
        System.out.println(); 
      } 
      System.out.println();    
    }
} // einde klasse
