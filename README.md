# ARPY DUEL - BACKEND
This project is a backend application for a turn-based RPG game developed using Spring Boot 3.1.1 and Java 17. It is containerized with Docker and Docker-compose and uses environment variables from a .env file for configuration. The authentication is implemented using JWT (JSON Web Tokens).

### Technologies used
- Spring Boot 3.3.1
- Java 17
- Docker
- Docker-compose
- JSON Web Tokens (JWT)
- Gradle
- PostgreSQL

## GAME OVERVIEW
### Character Creation and Progression
At the outset, players can create their unique characters, choosing from three distinct classes: Mage, Warrior, or Hunter. Each class has its strengths and abilities, offering diverse playstyles for the players to explore.
As players embark on their journey, they will witness their characters' growth and development. With each victorious battle and successful duel, characters gain experience points (XP) and progress through levels. As characters level up, they become more powerful, and their base statistics, such as spell power, attack power, and critical strike chance, increase accordingly yor character class.

### Equipment System and Claims
Equipping the right gear is crucial for survival and success on the battlefield. Characters can wield a combination of one weapon, one item, and one armor at a time. Obtaining equipment is achieved through the use of a virtual currency called "claims."

Players earn claims in various ways: by creating new characters, emerging victorious in duels, or achieving new levels of experience. As they amass claims, players can spend them to obtain randomized equipment with varying stats based on character level and potential enchantments.

### Enchantments and Randomization
Weapons are especially versatile and can be enchanted, enhancing their prowess in combat. Enchantments are magical properties that are randomly applied to weapons when acquired.
However, the rarest and most potent enchantments are harder to come by, adding an element of excitement and anticipation each time a new weapon is acquired.

### Items and Buffs/Debuffs
Items in the game offer powerful buffs and debuffs that can turn the tide of a battle. When players acquire items with their claims, they receive random effects that can either strengthen their characters or weaken their adversaries.

The unpredictability of the items' effects adds an extra layer of strategy, requiring players to adapt and plan their tactics accordingly.

### Friends and Duels
Players can add friends to their network. Having friends comes with benefits, one of which is the ability to challenge each other to thrilling duels. Test your characters' mettle against those of your closest companions.

### Matchmaking Queue
For those seeking a challenge beyond their circle of friends, the game offers a matchmaking queue. This feature pairs players with opponents of similar character levels, ensuring balanced and fair duels.

### The Journey to Level 30
As characters progress and gain experience, the ultimate goal is to reach level 30—the pinnacle of power and mastery (ranked queues will be added in the future). 

## Application Configuration
### Environment Variables
The application is configured using environment variables from a .env file, so it is necessary to create one in the root directory of the project. The following variables are required for the application to run:
```dosini
PSQL_DB_NAME=
PSQL_DB_HOST=
PSQL_DB_PORT=
PSQL_DB_USER=
PSQL_DB_PASSWORD=
APP_PORT=
SPRING_PROFILES_ACTIVE=
```
### JWT 
No need to configure but If you want to change the default configuration for JWT, you can do so by modifying the following attributes in JwtService.java:
```java
// HMAC-SHA256 secret key of length 256 bits
private final static  String SECRET_KEY = "po9+LwqlQJdaZJZ/G6+2UK4Sq/AFO4DIH3fma5G+um27cTfyonkf+yB2GwKKvIBT";
// 1 day
private final static Integer EXPIRATION_DELTA = 1000*60*60*24; 
```
## How to Run
### Run with Gradle
Set up the environment variables in the .env file and make sure you have a PostgreSQL database running. Then, execute the following commands in the root directory of the project:
```bash
gradlew build
gradlew bootRun
```

### Run with Docker
Make sure you have a PostgreSQL database running. Then, execute the following commands in the root directory of the project:
```bash
docker build -t arpyduel-backend .
docker run --name arpyduel \
-e APP_PORT=8080 \
-e PSQL_DB_NAME=arpy \
-e PSQL_DB_HOST=127.0.0.1 \
-e PSQL_DB_PORT=5432 \
-e PSQL_DB_USER=postgres \
-e PSQL_DB_USER=postgres \
-e PSQL_DB_PASSWORD=example \
simulador_db_loader  --plancomercial --compras --savelogs

docker run -p 8080:8080 arpyduel-backend
```

### Run with docker-compose (Recommended)
To run the application using docker-compose, create a .env file in the root directory of the project and set up the environment variables. Then, execute the following command:
```bash
docker-compose up --build
```