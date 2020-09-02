## Oversikt – Modellering
Vi kan tenke på programmet vårt som en “modell” av et dungeon crawler spill.

For å finne ut hvilke klasser og interfaces vi trenger, må vi
* a) først tenke oss hvilke elementer som inngår i spill-verdenen;
* b) finne ut hvordan vi representerer og implementerer disse på datamaskinen.

Dette er likende til prosessen vi snakket om på de første forelesningene:
1. Vi har et problemdomene, *dungeon crawling*
2. Vi analyserer domenet, og lager en abstrakt beskrivelse av det, f.eks. spillreglene skrevet på papir.
3. Vi designer programmet, og prøver å lage en konkret implementasjon.
4. Vi tester programmet vårt mot forventingene, og justerer på ting og gjentar prosessen.

Mesteparten av denne jobben er allerede gjort for deg. Din jobb er nå å sette deg inn i koden og vår dungeon crawling modell. Vi har gjort analyse og design og en god del implementasjon, så vi er ca. på slutten av punkt 3. Du må bli kjent med koden og komfortabel med å gjøre endringer, og så gå videre med implementasjonen. I siste del av oppgaven er det du som er “sjefen”, og kan gjøre (nesten) som du vil med design og regler.

### Analyse – hva er de essensielle bitene av Rogue-101?
Basert på tidligere erfaringer med slike spill, samt grunding tenking og lesing av de relevante Wikipedia-sidene, ser vi for oss følgende:

* Alt foregår på et ruteformet kart (2D grid)
* Hver celle/rute på kartet kan inneholde enten:
  * en vegg
  * 0 eller flere ting
* En “ting” i denne sammenhengen er:
  * et objekt som spilleren/noen kan plukke opp og bruke til noe – f.eks. en gulrot, et papirark, et sverd, en brunostskive, osv.
  * *eller* en aktør – en “levende” ting som kan bevege seg rundt på kartet
* For enkelhets skyld sier vi at det bare kan være maks én aktør i hver kartrute – men en aktør kan dele plass med andre ting.
* Aktørene er enten styrt av datamaskinen, eller styrt av spilleren.
* For å gjøre ting litt mer spill-aktig, har vi følgende regler for ting og aktører:
  * alle ting (inkl aktører) har “helse-poeng” som indikerer i hvor god form/stand aktøren/tingen er; negative helsepoeng betyr at tingen er helt ødelagt og skal fjernes fra brettet
  * alle ting (inkl aktører) har “forsvars-poeng” som indikerer hvor god den er til å forsvare seg (mot å bli angrepet, plukket opp, vasket bak ørene, e.l.)
  * alle aktører har "angreps-poeng" som indikerer hvor god den er til å overgå andres forsvar (og f.eks. skade dem, plukke dem opp, vaske dem bak ørene, e.l.)
  * merk deg at alle ting har helse og forsvar, selv ting som er ikke er levende – dette kan vi f.eks. bruke til å gjøre det mulig å ødelegge ting eller gjøre enkelte ting vanskelige å plukke opp

### Design – hvordan lager vi dette i Java?
Basert på dette tenker vi oss følgende typer objekter:

* [IMapView](src/inf101/v20/rogue101/map/IMapView.java), [IGameMap](src/inf101/v20/rogue101/map/IGameMap.java) – spillkartet
* [IGame](src/inf101/v20/rogue101/game/IGame.java) – selve spillet, som styrer reglene i spillverdenen
* [IItem](src/inf101/v20/rogue101/objects/IItem.java) – en ting. Siden både småobjekter (sverd og gulrøtter), aktører og vegger er ting som befinner seg på kartet, er det praktisk å gjøre alle til `IItem`:
    * [Wall](src/inf101/v20/rogue101/objects/Wall.java) – en `IItem` som ikke kan dele plass med noe annet
    * [IActor](src/inf101/v20/rogue101/objects/IActor.java) – en `IItem` som bevege seg og ikke kan dele plass med en annen `IActor`
       * [IPlayer](src/inf101/v20/rogue101/objects/IPlayer.java) – en `IActor` som styres ved at brukeren trykker på tastene
       * [INonPlayer](src/inf101/v20/rogue101/objects/INonPlayer.java) – en `IActor` som styrer seg selv (datamaskinen styrer)

