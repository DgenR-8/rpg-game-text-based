/*
 * 
 * 
 */
import java.util.Scanner;
import java.util.Random;

public class KylesRpgGame {
    final static Scanner SCANNER = new Scanner(System.in);
    final static Random RANDOM = new Random();
    // Direction-based outcome arrays
    final static String[] DIRECTION = {"north", "east", "south", "west"};
    final static String[] DIRECTIONOUTCOME = {"Wow you're trash...\nGame Over!\n", "Nice you found 2 shiny gold coins...\nGood job lil bro!\n", "YOU'RE COOKED!","You found the treasure, nice!"};
    // Boss-based encounter arrays
    final static String[] BOSSFIGHT = {"You defeated the boss!", "You were defeated by the boss!"};
    final static String[] BOSSRUN = {"You successfully ran from the boss!", "You died running from the boss..."};
    // Treasure encounter arrays
    final static String[] TREASURETAKE = {"You took the treasure and got out with your life!\nCongratulations!", "There was a trap... You died\nGame over!"};
    final static String[] TREASURELEAVE = {"You left the treasure...\nBut found double the treasure congratulations!", "You left the treasure", "You left the treasure...\nBut fell to a spike-pitfall...\nGame over!"};

    public static void main(String[] args) {
        System.out.println("\nHello, Welcome to Kyle's RPG game re-written in java!\nYou will be travelling North, East, South or West\nThe outcome of your adventure depends on your choices so choose wisely!");
        run();
    }

    // Infinite loop run() method will "run" call play() method until returns false value or break statement
    static void run() {
        while (true) {
            play();
            
            if (!continueGame()) {
                System.out.println("Thanks for playing! Goodbye.\n");
                break;
            }
        }
    }

    static void play() {
        // Get user direction method as String
        String userDirection = getUserDirection();
        
        // Display the user's direction choice
        if (userDirection == null) {
            System.out.println("\nUmmm...\nThat's not a real direction try again!\n");
            return;
        }
        
        // Display direction chosen
        System.out.println("You chose " + userDirection + "\n");
        
        // Retrieve direction outcome
        String randomDirectionOutcome = getDirectionOutcome();
        System.out.println(randomDirectionOutcome);
        
        // Check for special events based on the random outcome
        switch(randomDirectionOutcome) {
            case "YOU'RE COOKED!" -> {playBoss();}
            case "You found the treasure, nice!" -> {playTreasure();}
            case "Wow you're trash...\nGame Over!" -> {gameOver();}
        }
    }

    static void playBoss() {
        String userBossChoice = getUserBossChoice();

        if (userBossChoice == null) {
            System.out.println("Hey it's either fight or leave\nChoose again!\n");
            return;
        }

        System.out.println("You chose to " + userBossChoice + "\n");
        String bossResult = determineBossOutcomes(userBossChoice);
        System.out.println(bossResult + "\n");

        // Check for instant lose boss events based on random outcome
        switch (bossResult) {
            case "You were defeated by the boss!" -> {gameOver();}
            case "You died running from the boss..." -> {gameOver();}
        }
    }

    static void playTreasure() {
        String userTreasureChoice = getUserTreasureChoice();

        if (userTreasureChoice == null) {
            System.out.println("Take the treasure or leave\nNo other option try again\n");
            return;
        }

        System.out.println("You chose " + userTreasureChoice + "\n");
        String treasureResult = determineTreasureOutcomes(userTreasureChoice);
        System.out.println(treasureResult + "\n");

        // Check for instant lose treasure events based on random outcome
        switch (treasureResult) {
            case "You left the treasure...\nBut fell to a spike-pitfall...\nGame over!" -> {gameOver();}
            case "There was a trap... You died\nGame over!" -> {gameOver();}
        }
    }
    
    // Choose direction
    static String getUserDirection() {
        // User direction input
        System.out.print("\nEnter direction to travel (north/east/west/south): ");
        String direction = SCANNER.nextLine().toLowerCase().trim();
        
        for (String directionChoice : DIRECTION) {
            if (directionChoice.equals(direction))
                return direction;
        }
        return null; // returns null value to play() method so user can try again
    }

    static String getDirectionOutcome() {
        return DIRECTIONOUTCOME[RANDOM.nextInt(DIRECTIONOUTCOME.length)];
    } 

    static String getUserBossChoice() {
        System.out.print("\nFight the boss or run? (fight/run): ");
        String boss = SCANNER.nextLine().toLowerCase().trim();

        switch (boss) {
            case "fight" -> { return "fight";} 
            case "run" -> { return "run";} 
            // Fixed: return statment added to recusrive call - loops until valid choice presented by user
            default -> { System.out.println("Invalid option\nPack it up lil bro and try again");return getUserBossChoice();} 
        }
    }

    static String getBossFight() {
        return BOSSFIGHT[RANDOM.nextInt(BOSSFIGHT.length)];
    }

    static String getBossRun() {
        return BOSSRUN[RANDOM.nextInt(BOSSRUN.length)];
    }

    static String determineBossOutcomes(String userChoice) {
        switch (userChoice) {
            case "fight" -> {return getBossFight();}
            case "run" -> {return getBossRun();}
            default -> {return "Invalid choice!";}
        }
    }

    static String getUserTreasureChoice() {
        System.out.print("\nTake the treasure or leave? (take/leave): ");
        String treasure = SCANNER.nextLine().toLowerCase().trim();

        switch (treasure) {
            case "take" -> { return "take";} 
            case "leave" -> { return "leave";} 
            default -> {System.out.println("Invalid option lil bro\nTry again");return getUserTreasureChoice();}
            // Fixed: return the recursive call (getUserTreasureChoice())
        }
    }

    static String getTreasureTake() {
        return TREASURETAKE[RANDOM.nextInt(TREASURETAKE.length)];
    }

    static String getTreasureLeave() {
        return TREASURELEAVE[RANDOM.nextInt(TREASURELEAVE.length)];
    }

    static String determineTreasureOutcomes(String userChoice) {
        switch (userChoice) {
            case "take" -> {return getTreasureTake();}
            case "leave" -> {return getTreasureLeave();}
            default -> {return "Invalid choice!";}
        }
    }
    
    static boolean continueGame() {
        while (true) {
            System.out.print("Continue adventure? (yes/no): ");
            String response = SCANNER.nextLine().toLowerCase().trim();

            switch (response) {
                case "y", "yes" -> {System.out.println("Continuing adventure");return true;}
                case "n", "no" -> {System.out.println("Ending adventure\n");return false;}
                // false value: end program in runGame() method if statement
                default -> System.out.println("Invalid choice!\nEnter 'yes' or 'no' again.\n");
            }
        }
    }

    static void gameOver() {
        System.out.println("Ending game");
        System.exit(0);
    }
}