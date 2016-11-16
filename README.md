    • ▌ ▄ ·.        ▐ ▄ .▄▄ · ▄▄▄▄▄▄▄▄ .▄▄▄      ▄▄▄▄▄▄▄▄   ▄▄▄· ▪   ▐ ▄ ▄▄▄ .▄▄▄  
    ·██ ▐███▪▪     •█▌▐█▐█ ▀. •██  ▀▄.▀·▀▄ █·    •██  ▀▄ █·▐█ ▀█ ██ •█▌▐█▀▄.▀·▀▄ █·
    ▐█ ▌▐▌▐█· ▄█▀▄ ▐█▐▐▌▄▀▀▀█▄ ▐█.▪▐▀▀▪▄▐▀▀▄      ▐█.▪▐▀▀▄ ▄█▀▀█ ▐█·▐█▐▐▌▐▀▀▪▄▐▀▀▄ 
    ██ ██▌▐█▌▐█▌.▐▌██▐█▌▐█▄▪▐█ ▐█▌·▐█▄▄▌▐█•█▌     ▐█▌·▐█•█▌▐█ ▪▐▌▐█▌██▐█▌▐█▄▄▌▐█•█▌  
    ▀▀  █▪▀▀▀ ▀█▄▀▪▀▀ █▪ ▀▀▀▀  ▀▀▀  ▀▀▀ .▀  ▀     ▀▀▀ .▀  ▀ ▀  ▀ ▀▀▀▀▀ █▪ ▀▀▀ .▀  ▀
    
# monster-trainer

Le but de cet exercice est de modéliser un programme permettant de faire evoluer des dresseurs de monstres dans un monde ouvert.
Le monde comporte des obstacles qui pourront empêcher le dresseur d'avancer. Il comportera également des montres que les dresseurs pourront capturer en se déplaçant.

Première User story
-------------
```
En tant que joueur, je veux pouvoir créer un monde dans lequel je place un dresseur auquel je donne une suite d'action de déplacement afin de connaitre sa position finale dans le monde.
```

Cette première user story a pour objectif de réaliser le coeur du « domaine » c’est à dire
permettre de déplacer un dresseur sur une carte pour y capturer les monstres tout en évitant les
obstacles. On peut connaître à tout moment la position du dresseur et le nombre de monstres
ramassés !
-> Temps estimé 2 à 3h

**Nouvelles User Stories à venir...**

Les différents éléments du besoin
-------------
#### Le Monde
Le monde, principalement composés de plaines, est rectangulaire et découpé en case de
même taille, formant ainsi un quadrillage. Par convention, les lignes sont numérotées de haut en
bas et les colonnes de gauche à droite. Il peut y avoir des grands comme des petits mondes.
Voici un exemple de représentation pour un monde de six colonnes et cinq lignes.

| \ | 1 | 2 | 3 | 4 | 5 | 6 |
|---|---|---|---|---|---|---|
| 1 |   |   |   |   |   |   |
| 2 |   |   |   |   |   |   |
| 3 |   |   |   |   |   |   |
| 4 |   |   |   |   |   |   |
| 5 |   |   |   |   |   |   |

Dans le fichier texte, ce monde 6x5 sera indiqué comme suit :
    
    # monde(W) largeur hauteur
    W 6 5

#### Les Monstres
Les cases peuvent contenir des monstres. La plupart des cases sont vides mais certaines cases contiennent un ou plusieurs monstres. 
Voici un exemple de représentation pour une carte qui aurait un monstre dans la case 4-2 et trois monstres dans la case 1-4. 
À titre d’illustration, nous avons indiqué le nombre de monstre dans la case.

| \ | 1 | 2 | 3 | 4 | 5 | 6 |
|---|---|---|---|---|---|---|
| 1 |   |   |   |   |   |   |
| 2 |   |   |   | 1 |   |   |
| 3 |   |   |   |   |   |   |
| 4 | 3 |   |   |   |   |   |
| 5 |   |   |   |   |   |   |

Dans le fichier texte, les monstres seront indiqués comme suit :
    
    # monstre(M) position(colonne-ligne) nombre
    M 4-2 1
    M 1-4 3

