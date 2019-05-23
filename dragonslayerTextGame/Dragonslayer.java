import java.util.Scanner;
import java.security.SecureRandom;

public class Dragonslayer
{
    public static enum Status //enum with 4 states of game
    {
        CONTINUE, WIN, LOSE, QUIT
    }
    
    //new instance of scanner
    static Scanner input = new Scanner(System.in);
    //loop control variable
    private static Status gameStatus;
    //main prompt variables
    public static final int ADVENTURE = 1;
    public static final int DRAGON = 2;
    public static final int QUIT = 3;
    //monsters
    public static final int GOBLIN = 0;
    public static final int ORK = 1;
    public static final int TROLL = 2;
    //createCharacter variables
    public static int statPoints = 20;
    public static int invalid = 0;
    //hero data variables
    public static int herosHeath = 0;
    public static int heroAttackPower = 0;
    public static int heroMagic = 0;
    public static int heroLevel = 1;
    //monster data variables
    public static int monstersHealth;
    public static int monstersAttackPower;
    public static int monsterExperienceLevel;
    public static String monstersName;
    //new instance of random
    public static SecureRandom randomNumbers = new SecureRandom();
    //combat loop variable
    public static boolean isFighting;
    //executeStatChoice variable
    public static int pointsRefund;
    //combat menu constants
    public static final int MELEE = 1;
    public static final int MAGIC = 2;
    public static final int CHARGE = 3;
    public static final int RUN = 4;
            
            
    
    public static void main(String[] args)
    {
        System.out.println("Your village is attacked by monstrous creatures. Kill a dragon to become a hero and win!\n");
        
        gameStatus = Status.CONTINUE; //set game status to continue
        
            while (gameStatus == Status.CONTINUE)
            {
                createCharacter();
                int choice = input.nextInt();
                executeAdventureChoice(choice);
            }
         printEndMessage();
    }
    
        public static void createCharacter() //set stat points and decrement
        {
                for (int i = 0; statPoints > 0; statPoints--)
                {
                    printCreationPrompt();
                    
                    int choice = input.nextInt();
                    executeStatChoice(choice);
                    statPoints += pointsRefund;
                }
            printMainPrompt();
            gameStatus = Status.QUIT;
        }
        
        public static void printMainPrompt()
        {
            System.out.println("\n***It is dangerous out there, do you:***");
            System.out.println("1.) Find adventure");
            System.out.println("2.) Confront the dragon");
            System.out.println("3.) Quit and go home!");
        }
        
       public static void executeAdventureChoice(int input) //choose adventure
        {
            
            if(input == ADVENTURE)
            {
                generateMonster();
                runCombatLoop();
            }
            else if(input == DRAGON)
            {
                createDragon();
                runCombatLoop();
            }
            else if(input == QUIT)
            {
                gameStatus = Status.QUIT;
            }
            else
            {
                System.out.println("invalid option");
            }
        }
        
        public static void printEndMessage() //game control of ending message
        {
            System.out.println("\nYou got to level: " + heroLevel);
            
            if(gameStatus == Status.LOSE)
            {
                System.out.println("You've lost!");
            }
            else if(gameStatus == Status.WIN)
            {
                System.out.println("You've won!");
            }
            else if(gameStatus == Status.QUIT)
            {
                System.out.println("You've abandoned you're mission!");
            }
        }
        
        public static void printCreationPrompt()
        {
            System.out.println("\nHealth:" + herosHeath + ", Attack:" + heroAttackPower + ", Magic:" + heroMagic);
            System.out.println("1.) +10 HP");
            System.out.println("2.) +1 Attack");
            System.out.println("3.) +3 Magic Points");
            System.out.print("You have " + statPoints + " to spend: ");
        }
        
        public static int executeStatChoice(int input)
        {
            pointsRefund = 0;
            final int BUY_HP = 1;
            final int BUY_AP = 2;
            final int BUY_MP = 3;
           
                if(input == BUY_HP)
                {
                    herosHeath += 10;
                }
                else if(input == BUY_AP)
                {
                    heroAttackPower++;
                }
                else if(input == BUY_MP)
                {
                    heroMagic += 3;
                }
                else
                {
                    System.out.println("not a valid option");
                    pointsRefund++;
                }
            return pointsRefund;
        }
        
        public static void generateMonster()
        {
            int monsterChoice = randomNumbers.nextInt(3); //random number between 1-3
            
            if(monsterChoice == GOBLIN)
            {
                createGoblin();
            }
            else if(monsterChoice == ORK)
            {
                createOrk();
            }
            else if(monsterChoice == TROLL)
            {
                createTroll();
            }
        }
        
        public static void runCombatLoop()
        {
            isFighting = true;
            
            while(isFighting == true && isMonsterAlive() == true && isPlayerAlive() == true)
            {
                runCombatRound();
                isMonsterAlive(); 
                isPlayerAlive();
            }
        }
        
        public static void createDragon()
        {
            monstersName = "dragon";
            monstersHealth = 1000;
            monstersAttackPower = 50;
            monsterExperienceLevel = 20;
        }
        
        public static void createGoblin()
        {
            monstersName = "goblin";
            monstersAttackPower = 8 + randomNumbers.nextInt(5);
            monstersHealth = 75 + randomNumbers.nextInt(25);
            monsterExperienceLevel = 1;
        }
        
