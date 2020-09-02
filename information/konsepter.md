## Kunnskap/konsepter du har bruk for til denne delen
* *Abstraksjon* – å se bort fra uvesentlige detaljer og konsentrere seg om de tingene som er viktig (for det vi holder på med / fra vårt synspunkt)
* *Modellering* – en modell er en (som regel forenklet) representasjon av noe. Vi bruker modeller for å
    * prøve ut / leke med / teste ting når vi ikke kan/vil gjøre direkte med det vi modellerer (f.eks.: en klimamodell lar oss forstå hvordan klimaet kan utvikle seg i forskjellige scenarier uten at vi trenger å teste det i virkeligheten; en prototype lar deg se hvordan at produkt kan bli; en modell lar deg se hvordan klær ser ut uten å måtte prøve dem på deg selv; et dataspill lar deg gjøre ting du ikke kunne gjort (eller hatt lov til) i virkeligheten; lek lar barn bygge “romskip” og utføre medisinsk behandling på “spedbarn” uten å svi av plenen eller skade lillebror).
    * ha et eksempel som vi kan bruke når vi skal lage flere ting  (f.eks.: [kilogramprototypen](https://en.wikipedia.org/wiki/Kilogram#International_prototype_kilogram), arkitekttegninger, 3D designmodeller (CAD/CAM))
* *Objekter* (`new C()`) – med oppførsel (metoder) og innkapslet tilstand (feltvariabler), og relasjoner til og interaksjon med andre objekter.
* *Interface* (`interface I`, `class C implements I`) – hvordan du kan bruke objekter som du har (og hvilke metoder objektenes klasse må implementere)

### Ting som (kanskje) er nytt:

* `interface I extends J` – både `I` og `J` må være grensesnitt
    * Alle metodene i `J` er også med i `I` (som om du hadde listet dem opp i `I`)
    * `I` blir en *subtype*  av `J` – hvis noe er en `I` (er av en klasse som implementerer `I`) så kan det også brukes som en `J` (i en variabel av type `J`, f.eks.). (Det motsatte er *ikke* tilfelle – ikke alle `J`-er er `I`-er.)
* `default`-metoder i `interface`/grensesnitt – disse gir deg kode for en metode så du slipper å implementere den selv i klassen din.
    * Default-metodene har ikke tilgang til feltvariabler, og de kan bare bruke metodene i grensesnittet.
    * Typiske default-metoder er ekstrametoder som bygger på de andre metodene (metoder som er praktisk for bruk uten å være helt nødvendige). Det er også praktisk når man senere skal legge til flere metoder i et grensesnitt, for å slippe å måtte oppdatere en hel haug med klasser.