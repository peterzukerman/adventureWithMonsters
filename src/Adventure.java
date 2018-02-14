
import com.google.gson.Gson;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;

public class Adventure {

    static Gson gson = new Gson();
    static Layout[] gameLayout;
    static List<Item> items = new ArrayList<Item>();
    static List<Monster> monsters;
    static double startingHealth;
    static int experienceLevel = 0;
    static int experienceOver = 0;

    public static void makeGame(){
        gameLayout = gson.fromJson("[\n" +
                "  {\n" +
                "    \"startingRoom\": \"Basement\",\n" +
                "    \"endingRoom\": \"EndRoom\",\n" +
                "    \"rooms\": [\n" +
                "      {\n" +
                "        \"name\": \"Basement\",\n" +
                "        \"description\": \"It's kinda smelly down here...\",\n" +
                "        \"directions\": [\n" +
                "          {\n" +
                "            \"directionName\": \"Down\",\n" +
                "            \"room\": \"Lower Basement\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"items\": [\"old rag\", \"spiderweb\", \"broom\"],\n" +
                "        \"monstersInRoom\": [\"Boris\"]\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Lower Basement\",\n" +
                "        \"description\": \"It's getting harder to see...\",\n" +
                "        \"directions\": [\n" +
                "          {\n" +
                "            \"directionName\": \"through the Door\",\n" +
                "            \"room\": \"DoorRoom\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"directionName\": \"Deeper\",\n" +
                "            \"room\": \"Catacombs\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"items\": [\"strange fluid\", \"key\"],\n" +
                "        \"monstersInRoom\": [\"Boris\", \"Vlad\"]\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"DoorRoom\",\n" +
                "        \"description\": \"You went through the door.\",\n" +
                "        \"directions\": [\n" +
                "          {\n" +
                "            \"directionName\": \"back\",\n" +
                "            \"room\": \"Lower Basement\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"items\": [\"pizza\", \"key to life\"],\n" +
                "        \"monstersInRoom\": \"\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Catacombs\",\n" +
                "        \"description\": \"It's pitch black. You hear something dripping in the distance.\",\n" +
                "        \"directions\": [\n" +
                "          {\n" +
                "            \"directionName\": \"Deeper\",\n" +
                "            \"room\": \"Depths\"\n" +
                "          },\n" +
                "          {\n" +
                "            \"directionName\": \"Left\",\n" +
                "            \"room\": \"Catacombs 2\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"items\": [\"The Bible\", \"Scissors\"],\n" +
                "        \"monstersInRoom\": [\"Vlad\", \"Boris\", \"Greg\"]\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Catacombs 2\",\n" +
                "        \"description\": \"You see a large hole in front of you. The bottom is nowhere to be seen.\",\n" +
                "        \"items\": [\"Chocolate Milk\", \"Torn Photo\"],\n" +
                "        \"directions\": [\n" +
                "          {\n" +
                "            \"directionName\": \"back\",\n" +
                "            \"room\": \"Catacombs\"\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Depths\",\n" +
                "        \"description\": \"There is a skull in the corner.\",\n" +
                "        \"directions\": [\n" +
                "          {\n" +
                "            \"directionName\": \"Deeper\",\n" +
                "            \"room\": \"EndRoom\"\n" +
                "          }\n" +
                "        ],\n" +
                "        \"items\": [\"nothing\"],\n" +
                "        \"monstersInRoom\": [\"Greg\", \"Michael\"]\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"EndRoom\",\n" +
                "        \"description\": \"You reached the end!\",\n" +
                "        \"directions\": [],\n" +
                "        \"items\": [\"Strawberry Milk\"],\n" +
                "        \"monstersInRoom\":[]\n" +
                "      }\n" +
                "    ],\n" +
                "    \"player\": \"Peter Zukerman\",\n" +
                "    \"monsters\": [\n" +
                "      {\n" +
                "        \"name\": \"Greg\",\n" +
                "        \"attack\": 15,\n" +
                "        \"defense\": 20,\n" +
                "        \"health\": 50\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Michael\",\n" +
                "        \"attack\": 25,\n" +
                "        \"defense\": 5,\n" +
                "        \"health\": 30\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Vlad\",\n" +
                "        \"attack\": 15,\n" +
                "        \"defense\": 40,\n" +
                "        \"health\": 20\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"Boris\",\n" +
                "        \"attack\": 10,\n" +
                "        \"defense\": 10,\n" +
                "        \"health\": 15\n" +
                "      }\n" +
                "    ]\n" +
                "\n" +
                "  }\n" +
                "]", Layout[].class);
        monsters = new ArrayList<>(Arrays.asList(gameLayout[0].getMonsters()));
        startingHealth = gameLayout[0].getPlayer().getHealth();
    }