Vi har også et par andre mer abstrakte ting vi bør tenke på – f.eks. koordinater. Det går an å bruke heltall som koordinater / indekser (`int x`, `int y`), men det er generelt ganske praktisk med en egen abstraksjon for grid-plasseringer; blant annet kan vi da slippe å gjøre kompliserte utregninger på koordinatene for å finne frem til andre koordinater. Vi har derfor også:
* [ILocation](src/inf101/v20/grid/ILocation.java) – en lovlig (x,y)-koordinat på kartet. Hver ILocation har opptil åtte andre ILocations som naboer, og har metoder for å finne alle eller noen av naboene, og for å finne nabo i en spesifikk retning.
* [GridDirection](src/inf101/v20/grid/GridDirection.java) – en retning (NORTH, SOUTH, ...), til bruk sammen med ILocation
* [IArea](src/inf101/v20/grid/IArea.java) – et rektangulært sett med ILocations. Brukes f.eks. av spillkartet for å lettvint gå gjennom alle cellene/rutene i kartet.
* ([IGrid<T>](src/inf101/v20/grid/IGrid.java) og [IMultiGrid<T>](src/inf101/v20/grid/IMultiGrid.java) – IGrid<T> er tilsvarende til den du har brukt i labbene tidligere; IMultiGrid<T> er et grid der hver celle er en liste av T-er. Den blir brukt av spillkartet, men du trenger neppe bruke den selv.)

UML:
<a href="https://retting.ii.uib.no/inf101/inf101.v18/wikis/img/RogueInterface.png">
<img src="https://retting.ii.uib.no/inf101/inf101.v18/wikis/img/RogueInterface.png" width="200">
</a>


### *(4%)* Deloppgave A1: Tilstand, oppførsel og grensesnitt for objektene
*Du vil sikkert finne på lurere svar på spørsmålene etterhvert som du jobber med oppgaven. Det er fint om du lar de opprinnelige svarene stå (det er helt OK om de er totalt feil eller helt på jordet) og heller gjør tilføyelser. Du kan evt. bruke ~~overstryking~~ (putt dobbel tilde rundt teksten, `~~Rabbit.java funker fordi det bor en liten kanin inni datamaskinen~~`) for å markere det du ikke lenger synes er like lurt.*

Alle grensesnittene beskriver *hvordan du kan håndtere objekter* (dvs. objekter som er av klasser som implementerer grensesnittene). Selv om tilstanden til objektene er innkapslet (du vet ikke om feltvariablene), så lar metodene deg *observere* tilstanden, så ut fra de tilgjengelige metodene kan du spekulere litt rundt hvordan tilstanden må være.

**Les gjennom grensesnittene vi har nevnt over ([IGame](src/inf101/v20/rogue101/game/IGame.java), [IMapView](src/inf101/v20/rogue101/map/IMapView.java), [IItem](src/inf101/v20/rogue101/objects/IItem.java), [IActor](src/inf101/v20/rogue101/objects/IActor.java), [INonPlayer](src/inf101/v20/rogue101/objects/INonPlayer.java), [IPlayer](src/inf101/v20/rogue101/objects/IPlayer.java)** – vent med å se på klassene) og svar på spørsmålene (skriv svarene i [README.md](../README.md), det holder med én eller noen få setninger):

* **a)** **Hva vil du si utgjør tilstanden til objekter som implementerer de nevnte grensesnittene?**  *(F.eks. hvis du ser på `ILocation` så vil du gjerne se at ILocation-objekter må ha en tilstand som inkluderer `x`- og `y`-koordinater – selv om de sikkert kan lagres på mange forskjellige måter. De må også vite om eller være koblet til et `IArea`, siden en `ILocation` ser ut til å “vite” hvilke koordinater som er gyldige.)*

