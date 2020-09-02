# Om semesteroppgaven
*Denne filen inneholder praktisk info om [Semesteroppgave 1: “Rogue One oh one”](https://retting.ii.uib.no/inf101.v20.sem1/blob/master/SEM-1.md)* Semesteroppgaven er *obligatorisk*, og er ment å gi innsikt i og erfaring med
teknikkene vi har lært hittil i semesteret, og å teste en del praktiske
ferdigheter som ikke er så lette å teste på eksamen. Se under angående
karakter.

### Innlevering
Oppgaven skal leveres inn via GitLab **fredag 6. mars kl. 2359**. 

*Hvis du ikke har brukt GitLab enda, bør du gå gjennom lab 0 og lab 2.*

Hvis du får mindre enn 40 poeng på én eller begge av semesteroppgaven **får du ikke ta eksamen**.  

* **Semesteroppgaven vil være mye lettere å løse når du har ferdighetene og teorien fra lab-oppgavene** – det er best å ta seg tid til å løse disse først!

### Læringsmål

Målene for denne semesteroppgaven er:

* Å kunne sette seg inn i et eksisterende program/rammeverk, og utvide det ut ifra *spesifikasjoner* (beskrivelsen i oppgavene).
* Å bruke *interfaces* til å kommunisere mellom objekter.
* Å lage programmer hvor objektene selv styrer sin egen oppførsel 
* Å beskrive komplekse objekt-orienterte systemer på en forståelig måte.

### Retting og poeng
Semesteroppgaven blir rettet av en gruppeleder, som gir tilbakemeldinger på
innleveringen. Semesteroppgaven gir deg poeng, og til sammen teller de to 
semesteroppgavene 30 % på karakteren i faget. 

Ved poengsetting legger vi vekt
på følgende:

* At du har fungerende løsninger på de forskjellige deloppgavene
* At koden din er ryddig og at eventuelle deler som er vanskelig å forstå er forklart i kommentarer
* At du har laget tester for koden din
* At tekst-svarene er riktige og forståelige
* Kreativitet, og at du gjør mer enn minimum for å fullføre oppgaven

Du kan regne med en godt gjennomført innlevering som oppfyller minimumskravene gir en
poengsum ca. tilsvarende B. For høyere poengsum må man ha gjort en del av bonusoppgavene. Manglende /
svært mangelfull innlevering gir 0 poeng.

### Samarbeid

Innleveringen er *individuell* og kan ikke løses i grupper. Dere står likevel fri
til å samarbeide om utarbeiding av ideer, diskutere løsninger og å hjelpe
hverandre med å finne og løse problemer (vi oppfordrer faktisk til det!) – men programmeringen må du gjøre selv, og du er selv ansvarlig for din egen kode og at du vet og kan forklare hvordan den virker.

Hvis du har diskutert ideer eller løsninger med noen, gi en kort redegjørelse for det i `Svar.md` og evt. i commit-meldingen hvis det er relatert til en konkret commit. F.eks. *“‘Kaniner-og-aliens’-konseptet er tenkt ut sammen med Helene Harepus, men vi har kodet det hver for oss”; “Sorter elementene i riktig rekkefølge (fixes #23, takk til bestemor som la merke til feilen)”.*

### Fusk og opphavsrett
Forøvrig gjelder [UiBs regler om fusk og plagiat](http://www.uib.no/studiekvalitet/77864/fusk-hva-er-det-og-hvilke-konsekvenser-f%C3%A5r-det-deg-som-student). Akademisk uredelighet og (forsøk på) fusk reguleres av Universitetsloven, og mulige konsekvenser er blant annet annullering av eksamen og utestenging (evt. tilbaketrekking av vitnemålet om ting blir oppdaget i ettertid).

(*Men:* Så lenge det er klart og tydelig hvem som har skrevet hva, hva kilden er og hvem som evt. har hjulpet til med hva, er det *ikke* fusk eller plagiat – men du får selvfølgelig bare poeng for ting du har gjort selv.)

Opphavsrett er et separat spørsmål – du kan generelt ikke klippe kode eller bruke bilder/lyd/media fra nettet [uten at du har tillatelse](https://retting.ii.uib.no/inf101/inf101.v19/wikis/opphavsrett-lisenser). Hvis du bruker ting du har funnet på nettet e.l. må du opplyse i `README.md` om hva det er, hvem som har laget det og hvor du har funnet det. For grafikk/lyd som du har rett til å gjenbruke, se gjerne etter ting med [Creative Commons lisens](https://creativecommons.org/licenses/). Vi har en liste med greie kilder på slutten av oppgaven. (Og om du er nysgjerrig, finner du lisensen for koden du har fått utlevert i filen [LICENSE](../LICENSE).)

### Innlevering
 Du finner koden din i repositoriet med URIen:

    https://retting.ii.uib.no/<brukernavn>/inf101.v20.sem1.git

Oppgaven leveres inn ved å pushe til retting.ii.uib.no, [slik du har gjort med alle tidligere INF101-oppgaver](https://retting.ii.uib.no/inf101/inf101.v20/wikis/hente-levere-oppgaver). Husk å få med eventuelle nye filer du har opprettet (hvis testene virker hos deg, men ikke i innleveringssystemet, er det gjerne det som er feil).

**VIKTIG:** *Sjekk kvitteringssiden som kommer opp når du pusher, i tilfelle det skjer feil!* Du må evt. gjøre Pull før Push, slik du så i (TODO link).

Vi anbefaler at du gjør commit hver dag, eller hver gang du er ferdig med en
deloppgave, i tilfelle du mister det du jobber med på din egen maskin.

* Du kan levere inn så mye og ofte du vil. Versjonen som teller er den **siste du
  pushet før innleveringsfristen**.

* *VIKTIG:* Hvis du ikke allerede har prøvd ut GitLab / https://retting.ii.uib.no/ og pushing av
  innleveringer, må du gjøre det *med en gang* (gjør labbene!). Du kan ikke regne med å få hjelp til
  dette på innleveringsdagen, så gå på gruppetimer **så tidlig som mulig**.

* Alle testene bør passere (være grønne). Det blir i tillegg lagt betydelig
  vekt på kodekvalitet og dokumentasjon. Dvs. koden din skal ikke bare *virke*,
  den være *lett å forstå og å endre*.

* Du kan selv sjekke status i
  [innleveringssystemet](http://retting.ii.uib.no:81/) – det vil gi rask
  tilbakemelding hver gang du pusher til Gitlab, også før innleveringsfristen.
  Alt skal være *grønt* der. Hvis du ser feil der som du ikke finner ut av, er det bare å spørre om hjelp.

## Symboler i oppgaveteksten

Vi har lagt inn en del symboler i oppgaveteksten for å markere viktige ting i deloppgavene. Det kan være at disse ikke vises skikkelig hvis du leser README-filen inne i Eclipse, da anbefaler vi at dere leser de på Retting (INF101 sin GitLab-instanse).

- 👉: Dette er selve oppgavesetningen som sier hva som skal gjøres.
- ✅: Dette er en test som (sannsynligvis) er rød for du gjør oppgaven, og skal bli grønn når oppgaven er ferdig.
- 🤔: Dette er et spørsmål til refleksjon. Du skal ikke levere noe her, men forståelse for dette vil hjelpe deg til å løse oppgaven.

## Sjekkliste:
TODO: liker ideen om sjekkeliste, fyll ut eller slett

### Tips
* Selv om det kanskje bare er litt mer å gjøre enn i en vanlig ukeoppgave, er
  det *veldig mye* å sette seg inn i. Du bør begynne tidlig og jobbe jevnt. Du må
  også regne med å jobbe utenom labtimene.

**Utsettelse:** 
   * Hvis du trenger forlenget frist er det mulig å be om det (spør gruppeleder – evt. assistenter/underviser hvis det er en spesiell situasjon). Hvis du ber om utsettelse bør du helst være i gang (ha gjort litt ting, og pushet) innen den første fristen.
   * Om det er spesielle grunner til at du vil trenge lengre tid, så er det bare å ta kontakt, så kan vi avtale noe. Ta også kontakt om du [trenger annen tilrettelegging](http://www.uib.no/student/49241/trenger-du-tilrettelegging-av-ditt-studiel%C3%B8p). 