    /**
     *
     * @param currentRoom is needed to determine if it's a starting or ending room. then updates.
     */
    public static void updateInterface(Room currentRoom){
        System.out.println(currentRoom.getDescription());

        if (currentRoom.getName().equals(gameLayout[0].getStartingRoom())){
            System.out.println("Your journey begins here");
        }

        if (currentRoom.getName().equals(gameLayout[0].getEndingRoom())){
            System.out.println("You have reached your final destination");
            System.exit(0);
        }

        System.out.println("This room contains: " + Arrays.toString(currentRoom.getItems()));
        System.out.println("Monsters in this room: " + Arrays.toString(currentRoom.getMonstersInRoom()));
        if (currentRoom.getMonstersInRoom().length == 0){
            System.out.println("From here, you can go: ");
            for (Direction d : currentRoom.getDirections()) {
                System.out.println(d.getDirectionName());
            }
        }
        else {
            System.out.println("There are still monsters here, I can't move");
        }

        interfaceInput(currentRoom);

    }

    /**
     *
     * @param roomToFind is the room we're looking for in the array of rooms.
     * @return the actual room that we found.
     */
    public static Room roomFinder(String roomToFind){
        for (Room r:gameLayout[0].getRooms()) {
            if (roomToFind.equals(r.getName())){ //if we find an equivalence, return that room
                return r;
            }
        }
        return null;
    }

    /**
     *
     * @param currentRoom is needed to update the interface using the current room information
     */
    public static void interfaceInput(Room currentRoom){
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        String inputToLowercase = userInput.toLowerCase();

        if (inputToLowercase.length()<4){
            System.out.println("Sorry, your input was too short: " + userInput);
            updateInterface(currentRoom);
        }

        else if (inputToLowercase.equals("quit") || inputToLowercase.equals("exit")){
            System.exit(0); //causes the java virtual machine to exit
        }
        else if (inputToLowercase.substring(0, 10).equals("playerinfo")){
            playerInfo(currentRoom);
        }
        else if (inputToLowercase.substring(0, 4).equals("duel")){
            duel(currentRoom, inputToLowercase);
        }
        else if (inputToLowercase.substring(0, 2).equals("go")){
            goADirection(currentRoom, inputToLowercase); //calls method to check if we can move in direction, and does
        }
        else if (inputToLowercase.substring(0, 4).equals("take")){
            takeItem(currentRoom, inputToLowercase); //tries to take item if possible, calls this method
        }
        else if (inputToLowercase.substring(0, 4).equals("drop")){
            dropItem(currentRoom, inputToLowercase); //tries to drop item if possible, then drops it from array
        }
        else if (inputToLowercase.equals("list")){
            System.out.println("You are carrying: " + items);
            updateInterface(currentRoom);
        }
        else {
            System.out.println("I don't understand: " + userInput);
            updateInterface(currentRoom);
        }


    }

    public static void updateDuel(Room currentRoom, Monster enemy, double enemyMaxHealth){
        if (enemy == null){
            System.out.print("error. Enemy is null");
        }
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        String inputToLowercase = userInput.toLowerCase();
        if (enemy.getHealth() <= 0 ){
            System.out.println("You have slain the enemy!");
            monsters.remove(enemy);
            updatePlayer(enemy, currentRoom);
        }
        else if (inputToLowercase.substring(0, 11).equals("attack with")){
            userInput = inputToLowercase.substring(13);
            if (!items.contains(userInput)){
                System.out.println("You aren't holding that item.");
            }
            else{
                enemy.setHealth(enemy.getHealth() - (gameLayout[0].getPlayer().getAttack()
                        + items.get(items.indexOf(userInput)).getDamage() - enemy.getDefense()));
            }

        }
        else if (inputToLowercase.substring(0, 6).equals("attack")){
            enemy.setHealth(enemy.getHealth() -
                    (gameLayout[0].getPlayer().getAttack() - enemy.getDefense()));

        }
        else if (inputToLowercase.substring(0, 9).equals("disengage")){}

        else if (inputToLowercase.substring(0, 6).equals("status")){
            int playerHealthInt = (int) ((gameLayout[0].getPlayer().getHealth()*10)/(int)startingHealth);
            int monsterHealthInt = (int) ((enemy.getHealth()*10)/(int)enemyMaxHealth);
            System.out.print("Player: ");
            for (int i = 0; i <= playerHealthInt; i++){
                System.out.print("#");
            }
            for (int i = 0; i <= 10 - playerHealthInt; i++){
                System.out.print("_");
            }
            System.out.print("\nMonster: ");
            for (int i = 0; i <= monsterHealthInt; i++){
                System.out.print("#");
            }
            for (int i = 0; i <= 10 - monsterHealthInt; i++){
                System.out.print("_");
            }

        }

        double damageTaken = enemy.getAttack() - gameLayout[0].getPlayer().getDefense();
        gameLayout[0].getPlayer().setHealth(gameLayout[0].getPlayer().getHealth() - damageTaken);
        System.out.println();
        if (gameLayout[0].getPlayer().getHealth() <= 0 ){
            System.out.println("You have died.");
            System.exit(0);
        }
        updateDuel(currentRoom, enemy, enemyMaxHealth);

    }