        public static void createOrk()
        {
            monstersName = "ork";
            monstersAttackPower = 12 + randomNumbers.nextInt(5);
            monstersHealth = 100 + randomNumbers.nextInt(25);
            monsterExperienceLevel = 3;
        }
        
        public static void createTroll()
        {
            monstersName = "troll";
            monstersAttackPower = 15 + randomNumbers.nextInt(5);
            monstersHealth = 150 + randomNumbers.nextInt(50);
            monsterExperienceLevel = 5;
        }
        
        public static boolean isMonsterAlive()
        {
            if(monstersHealth > 0)
            {
                boolean result = true;
                return result;
            }
            else
            {
                return false;
            }
        }
        
        public static boolean isPlayerAlive()
        {
            if(herosHeath > 0)
            {
                boolean result = true;
                return result;
            }
            else
            {
                return false;
            }
        }
        
        public static void runCombatRound()
        {
            printCombatPrompt();
            int choice = input.nextInt();
            
            if(executeCombatChoice(choice) == true)
            {
                monsterAttack();
                updateCombatResults();
                printResultMessage();
            }
        }
        
        public static void printCombatPrompt()
        {
            //report combat stats
            System.out.println("\nYou are fighting a " + monstersName);
            System.out.println("The monster HP: " + monstersHealth);
            System.out.println("Your HP: " + herosHeath);
            System.out.println("Your MP: " + heroMagic);
            
            //combat menu prompt
            System.out.println("\nWhat action do you want to perform?");
            System.out.println("1.) Sword Attack");
            System.out.println("2.) Cast Spell");
            System.out.println("3.) Charge Mana");
            System.out.println("4.) Run Away\n");
        }
        
        public static boolean executeCombatChoice(int input)
        {
            boolean validChoice = true;
            String description = " ";
            
            if(input == MELEE)
            {
                description = meleeOption();
            }
            else if(input == MAGIC)
            {
                if(hasEnoughMana() == true)
                {
                   description = magicOption(); 
                }
            }
            else if(input == CHARGE)
            {
                description = chargeOption();
            }
            else if(input == RUN)
            {
                description = fleeOption();
            }
            else
            {
                validChoice = false;
                description = "I don't understand that command.";
            }
            
            System.out.println(description);
            return validChoice;
        }
        
        public static void monsterAttack()
        {
            if(isMonsterAlive() == true && isFighting == true)
            {
                int monsterDamage = calculateMeleeDamage(heroAttackPower);
                herosHeath -= monsterDamage;
                
                System.out.println("The " + monstersName + " attacks you for " + monsterDamage + " damage.");
            }
        }
        
        public static void updateCombatResults()
        {
            if(isMonsterAlive() != true)
            {
                levelUp();
            }
            
            if(isPlayerAlive() != true)
            {
                gameStatus = Status.LOSE;
            }
            //isFighting = false; //control********
        }
        
        public static void printResultMessage()
        {
            if(isMonsterAlive() != true)
            {
                System.out.println("You defeated the " + monstersName);
            }
            
            if(isPlayerAlive() != true)
            {
                System.out.println("You were slain by the " + monstersName);
            }
        }
        
        public static String meleeOption()
        {
            int damangeFromAttack = calculateMeleeDamage(heroAttackPower);
            monstersHealth -= damangeFromAttack;
            return "You strike the " + monstersName + " with sword for " + damangeFromAttack + " damage\n";
        }
        
        public static boolean hasEnoughMana()
        {
            if(heroMagic >= 3)
            {
                return true;
            }
            else
            {
                return false;
            }
                
        }
        
        public static String magicOption()
        {
            int magicDamage = calculateMagicDamage(monstersHealth, heroMagic);
            monstersHealth -= magicDamage;
            String magicMessage = getMagicText();
            calculateManaLeft();
            return magicMessage;
        }
        
        public static String chargeOption()
        {
            heroMagic++;
            
            String actionMessage = "You focus and charge your magic power.\n"; 
            return actionMessage;
        }
        
        public static String fleeOption()
        {
            isFighting = false;
            
            return "You run away!\n"; 
        }
        
        public static void levelUp()
        {
            heroLevel += monsterExperienceLevel;
            heroAttackPower += monsterExperienceLevel;
            herosHeath = 50 + randomNumbers.nextInt(30);
            System.out.println("You have leveled up!");
            System.out.println("level: " + heroLevel);
            
            if(monstersName == "dragon")
            {
                gameStatus = Status.WIN;
            }
        }
        
        public static int calculateMeleeDamage(int heroAttackPowerInput)
        {
            int damage = 1 + randomNumbers.nextInt(heroAttackPowerInput);
            
            return damage;
        }
        
        public static int calculateMagicDamage(int defendersHealth, int attackersMana) 
        {
            int damage = 0;
            
            if(hasEnoughMana() == true)
            {
                damage = (monstersHealth/2);
            }
            return damage;
        }
        
        public static String getMagicText()
        {
            String magicText = " ";
            
            if(hasEnoughMana() == true)
            {
                magicText = "You cast the weaken spell on the " + monstersName;
            }
            if(hasEnoughMana() == false) //won't print this for some reason
            {
                magicText = "You don't have enough mana";
            }
            
            return magicText;
        }
        
        public static void calculateManaLeft()
        {
            if(hasEnoughMana() == true)
            {
                heroMagic -= 3;
            }
        }
}
