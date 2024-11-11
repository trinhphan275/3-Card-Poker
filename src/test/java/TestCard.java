import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.ParameterizedTest;

public class TestCard {

    @Test
    void testGetters(){
        Card card = new Card('H', 12);
        Card card2 = new Card('S', 2);
        assertEquals(card.getSuit(), 'H');
        assertEquals(card.getValue(), 12);
        assertEquals(card2.getSuit(), 'S');
        assertEquals(card2.getValue(), 2);
    }

    @Test
    void testSetters(){
        Card card = new Card('H', 12);
        Card card2 = new Card('S', 2);
        card.setSuit('S');
        card.setValue(1);
        card2.setSuit('H');
        card2.setValue(5);

        assertEquals(card.getSuit(), 'S');
        assertEquals(card.getValue(), 1);
        assertEquals(card2.getSuit(), 'H');
        assertEquals(card2.getValue(), 5);
    }
}