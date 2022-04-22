# GruppenPhaseAndroid
Wir implementieren das Brettspiel DogRoyal als Android App im Rahmen unseres Software Engineering Projektes. Wir arbeiten mit Android Studio.



DogRoyal hat die Grundphasen von dem Brettspiel Mensch Ärgere dich nicht.

Spielmaterial:
1 Spielplan
110 Spielkarten (je 10x die 11 und 13, je 7x 2, 3, 4,
5, 6, 1-7, 8, 9, 10, 12, = und Tauscher, 6x Magnet)
16 Spielfiguren (pro Farbe 4 Figuren (König, Ritter,
Bürger und Narr))

Spielziel:
Gewonnen hat der erste Spieler, der alle 4 Steine in seinem Zielbereich hat.

Spielvorbereitung:
Das Spielbrett wird für alle gut erreichbar in die Tischmitte
gelegt. Die Spieler stellen ihre Spielfiguren mit der bedruckten Seite nach unten (die bedruckte Seite wird für die
„Royal“-Regel benötigt und bleibt ansonsten verdeckt) auf
den der Spielerfarbe entsprechenden Start. Jeweils zwei
Personen, die sich gegenüber sitzen, spielen zusammen
und bilden ein Team.

Spielablauf:
Ein Spieler wird bestimmt, die Karten zu mischen. Er mischt
die Karten gründlich und teilt an jeden Spieler verdeckt
5 Karten aus. Anschließend gibt er für die nächste Runde
den Kartenstapel an seinen linken Nachbarn weiter, der
ihn erst einmal zur Seite legt. Nach dem Austeilen der
Karten tauschen die Teampartner gegenseitig je eine
Karte verdeckt aus. Die neue Karte vom Partner darf erst
angesehen werden, wenn die eigene Karte verdeckt zum
Partner geschoben wurde. Dieses Tauschen sollten die Partner nutzen, um einander zu helfen. Wenn z.B. der Partner
keine Figuren auf der Laufbahn hat, ist es sinnvoll ihm eine
Startkarte zuzuschieben.
Zu Spielbeginn dürfen die Spieler jeweils noch eine Spielfigur auf ihr Startfeld setzen.
Der Spieler, der den Kartenstapel vor sich hat, beginnt
das Spiel, indem er eine Handkarte offen in die Mitte des
Spielfeldes legt und eine seiner Spielfiguren entsprechend
bewegt. Der Spieler zieht keine Karte nach! Anschießend
ist der im Uhrzeigersinn nächste Spieler mit seinem Zug
an der Reihe usw. Kann ein Spieler keine seiner Figuren
bewegen, so zeigt er seine Karten vor. Die Karten verfallen
und werden unter den Ablagestapel gelegt. Der Spieler
scheidet für diese Runde aus. Eine Runde ist zu Ende, wenn
alle Karten ausgespielt oder abgelegt wurden.
In der zweiten Runde verteilt der Spieler, der den Kartenstapel hat, erneut an alle Spieler je 5 Karten, und gibt den
Kartenstapel an seinen linken Nachbarn weiter, der in dieser
Runde beginnt. So wechseln das Austeilen der Karten und
der Rundenbeginn von Runde zu Runde im Uhrzeigersinn
weiter. Sollte der Kartenstapel zum Austeilen nicht mehr
ausreichen, wird der Ablagestapel gemischt und als neuer
Kartenstapel verwendet.
Nach jedem Austeilen von 5 Karten wird zu Rundenbeginn
dem Partner immer eine Karte verdeckt zugeschoben.

Startfeld:
Um eine Figur vom Start auf das Startfeld setzen zu können,
wird eine Startkarte (Karte mit dem Symbol ) benötigt.
Eine Spielfigur auf ihrem gleichfarbigen Startfeld blockiert
eigene und fremde Figuren. Dabei ist es gleich, ob sie
durch Herausziehen aus dem Start oder durch Umrunden der
Laufstrecke auf das Startfeld gezogen wurde.
Eine Figur auf dem eigenen (gleichfarbigen) Startfeld
ist geschützt und kann nicht geschlagen oder getauscht werden, die darf von keiner anderen Figur (fremde oder eigene) überholt werden.

