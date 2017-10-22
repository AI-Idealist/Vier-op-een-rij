# Vier-op-een-rij
Deze repostiry bevat programmatuur die het spelletje vier op een rij implemementeert waarbij de mens tegen de computer speelt.

Spelregels

Vier op een rij wordt gespeeld op een (verticaal) bord van 8x6 vakken
    - Er zijn twee spelers
    - De spelers spelen om de beurt
    - Een beurt bestaat uit het vullen van een vak met het symbool van de betreffende speler (rood, geel: hier kruisje, rondje)
    - Een vak kan alleen gevuld worden als het op de bodem van het veld is of als het onderliggende vak gevuld is.
    - Degene die als een eerste een reeks van vier dezelfde symbolen heeft horizontaal, verticaal of diagonaal, heeft gewonnen.

Let op! Dit programma is niet af. De agent, zou in staat moeten zijn om op basis van de opstelling van het bord, te analyseren 
wat de best volgende zet is. De meeste functies daarvoor zitten in het object IdeOne.java. Verder ben ik niet tevreden over het 
gekozen datatype van het bord. Dat is nu een array of integers en dat zou een array of char moeten worden.

Project staat voorlopig on-hold.
