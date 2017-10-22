package vieropeenrij;

import static vieropeenrij.Spel.EMPTY;
import static vieropeenrij.Spel.AGENT;
import static vieropeenrij.Spel.USER;
import static vieropeenrij.Spel.KOL;
import static vieropeenrij.Spel.RIJ;

public class Bord {
     
        public int[][] velden = new int[RIJ][KOL];; 
  
        public Bord() {
         
          for (int i=0;i <= RIJ-1;i++) {
             for (int j=0;j <= KOL-1;j++) {
                velden[i][j] = EMPTY;
             }
          }   
        }  
        
        public boolean isLegaleZet(int pzet) {
            boolean result = false;
            int pkolom = pzet;
                       
            if  (0 <= pkolom && pkolom <= KOL-1) {
               if ( !(kolomIsVol(pkolom)) ) {
               result = true;}
            } 
                      
         return result;
        }      
        
          public boolean kolomIsVol(int pkolom) {
          boolean result = false;
                   
          if (this.velden[0][pkolom] != EMPTY) {
             System.out.println("Deze Kolom is vol.");
             result = true; 
          }
         return result;
     }
        
     public void DoeZet(int pkolom, int pspeler){ 
        // kan effiecenter  
        int toprij = bepaalTopRij(pkolom); 
        this.velden[toprij][pkolom] = pspeler;
      }   
        
     
     public int bepaalTopRij ( int pkolom) {
        int toprij = 0; 
        for (int i = 0; i<= RIJ-1; i++) {
          if (this.velden[i][pkolom] == EMPTY) {// er is nog ruimte
            toprij++;
          }
        }
        return toprij -1; 
     }  
        
   public boolean isGelijkSpel() {
      for (int i = 0; i <= RIJ-1; i++) {
         for (int j = 0; j <= KOL-1; j++) {
            if (velden[i][j] == EMPTY) {
               return false;  
            }
         }
      }
      return true; 
   }
   
   
 public boolean gewonnen(int speler){

    // horizontalCheck 
    for (int j = 0; j<=KOL-1-3 ; j++ ){
        for (int i = 0; i<=RIJ-1; i++){
            if (velden[i][j] == speler && velden[i][j+1] == speler && velden[i][j+2] == speler && velden[i][j+3] == speler){
                return true;
            }           
        }
    }
    // verticalCheck
    for (int i = 0; i<=RIJ-1-3 ; i++ ){
        for (int j = 0; j<=KOL-1; j++){
            if (velden[i][j] == speler && velden[i+1][j] == speler && velden[i+2][j] == speler && velden[i+3][j] == speler){
                return true;
            }           
        }
    }
    // ascendingDiagonalCheck 
    for (int i=3; i<=RIJ-1; i++){
        for (int j=0; j<=KOL-3-1; j++){
            if (velden[i][j] == speler && velden[i-1][j+1] == speler && velden[i-2][j+2] == speler && velden[i-3][j+3] == speler)
                return true;
        }
    }
    // descendingDiagonalCheck
    for (int i=3; i<=RIJ-1; i++){
        for (int j=3; j<=KOL-1; j++){
            if (velden[i][j] == speler && velden[i-1][j-1] == speler && velden[i-2][j-2] == speler && velden[i-3][j-3] == speler)
                return true;
        }
    }
    return false;
}  
   
    
    public void printcel(int i, int j) {
      if (velden[i][j] == EMPTY) System.out.print("-");
      if (velden[i][j] == USER) System.out.print("x");
      if (velden[i][j] == AGENT) System.out.print("o");
    }
        
   public void printbord() {
      System.out.println(" ");
      for (int i=0;i <= RIJ-1;i++) {
           for (int j=0;j<=KOL-1;j++) {
                printcel(i,j);
           }
       System.out.println(); 
       } 
     System.out.println();    
   }
 }