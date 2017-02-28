/*************************************************
 File: [PlayingCard.java]
 By: [Artsem Holdvekht]
 Date: [8 August 16]
 Description: [Implements VideoPoker class. That simulates the game of poker, checks for winning hands, updates players
               balance, continues the game until the player decides to quit or runs out of money.]
 *************************************************/
package PJ4;
import java.util.*;

/*
 * Ref: http://en.wikipedia.org/wiki/Video_poker
 *      http://www.freeslots.com/poker.htm
 *
 * Short Description and Poker rules:
 *
 * Video poker is also known as draw poker. 
 * The dealer uses a 52-card deck, which is played fresh after each playerHand. 
 * The player is dealt one five-card poker playerHand. 
 * After the first draw, which is automatic, you may hold any of the cards and draw 
 * again to replace the cards that you haven't chosen to hold. 
 * Your cards are compared to a table of winning combinations. 
 * The object is to get the best possible combination so that you earn the highest 
 * payout on the bet you placed. 
 *
 * Winning Combinations
 *  
 * 1. Jacks or Better: a pair pays out only if the cards in the pair are Jacks, 
 * 	  Queens, Kings, or Aces. Lower pairs do not pay out.
 * 2. Two Pair: two sets of pairs of the same card denomination. 
 * 3. Three of a Kind: three cards of the same denomination. 
 * 4. Straight: five consecutive denomination cards of different suit. 
 * 5. Flush: five non-consecutive denomination cards of the same suit. 
 * 6. Full House: a set of three cards of the same denomination plus 
 * 	  a set of two cards of the same denomination.
 * 7. Four of a kind: four cards of the same denomination. 
 * 8. Straight Flush: five consecutive denomination cards of the same suit. 
 * 9. Royal Flush: five consecutive denomination cards of the same suit, 
 * 	  starting from 10 and ending with an ace
 *
 */


/* This is the main poker game class.
 * It uses Decks and Card objects to implement poker game.
 * Please do not modify any data fields or defined methods
 * You may add new data fields and methods
 * Note: You must implement defined methods
 */


public class VideoPoker {

    // default constant values
    private static final int startingBalance = 100;
    private static final int numberOfCards = 5;

    // default constant payout value and playerHand types
    private static final int[] multipliers = {1, 2, 3, 5, 6, 9, 25, 50, 250};
    private static final String[] goodHandTypes = {
            "Royal Pair", "Two Pairs", "Three of a Kind", "Straight", "Flush	",
            "Full House", "Four of a Kind", "Straight Flush", "Royal Flush"};

    // must use only one deck
    private static final Decks oneDeck = new Decks(1);

    // holding current poker 5-card hand, balance, bet    
    private List<Card> playerHand;
    private int playerBalance;
    private int playerBet;

    /**
     * default constructor, set balance = startingBalance
     */
    public VideoPoker() {
        playerBalance = startingBalance;
    }

    /**
     * constructor, set given balance
     */
    public VideoPoker(int balance) {
        this.playerBalance = balance;
    }

    /**
     * This display the payout table based on multipliers and goodHandTypes arrays
     */
    private void showPayoutTable()
    {
        System.out.println("\n\n");
        System.out.println("Payout Table   	       Multiplier   ");
        System.out.println("=======================================");
        int size = multipliers.length;
        for (int i = size - 1; i >= 0; i--) {
            System.out.printf("%-16s\t|\t%-20s\n", goodHandTypes[i], multipliers[i]);
        }
        System.out.println("\n\n");
    }

