package vieropeenrij;

import java.util.Scanner;

public class Spel {
    static final int EMPTY = -1;
    static final int USER = 0;
    static final int AGENT = 1;
    static final int KOL = 7;
    static final int RIJ = 6;
   
  Bord bord = new Bord();
  int zet= -1;
  AISpeler aispeler = new AISpeler(bord);
   
public int GebruikerKiestZet(){
  int zet = -1;

  Scanner scan = new Scanner(System.in);
      
  while (!bord.isLegaleZet(zet)){
    System.out.print("Welke kolom? ");
     int kol = scan.nextInt();
     zet = kol;
 }
  return zet;
}
 
    // De gebruiker speelt met fiche x
    // De agent speelt met fiche o
    public int speel() {
            
        bord.printbord();
        Scanner scan = new Scanner(System.in);
        System.out.println("Wil jij beginnen? (J/N) ");
        String antwoord = scan.next().trim();
    
        if(antwoord.equalsIgnoreCase("N")) {
             zet = 3;
             bord.DoeZet(zet, AGENT);  
             bord.printbord();
        }
        
        while(true){ 
            zet = GebruikerKiestZet();
            bord.DoeZet(zet,USER); 
            bord.printbord();
            if (this.bord.gewonnen(USER)) {System.out.println("U wint!");break;}
             else if (bord.isGelijkSpel()) {System.out.println("Gelijk spel");break;}
            
            zet = aispeler.KiestZetNaief();
            bord.DoeZet(zet, AGENT);
            bord.printbord();
            if (this.bord.gewonnen(AGENT)) {System.out.println("Agent Wint!");break;}
            else if (bord.isGelijkSpel()) {System.out.println("Gelijk spel");break;}
        }
        return 1;
        }     
    
     public static void main(String[] args) {
               
        Spel spel = new Spel();
        spel.speel();
    }
   
}
