package vieropeenrij;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.*;
import static vieropeenrij.Spel.RIJ;
import static vieropeenrij.Spel.KOL;
import static vieropeenrij.Spel.AGENT;
   
/* Deze klasse is een tijdelijke rommelklassse met functies die de AI-agent 
 moeten helpen een goede analyse te maken van de opstelling van het bord.  
*/
class Ideone  {
   static char[][] lvelden = new char[RIJ][KOL];
       
    public static void main (String[] args) throws java.lang.Exception{
      String plijn = "xxxx";
      System.out.println(plijn + " " + scoreLine(plijn,0));
      plijn = "xx-o";
      System.out.println(plijn + " " + scoreLine(plijn,0));
            
      BufferedReader inputStream = null;
      inputStream = new BufferedReader(new FileReader("bord.txt"));
    
      String l;
      int i = 0;
      while ((l = inputStream.readLine()) != null) {
        for (int j = 0; j <= KOL-1; j++) {
          lvelden[i][j] = (char)l.charAt(j);
        }
      i++; 
      }
           
     for ( i = 0; i <= RIJ-1; i++) {
       for (int j = 0; j <= KOL-1; j++) {
         System.out.print(lvelden[i][j]);
       }
     System.out.println();
     }
   
    int[][] lijn = new int[4][2];
    lijn = rechterBuren(3,3);
            printbord(lijn);
            String line = createLine(lijn);
            int score = scoreLine(line,0);
            System.out.println(line + " " + score);
                        
                      
            lijn = linkerBuren(3,3);
            printbord(lijn);
            line = createLine(lijn);
            score = scoreLine(line,0);
            System.out.println(line + " " + score);
                        
            
            lijn = bovenBuren(3,3);
            printbord(lijn);
             line = createLine(lijn);
             score = scoreLine(line,0);
            System.out.println(line + " " + score);
              
            
            
            
            lijn = onderBuren(2,3);
            printbord(lijn);
             line = createLine(lijn);
             score = scoreLine(line,0);
            System.out.println(line + " " + score);
              
            
                    
                        
            lijn = rechterbovenBuren(3,3);
            printbord(lijn);
              line = createLine(lijn);
             score = scoreLine(line,0);
            System.out.println(line + " " + score);
             
              lijn = linkerbovenBuren(3,3);
            printbord(lijn);
              line = createLine(lijn);
             score = scoreLine(line,0);
            System.out.println(line + " " + score);
            
            
             lijn = rechteronderBuren(2,3);
            printbord(lijn);
              line = createLine(lijn);
             score = scoreLine(line,0);
            System.out.println(line + " " + score);
           
             lijn = linkeronderBuren(2,3);
            printbord(lijn);
              line = createLine(lijn);
             score = scoreLine(line,0);
            System.out.println(line + " " + score);
            
            
            int t = scoreFiche(0,0);
            System.out.print(t);
            
                 
     
            
            
            
        }
        
        
       
       public static String createLine(int[][] plijn) {
          String line = new String();
          for (int i=0;i<=plijn.length-1;i++){
            int rij = plijn[i][0];
            int kol = plijn[i][1];
            line = line.concat(String.valueOf(lvelden[rij][kol]));
          }  
        return line;   
       }
        
        
        public static int scoreLine(String plijn, int speler) { 
           String vieroprij = "(?=(xxxx))";                    
           String drieoprij = "(?=(xxx-|xx-x|x-xx|-xxx))";
           String tweeoprij = "(?=(xx--|x-x-|-xx-|--xx|x--x|-x-x))";
           String eenoprij = "(?=(x---|-x--|--x-|---x))";
           
           if (speler == AGENT) {
               vieroprij.replace("x","o");
               drieoprij.replace("x","o");
               tweeoprij.replace("x","o");
               eenoprij.replace("x","o");
           }
            
    		Pattern patroon = Pattern.compile(vieroprij);
    		Matcher matcher = patroon.matcher(plijn);
                if (matcher.find()) {
                  return 1000;
                }    
                
                patroon = Pattern.compile(drieoprij);
    		matcher = patroon.matcher(plijn);
       		if (matcher.find()) {
                  return 100;
                }  
              
                patroon = Pattern.compile(tweeoprij);
    		matcher = patroon.matcher(plijn);
      		if (matcher.find()) {
                  return 10;
                }
                
                patroon = Pattern.compile(eenoprij);
    		matcher = patroon.matcher(plijn);
      		if (matcher.find()) {
                  return 1;
                }
                return 0;
        }
        
