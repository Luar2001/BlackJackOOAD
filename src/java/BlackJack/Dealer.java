package BlackJack;

import java.util.ArrayList;

/**
 * Created by Lukas Aronsson
 * Date: 01/12/2020
 * Time: 16:22
 * Project: BlackJackOOAD
 * Copyright: MIT
 **/
public class Dealer   {

    private int stopValue = 17; //the value of the hand were the dealer will stop drawing cards

    /**
     * Should the dealer hit or not? (should the dealer draw a card?
     * @return returns true if the dealers hand has a value smaller then the stopValue and false if the opposite
     */
    public boolean shouldDealerHit() {
        return getHandValue() < getStopValue();
    }

    /**
     * shows the dealers hidden card.
     */
    public void showCards() {
        // TODO: 12/1/2020 : Lukas : figure out what im supposed to do here (i guess i have 2 wait)
    }

    /**
     * Getter for stopValue
     * @return the value of the hand were the dealer will stop drawing cards
     */
    public int getStopValue() {
        return stopValue;
    }

    /**
     * Setter for stopValue
     * @param stopValue the value of the hand were the dealer will stop drawing cards
     */
    public void setStopValue(int stopValue) {
        this.stopValue = stopValue;
    }

    ArrayList<Card> hand = new ArrayList<>();


    /**
     * Calculates and returns the value of the current hand
     *
     * @return the total value of the current hand
     */
     void clearHand(){
        hand.clear();
    }
    //Den här metoden räknar ut värdet av en spelares hand. Den tar till vara på om ifall en spelare har Ess
    //Vanliga kort har värde 1-13 ess har värde 0, eller -1 beroende på om en vill att det ska vara värt
    //11 eller 1 poäng.
     int getHandValue() {
        int lenght = hand.size();
        int total = 0;
        for (int i = 0; i < lenght; i++) {
            Card temp = (Card) hand.get(i);
            if (temp.getRank() >= 10) total = total + 10;//Alla kort från tio och upp är värda 10
            if (temp.getRank() < 10 && temp.getRank() > 1)
                total = total + temp.getRank();//kortets värde läggs till totalen
            if (temp.getRank() == 1) total = total + 11;//Ess kan vara värda 11
            if (temp.getRank() == 0) total = total + 1;//Ess kan vara värda 1
            if (total > 21) {
                //Om vi har mer än 21 kollar denna loop igenom ifall vi har Ess, om vi har det sätter vi esset till att
                //vara värt ett istället för elva och börjar om räkningen.
                for (int j = 0; j < lenght; j++) {
                    Card temp2 = (Card) hand.get(j);
                    if (temp2.getRank() == 0) {
                        hand.set(j, new Card(temp2.getSuit(), 0));
                        i = -1;
                        total = 0;
                        j = lenght;
                    }
                }
            }
        }
//        for (int i = 0; i < hand.size(); i++) { //Skriver ut korten till konsolen.
//            System.out.println(hand.get(i));
//        }
//        System.out.println("Totalt värde: " + total); // Skriver ut handens totala värde.
        return total;
    }



}