    /**
     * Check current playerHand using multipliers and goodHandTypes arrays
     * Must print yourHandType (default is "Sorry, you lost") at the end of function.
     * This can be checked by testCheckHands() and main() method.
     */
    private void checkHands() {
        /** Implement comparator to sort the cards based on their rank to be able to count/walk the list easier
         *  Note: make a shallow copy of playerHand to sort it while checking the cards
         *        but preserving the original order of the giving hand.
         */

        List<Card> copyPlayerHand = new ArrayList<Card>(playerHand);
        //initialize the handType

        int yourHandType = -1;

        Collections.sort(copyPlayerHand, new Comparator<Card>()
        {
            public int compare(Card card1, Card card2)
            {
                return card1.getRank() - card2.getRank();
            }
        });

        // Check for player hand in the order highest to lowest and assign yourHandType
        if(isRoyalFlush(copyPlayerHand))
        {
            yourHandType = 8;
        }
        else if(isStraight(copyPlayerHand))
        {
            yourHandType = 7;
        }
        else if(isFourOfAKind(copyPlayerHand))
        {
            yourHandType = 6;
        }
        else if(isFullHouse(copyPlayerHand))
        {
            yourHandType = 5;
        }
        else if(isFlush(copyPlayerHand))
        {
            yourHandType = 4;
        }
        else if(isStraight(copyPlayerHand))
        {
            yourHandType = 3;
        }
        else if(isThreeOfAKind(copyPlayerHand))
        {
            yourHandType = 2;
        }
        else if(isTwoPair(copyPlayerHand))
        {
            yourHandType = 1;
        }
        else if(isRoyalPair(copyPlayerHand))
        {
            yourHandType = 0;
        }

        // Switch to output the hand type and compute the new playerbalance
        switch(yourHandType)
        {
            case 8:
                int multiplier = multipliers[yourHandType];
                System.out.println("You have: " + goodHandTypes[yourHandType]);
                playerBalance += (playerBet * multiplier);
                break;
            case 7:
                multiplier = multipliers[yourHandType];
                System.out.println("You have: " + goodHandTypes[yourHandType]);
                playerBalance += (playerBet * multiplier);
                break;
            case 6:
                multiplier = multipliers[yourHandType];
                System.out.println("You have: " + goodHandTypes[yourHandType]);
                playerBalance += (playerBet * multiplier);
                break;
            case 5:
                multiplier = multipliers[yourHandType];
                System.out.println("You have: " + goodHandTypes[yourHandType]);
                playerBalance += (playerBet * multiplier);
                break;
            case 4:
                multiplier = multipliers[yourHandType];
                System.out.println("You have: " + goodHandTypes[yourHandType]);
                playerBalance += (playerBet * multiplier);
                break;
            case 3:
                multiplier = multipliers[yourHandType];
                System.out.println("You have: " + goodHandTypes[yourHandType]);
                playerBalance += (playerBet * multiplier);
                break;
            case 2:
                multiplier = multipliers[yourHandType];
                System.out.println("You have: " + goodHandTypes[yourHandType]);
                playerBalance += (playerBet * multiplier);
                break;
            case 1:
                multiplier = multipliers[yourHandType];
                System.out.println("You have: " + goodHandTypes[yourHandType]);
                playerBalance += (playerBet * multiplier);
                break;
            case 0:
                multiplier = multipliers[yourHandType];
                System.out.println("You have: " + goodHandTypes[yourHandType]);
                playerBalance += (playerBet * multiplier);
                break;
            default:
                System.out.print("Sorry you lost!");
                playerBalance -= playerBet;
                break;
        }

    }


    /*************************************************
     *   add other private methods here ....
     *
     *************************************************/
    /** Implement checks for all the hands and aslo a method for switching the cards in the hand.
     *  Note: all methods have also built-in checks for other hands within in case if the implementation will change
     *        from above, also if needed the description of every hand can be found at the top of this file.
     *
     */


    /**
     * checks for Straight Flush combination
     * @param copy copy of a player hand
     * @return boolean true or false
     */
    private boolean isStraightFlush(List<Card> copy)
    {
        int consecCards = 1;
        for (int i = 0; i < numberOfCards - 1; i++) {
            if (copy.get(i).getRank() == (copy.get(i + 1).getRank() - 1) && (copy.get(i).getSuit() == (copy.get(i + 1).getSuit()))) {
                consecCards++;
            } else {
                return false;
            }
        }
        return consecCards == 5;
    }//end isStraightFlush

    /**
     * check for Royal Pair
     * @param copy copy of a player hand
     * @return boolean true or false
     */
    private boolean isRoyalPair(List<Card> copy)
    {
        for(int i = 0; i < numberOfCards - 1;)
        {
            if(copy.get(i).getRank() == copy.get(i+1).getRank() && copy.get(i).getRank() >= 11)
            {
                return true;
            }
            else
            {
                i++;
            }
        }
        return false;
    }//end isRoyalPair

