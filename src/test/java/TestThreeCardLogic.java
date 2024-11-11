import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.ParameterizedTest;
import java.util.ArrayList;


public class TestThreeCardLogic {
    @Test
    void isPairTest(){
        Deck deck = new Deck();
        deck.clear();
        Card card = new Card('S', 2);
        Card card2 = new Card('D', 2);
        Card card3 = new Card('H', 9);
        deck.add(card);
        deck.add(card2);
        deck.add(card3);
        assertEquals(5, ThreeCardLogic.evalHand(deck));
    }

    @Test
    void isPairTest2(){
        Deck deck = new Deck();
        deck.clear();
        Card card = new Card('S', 2);
        Card card2 = new Card('D', 2);
        Card card3 = new Card('H', 9);
        Deck deck2 = new Deck();
        deck.clear();
        Card card4 = new Card('S', 6);
        Card card5 = new Card('D', 7);
        Card card6 = new Card('H', 8);
        deck.add(card);
        deck.add(card2);
        deck.add(card3);
        deck2.add(card4);
        deck2.add(card5);
        deck2.add(card6);
        assertEquals(5, ThreeCardLogic.evalHand(deck));
        assertEquals(5, ThreeCardLogic.evalHand(deck));
    }

    @Test
    void isFlushTest(){
        Deck deck = new Deck();
        deck.clear();
        Card card = new Card('S', 2);
        Card card2 = new Card('S', 2);
        Card card3 = new Card('S', 9);
        deck.add(card);
        deck.add(card2);
        deck.add(card3);
        assertEquals(4, ThreeCardLogic.evalHand(deck));
    }

    @Test
    void isFlushTest2(){
        Deck deck = new Deck();
        deck.clear();
        Card card = new Card('S', 2);
        Card card2 = new Card('S', 3);
        Card card3 = new Card('S', 9);
        deck.add(card);
        deck.add(card2);
        deck.add(card3);
        assertEquals(4, ThreeCardLogic.evalHand(deck));

        Deck deck2 = new Deck();
        deck.clear();
        Card card4 = new Card('H', 2);
        Card card5 = new Card('H', 5);
        Card card6 = new Card('H', 9);
        deck.add(card4);
        deck.add(card5);
        deck.add(card6);
        assertEquals(4, ThreeCardLogic.evalHand(deck));
    }


    @Test
    void isStraightTest(){
        Deck deck = new Deck();
        deck.clear();
        Card card = new Card('S', 7);
        Card card2 = new Card('H', 8);
        Card card3 = new Card('H', 9);
        deck.add(card);
        deck.add(card2);
        deck.add(card3);
        assertEquals(3, ThreeCardLogic.evalHand(deck));
    }

     @Test
    void isStraightTest2(){
        Deck deck = new Deck();
        deck.clear();
        Card card = new Card('S', 7);
        Card card2 = new Card('H', 8);
        Card card3 = new Card('H', 9);
        deck.add(card);
        deck.add(card2);
        deck.add(card3);
        assertEquals(3, ThreeCardLogic.evalHand(deck));

         Deck deck2 = new Deck();
        deck.clear();
        Card card4 = new Card('S', 10);
        Card card5 = new Card('H', 8);
        Card card6 = new Card('H', 9);
        deck.add(card4);
        deck.add(card5);
        deck.add(card6);
        assertEquals(3, ThreeCardLogic.evalHand(deck));
    }

    @Test
    void threeOfAKindTest(){
        Deck deck = new Deck();
        deck.clear();
        Card card = new Card('S', 5);
        Card card2 = new Card('H', 5);
        Card card3 = new Card('H', 5);
        deck.add(card);
        deck.add(card2);
        deck.add(card3);
        assertEquals(2, ThreeCardLogic.evalHand(deck));
    }

    @Test
    void threeOfAKindTest2(){
        Deck deck = new Deck();
        deck.clear();
        Card card = new Card('S', 8);
        Card card2 = new Card('H', 8);
        Card card3 = new Card('H', 8);
        deck.add(card);
        deck.add(card2);
        deck.add(card3);
        assertEquals(2, ThreeCardLogic.evalHand(deck));

        Deck deck2 = new Deck();
        deck.clear();
        Card card4 = new Card('S', 10);
        Card card5 = new Card('H', 10);
        Card card6 = new Card('H', 10);
        deck.add(card4);
        deck.add(card5);
        deck.add(card6);
        assertEquals(2, ThreeCardLogic.evalHand(deck));
    }

    @Test
    void straightFlushTest(){
        Deck deck = new Deck();
        deck.clear();
        Card card = new Card('H', 5);
        Card card2 = new Card('H', 6);
        Card card3 = new Card('H', 7);
        deck.add(card);
        deck.add(card2);
        deck.add(card3);
        assertEquals(1, ThreeCardLogic.evalHand(deck));
    }

    @Test
    void straightFlushTest2(){
        Deck deck = new Deck();
        deck.clear();
        Card card = new Card('H', 5);
        Card card2 = new Card('H', 6);
        Card card3 = new Card('H', 7);
        deck.add(card);
        deck.add(card2);
        deck.add(card3);
        assertEquals(1, ThreeCardLogic.evalHand(deck));

        Deck deck2 = new Deck();
        deck.clear();
        Card card4 = new Card('S', 8);
        Card card5 = new Card('S', 9);
        Card card6 = new Card('S', 10);
        deck.add(card);
        deck.add(card2);
        deck.add(card3);
        assertEquals(1, ThreeCardLogic.evalHand(deck));
    }

