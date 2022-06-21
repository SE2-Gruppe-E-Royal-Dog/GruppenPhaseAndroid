# GruppenPhaseAndroid
Im Rahmen unseres Software Engineering 2 Projektes haben wir eine abgeleitete Version des Brettspiels "Dog Royal" als Android App implementiert.

Nachdem unsere App gewisse Sonderfunktionen hat, welche im normalen Spiel nicht gegeben sind, haben wir unsere neu entwickelte Version als "CAT ROYAL" bezeichnet.
![ic_launcher-playstore](https://user-images.githubusercontent.com/101360700/174664561-e1d662cf-c6c3-4905-8162-9770e6d5b67f.png)


Spielmaterial:
1 Spielplan
110 Spielkarten (je 10x die 1/11_Start und 13_Start, je 7x 2, 3, 4+/-,
5, 6, 1-7, 8, 9, 10, 12, Kopierkarte und Tauscher, 6x Magnet), 
16 Spielfiguren (pro Farbe 4 Figuren (König, Ritter, Bürger und Narr)), 
4 Wurmlöcher, 
ein Anschuldigungsfeld bei welchen man Schummeln anklagen kann

Spielziel:
Gewonnen hat der erste Spieler, der alle 4 Steine in seinem Zielbereich hat.
Sobald dies der Fall ist, scheint ein Gewinnerfeld auf, bei dem eine Platzierung von allen Spielern vorgenommen wird. Es zählen die Figuren, welche sich im Zielfeld befinden.

Spielvorbereitung:
Jeder Spieler meldet sich bei der Lobby über seinen Username an. Bis zu vier Spieler können teilnehmen. Nachdem alle Spieler, welche mitspielen wollen, anwesend sind kann ein Spieler den "Start Game" Button drücken und das Spiel wird gestartet und jeder Spieler wird darüber informiert, welche Farbe er/sie hat. Es beginnt derjenige Spieler, der als erstes die Lobby gejoint hat. Am Spielfeld erscheint für jeden Player, welcher Spieler gerade am Zug ist.

Bewegen und Schlagen: 
Jeder Spieler erhält eine Deck mit fünf Karten. Für das Fahren mit einer Figur muss man eine Karte auswählen und dann die gewünschte Figur. Auf jedem Feld darf maximal eine Figur stehen. Sollten auf eine Feld zwei Figuren zu stehen kommen, dann kommt es zum Schlagen und die geschlagene Figur wird auf den Starbereich gesetzt.

Berücksichtigung der Hierarchie:
Bei den Figuren gilt eine Hierarchie, die beim Überholen prinzipiell einzuhalten ist - König vor Ritter vor Bürger vor Narr. So darf zum Beispiel ein Bürger nur einem Narren oder einen anderen Bürger egal welcher Farbe überholen - ein König hingegen alle Figuren. Grüne Karten (4+/- und 10) setzen die Hierarchien außer Kraft.

Schmeiß-, Überhol- und Blockierverbot:
Hat man eine Figur auf seinem eigenen Startfeld, kann diese prinzipiell weder von einer anderen Figur (egal welcher Farbe) geschmissen noch überholt werden. Das Überholverbot gilt hingegen nicht für den Ritter - aber nur im Zuge der Hierarchie. Im Gegenzug darf keine fremde Figur das eigene Startfeld blockieren und kann damit immer geschmissen werden. Generell gilt im eigenen Zielbereich Schmeißverbot. Die Überholregeln müssen jedoch auch im Ziel eingehalten werden.

Verbot der Zieleinfahrt:
Eine Einfahrt in das Ziel ist nicht möglich, wenn die eigene Figur nach einer Runde am Startfeld zu stehen kommt. Denn dann muss sie die Runde erneut absolvieren.

Figuren:

Der König:

![image](https://user-images.githubusercontent.com/101360700/174873386-49947c70-209d-4a24-9435-048647a1f6be.png)

Er darf nur mit Karten im Wert von 1 bis 7, den Startkarten, 
den Tauschkarte, den Magnetkarte, den Kopiekarte - 
solange der vorherige Zug für den König zulässig ist - und 
den 4+/- Karten fahren. 
Die Verbotskarten sind mit einer durchgestrichenen Krone markiert. 
Ein König kann nur von einem anderen König geschlagen werden - 
außer er befindet sich auf einem fremden Startfeld. 
Auch kann er mit einer minus 4 Karte geschlagen werden.

Der Ritter:

![image](https://user-images.githubusercontent.com/101360700/174872940-791cf825-a9b5-4ce1-acee-8777b77fe303.png)

Ein fremdes Startfelder, das
von einer gleichfarbigen Figur
besetzt sind, ist keine Blockade für den Ritter. 
Er darf im Zuge der Hierarchieordnung
darüber hinwegziehen. 

Der Bürger:

![image](https://user-images.githubusercontent.com/101360700/174873987-3d56b477-1a3a-4e8a-ab13-ba52e046a6ca.png)

Der Bürger ist keine gewöhnliche
Figur. Er hat mehr Ansehen als ein
Narr (kann diesen also überholen),
steht aber unter dem Ritter und dem
König.

Der Narr:

![image](https://user-images.githubusercontent.com/101360700/174873769-a2f8e0f9-6264-480e-987c-51711d2f08a3.png)

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

![image](https://user-images.githubusercontent.com/101360700/174874297-266950f8-a4a0-47d6-baed-51fadc72c202.png) ![image](https://user-images.githubusercontent.com/101360700/174874477-ffa2bc4c-1b9a-476c-9931-72de452ee1cc.png) ![image](https://user-images.githubusercontent.com/101360700/174874593-bdd83ef0-df5f-4fb7-9996-51494d96c15f.png) ![image](https://user-images.githubusercontent.com/101360700/174874774-0f38bf8b-39bf-402d-bef8-ebebbd4ccfe0.png) ![image](https://user-images.githubusercontent.com/101360700/174874886-dd79cd08-a45f-4251-9fbe-1c7f19297ed7.png) ![image](https://user-images.githubusercontent.com/101360700/174875010-0c13ca4e-3f22-44ed-94e4-e3d22826a347.png) ![image](https://user-images.githubusercontent.com/101360700/174875083-677c0b42-844c-4367-81b4-3efe9c8eaa5f.png)




Wurmlöcher:

![image](https://user-images.githubusercontent.com/101360700/174874117-2854cd4d-4e87-416d-8276-044e237bcbe2.png)

Wenn eine Figur auf ein mit Spiralenform gekennzeichnetes Feld fährt, wird die Figur zu einem anderen Wurmloch teleportiert. Die Wurmlöche können mittels Abdecken des Lichtsensors am Handy random bewegt werden. Eine Verschiebung der Wurmlöcher ist nur möglich, wenn man selbst nicht am Zug ist, sowie nur einmal pro fünf Runden.

Schummelfunktion:
In Cat Royal ist eine eigene Schummelfunktion implementiert. Ist man am Zug und hat eine Karte ausgwählt, so kann man das Handy nach links oder rechts kippen und dies verändert den Kartenwert um ein Feld mehr oder ein Feld weniger. Nach links bedeutet einen Wert weniger fahren und nach rechts kippen bedeutet einen Wert mehr fahren. Jeder Spieler kann einmal pro fünf Runden Schummeln, sprich das Schummeln ist an die Neuverteilung der Karten gekoppelt.

Anschuldigung eines Schummelversuches:
Wenn ein Mitspieler denkt, dass geschummelt wurde, kann er den Schummelversuch anklagen. Sobald er an der Reihe ist, kann er ein eigenes Fragment öffnen und die Farbe des Schummlers auswählen. Die Auswahl ist nur möglich, wenn der Kläger selbst eine Figur auf dem Spielbrett hat (egal ob Zielbereich oder normale Spielrunde). Wurde der Schummler richtig beschuldigt verliert dieser eine Figur auf dem Spielfeld. Welche Figur zurück zum Starbereich geht, wird random entschieden. Sollte der Ankläger allerdings falsch beschuldigen, verliert dieser eine seiner Figuren random.