    /**
     * check for Full House
     * @param copy copy of a player hand
     * @return boolean true or false
     */
    private boolean isFullHouse(List<Card> copy)
    {
        int countFirstRank = 1;
        int countLastRank = 1;
        int firstRank = copy.get(0).getRank();
        int lastRank= copy.get(copy.size() - 1).getRank();

        for (int i = 1; i < numberOfCards - 1; i++)
        {
            if (copy.get(i).getRank() == firstRank)
            {
                countFirstRank++;
            }

            if (copy.get(i).getRank() == lastRank)
            {
                countLastRank++;
            }
        }

        if((countFirstRank == 3 && countLastRank == 2) || (countFirstRank == 2 && countLastRank== 3))
        {
            return true;
        }

        return false;
    }//end isFullHouse

    /**
     * check for  Two Pairs
     * @param copy copy of a player hand
     * @return boolean true or false
     */
    private boolean isTwoPair(List<Card> copy)
    {
        int pairCnt = 0;

        if(isFullHouse(copy) || isFourOfAKind(copy))
        {
            return false;
        }

        for (int i = 0; i < numberOfCards - 1; )
        {
            if (copy.get(i).getRank() == copy.get(i + 1).getRank())
            {
                pairCnt++;
                i += 2;
            }
            else
            {
                i++;
            }
        }

        return pairCnt == 2;
    }//end isTwoPair

    /**
     * check for  Four of a Kind
     * @param copy copy of a player hand
     * @return boolean true or false
     */
    public boolean isFourOfAKind(List<Card> copy)
    {

        for (int rank = 0; rank < Card.Rank.length; rank++)
        {
            int kindCount = 0;

            for (int i = 0; i < numberOfCards - 1 ; i++)
            {

                if (copy.get(i).getRank() == rank)
                {
                    kindCount++;
                }
            }

            if (kindCount == 4) {
                return true;
            }

        }

        return false;

    }
    /**
     * check for  Three of a Kind
     * @param copy copy of a player hand
     * @return boolean true or false
     */
    private boolean isThreeOfAKind(List<Card> copy)
    {
        if(isFullHouse(copy))
        {
            return false;
        }

        for (int rank = 0; rank < Card.Rank.length; rank++) {

            int kindCount = 0;

            for (int i = 0; i < numberOfCards - 1; i++) {

                if (copy.get(i).getRank() == rank) {
                    kindCount++;
                }
            }

            if (kindCount == 3) {
                return true;
            }

        }

        return false;
    }
    /**
     * check for Straight
     * @param copy copy of a player hand
     * @return boolean true or false
     */
    private boolean isStraight(List<Card> copy)
    {
        if(isStraightFlush(copy))
       {
            return false;
       }

        int consecCards = 1;
        for (int i = 0; i < numberOfCards - 1; i++)
        {
            if (copy.get(i).getRank() == (copy.get(i + 1).getRank() - 1))
            {
                consecCards++;
            }
            else
            {
                return false;
            }
        }
        return consecCards == 5;
    }

    /**
     * check for Flush
     * @param copy copy of a player hand
     * @return boolean true or false
     */
    private boolean isFlush(List<Card> copy)
    {

        if(isRoyalFlush(copy) || isStraightFlush(copy))
        {
            return false;
        }

        for (int i = 0; i < numberOfCards - 1; i++)
        {
            if (copy.get(i).getSuit() != copy.get(i + 1).getSuit())
            {
                return false;
            }
        }

        return true;

    }

    /**
     * check for Royal Flush
     * @param copy copy of a player hand
     * @return boolean true or false
     */
    private boolean isRoyalFlush(List<Card> copy)
    {
        for (int i = 2; i < numberOfCards - 1; i++) {
            if (copy.get(0).getRank() == 1 && copy.get(1).getRank() == 10 && (copy.get(i).getRank() - 1) == (copy.get(i - 1).getRank()))
            {
                return true;
            }
        }
        return false;
    }


