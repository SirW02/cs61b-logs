import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offBy3 = new OffByN(3);

    // Your tests go here.
    @Test
    public void testOffByN() {
        assertTrue(offBy3.equalChars('a','d'));
        assertTrue(offBy3.equalChars('f','c'));
        assertFalse(offBy3.equalChars('a','e'));
        assertFalse(offBy3.equalChars('b','b'));
        assertTrue(offBy3.equalChars('p','s'));
        assertFalse(offBy3.equalChars('d','E'));
    }
}
