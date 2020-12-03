# Project Proposal
## Team Members
- Adi Chopra
- Elias Hawa
- Veronica Yung
- Zara Ali
## Main Idea
The general idea of the project is a Pokémon-style battle game, where the user has to fight random
enemies with some basic moves that they are given. The users are also given the option to buy moves as
they receive coins when after winning battles and leveling up. The user would gain XP and coins after
winning a battle, based on how much damage the opponent has done.
### Players
The player starts at level 1 and has to fight enemies for gold and XP. XP helps them level up, and gold
helps them buy more moves to fight enemies with and items that empower these moves, as well as help
with health regeneration.
### Enemies
Different types of enemies (brawlers, gunners, etc. all with different moves/weapons) show up at
random intervals between 2 to 6 seconds. The enemies would have the same number of moves as the
user and would have moves that do damage based on the user’s current level (so that the opponent is
balanced)
### Levels
There would be a concept of leveling up. The user would level up based on the XP they get from fighting
enemies/winning fights. Buying items could also give them XP and using certain moves could give them
an XP boost. As users level up, they get higher-leveled enemies and defeating enemies can also give
them XP boosts. The user levels up after each interval of 1000 XP gained.
### Moves
Each move would have damage points, which would do damage to either opponent (user or enemy).
The damage points would do a certain amount of damage to the health of an opponent (a move’s
damage points would reduce the opponent’s health by that amount, e.g. if a move does 50 damage,
then the opponent’s health is reduced by 50). Users could purchase more moves. Any non-basic move
would have a limit to how many times it can be used during battle.
There would be a small probability of the user or the opponent missing the move (5%), which is
implemented using random number generation. A number between 0 and 100 is
generated and if the number is less than whatever the probability is (in this case 5), then the move
would miss, otherwise the move would hit. There would be a similar approach for picking the move for
the AI opponent. There is a 10% chance that the AI picks a move that is not the highest damaging move.
### Coins
The user would have a certain amount of coins which they can use to buy more items or moves. The
users can have a maximum of 10 moves (this number is subject to change). If users want to buy another
move, but they are already at the maximum moves, they can sell a move for a specific amount of coins
(less than it was purchased for, and they can’t sell any of their 3 basic moves).
### Items
Different items have different advantages. Some items can boost XP. Others can amplify move damage.
Some items can also reduce the damage received from an opponent. Other items can help with health
regeneration. Most items will be limited use (i.e. can only be used for a certain number of moves/unit of
time). The user can also sell an item, but the item’s selling price is proportional to the durability/life left.
The items can also regenerate health. For now, only the user can have items, but as an extension,
opponents could have items (see Potential Extensions )
### User Health
If a user reaches 0 health, they will lose a certain amount of XP, depending on the level they are at. After
each match, the user’s health regenerates to the full amount. The user’s health regenerates between
battles at a pace determined on the user’s level (i.e. higher level = faster regeneration)
## Programming Concepts
### Concepts from Class
The project will make use of many concepts we have learned in class. To name a few:
- OOP Principles:
  - Inheritance for the user and the opponents
    - enemies
    - items
    - moves
  - Polymorphism
    - Subclasses of items and moves can be classified together and organized under a group of items and moves
    - classes.Enemy subclasses extend from an interface.
- Abstraction
  - The user will only be shown the moves and items they can use. The details of how those items and moves work in the game are not important and not shown to the user
- Encapsulation
  - With multiple people working on the project, using encapsulation will aid in making sure that no values are being changed or manipulated when they shouldn’t be
- Interfaces
  - Our game plans to have different classes of enemies, each with their own set of moves. This set of moves will be an interface that is implemented differently in each class.
- Stacks
  - certain combination of moves will be held in a stack to create an ultimate move
  - this will be implemented for the user only
- GUI
  - we will use JavaFX to illustrate character movements using sprites
  - specific characters will be graphically drawn by one of the team members
- File Input/Output (Potential Extension)
  - high scores will be saved and displayed at the end of the game
  - sorting algorithm will be implemented as well
  - Using serialization to save game state data.
### Other Concepts
- User input validation
  - I would need to make sure that whatever the user inputs is correct and is a valid
command at the given moment in the game (i.e. does the user have the move they are
trying to use, is there still a certain amount of a specific move left for the current battle,
etc.).
## Potential Extensions
There are many potential extensions to this project, for example:
- The enemies could have random items to make their moves more powerful
- Adding a weaponized pet that can help the player fight
- More quotes for enemies, players, and shopkeeper
- Making a shopkeeper named Dave. :)
- Quests (ex. defeat X amount of enemies with X amount moves) that give the player coins or XP
for levelling up.
- Boss levels with higher consequences/rewards
- Choose a different type of player at the beginning (Elf always goes first, Knight gets 50 coins per
matching move with the enemy, etc.)
- Add accuracy and speed to moves.
- Involving network programming
- Online leaderboard
- Serialization for saving game state
- The user can choose to create a new “account”/game, where they would have a username and
they would start at level 1, or they can choose to continue a game, where the stats would be
stored in a file and would continue with what the user has.
## Problems and Potential Problems
- We may have included too many features and not have the time to complete all of them
- There may be a lot of edge cases to consider
- Please give us advice on whatever you think we need to work on!