#### Les obstacles
La carte n’est pas une vaste étendue de plaines, elle contient aussi des obstacles naturels.
Voici un exemple de représentation pour une carte qui aurait un obstacle dans la case 5-3. À
titre d’illustration, nous avons marqué la case possédant un obstacle avec un X.

| \ | 1 | 2 | 3 | 4 | 5 | 6 |
|---|---|---|---|---|---|---|
| 1 |   |   |   |   |   |   |
| 2 |   |   |   |   |   |   |
| 3 |   |   |   |   | X |   |
| 4 |   |   |   |   |   |   |
| 5 |   |   |   |   |   |   |

Dans le fichier texte, les montagnes seront indiqués comme suit :
    
    # Obstacles(O) position(colonne-ligne)
    O 5-3

#### Le monde complet
Voici une représentation avec l’ensemble des éléments : le monde, les monstres et les obstacles.

| \ | 1 | 2 | 3 | 4 | 5 | 6 |
|---|---|---|---|---|---|---|
| 1 |   |   |   |   |   |   |
| 2 |   |   |   | 1 |   |   |
| 3 |   |   |   |   | X |   |
| 4 | 3 |   |   |   |   |   |
| 5 |   |   |   |   |   |   |

Dans le fichier texte, l’ensemble du monde sera donc indiqué comme suit :

    W 6 5
    M 4-2 1
    M 1-4 3
    O 5-3

#### Les dresseurs
Le monde est pris d’assaut par des dresseurs. Il peut y avoir plusieurs dresseurs en même
temps sur la carte. Un dresseur ne peut se déplacer qu’en avançant. Il peut néanmoins tourner
sur lui-même, à gauche ou à droite, de 90°.
Les déplacements sont codifiés: A avance, D tourne à droite, G tourne à gauche
Par exemple, la séquence « AADADAGA » signifie que le dresseur avance, puis avance, puis fait
une rotation à droite, puis avance, puis fait une rotation à droite, puis avance, puis fait une rotation
à gauche, puis avance. Chaque mouvement prend une seconde, y compris les rotations. Ainsi, un
tour complet (360°) prend quatre secondes.
Au début du jeu, les dresseurs ont également une position et une orientation initiales.
L’orientation correspond aux points cardinaux: N nord, E est, S sud, O ouest
Voici un exemple de représentation, pour un dresseur initialement dans la case en haut à gauche, regardant vers l’est et exécutant la séquence « AADADAGA »

| \ | 1 | 2 | 3 | 4 | 5 | 6 |
|---|---|---|---|---|---|---|
| 1 | ▶ | ▶ | ▼ |   |   |   |
| 2 |   | ▼ | ◀ |   |   |   |
| 3 |   | ▼ |   |   |   |   |
| 4 |   |   |   |   |   |   |
| 5 |   |   |   |   |   |   |

Dans le fichier texte, les dresseurs seront indiqués comme suit :

    # dresseur position initiale(colonne-ligne) orientation initiale déplacements nom
    Sacha 1-1 E AADADAGA

#### Notes importantes
Pour des raisons de flexibilité, les dresseurs et leurs déplacements sont indiqués dans un fichier
texte différent de celui du monde. Il est ainsi possible de mixer un ensemble de dresseurs avec
un monde spécifique.
Lorsqu’un dresseur arrive sur une case contenant des monstres, il le capture et le met dans son
sac. Capturer un monstre prend une seconde. Il passe donc une seconde pour aller sur la case et
une autre seconde pour capturer le monstre, soit deux secondes.
Un dresseur ne peut pas aller sur une case où se trouve un obstacle. Les instructions
d’avancer (A) vers une case contenant un obstacle sont ignorées. En toute logique, la séquence
reprend donc à l’instruction de rotation (G ou D) suivante.
Une case ne peut contenir qu’un seul dresseur à la fois. Un dresseur désirant aller vers une
case déjà occupée par un autre dresseur doit se mettre en pause tant que la case n’est pas vide.
Chaque seconde, un dresseur est donc en mouvement vers l’avant, en rotation ou en pause.
Tous les dresseurs commencent leurs séquences respectives en même temps. Lorsque la
séquence d’un dresseur est terminée, celui-ci se met en pause.


