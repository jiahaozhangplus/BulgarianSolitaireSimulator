// Name:
// USC NetID:
// CSCI455 PA2
// Spring 2019

package PA2;

import java.util.ArrayList;
import java.util.Scanner;

/**
   <add main program comment here>
*/

public class BulgarianSolitaireSimulator {

   public static void main(String[] args) {
     
      boolean singleStep = false;
      boolean userConfig = false;

      for (int i = 0; i < args.length; i++) {
         if (args[i].equals("-u")) {
            userConfig = true;
         }
         else if (args[i].equals("-s")) {
            singleStep = true;
         }
      }

      // <add code here>
      SolitaireBoard test = createArray(userConfig);
      System.out.println("Initial configuration: " + test.configString());
      runSimulator(test, singleStep);
      
   }
   

// <add private static methods here>
    private static SolitaireBoard createArray(boolean userConfig) {
    	System.out.println("Number of total cards is " + SolitaireBoard.CARD_TOTAL);
	    if(userConfig == false) {
		   return new SolitaireBoard();
	    }
	    else {
	    	ArrayList<Integer> piles = new ArrayList<Integer>();
	    	Scanner in1 = new Scanner(System.in);
	    	System.out.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");
	    	while(true){
	    	   System.out.println("Please enter a space-separated list of positive integers followed by newline:");
	           String input = in1.nextLine();  
	           Scanner in2 = new Scanner(input);  
			   while(in2.hasNextInt()) {
				   piles.add(in2.nextInt());
		       }
			   if(checkArray(piles)) {
				   break;
			   }
	           piles.clear();  
	    	}
	    	return new SolitaireBoard(piles);
	    }
    }
    
    private static boolean checkArray(ArrayList<Integer> piles) {
    	int sum = 0;
    	for(int i = 0;i<piles.size();i++) {
    		if(piles.get(i) <= 0) {
    			return false;
    		}
    		sum += piles.get(i);
    	}
    	if(sum == SolitaireBoard.CARD_TOTAL) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    private static void runSimulator(SolitaireBoard test, boolean singleStep) {
		int i = 0;
    	while(!test.isDone()){
			test.playRound();
			i++;
			System.out.println("[" + i + "] Current configuration: " + test.configString());
			if(singleStep == true) {
				System.out.println("<Type return to continue>");
				Scanner typeReturn = new Scanner(System.in);
				typeReturn.nextLine();				
			}
		}
		System.out.println("Done!");
    }
}