* **b)** **Hva ser ut til å være sammenhengen mellom grensesnittene?** Flere av dem er f.eks. laget slik at de utvider (extends) andre grensesnitt. Hvem ser ut til å ta imot / returnere objekter av de andre grensesnittene?

* **c)** Det er to grensesnitt for kart, både [IGameMap](src/inf101/v20/rogue101/map/IGameMap.java) og [IMapView](src/inf101/v20/rogue101/map/IMapView.java). **Hvorfor har vi gjort det slik?**

* **d)** **Hvorfor tror du [INonPlayer](src/inf101/v20/rogue101/objects/INonPlayer.java) og [IPlayer](src/inf101/v20/rogue101/objects/IPlayer.java) er forskjellige?** Ville du gjort det annerledes?

### *(3%)* Deloppgave A2: Eksempler på IItem og IActor
**Til denne deloppgaven kan du se først på [Carrot](src/inf101/v20/rogue101/objects/Carrot.java) og [Rabbit](src/inf101/v20/rogue101/objects/Rabbit.java).** Svar på spørsmålene (skriv svarene i [README.md](../README.md), det holder med én eller noen få setninger):

* **e)** **Stemmer implementasjonen overens med hva du tenkte om tilstanden i Spørsmål 1 (over)?** Hva er evt. likt / forskjellig?

**Se på [Game](src/inf101/v20/rogue101/game/Game.java) og [GameMap](src/inf101/v20/rogue101/map/GameMap.java) også.**

`Rabbit` trenger å vite hvor den er, fordi den skal prøve å spise gulroten (hvis den finner en) og fordi den må finne seg et gyldig sted å hoppe videre til.
* **f)** **Hvordan finner Rabbit ut hvor den er, hvilke andre ting som er på stedet og hvor den har lov å gå?**

* **g)** **Hvordan vet `Game` hvor `Rabbit` er når den spør / hvordan vet `Game` *hvilken* `Rabbit` som kaller `getLocation()`?**

### *(8%)* Deloppgave A3: Litt endringer

**Husk att du kan kjøre programmet ved å kjøre `inf101.v19.rogue101.Main`.**
    * *Return* – gjør ett steg (selv om vi foreløpig ikke har en IPlayer på brettet)
    * Ctrl-Q / Cmd-Q – avslutt
    * F11 eller Ctrl/Cmd-F – bytt til/fra fullskjerm
    * Ctrl-R / Cmd-R – bytt mellom forskjellige varianter av 40- og 80-tegn tekstskjerm

#### Smart kanin
Hvis du ser på koden for [Rabbit.java](src/inf101/v20/rogue101/examples/Rabbit.java) finner du gjerne også ut hvorfor ting oppfører seg slik: kaninenes helse er avhengig av at de finner noe å spise, og bevegelsene er helt tilfeldige. Prøv ut noen forskjellige endringer:

* **a)** **Juster maksimale helsepoeng på kaninene** (evt. også på gulrøttene), og om du merker noen forskjell (husk at programmet foreløpig ikke gjør noe før du trykker retur/enter)
* **b)** **La kaninene alltid gå i samme retning** (f.eks. `game.move(GridDirection.NORTH` – kommenter ut den gamle koden). Prøv ut hva som skjer når de treffer vegger.
* **c)** **En annen mulighet er å alltid gå i første tilgjengelige retning;** dvs. samme som den opprinnelige implementasjonen, men plukk første retning i stedet for en tilfeldig en. Prøv dette. Ser det ut som de blir bedre eller dårligere til å finne gulrøtter?
* **d)** **En enda bedre strategi er å sjekke alle nabofeltene,** se om det ligger en gulrot der, og såfall gå dit (hvis den ikke ser en gulrot kan den f.eks. gå tilfeldig). Implementer dette. Tips:
    * når du har funnet ut hvilke retninger (GridDirection) du kan gå i, kan du finne nabofeltene med `ILocation loc = game.getLocation(dir)` (evt `game.getLocation().go(dir)`).
    * du kan finne ut hva som ligger i nabofeltet ved hjelp av kartet (`game.getMap()`); f.eks. med metoden `getItems()`.
    * kaninen har allerede kode for å sjekke gjennom tingene og se om den finner en `Carrot` – du kan kopiere og tilpasse denne
    * hvis kaninen finner en gulrot kan den gjøre `game.move(...)` og så returnere med en gang
