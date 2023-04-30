# Crystalline: Shattered Siege

Crystalline: Shattered Siege er et strategibasert Tower Defense spill, likt Bloons TD, der spilleren må forsvare sitt territorium mot bølger av inntrengende fiender. For å klare dette plasserer spilleren strategisk tårn langs fiendens bane. Tårnene angriper automatisk fiendene når de passerer forbi.


## Spilldemo

https://youtu.be/0NtGqEaoqWs


## Installasjon

For å installere og kjøre spillet, følg instruksjonene nedenfor:

1. Klone repositoriet til din lokale maskin:
```
git clone https://git.app.uib.no/Ditt.Brukernavn/sem2.git
```

2. Åpne prosjektet i din foretrukne Java IDE (f.eks., IntelliJ IDEA eller Eclipse).

3. Bygg og kjør `Main.java` for å starte spillet.

## Spillkontroller

- **Venstre museklikk**: Velg et tårn fra tårnmenyen og dra for å plassere det på spillefeltet.

## Spillkomponenter

Spillet består av følgende hovedkomponenter:

- **Fiender**: Forskjellige typer fiender invaderer spillerens territorium i bølger. Fiender har ulik helse, skade og hastighet.
- **Tårn**: Spilleren kan plassere forskjellige typer tårn for å forsvare territoriet. Tårnene har ulik rekkevidde, skade og skytehastighet. Tårnene kan også oppgraderes for å forbedre deres egenskaper.
- **Prosjektiler**: Tårnene skyter prosjektiler mot fiendene. Prosjektiler har ulik skade og effekter (f.eks., saktegjørende eller eksplosiv skade).
- **Brett**: Spillebrettet er delt inn i rutenettceller der tårn kan plasseres. Spilleren må forhindre fiender fra å nå slutten av banen ved å plassere tårn strategisk.
