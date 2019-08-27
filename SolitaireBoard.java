
// Name:
// USC NetID:
// CSCI455 PA2
// Spring 2019
package PA2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/*
  class SolitaireBoard
  The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
  by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
  for CARD_TOTAL that result in a game that terminates.
  (See comments below next to named constant declarations for more details on this.)
*/


public class SolitaireBoard {
   
   public static final int NUM_FINAL_PILES = 9;
   // number of piles in a final configuration
   // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)
   
   public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
   // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
   // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
   // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES

   // Note to students: you may not use an ArrayList -- see assgt description for details.
   
   
   /**
      Representation invariant:

      -- cardsArray[i]>0, for i from 0 to currPiles - 1
      -- currPiles less equal than CARD_TOTAL
      -- the sum of cardsarray[0] to cardsarray[currPiles-1] equal to CARD_TOTAL

   */
   
   // <add instance variables here>
   private int[] cardsArray = new int[CARD_TOTAL+1];// the array to store the cards, upper bound 
   private int currPiles = 0; //initial the current number of piles
   
 
   /**
      Creates a solitaire board with the configuration specified in piles.
      piles has the number of cards in the first pile, then the number of cards in the second pile, etc.
      PRE: piles contains a sequence of positive numbers that sum to SolitaireBoard.CARD_TOTAL
   */
   public SolitaireBoard(ArrayList<Integer> piles) {
	   for(int i = 0; i<piles.size();i++) {
		   cardsArray[i] = piles.get(i);
		   currPiles++;
	   }
      assert isValidSolitaireBoard();   // sample assert statement (you will be adding more of these calls)
   }
 
   
   /**
      Creates a solitaire board with a random initial configuration.
   */
   public SolitaireBoard() {
	   
	   int cardLeft = CARD_TOTAL;
	   Random r = new Random();
       for(int i = 0; i<CARD_TOTAL;i++) {
    	   if(cardLeft == 0) {
    		   break;
    	   }
    	   else {
    		   cardsArray[i] = r.nextInt(cardLeft)+1;// create a number from 1~45
    		   cardLeft -= cardsArray[i];
    		   currPiles++;
    	   }
       }
       assert isValidSolitaireBoard(); 
   }
  
   
   /**
      Plays one round of Bulgarian solitaire.  Updates the configuration according to the rules
      of Bulgarian solitaire: Takes one card from each pile, and puts them all together in a new pile.
      The old piles that are left will be in the same relative order as before, 
      and the new pile will be at the end.
   */
   public void playRound() {
	  int emptyPiles = 0;
      for(int i = 0;i<currPiles;i++) {	  
    	  cardsArray[i]--;
    	  cardsArray[currPiles]++;
    	  if(cardsArray[i] ==0) {
    		  emptyPiles++;
    	  }
      }
      int tempArr[] = new int[CARD_TOTAL+1];
      int k =0;
      for(int i=0;i<currPiles +1;i++) {
    	  if(cardsArray[i] !=0) {
    		  tempArr[k] = cardsArray[i];
    		  k++;
    	  }
      }
      cardsArray = tempArr;
      currPiles = k;
      assert isValidSolitaireBoard();
   }
   
   /**
      Returns true iff the current board is at the end of the game.  That is, there are NUM_FINAL_PILES
      piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES, in any order.
   */
   
   public boolean isDone() {
      if(currPiles == NUM_FINAL_PILES) {
    	  int[] copyArray = Arrays.copyOf(cardsArray, NUM_FINAL_PILES);
    	  Arrays.sort(copyArray);
    	  int[] endArray = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
    	  if(Arrays.equals(copyArray, endArray)) {
    		  assert isValidSolitaireBoard();
    		  return true;
    	  }
    	  else {return false;}
      }
      return false;  
   }

   
   /**
      Returns current board configuration as a string with the format of
      a space-separated list of numbers with no leading or trailing spaces.
      The numbers represent the number of cards in each non-empty pile.
   */
   public String configString() {
	  int[] copyArray = Arrays.copyOf(cardsArray, currPiles); 
	  assert isValidSolitaireBoard();
      return Arrays.toString(copyArray); 
   }
   
   
   /**
      Returns true iff the solitaire board data is in a valid state
      (See representation invariant comment for more details.)
   */
   private boolean isValidSolitaireBoard() {
      int sum =0;
      if(currPiles <= CARD_TOTAL){
    	  for(int i = 0; i<currPiles;i++) {
    		  if(cardsArray[i] <= 0) {
    			  return false;
    		  }
    		  else {
    			  sum += cardsArray[i];
    		  }
    	  }
    	  if(sum == CARD_TOTAL) {
    		  return true;
    	  }
    	  else {
    		  return false;
    	  }
      }
      return false;
   }
   

   // <add any additional private methods here>


}