       public static int[][] rechterBuren(int row, int kol) {      
            int[][] lijn = new int[4][2];
                        
            for (int i=0;i<=3;i++){
                lijn[i][0] = row;
                lijn[i][1] = kol+i;
            }
            return lijn;           
    }
       
       public static int[][] linkerBuren(int row, int kol) {      
            int[][] lijn = new int[4][2];
                        
            for (int i=0;i<=3;i++){
                lijn[i][0] = row;
                lijn[i][1] = kol-i;
            }
            return lijn;           
    }   
       
     public static int[][] bovenBuren(int row, int kol) {      
            int[][] lijn = new int[4][2];
                        
            for (int i=0;i<=3;i++){
                lijn[i][0] = row-i;
                lijn[i][1] = kol;
            }
            return lijn;           
    }   
    
     public static int[][] onderBuren(int row, int kol) {      
            int[][] lijn = new int[4][2];
                        
            for (int i=0;i<=3;i++){
                lijn[i][0] = row+i;
                lijn[i][1] = kol;
            }
            return lijn;           
    }  
     
     
       public static int[][] rechterbovenBuren(int row, int kol) {      
            int[][] lijn = new int[4][2];
                        
            for (int i=0;i<=3;i++){
                lijn[i][0] = row-i;
                lijn[i][1] = kol+i;
            }
            return lijn;           
    }  
     
       public static int[][] rechteronderBuren(int row, int kol) {      
            int[][] lijn = new int[4][2];
                        
            for (int i=0;i<=3;i++){
                lijn[i][0] = row+i;
                lijn[i][1] = kol+i;
            }
            return lijn;           
    }    
       
    public static int[][] linkeronderBuren(int row, int kol) {      
            int[][] lijn = new int[4][2];
                        
            for (int i=0;i<=3;i++){
                lijn[i][0] = row+i;
                lijn[i][1] = kol-i;
            }
            return lijn;           
    }       
     
    public static int[][] linkerbovenBuren(int row, int kol) {      
            int[][] lijn = new int[4][2];
                        
            for (int i=0;i<=3;i++){
                lijn[i][0] = row-i;
                lijn[i][1] = kol-i;
            }
            return lijn;           
    }  
    
      public static int scoreFiche(int row,int kol) {
         int[][] lijn = new int[4][2];
         int score = 0;
         int speler = AGENT;
          
          
         if (kol <= 3) { 
           System.out.println("bepaal rechterbuur");
           lijn = rechterBuren(row,kol);  
           score = score + scoreLine(createLine(lijn),speler); 
         }
                  
         if (kol>= 3) { 
           System.out.println("bepaal linkerbuur");
           lijn = linkerBuren(row,kol);  
           score = score + scoreLine(createLine(lijn),speler);          
         
         }
         
         if (row <= 3) { 
           System.out.println("bepaal onderbuur");
           lijn = onderBuren(row,kol);  
           score = score + scoreLine(createLine(lijn),speler);  
         }
         else {
           System.out.println("bepaal bovenbuur"); 
           lijn = bovenBuren(row,kol);  
           score = score + scoreLine(createLine(lijn),speler);  
         }
         
         if (kol <= 3 && row <= 2) {
           System.out.println("bepaal rechteronderbuur"); 
           lijn = rechteronderBuren(row,kol);  
           score = score + scoreLine(createLine(lijn),speler);  
         }
         if (kol <= 3 && row >= 3) {
           System.out.println("bepaal rechterbovenbuur"); 
           lijn = rechterbovenBuren(row,kol);  
           score = score + scoreLine(createLine(lijn),speler);  
         }    
         if (kol >= 3 && row <= 2) {
           System.out.println("bepaal linkeronderbuur"); 
           lijn = linkeronderBuren(row,kol);  
           score = score + scoreLine(createLine(lijn),speler);  
         }
         if (kol >= 3 && row >= 3) {
           System.out.println("bepaal linkerbovenbuur"); 
           lijn = linkerbovenBuren(row,kol);  
           score = score + scoreLine(createLine(lijn),speler);  
        }    
      return score;
      }
      
      
      public static void printbord(int[][] lijn) {
      System.out.println(" ");
      for (int i=0;i <= 3;i++) {
           for (int j=0;j<=1;j++) {
                System.out.print(lijn[i][j]);
           }
       System.out.println(); 
       } 
     System.out.println();    
   } 
       
       
 }     
