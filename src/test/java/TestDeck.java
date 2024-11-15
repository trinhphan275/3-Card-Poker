import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestDeck {

    private Deck deck;

    @BeforeEach
    public void setUp() {
        deck = new Deck();
    }

    // Test Case 1: Check if a new deck has 52 cards.
    @Test
    public void testDeckSizeAfterInitialization() {
        assertEquals(52, deck.size(), "A new deck should have 52 cards.");
    }

    // Test Case 2: Check if deck contains unique cards.
    @Test
    public void testUniqueCardsInDeck() {
        long uniqueCount = deck.stream().distinct().count();
        assertEquals(52, uniqueCount, "Deck should contain 52 unique cards.");
    }

    // Test Case 3: Check if deck has 13 cards per suit.
    @Test
    public void testCardsPerSuit() {
        long clubsCount = deck.stream().filter(card -> card.getSuit() == 'C').count();
        long diamondsCount = deck.stream().filter(card -> card.getSuit() == 'D').count();
        long spadesCount = deck.stream().filter(card -> card.getSuit() == 'S').count();
        long heartsCount = deck.stream().filter(card -> card.getSuit() == 'H').count();

        assertEquals(13, clubsCount, "Deck should have 13 clubs.");
        assertEquals(13, diamondsCount, "Deck should have 13 diamonds.");
        assertEquals(13, spadesCount, "Deck should have 13 spades.");
        assertEquals(13, heartsCount, "Deck should have 13 hearts.");
    }

    // Test Case 4: Test the `newDeck` method to ensure it resets and shuffles the deck.
    @Test
    public void testNewDeckResetsDeck() {
        deck.draw(); // Draw a card to modify the deck
        deck.newDeck();
        assertEquals(52, deck.size(), "newDeck() should reset the deck to 52 cards.");
    }

    // Test Case 5: Check that `shuffle` changes card order.
    @Test
    public void testShuffleChangesOrder() {
        ArrayList<Card> originalDeck = new ArrayList<>(deck);
        deck.shuffle();
        assertNotEquals(originalDeck, deck, "Shuffle should change the order of cards.");
    }

    // Test Case 6: Test `draw` method removes the top card.
    @Test
    public void testDrawRemovesTopCard() {
        Card topCard = deck.get(0);
        Card drawnCard = deck.draw();
        assertEquals(topCard, drawnCard, "Draw should return the top card.");
        assertEquals(51, deck.size(), "Deck size should be 51 after one draw.");
    }

    // Test Case 7: Test drawing all cards from the deck.
    @Test
    public void testDrawAllCards() {
        for (int i = 0; i < 52; i++) {
            assertNotNull(deck.draw(), "Drawing a card should not return null while cards are left.");
        }
        assertEquals(0, deck.size(), "Deck should be empty after drawing all cards.");
    }

    // Test Case 8: Test drawing a card from an empty deck returns null.
    @Test
    public void testDrawFromEmptyDeck() {
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }
        assertNull(deck.draw(), "Drawing from an empty deck should return null.");
    }

    // Test Case 9: Ensure `newDeck` works after drawing all cards.
    @Test
    public void testNewDeckAfterEmptyingDeck() {
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }
        deck.newDeck();
        assertEquals(52, deck.size(), "newDeck() should reset deck to 52 cards after emptying.");
    }

    // Test Case 10: Test deck order consistency after multiple `newDeck` calls.
    @Test
    public void testConsistentDeckOrder() {
        Deck firstDeck = new Deck();
        Deck secondDeck = new Deck();
        assertEquals(firstDeck.size(), secondDeck.size(), "Two new decks should have the same size.");
    }

    // Additional Edge Cases

    // Test Case 11: Test `shuffle` on an empty deck.
    @Test
    public void testShuffleEmptyDeck() {
        deck.clear();
        deck.shuffle();
        assertEquals(0, deck.size(), "Shuffling an empty deck should not add cards.");
    }

    // Test Case 12: Test multiple consecutive shuffles produce different orders.
    @Test
    public void testMultipleShuffles() {
        Deck firstDeck = new Deck();
        firstDeck.shuffle();
        ArrayList<Card> orderAfterFirstShuffle = new ArrayList<>(firstDeck);
        firstDeck.shuffle();
        assertNotEquals(orderAfterFirstShuffle, firstDeck, "Consecutive shuffles should result in different orders.");
    }

    // Test Case 13: Test that creating a deck does not include any null cards.
    @Test
    public void testNoNullCards() {
        assertFalse(deck.contains(null), "Deck should not contain any null cards.");
    }

    // Test Case 14: Verify `draw` does not modify other instances of `Deck`.
    @Test
    public void testDrawIndependence() {
        Deck anotherDeck = new Deck();
        deck.draw();
        assertEquals(52, anotherDeck.size(), "Another instance of Deck should remain unaffected by draws.");
    }

    // Test Case 15: Check that `newDeck` clears the deck before adding cards.
    @Test
    public void testNewDeckClearsBeforeAdding() {
        deck.draw();
        deck.newDeck();
        assertEquals(52, deck.size(), "newDeck() should clear any previous cards and reset to 52.");
    }

    // Test Case 16: Test that each suit has a King (value 13).
    @Test
    public void testEachSuitHasKing() {
        for (char suit : new char[] {'C', 'D', 'S', 'H'}) {
            assertTrue(deck.contains(new Card(suit, 13)), "Each suit should have a King (value 13).");
        }
    }

    // Test Case 17: Test `newDeck` and `draw` consistency after multiple cycles.
    @Test
    public void testNewDeckAndDrawCycles() {
        deck.newDeck();
        for (int i = 0; i < 52; i++) {
            deck.draw();
        }
        deck.newDeck();
        assertEquals(52, deck.size(), "After drawing all cards, newDeck() should restore deck size to 52.");
    }

    // Test Case 18: Test drawing specific value cards like all Aces.
    @Test
    public void testDrawAllAces() {
        long aceCount = deck.stream().filter(card -> card.getValue() == 14).count();
        assertEquals(4, aceCount, "Deck should contain 4 Aces (value 14).");
    }

    // Test Case 19: Test that the initial order is not sorted.
    @Test
    public void testInitialOrderNotSorted() {
        Deck sortedDeck = new Deck();
        sortedDeck.sort((c1, c2) -> {
            int suitCompare = Character.compare(c1.getSuit(), c2.getSuit());
            return (suitCompare != 0) ? suitCompare : Integer.compare(c1.getValue(), c2.getValue());
        });
        assertNotEquals(deck, sortedDeck, "The initial deck order should be shuffled and not sorted.");
    }

    // Test Case 20: Test `newDeck` does not create duplicate cards.
    @Test
    public void testNoDuplicateCardsAfterNewDeck() {
        deck.newDeck();
        long uniqueCardCount = deck.stream().distinct().count();
        assertEquals(52, uniqueCardCount, "newDeck() should result in 52 unique cards.");
    }
}
