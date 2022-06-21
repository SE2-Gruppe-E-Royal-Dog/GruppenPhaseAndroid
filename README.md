# GruppenPhaseAndroid
Im Rahmen unseres Software Engineering 2 Projektes haben wir eine abgeleitete Version des Brettspiels "Dog Royal" als Android App implementiert.

Nachdem unsere App gewisse Sonderfunktionen hat, welche im normalen Spiel nicht gegeben sind, haben wir unsere neu entwickelte Version als "CAT ROYAL" bezeichnet.
![ic_launcher-playstore](https://user-images.githubusercontent.com/101360700/174664561-e1d662cf-c6c3-4905-8162-9770e6d5b67f.png)


Spielmaterial:
1 Spielplan
110 Spielkarten (je 10x die 11 und 13, je 7x 2, 3, 4,
5, 6, 1-7, 8, 9, 10, 12, = und Tauscher, 6x Magnet), 
16 Spielfiguren (pro Farbe 4 Figuren (König, Ritter,Bürger und Narr)), 
4 Wurmlöcher, 
ein Anschuldigungsfeld bei welchen man Schummeln anklagen kann

Spielziel:
Gewonnen hat der erste Spieler, der alle 4 Steine in seinem Zielbereich hat.
Sobald dies der Fall ist, scheint ein Gewinnerfeld auf, bei dem eine Platzierung von allen Spielern vorgenommen wird. Es zählen die Figuren, welche sich im Zielfeld befinden.

Spielvorbereitung:
Jeder Spieler meldet sich bei der Lobby über seinen Username an. Bis zu vier Spieler können teilnehmen. Nachdem alle Spieler, welche mitspielen wollen, anwesend sind kann ein Spieler den "Start Game" Button drücken und das Spiel wird gestartet und jeder Spieler wird darüber informiert, welche Farbe er/sie hat. Es beginnt derjenige Spieler, der als erstes die Lobby gejoint hat. Am Spielfeld erscheint für jeden Player, welcher Spieler gerade am Zug ist.

Bewegen und Schlagen
Jeder Spieler erhält eine Deck mit fünf Karten. Für das Fahren mit einer Figur muss man eine Karte auswählen und dann die gewünschte Figur. Auf jedem Feld darf maximal eine Figur stehen. Sollten auf eine Feld zwei Figuren zu stehen kommen, dann kommt es zum Schlagen und die geschlagene Figur wird auf den Starbereich gesetzt.

Figuren:
Der König:
Er darf nur mit Karten des Wertes 1 bis 7, den Startkarten, 
den Tauschkarte, den Magnetkarte und mit den Kopiekarte - 
solange der vorherige Zug für den König zulässig ist - gefahren werden. 
Die Verbotskarten sind mit einer durchgestrichenen Krone markiert. 
Ein König kann nur von einem anderen König geschlagen werden - 
außer er befindet sich auf einem fremden Startfeld. 
Auch kann er mit einer minus 4 Karte geschlagen werden.

Der Ritter:
Für den Ritter gelten die Hierarchieregeln nicht. 
So sind auch fremde Startfelder, die
von einer gleichfarbigen Figur
besetzt sind, keine Blockade für ihn. Er darf
darüber hinwegziehen. 

Der Bürger:
Der Bürger ist keine gewöhnliche
Figur. Er hat mehr Ansehen als ein
Narr (kann diesen also überholen),
steht aber unter dem Ritter und dem
König.

Der Narr:
Der Narr kann, wenn er ins Ziel
hineinzieht, bis zu 2 Punkte verfallen
lassen. Im Verlauf des Spiels darf
der Narr auf der umlaufenden Bahn
– wie alle anderen Figuren auch –
keine Punkte verfallen lassen.


Karten:
Wir unterscheiden Karten mit verschiedenen Farben und Funktionen. Blaue Karten geben die genau Zahl an, welche die Spielfigur vorwärts bewegt wird. 
Rote Karten, hier werden verschiedene Events implementiert. Es gibt eigene Karten für Tausch, Start, Magnet oder Kopie. 
Es gibt eine grün/rote Karte bei der man entscheiden kann ob man 4 Felder nach vorne oder nach hinten rückt.
Die grüne sowie die grün/rote Karte ignoriert die Hierarchie der Spielfigueren und man kann ohne Blockaden die angebene Zahl am Spielfeld fahren.


Wurmlöcher:
Wenn eine Figur auf ein mit Spiralenform gekennzeichnetes Feld fährt, wird die Figur zu einem anderen Wurmloch teleportiert. Die Wurmlöche können mittels Abdecken des Lichtsensors am Handy random bewegt werden. Eine Verschiebung der Wurmlöcher ist nur möglich, wenn man selbst nicht am Zug ist, sowie nur einmal pro fünf Runden.

Schummelfunktion:
In Cat Royal ist eine eigene Schummelfunktion implementiert. Ist man am Zug und hat eine Karte ausgwählt, so kann man das Handy nach links oder rechts kippen und dies verändert den Kartenwert um ein Feld mehr oder ein Feld weniger. Nach links bedeutet einen Wert weniger fahren und nach rechts kippen bedeutet einen Wert mehr fahren. Jeder Spieler kann einmal pro fünf Runden Schummeln, sprich das Schummeln ist an die Neuverteilung der Karten gekoppelt.

Anschuldigung eines Schummelversuches:
Wenn ein Mitspieler denkt, dass geschummelt wurde, kann er den Schummelversuch anklagen. Sobald er an der Reihe ist, kann er ein eigenes Fragment öffnen und die Farbe des Schummlers auswählen. Die Auswahl ist nur möglich, wenn der Kläger selbst eine Figur auf dem Spielbrett hat (egal ob Zielbereich oder normale Spielrunde). Wurde der Schummler richtig beschuldigt verliert dieser eine Figur auf dem Spielfeld. Welche Figur zurück zum Starbereich geht, wird random entschieden. Sollte der Ankläger allerdings falsch beschuldigen, verliert dieser eine seiner Figuren random.


