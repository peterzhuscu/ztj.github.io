import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class CookieFinderTest {

    @Test
    public void testMostActiveCookiesSingleWinner() {
        List<String> cookies = Arrays.asList("A", "B", "A", "C", "A", "B");
        List<String> result = CookieFinder.mostActiveCookies(cookies);
        assertEquals(1, result.size());
        assertEquals("A", result.get(0));
    }

    @Test
    public void testMostActiveCookiesTie() {
        List<String> cookies = Arrays.asList("A", "B", "A", "B");
        List<String> result = CookieFinder.mostActiveCookies(cookies);
        assertTrue(result.contains("A"));
        assertTrue(result.contains("B"));
        assertEquals(2, result.size());
    }

    @Test
    public void testMostActiveCookiesEmpty() {
        List<String> cookies = new ArrayList<>();
        List<String> result = CookieFinder.mostActiveCookies(cookies);
        assertTrue(result.isEmpty());
    }
}
