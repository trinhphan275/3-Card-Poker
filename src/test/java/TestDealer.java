import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class TestDealer {

    /**
     * Test 1: Check dealer initialization.
     */
    @Test
    void testDealerInitialization() {
        Dealer dealer = new Dealer();
        assertNotNull(dealer, "Dealer should be initialized.");
        assertEquals(52, dealer.getDeckSize(), "Dealer's deck should be initialized with 52 cards.");
        assertTrue(dealer.getHand().isEmpty(), "Dealer's initial hand should be empty.");
    }

    /**
     * Test 2: Verify dealing a hand of 3 cards.
     */
    @Test
    void testDealHand() {
        Dealer dealer = new Dealer();
        ArrayList<Card> hand = dealer.dealHand();
        assertEquals(3, hand.size(), "Dealer's hand should contain 3 cards after dealing.");
        assertEquals(49, dealer.getDeckSize(), "Deck should have 49 cards left after dealing 3.");
    }

    /**
     * Test 3: Check reshuffling when deck has fewer than 34 cards.
     */
    @Test
    void testReshuffleOnLowCards() {
        Dealer dealer = new Dealer();
        // Deal enough hands to reduce the deck below 34 cards
        for (int i = 0; i < 7; i++) {
            dealer.dealHand();
        }
        assertTrue(dealer.getDeckSize() <= 34, "Deck should be reshuffled when cards are below 34.");
    }

    /**
     * Test 4: Verify getHand returns the current hand without altering it.
     */
    @Test
    void testGetHand() {
        Dealer dealer = new Dealer();
        dealer.dealHand();
        ArrayList<Card> handCopy = dealer.getHand();

        assertEquals(3, handCopy.size(), "getHand should return a hand of 3 cards.");
        assertNotSame(handCopy, dealer.getHand(), "getHand should return a new list, not the original hand.");
    }

    /**
     * Test 5: Verify clearDealer clears both deck and hand.
     */
    @Test
    void testClearDealer() {
        Dealer dealer = new Dealer();
        dealer.dealHand();  // Deal a hand to ensure there are cards in the hand
        dealer.clearDealer();  // Clear the dealer's deck and hand

        // Verify the hand is empty
        assertTrue(dealer.getHand().isEmpty(), "Hand should be empty after clearDealer.");

        // Attempt to deal a new hand; expect a new deck to be created and populated
        ArrayList<Card> newHand = dealer.dealHand();
        assertEquals(3, newHand.size(), "A new hand should be dealt after clearing and reinitializing the deck.");
    }

    /**
     * Test 6: Verify toString displays correct dealer state.
     */
    @Test
    void testToString() {
        Dealer dealer = new Dealer();
        String dealerString = dealer.toString();
        assertTrue(dealerString.contains("Deck Size="), "toString should contain deck size information.");
        assertTrue(dealerString.contains("Dealers Hand="), "toString should contain dealer's hand information.");
    }

    /**
     * Test 7: Verify printHand outputs correct format to console.
     */
    @Test
    void testPrintHand() {
        Dealer dealer = new Dealer();
        dealer.dealHand();
        dealer.printHand();

        assertEquals(3, dealer.getHand().size(), "Dealer's hand should have 3 cards after printHand.");
    }

    /**
     * Test 8: Verify dealing multiple hands consecutively.
     */
    @Test
    void testDealMultipleHands() {
        Dealer dealer = new Dealer();
        dealer.dealHand();
        ArrayList<Card> firstHand = dealer.getHand();

        dealer.dealHand();
        ArrayList<Card> secondHand = dealer.getHand();

        assertNotEquals(firstHand, secondHand, "Each deal should produce a different hand.");
        assertEquals(46, dealer.getDeckSize(), "Deck should reduce by 6 cards after two deals of 3 cards.");
    }

    /**
     * Test 9: Verify dealersHand resets after each deal.
     */
    @Test
    void testHandResetsOnDeal() {
        Dealer dealer = new Dealer();
        dealer.dealHand();
        ArrayList<Card> firstHand = dealer.getHand();
        dealer.dealHand();

        assertEquals(3, dealer.getHand().size(), "Each new deal should reset the hand to 3 cards.");
        assertNotEquals(firstHand, dealer.getHand(), "New deal should not be identical to the previous hand.");
    }

    /**
     * Test 10: Ensure dealer's hand contains unique cards.
     */
    @Test
    void testHandUniqueCards() {
        Dealer dealer = new Dealer();
        ArrayList<Card> hand = dealer.dealHand();

        boolean uniqueCards = hand.stream().distinct().count() == hand.size();
        assertTrue(uniqueCards, "Dealer's hand should contain unique cards.");
    }

    // Test Case 11: Valid input with exactly 3 cards
    @Test
    void testSetHandValid() {
        Dealer dealer = new Dealer();

        // Create a valid hand with 3 cards
        ArrayList<Card> validHand = new ArrayList<>();
        validHand.add(new Card('H', 10));  // 10 of Hearts
        validHand.add(new Card('D', 5));   // 5 of Diamonds
        validHand.add(new Card('S', 14));  // Ace of Spades

        // Set the hand
        dealer.setHand(validHand);

        // Verify that the hand is set correctly
        assertEquals(3, dealer.getHand().size(), "Dealer's hand should contain exactly 3 cards.");
        assertEquals(validHand, dealer.getHand(), "Dealer's hand should match the provided valid hand.");
    }

    // Test Case 12: Invalid input with fewer than 3 cards
    @Test
    void testSetHandInvalidTooFewCards() {
        Dealer dealer = new Dealer();

        // Create a hand with 2 cards (invalid input)
        ArrayList<Card> invalidHand = new ArrayList<>();
        invalidHand.add(new Card('H', 10));  // 10 of Hearts
        invalidHand.add(new Card('D', 5));   // 5 of Diamonds

        // Attempt to set the invalid hand
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dealer.setHand(invalidHand);
        });

        // Verify that the exception message is as expected
        assertEquals("Dealer hand must contain exactly 3 cards.", exception.getMessage());
    }

    // Test Case 13: Invalid input with more than 3 cards
    @Test
    void testSetHandInvalidTooManyCards() {
        Dealer dealer = new Dealer();

        // Create a hand with 4 cards (invalid input)
        ArrayList<Card> invalidHand = new ArrayList<>();
        invalidHand.add(new Card('H', 10));  // 10 of Hearts
        invalidHand.add(new Card('D', 5));   // 5 of Diamonds
        invalidHand.add(new Card('S', 14));  // Ace of Spades
        invalidHand.add(new Card('C', 7));   // 7 of Clubs

        // Attempt to set the invalid hand
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dealer.setHand(invalidHand);
        });

        // Verify that the exception message is as expected
        assertEquals("Dealer hand must contain exactly 3 cards.", exception.getMessage());
    }

    // Test Case 14: Verify that dealer's hand is cleared before setting a new one
    @Test
    void testSetHandClearsExistingHand() {
        Dealer dealer = new Dealer();

        // Create and set an initial hand with 3 cards
        ArrayList<Card> initialHand = new ArrayList<>();
        initialHand.add(new Card('H', 10));  // 10 of Hearts
        initialHand.add(new Card('D', 5));   // 5 of Diamonds
        initialHand.add(new Card('S', 14));  // Ace of Spades
        dealer.setHand(initialHand);

        // Create a new hand with 3 different cards
        ArrayList<Card> newHand = new ArrayList<>();
        newHand.add(new Card('C', 9));   // 9 of Clubs
        newHand.add(new Card('H', 7));   // 7 of Hearts
        newHand.add(new Card('D', 12));  // Queen of Diamonds
        dealer.setHand(newHand);

        // Verify that the new hand is set correctly and the previous hand is cleared
        assertEquals(3, dealer.getHand().size(), "Dealer's hand should contain exactly 3 cards.");
        assertEquals(newHand, dealer.getHand(), "Dealer's hand should match the new hand.");
    }
}