* **e)** Kaninens jobb blir litt enklere om den får litt hjelp fra `Game` med å finne ut hvor den kan gå. **Implementer metoden `getPossibleMoves()` i `Game`.**

#### Bedre gulrøtter

Prøv også å justere gulrøttene litt ([Carrot](src/inf101/v20/rogue101/examples/Carrot.java)):

* **a)** Gulrøttene får helsen satt til -1 når de blir spist – etter helsereglene våre vil de derfor bli fjernet fra kartet. Prøv å sette helsen til 0 i stedet. Hvorfor går det ikke bedre med kaninene selv om gulrøttene nå blir værende på kartet?
* **b)** Det hadde kanskje vært praktisk (ihvertfall for kaninene) om gulrøttene vokste seg store og fine igjen etter en stund; for eksempel ved at de “helbreder” ett helsepoeng for hver runde som går – men `Carrot` har ingen `doTurn()` metode slik `Rabbit` har, så den får ikke med seg at rundene går eller at kaninene hopper rundt (rent bortsett fra at det går an å “jukse” ved å regne med / håpe på at `draw()` blir kalt en gang per runde).
   * ~~Lag en `doTurn()`-metode i `Carrot` som øker `hp` med 1 for hver runde (opp til `getMaxHealth()`).~~ (Denne fulgte med fra før.)
   * Hva skjer, ser det ut til å virke?
   * Hvis det ikke virker, hva må du eventuelt gjøre for å få `Carrot` til å gjøre noe hver runde?
* **c)** Du kan også prøve å la `Game` legge til nye, tilfeldig plasserte gulrøtter av og til:
   * `random.nextInt(100) < 20` er en grei test hvis du f.eks. vil gjøre noe med 20% sannsynlighet
   * For å finne en tilfeldig `ILocation` kan du bruke `map.getLocation(x, y)` med tilfeldig x og y (innenfor `getWidth()`/`getHeight()`). Du kan også plukke et tilfeldig element fra `map.getArea().locations()`.
   * Før du evt. putter en `new Carrot()` på kartet må du også passe på at kartruten du har funnet er ledig (ihvertfall at den ikke inneholder en vegg).

### *(0%*) Deloppgave A4: Oversikt

**Tegn en liten oversikt over det du tenker er de viktigste grensesnittene/klassene i programmet.**

* Hvis noe `implements`/`extends` noe, tegn en pil til det det implementerer/utvider.
* Du kan skrive ned f.eks. de viktigste metodenavnene eller noen stikkord om rollen til den aktuelle typen.
* Tegn også en liten oversikt over objekter i forskjellige situasjoner (f.eks. Rabbit, Carrot, Game, GameMap og hvordan de (evt) kommuniserer når kaninen holder på å gjøre noe).

(Du trenger ikke legge ved tegningen din, men du kan gjerne lage og legge ved en oppdatert utgave når du har fått bedre/full forståelse av systemet.)

### *(0%)* Deloppgave A5: Ting du ikke trenger å se på (0/100)

* Du trenger ikke se på koden i `gfx` (grafikkbibliotek), `grid` (utvidet IGrid) eller `util` (generering av testdata).
* Hvis du lager grafikk selv, vil du gjerne komme til å *bruke* [`ITurtle`](src/inf101/v20/gfx/gfxmode/ITurtle.java) (fra `gfx`), men du trenger ikke se på implementasjone.
* GameMap gjør bruk av `grid`-pakken, men du trenger antakelig ikke gjøre noe med den selv.