Bewegen und Schlagen:
Die Spielfiguren werden durch das Ausspielen von Karten
bewegt. Spielt ein Spieler z.B. eine 5 aus, muss er eine
seiner Spielfiguren um genau 5 Felder im Uhrzeigersinn
weiterbewegen. Beim Bewegen dürfen eigene und fremde
Figuren übersprungen werden (Ausnahme: blockierte
Startfelder sowie Zielfelder), wobei das übersprungene Feld
mitgezählt wird.
Auf einem Feld darf immer nur eine Figur stehen. Endet der
Zug eines Spielers auf einem Feld, auf dem bereits eine Figur
steht, so wird diese Figur (auch eine eigene) geschlagen und
in den gleichfarbigen Start zurückgestellt.

Zugzwand:
Jede Karte muss komplett ausgeführt werden. Es ist
nicht möglich, Punkte verfallen zu lassen. So kann es durchaus auch passieren, dass ein Spieler, der z.B. mit 5 Schritten
ins Ziel kommen würde, nur noch höhere Karten auf der
Hand hat und somit am Ziel vorbei geht und mit der Figur
eine weitere Runde drehen muss. Grundsätzlich können nur
eigene Figuren gezogen werden.


Karten:
Wir unterscheiden Karten mit verschiedenen Farben und Funktionen. Blaue Karten geben die genau Zahl an, welche die Spielfigur vorwärts bewegt wird. 
Rote Karten, hier werden verschiende Events implementiert. Starkarte, Tausch. Start oder alternativ Spielfigur bewegen, Magnet, Kopie. 
Es gibt eine grün rote Karte bei der man entscheiden kann ob man Felder nach vorne oder nach hinten rückt.


Die Komplexität des Spieles wird durch die Royal Methoden erhöht:

Bei „Royal“ werden die Figuren mit der bedruckten Seite
nach oben (König, Ritter, Bürger und Narr) gespielt. Die
Kartenanzahl ändert sich von Runde zu Runde nicht (anders
als bei DOG, Artikelnummer 49201). Es werden zu Rundenbeginn immer 5 Karten ausgeteilt.
Zu Spielbeginn, nachdem die Partner ihre Karten
getauscht haben, stellt jeder Spieler, beginnend mit dem
Startspieler und dann im Uhrzeigersinn folgend, eine
beliebige eigene Figur auf sein Startfeld. Jeder Spieler
startet also mit einer Figur auf dem Startfeld. Welche
Figur er wählt, bleibt ihm überlassen. Die Rangfolge (s. u.)
spielt dabei keine Rolle. Diesen Sonderstart gibt es nur zu
Spielbeginn – nicht vor jeder neuen Runde.

Rangfolge:
Zwischen den Figuren wird eine Rangfolge eingeführt, die
auch für die Figuren der eigenen Farbe gilt. Die ranghöchste
Figur ist der König, gefolgt vom
Ritter, dem Bürger und dem
Narren als rangniedrigste Figur.
Diese Rangfolge ist auf dem
Spielplan noch einmal abgebildet. Grundsätzlich darf immer
jede Figur bewegt werden. Will
ein Spieler aber eine Figur
überholen, muss er dabei die
Rangfolge beachten. Eine
Figur darf immer nur gleichrangige oder rangniedrigere Figuren überholen.
Ein Bürger darf also an anderen Bürgern oder Narren
vorbeiziehen. Um eine ranghöhere Figur zu überholen,
benötigt er eine grüne Karte.
Mit grünen Karten wird die Rangfolge der Figuren
außer Kraft gesetzt. Somit kann eine rangniedrigere
Figur auch eine ranghöhere Figur überspringen. Ein Spielziel
Nachdem ein Spieler alle seine Figuren ins Ziel gezogen hat,
hilft er seinem Partner und bewegt dessen Figuren, wenn
er eine Karte ausspielt. Der Spieler erhält weiterhin Karten
ausgeteilt. Jetzt versucht das Team zusammen, die letzten
Figuren ins Ziel zu bekommen. Allerdings darf auch in der
Endphase wie im gesamten Spiel zwischen den Partnern
keine Kommunikation über Handkarten oder Spielzüge erfolgen.
Sobald mit einem komplett ausgeführten Zug (es dürfen keine
Punkte verfallen) die letzte Figur eines Teams im Ziel gelandet
ist, steht das Siegerteam fest. Alle restlichen Handkarten
werden abgelegt. Es kann passieren, dass die letzte Figur
mehrere Runden drehen muss, bis sie ins Ziel kommt.

