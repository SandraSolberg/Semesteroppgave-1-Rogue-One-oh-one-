# Svar på spørsmål

*I denne filen legger du inn svar på spørsmålene som stilles i oppgaveteksten, eventuelt kommentarer og informasjon om kreative løsninger*

## Kommentarer
(Eventuelle kommentarer på oppgaven eller koden her).

## Spørsmål

## Oppgave 1 - Abstrakte Ting

### 1.1) //må ryddes
##Hvordan ville du abstrahert “ting” fra beskrivelsen av Rogue-spillet?

  👉  
 

1. ( I et interface som Iitem abstraherer ting-objekter bør egenskaper som gjelder alle ting i spille ligge.
     Alle ting har flere felles funksoner. Dette er en viktig egenskap for tingene. 
     Så interfacet som omhandler ting ITing. Dette kan være 
     ting som forteller hvordan de skal tegnes Paint() alle objekter må bli fremstilt)
     
 2.( size() alle ting må ha en størrelse å forholde seg til. Kan være viktig når vi skal implementere andre metoder 
3. (I beskrivelsen av Rouge101 står det at ting har HP, dermed må vi ha styr på dette HP() hvordan de mister dette og hvordan de får mer av dette)
4. (behavior() som kan bli implementer av enkle objekter og større objekter. Dette kan inneholde at noen ikke gjør noe annet en å ligge strødd, mens andre ting beveger seg)
5. (Poistion er også veldig viktig, at vi kan hente ut lokasjoner for elementene , men vanligvis er dette abstrahert til egne interfaces så en ting til alle ha tilfelles er vell value() vi kunne laget prioritering etter hva andre objekter vil ønske å forfølge eller angripe. Så vi kunne gitt alle en value etter sjeldenhet/hvordan de kan bli prioritert av andre ) 

### 1.2) // må ryddes
1. (Item implementerer en generisk liste med objekter (Comparable) Denne er bra for å gi en liste med prioriterte objekter etter størrelse på mapen
    Her dukker getHealthStatus() som er litt det samme som jeg tenkte på da jeg snakket om HP(). Den holder styr på damage hvor hvert objekt tar handleDamage(int amount) som var litt det jeg var borti
    Den abstraherer symboler til hvert element getSymbol() og navn getShortName(),getLongName() dette er noe alle objekter har og kan abstraheres, men jeg tenkte ikke på det fra beskrivelsen
    Den abstraherer en maxHealth for hvert objekt
    Istedenfor Paint getColor implementerer den String getGraphicTextSymbol() og tegner som gir tilbake en tekst "typsik emoji" figur istendenfor et paint-objekt fra et bilde, men her har den også en draw som bruker IBrush som extender IPainter) 

svarte bare velig kompakt her. ble kunstig å dele opp


### 1.3) // må ryddes
1.  (De to mest spennede egenskapene Carrot-klassen implementerer i Iitem er de metodene som omhandler Health og Draw
        int hp til objektet blir satt til getMaxHealth() som Carrot implementerer som 5 i overriden. Da blir også getCurrentHealth() satt til 5 ettersom den returnerer hp i Carrot. handleDamage() gjør ingenting enda med getCurrentHealth bare retunerer hvor antall amount.
         Egenskap nr er måten den bruker Item til å bli tegnet. Den lager et paramter som henter emojis hentet fra RougeApplications.Emojies) 

2. (svar her) 
3. (svar her) 
4. (svar her) 
5. (svar her) 

### 1.4)
(Det er en rekke andre klasser som implementerer Iitem
De "dumme" ojektene som DUST og Amulet. Disse bruker da metodene/elementene satt i Iitem de er låst til Iitem kontrakten, mens de "smarte" objektene bruker IActor interfacet som extender Iitem og er dermed en subklasse, disse arver metoder fra foreldre-klassen Iitem men lager også sin egen kontrakt med nye metoder. Rabbit må dermed implementere alle metodene i Iitem og IActor ) 


## Oppgave 2 - The Rabbit

### 2.1)
(IActor extender Iitem. Dette betyr at det er en subklasse av Iitem og den arver metoder fra Iitem, En klasse som implementerer IActor må bruke de metodene som er i IActor samt de metodene som er i Iitem (Default metodene i Iitem er valgfrie))

### 2.2)
(Rabbit overrider metoden doTurn() i IActor, Dersom rabbiten har en Hp differanse på over 2 og den spiser bruker den turen på å stå den i ro, ellers kaller den på preformMove() som har en liste med possible moves som blir shufflet for Rabbiten hvor den velger første posisjon i listen (altså den tar et tilfeldig move. Enten spiser den ellers tar den et random-move) ) 

### 2.3)
(Implementasjon av getPossibleMoves(), ligner litt på lab 4)

### 2.4)
(Svar her)

## Oppgave 3 - Objektfabrikken

### 3.1)
(ItemFactory og Player-klassen ser vi at et Player-Object blir presentert ved Symbol = @ 
     mens dust blir ikke kalt på i createItem i ItemFactory, men har symbolet '.' som vist i Dust-klassen))

### 3.2)
(Fra 3.1 fant vi 'R' satt i iFactory, en annen ting er i testItemFactoryCreateDust ble også dens symbol sjekket mot dens symbol skrevet inn '.'. 
 i Rabbit ser vi at SYMBOL = 'R' er public, med et lite høyre klikk på SYMBOL -> Go to -> show Declaration or Usage gir en indikasjon på hvilke klasser som bruker symbolet, her dukket ikke ItemFactory opp.
 dette bryter med S.O.L.I.D og kun fordi jeg har vært der tidligere vet jeg at den er satt til 'R' i ItemFacotry. 
  Etter endring i ItemFactory er dette fikset Single Responsibility når det gjelder symboler fikset. 
  Single Responsibility går ut på hver klasse skal ha ansvar over en enkel del ac funksjonaliteten i programmet, og det ansvaret skal være lukket i den klassen.)

### 3.3)
(svar her)

## Oppgave 4 - Et smartere kart

### 4.1)
(svar her)

### 4.2)
(svar her)

### 4.3)
(svar her)

## Oppgave 5

### 5.1)
(svar her)

### 5.2)
(svar her)

### 5.3)
(svar her)

### 5.4)
(svar her)

### 5.5)
(svar her)

## Oppgave 6

### 6.1)
(Svar her)

### 6.2)
(Svar her)

### 6.3)
(Svar her)

## Fri oppgave (Oppg. 7)

### Plan
(Måten jeg ville fortsatt med dette prosjektet hadde vært ved å legge in Heart objects som gav mer hp etter om man mistet det ved spider attack
  En annen ting jeg ville gjort er å skifte level når player har fullført. En morsom idee hadde vært å hatt et level med litt dumme rabbits/spider, så et vanskeligere level med spiders som bruker get reachable for å lete etter player
  En litt gøy ting kunne vært at player kunne funnet et våpen og beskytte seg mot spiders)

### Utførelse
(Her fikk jeg begynt på punkt en med heart objects som gir liv dersom player plukker det opp og har mindre enn 100 i hp. Om den plukker det opp fylles liv på inntil 100 hp.
Om player allerede har 100 hp kan den spare heart-objektet i baggen og droppe for å så plukke det opp igjen og få mer liv. )

### Flere utvidelser
(Legg inn eventuelle flere utvidelser du har gjort her)