    @Test
    void compareHandsTest_SimpleCards(){
        Deck deck = new Deck();
        Deck deck1 = new Deck();
        deck.clear();
        deck1.clear();
        Card card1 = new Card('H', 3);
        Card card2 = new Card('S', 5);
        Card card3 = new Card('D', 9);
        Card card4= new Card('H', 4);
        Card card5 = new Card('S', 8);
        Card card6 = new Card('S', 12);
        deck.add(card1);
        deck.add(card2);
        deck.add(card3);
        deck1.add(card4);
        deck1.add(card5);
        deck1.add(card6);
        assertEquals(0, ThreeCardLogic.compareHands(deck, deck1, ""));
    }

    @Test
    void compareHandsTest_SimpleCards2(){
        Deck deck = new Deck();
        Deck deck1 = new Deck();
        deck.clear();
        deck1.clear();
        Card card1 = new Card('H', 2);
        Card card2 = new Card('S', 8);
        Card card3 = new Card('D', 11);
        Card card4= new Card('H', 3);
        Card card5 = new Card('S', 2);
        Card card6 = new Card('S', 7);
        deck.add(card1);
        deck.add(card2);
        deck.add(card3);
        deck1.add(card4);
        deck1.add(card5);
        deck1.add(card6);
        assertEquals(0, ThreeCardLogic.compareHands(deck, deck1, ""));
    }

    @Test
    void compareHandsTest_SimpleCards3(){
        Deck deck = new Deck();
        Deck deck1 = new Deck();
        deck.clear();
        deck1.clear();
        Card card1 = new Card('H', 2);
        Card card2 = new Card('S', 8);
        Card card3 = new Card('D', 11);
        Card card4= new Card('H', 2);
        Card card5 = new Card('S', 8);
        Card card6 = new Card('S', 11);
        deck.add(card1);
        deck.add(card2);
        deck.add(card3);
        deck1.add(card4);
        deck1.add(card5);
        deck1.add(card6);
        assertEquals(0, ThreeCardLogic.compareHands(deck, deck1, ""));
    }

    @Test
    void compareHandsTest_FlushAndStraight(){
        Deck deck = new Deck();
        Deck deck1 = new Deck();
        deck.clear();
        deck1.clear();
        Card card1 = new Card('S', 8);
        Card card2 = new Card('S', 9);
        Card card3 = new Card('S', 10);
        Card card4= new Card('H', 6);
        Card card5 = new Card('D', 7);
        Card card6 = new Card('S', 8);
        deck.add(card1);
        deck.add(card2);
        deck.add(card3);
        deck1.add(card4);
        deck1.add(card5);
        deck1.add(card6);
        assertEquals(1, ThreeCardLogic.compareHands(deck, deck1, ""));
    }  

     @Test
    void compareHandsTest_FlushAndStraight2(){
        Deck deck = new Deck();
        Deck deck1 = new Deck();
        deck.clear();
        deck1.clear();
        Card card1 = new Card('H', 2);
        Card card2 = new Card('H', 3);
        Card card3 = new Card('H', 4);
        Card card4= new Card('H', 6);
        Card card5 = new Card('D', 7);
        Card card6 = new Card('S', 8);
        deck.add(card1);
        deck.add(card2);
        deck.add(card3);
        deck1.add(card4);
        deck1.add(card5);
        deck1.add(card6);
        assertEquals(1, ThreeCardLogic.compareHands(deck, deck1, ""));
    }  

    @Test
    void testEvalHandStraight(){
        Deck deck = new Deck();
        deck.clear();
        Card card = new Card('S', 7);
        Card card2 = new Card('H', 8);
        Card card3 = new Card('H', 9);
        deck.add(card);
        deck.add(card2);
        deck.add(card3);
        assertEquals(3, ThreeCardLogic.evalHand(deck));
    }  

     @Test
    void testEvalHandPair(){
        Deck deck = new Deck();
        deck.clear();
        Card card = new Card('S', 7);
        Card card2 = new Card('H', 7);
        Card card3 = new Card('H', 9);
        deck.add(card);
        deck.add(card2);
        deck.add(card3);
        assertEquals(5, ThreeCardLogic.evalHand(deck));
    }  


     @Test
    void testEvalHandFlush(){
        Deck deck = new Deck();
        deck.clear();
        Card card = new Card('H', 7);
        Card card2 = new Card('H', 7);
        Card card3 = new Card('H', 9);
        deck.add(card);
        deck.add(card2);
        deck.add(card3);
        assertEquals(4, ThreeCardLogic.evalHand(deck));
    }  

    @Test
    void testEvalHandThreeOfAKind(){
        Deck deck = new Deck();
        deck.clear();
        Card card = new Card('H', 7);
        Card card2 = new Card('H', 7);
        Card card3 = new Card('H', 9);
        deck.add(card);
        deck.add(card2);
        deck.add(card3);
        assertEquals(4, ThreeCardLogic.evalHand(deck));
    }

        @Test
        void testEvalHandStraightFlush(){
             Deck deck = new Deck();
        deck.clear();
        Card card = new Card('H', 7);
        Card card2 = new Card('H', 8);
        Card card3 = new Card('H', 9);
        deck.add(card);
        deck.add(card2);
        deck.add(card3);
        assertEquals(1, ThreeCardLogic.evalHand(deck));
        }



    }  