    private static void updatePlayer(Monster enemy, Room currentRoom) {
        int experienceEarned = (int) ((enemy.getAttack()
                        + enemy.getDefense())/2 + enemy.getHealth()*20);
        int experienceRequired = experienceLevel*25;
        if (experienceEarned>experienceRequired){
            gameLayout[0].getPlayer().setLevel(gameLayout[0].getPlayer().getLevel() + 1);
            experienceOver = experienceEarned - experienceRequired;
        }
        else{
            gameLayout[0].getPlayer().setLevel(gameLayout[0].getPlayer().getLevel() + 1);
            experienceOver = 0;
        }
        experienceLevel += experienceOver;
        gameLayout[0].getPlayer().setAttack(gameLayout[0].getPlayer().getAttack()*1.5);
        gameLayout[0].getPlayer().setDefense(gameLayout[0].getPlayer().getDefense()*1.5);
        gameLayout[0].getPlayer().setHealth(startingHealth*1.3);
        interfaceInput(currentRoom);
    }

    private static void duel(Room currentRoom, String inputToLowercase) {
        Monster currentEnemy = null;
        for (Monster m:monsters) {
            if (m.getName().equals(inputToLowercase)){
                currentEnemy = m;
            }
        }
        updateDuel(currentRoom, currentEnemy, currentEnemy.getHealth());
    }

    private static void playerInfo(Room currentRoom) {
        System.out.println("Level: " + gameLayout[0].getPlayer().getLevel());
        System.out.println("Attack: " + gameLayout[0].getPlayer().getAttack());
        System.out.println("Defense: " + gameLayout[0].getPlayer().getDefense());
        System.out.println("Health: " + gameLayout[0].getPlayer().getHealth());
        updateInterface(currentRoom);
    }

    public static void dropItem(Room currentRoom, String input) {
        String slicedString = input.substring(5);
        if (!items.contains(slicedString)){
            System.out.println("I can't drop " + slicedString);
        }
        updateInterface(currentRoom);
    }

    /**
     *
     * @param currentRoom is needed to see if we can take an item from the current room.
     * @param input is the item that we're trying to take
     */
    public static void takeItem(Room currentRoom, String input) {
        String slicedString = input.substring(5);
        for (Item item:currentRoom.getItems()) {
            if (slicedString.equalsIgnoreCase(item.getName())){
                items.add(item);
            }
        }

        if (!Arrays.asList(currentRoom.getItems()).contains(slicedString)){
            System.out.println("I can't take " + slicedString);
        }
        updateInterface(currentRoom);
    }

    /**
     *
     * @param currentRoom needed to check if the directions we're trying to access exist in the first place
     * @param inputToLowercase is the direction that we want to move in
     */
    public static void goADirection(Room currentRoom, String inputToLowercase) {
        String slicedString = inputToLowercase.substring(3);
        for (Direction d : currentRoom.getDirections()) {
            if (slicedString.equalsIgnoreCase(d.getDirectionName())){
                updateInterface(roomFinder(d.getRoom()));
            }
        }
        if (!Arrays.asList(currentRoom.getDirections()).contains(slicedString)){
            System.out.println("I can't go " + slicedString);
            updateInterface(currentRoom);
        }
    }

    /**
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        makeGame();
        updateInterface(gameLayout[0].getRooms()[0]);


    }
}