    /**
     * Method for taking user's input of cards that should be saved.
     * Converts Scanner into array of strings, check for good input
     * Create temporary hand to do all the switching operations and copying it into the actual player hand.
     */
    private void changeCards() {
        // stores the number of cards to be changed/dealt
        int numCardsAdd;
        //flag for do /while loop
        boolean pass;
        // temporary hand arrayList
        List<Card> tmpHand = new ArrayList<Card>();
        // List to be created using the scanner input
        List<String> keep_cards;
        Scanner input = new Scanner(System.in);

        do
        {
            pass = true;
            System.out.print("Enter the number of cards to keep: ");
            String user_input = input.nextLine();
            //convert input into array splititng the input by comma(,) or white space
            keep_cards = Arrays.asList(user_input.split("[,\\s]+"));

            // perform the check to make sure the input is valid
            for (int i = 0; i < keep_cards.size(); i++)
            {
                try
                {
                    int parseValue = Integer.parseInt(keep_cards.get(i));

                    if (parseValue < 0 || parseValue > numberOfCards)
                    {
                        System.out.println("Invalid input, please try again(1 -5).\n");
                        pass = false;
                        break;
                    }
                }
                catch(NumberFormatException e)
                {
                    System.out.print("The input is not a number, please try again.");
                    pass = false;
                }
            }
        }while(!pass);

        //a quick debuggin loop to see the input/cards that should be kept
       /*for (int i = 0; i < keep_cards.size(); i++) {
            System.out.println("Keep_Cards: " + keep_cards.get(i));
        }*/

        //add cards that should be saved into the temp hand
        for (int i = 0; i < keep_cards.size(); i++) {
            tmpHand.add(playerHand.get(Integer.parseInt(keep_cards.get(i)) - 1));
        }
        //compute the number of cards that should be dealt
        numCardsAdd = numberOfCards - keep_cards.size();


        try
        {
            tmpHand.addAll(oneDeck.deal(numCardsAdd));
        }
        catch (PlayingCardException e)
        {
            System.out.println("There was an error while dealing new cards." + e.getMessage());
        }

        // copy temp hand into the actual player hand
        playerHand = tmpHand;
        System.out.println("Your new hand is: " + playerHand + "\n");


    }//end changeCards

    public void play() {


        /** The main algorithm for single player poker game
         *
         * Steps:
         * 		showPayoutTable()
         *
         * 		++
         * 		show balance, get bet
         *		verify bet value, update balance
         *		reset deck, shuffle deck,
         *		deal cards and display cards
         *		ask for positions of cards to keep
         *          get positions in one input line
         *		update cards
         *		check hands, display proper messages
         *		update balance if there is a payout
         *		if balance = O:
         *			end of program
         *		else
         *			ask if the player wants to play a new game
         *			if the answer is "no" : end of program
         *			else : showPayoutTable() if user wants to see it
         *			goto ++
         */
        showPayoutTable();
        boolean goodBet = false;
        int bet;
        Scanner input = new Scanner(System.in);
        while(playerBalance > 0)
        {


            System.out.println("Your Balance: "  + playerBalance);

            //while the bet is not good
            while(!goodBet)
            {
                System.out.println("Please enter your bet: ");
                bet = input.nextInt();
                if(bet <= 0)
                {
                    System.out.println("The bet can't be negative or equal to 0.");
                }
                else if((playerBalance - bet) < 0 || bet > playerBalance)
                {
                    System.out.println("You have insufficient funds!");
                }
                else
                {
                    playerBet = bet;
                    goodBet = true;
                    //System.out.println("Playerbet = bet " + playerBet);
                }

            } //once the bet is good, move to the next statement

            oneDeck.reset();    //reset the deck
            oneDeck.shuffle();  //shuffle the deck

            try
            {
                playerHand = oneDeck.deal(numberOfCards);
            }
            catch (PlayingCardException e)
            {
                System.out.println("There was a problem dealing cards:" + e.getMessage());
            }

            System.out.print("Your current hand: " + playerHand + "\n");


            changeCards();  //let user to choose cards to keep

            checkHands();   //check for a hand combination

            //update the balance
            System.out.print("\nYour new balance is: " + playerBalance + "\n");

            //if the balance is 0 -> quit
            if(playerBalance <= 0)
            {
                System.out.println("Your balance is: " + playerBalance);
                System.out.println("Good Bye, good luck next time!");
                System.exit(0);
            }
            //else prompt user for input to play again and/or print the payout table
            else
            {
                System.out.println("\nYour balance is: " + playerBalance + " Would you like to play another game ( Please enter y or n)?");
                input = new Scanner(System.in);
                String userInput = input.nextLine();

                if(userInput.charAt(0) == 'N' || userInput.charAt(0) == 'n')
                {
                    System.out.println("Bye bye!");
                    System.exit(0);
                }
                else
                {
                    System.out.println("Would you like to see a Payout table again (y or n)?");
                        userInput = input.nextLine();
                    if(userInput.charAt(0) == 'Y' || userInput.charAt(0) == 'y')
                    {
                        showPayoutTable();
                    }
                }
            }

            goodBet = false;
        }

    }// end play()


