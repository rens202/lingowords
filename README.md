# lingowords
lingowords

<a href="https://codecov.io/gh/rens202/lingowords">
  <img src="https://codecov.io/gh/rens202/lingowords/branch/master/graph/badge.svg?token=2XBRL38V0V" />
</a>

Welkom Alex.

In de beschrijving stond dat ik de README kon gebruiken om je aandacht ergens extra neer te leggen so here we go.
UnitTests - > src/main/test/java

Java Package ordening
Domain -> obviously domain
Persistence -> DAO implementatie
Webservices -> webservices

Deployed op heroku. https://hu-lingowords.herokuapp.com/
PLEASE NOTE: Heroku ondersteund niet veel database regels dus als je een file link upload (bijvoorbeeld: https://github.com/dwyl/english-words/blob/master/words.txt) het niet te vaak doet. Als je 3x deze file zou uploaden wordt de database offline gehaald en doet niks het meer.
Heroku ondersteund ook deployed log.

Heroku ondersteund ook automatic deployement:
//automatic deploys from  master are enabled

Github actions voor package maken en codecov. 

Het lingowords idee was vrij breed en eigen vrijheid, dus mijn database bestaat uit:

Languages
Wordlists
Words

Words -< Wordslists -< Languages

Het is mogelijk nieuwe languages aan te maken en vervolgens nieuwe woordlijsten te maken met deze taal, deze valt vervolgens mij lingogame te selecteren. De taal van de "interface" engels.