Spielende: 
Das Team, das es als Erstes geschafft hat, mit allen 8 Figuren
ins Ziel zu ziehen, gewinnt das Spiel.
Sonderregel für 2, 3 und 5 Spieler7
Startfeld,
das mit einer gleichfarbigen Figur besetzt ist, blockiert aber weiterhin (Ausnahme: Ritter). Auch im Ziel darf weiterhin nicht übersprungen werden.

Sonderfelder:
Ein Spieler, der eine Figur aktiv auf ein
Sonderfeld (siehe Abbildung links) zieht,
zieht sofort eine zusätzliche Karte
vom Kartenstapel. Dies kann durch eine
Zahlenkarte oder durch den Magneten geschehen. Durch
Tauschen von zwei Figuren (von denen eine auf einem
Sonderfeld steht) bekommt der Spieler keine neue Karte.
Schlagen Seine Majestät der König darf nur von anderen Königen geschlagen werden! Ansonsten darf jede Figur jede andere schlagen (auch ranghöhere).
Sonderfall blockierte Startfelder: Eine fremde Figur
auf dem eigenen Startfeld darf von jeder startenden Figur
geschlagen werden. Somit darf z.B. auch ein fremder König
auf meinem eigenen Startfeld von einem startenden Narren
(aber auch von einem Narren, der eine komplette Runde
gelaufen ist) geschlagen werden. Es ist also nicht möglich,
das Startfeld eines Mitspielers zu blockieren.

Sonderfähigkeit der Figuren:
Alle Figuren besitzen eine Sonderfähigkeit, die es geschickt zu nutzen gilt:
König: Der König ist zwar langsam, dafür aber relativ sicher. Denn ein König kann nur von einem
anderen König geschlagen werden. Dafür darf der König nur mit Karten des Wertes 1 bis
7 laufen. Auf allen Karten, mit denen der König nicht laufen kann, ist eine durchgestrichene Krone
abgebildet. Startkarten zum Starten sowie ein Magnet oder ein Tauscher dürfen auf einen König
gespielt bzw. angewandt werden. Achtung: Ein Kopierer darf für einen König nur dann gespielt
werden, wenn die zuvor gespielte Karte auch für den König regulär eingesetzt werden kann.
Ritter: Für den Ritter sind Startfelder, die von einer gleichfarbigen Figur besetzt sind, keine
Blockade. Er darf sie nicht betreten, aber über sie hinweg ziehen. Allerdings muss er hierbei
auch die Rangfolge einhalten. So darf er an einem König auf dessen Startfeld vorbeiziehen,
benötigt dafür aber eine grüne Karte. Alle anderen Figuren auf dem Startfeld können vom Ritter
mit beliebigen anderen blauen und roten Karten (Ausnahme: Magnet) übersprungen werden. Als
einzige Figur kann der Ritter beim ins Ziel ziehen auch über einen eigenen Stein auf dem Startfeld
in sein eigenes Ziel ziehen.
Bürger: Wenn der Bürger auf ein Sonderfeld zieht, darf er, nachdem er eine Karte gezogen hat,
auf das nächste freie Sonderfeld im Uhrzeigersinn springen. Damit kann er auch an ranghöheren Figuren oder an blockierten Startfeldern vorbeikommen.
Narr: Der Narr kann, wenn er ins Ziel hineinzieht, bis zu 2 Punkte verfallen lassen. Im
Verlaufe des Spiels darf der Narr auf der umlaufenden Bahn – wie alle anderen Figuren auch –
keine Punkte verfallen lassen.

Schummelfunktion:
Es werden unteschiedliche Schummelfunktion, welche auf den Bewegungssensor und den Lichtsensor des Handys reagieren eingebaut. Alle fünf Runden soll es möglich sein, einmal zu schummeln und einmal das Schummmeln zu entlarven.