    /*************************************************
     *   Do not modify methods below
     /*************************************************


     /** testCheckHands() is used to test checkHands() method
     *  checkHands() should print your current hand type
     */
    private void testCheckHands()
    {
        try {
            playerHand = new ArrayList<Card>();

            // set Royal Flush
            System.out.println("Royal Flush");
            playerHand.add(new Card(4,1));
            playerHand.add(new Card(4,10));
            playerHand.add(new Card(4,12));
            playerHand.add(new Card(4,11));
            playerHand.add(new Card(4,13));
            System.out.println(playerHand);
            checkHands();
            System.out.println("After teh checkHand(): "+playerHand);
            System.out.println("-----------------------------------");

            // set Straight Flush
            System.out.println("Straight Flush");
            playerHand.set(0,new Card(4,9));
            System.out.println(playerHand);
            checkHands();
            System.out.println("After teh checkHand(): "+playerHand);
            System.out.println("-----------------------------------");

            // set Straight
            System.out.println("Straight");
            playerHand.set(4, new Card(2,8));
            System.out.println(playerHand);
            checkHands();
            System.out.println("After teh checkHand(): "+playerHand);
            System.out.println("-----------------------------------");

            // set Flush
            System.out.println("Flush");
            playerHand.set(4, new Card(4,5));
            System.out.println(playerHand);
            checkHands();
            System.out.println("After teh checkHand(): "+playerHand);
            System.out.println("-----------------------------------");

            // "Royal Pair" , "Two Pairs" , "Three of a Kind", "Straight", "Flush	",
            // "Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

            // set Four of a Kind
            System.out.println("Four of a Kind");
            playerHand.clear();
            playerHand.add(new Card(4,8));
            playerHand.add(new Card(1,8));
            playerHand.add(new Card(4,12));
            playerHand.add(new Card(2,8));
            playerHand.add(new Card(3,8));
            System.out.println(playerHand);
            checkHands();
            System.out.println("After teh checkHand(): "+playerHand);
            System.out.println("-----------------------------------");

            // set Three of a Kind
            System.out.println("Three of a Kind");
            playerHand.set(4, new Card(4,11));
            System.out.println(playerHand);
            checkHands();
            System.out.println("After teh checkHand(): "+playerHand);
            System.out.println("-----------------------------------");

            // set Full House
            System.out.println("Full House");
            playerHand.set(2, new Card(2,11));
            System.out.println(playerHand);
            checkHands();
            System.out.println("After teh checkHand(): "+playerHand);
            System.out.println("-----------------------------------");

            // set Two Pairs
            System.out.println("Two Pairs");
            playerHand.set(1, new Card(2,9));
            System.out.println(playerHand);
            checkHands();
            System.out.println("After teh checkHand(): "+playerHand);
            System.out.println("-----------------------------------");

            // set Royal Pair
            System.out.println("Royal Pair");
            playerHand.set(0, new Card(2,3));
            System.out.println(playerHand);
            checkHands();
            System.out.println("After teh checkHand(): "+playerHand);
            System.out.println("-----------------------------------");

            // non Royal Pair
            System.out.println("Non Royal Pair");
            playerHand.set(2, new Card(4,3));
            System.out.println(playerHand);
            checkHands();
            System.out.println("After teh checkHand(): "+playerHand);
            System.out.println("-----------------------------------");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    /* Run testCheckHands() */
    public static void main(String args[])
    {
        VideoPoker pokergame = new VideoPoker();
        pokergame.testCheckHands();
    }
}
