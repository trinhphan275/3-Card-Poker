import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.ParameterizedTest;
import java.util.ArrayList;


public class TestDealer {

    @Test
    void testConstructor3(){
        Deck deck = new Deck();
        int totalTwelveThrough14 = 0;

        for(int i = 0; i < deck.size(); i++){
        
            if((deck.get(i).getValue() == 12) ||
            (deck.get(i).getValue() == 13) ||
            (deck.get(i).getValue() == 14))
            totalTwelveThrough14++;
         }
         assertEquals(12, totalTwelveThrough14);
    }

    @Test 
    void testConstructor(){
        Deck deck = new Deck();
        int totalC = 0;
        int totalD = 0;
        int totalS = 0;
        int totalH = 0;

        for(int i = 0; i < deck.size(); i++){
            if(deck.get(i).getSuit() == 'C')
                totalC++;
        }
        for(int i = 0; i < deck.size(); i++){
            if(deck.get(i).getSuit() == 'D')
                totalD++;
        }
        for(int i = 0; i < deck.size(); i++){
            if(deck.get(i).getSuit() == 'S')
                totalS++;
        }
        for(int i = 0; i < deck.size(); i++){
            if(deck.get(i).getSuit() == 'H')
                totalH++;
        }
        assertEquals(13, totalC);
        assertEquals(13, totalD);
        assertEquals(13, totalS);
        assertEquals(13, totalH);
        assertEquals(52, deck.size());
    }

    @Test 
    void testConstructor2(){
        Deck deck = new Deck();
        int totalTwoThroughSix = 0;
        int totalSevenThroughEleven = 0;

        for(int i = 0; i < deck.size(); i++){
            if((deck.get(i).getValue() == 2) ||
            (deck.get(i).getValue() == 3) ||
            (deck.get(i).getValue() == 4) || 
            (deck.get(i).getValue() == 5) ||
            (deck.get(i).getValue() == 6))
            totalTwoThroughSix++;

            if((deck.get(i).getValue() == 7) ||
            (deck.get(i).getValue() == 8) ||
            (deck.get(i).getValue() == 9) || 
            (deck.get(i).getValue() == 10) ||
            (deck.get(i).getValue() == 11))
            totalSevenThroughEleven++;
         }
         assertEquals(20, totalTwoThroughSix);
         assertEquals(20, totalSevenThroughEleven);
    }

    @Test
    void testDealHand(){
        Deck deck = new Deck();
        ArrayList<Card> list = new ArrayList();
        list = deck.dealHand();
        assertEquals(3, list.size());
    }

    @Test
    void testDealHand2(){
        Deck deck = new Deck();
        ArrayList<Card> list = new ArrayList();
        list = deck.dealHand();
        list = deck.dealHand();
        list = deck.dealHand();
        assertEquals(3, list.size());
        assertEquals(43, deck.size());
    }

    @Test
    void testDealHand3(){
       Deck deck = new Deck();
        ArrayList<Card> list = new ArrayList();
        list = deck.dealHand();
        list = deck.dealHand();
        list = deck.dealHand();
        list = deck.dealHand();
        list = deck.dealHand();
        list = deck.dealHand();
        list = deck.dealHand();
        list = deck.dealHand();
        list = deck.dealHand();
        list = deck.dealHand();

        assertEquals(3, list.size());
        assertEquals(22, deck.size());
    }

    @Test
    void testDealHandSimple(){
        Deck deck = new Deck();
        ArrayList<Card> deck2 = deck.dealHand();
        ArrayList<Card> deck3 = deck.dealHand();
        ArrayList<Card> deck4 = deck.dealHand();
        ArrayList<Card> deck5 = deck.dealHand();
        ArrayList<Card> deck6 = deck.dealHand();
        assertEquals(3, deck2.size());
        assertEquals(37, deck.size());
    }



    @Test 
    void testDealerHand(){
        Deck deck = new Deck();
        ArrayList<Card> deck2 = deck.dealHand();
        assertEquals(deck.size() - deck2.size(), deck.size() - 3);
        ArrayList<Card> deck3 = deck.dealHand();
        assertEquals(deck.size() - deck2.size(), deck.size() - 3);
        ArrayList<Card> deck4 = deck.dealHand();
        assertEquals(deck.size() - deck2.size(), deck.size() - 3);
        ArrayList<Card> deck5 = deck.dealHand();
        assertEquals(deck.size() - deck2.size(), deck.size() - 3);
        ArrayList<Card> deck6 = deck.dealHand();
        assertEquals(deck.size() - deck2.size(), deck.size() - 3);
        ArrayList<Card> deck7 = deck.dealHand();
        assertEquals(deck.size() - deck2.size(), deck.size() - 3);
}

@Test
    void testDealerDeck(){
        Deck deck = new Deck();
        deck.shuffle();
        int totalC = 0;
        int totalD = 0;
        int totalS = 0;
        int totalH = 0;

        for(int i = 0; i < deck.size(); i++){
            if(deck.get(i).getSuit() == 'C')
                totalC++;
        }
        for(int i = 0; i < deck.size(); i++){
            if(deck.get(i).getSuit() == 'D')
                totalD++;
        }
        for(int i = 0; i < deck.size(); i++){
            if(deck.get(i).getSuit() == 'S')
                totalS++;
        }
        for(int i = 0; i < deck.size(); i++){
            if(deck.get(i).getSuit() == 'H')
                totalH++;
        }
        assertEquals(13, totalC);
        assertEquals(13, totalD);
        assertEquals(13, totalS);
        assertEquals(13, totalH);
        assertEquals(52, deck.size());
    }

    @Test 
    void testDealerDeckRearrange(){
        Card card1 = new Card('H', 5);
        Card card2 = new Card('S', 9);
        Card card3 = new Card('H', 13);
        Deck deck = new Deck();
        deck.clear();
        deck.add(card1);
        deck.add(card2);
        deck.add(card3);
        deck.rearrange(deck);
        int check = 13;

        for(int i = 0; i < deck.size(); i++){
            assertEquals(check, deck.get(i).getValue());
            check = check - 4;
        }
    }
}
