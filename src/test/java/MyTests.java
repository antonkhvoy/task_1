import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.example.ExpressionParser.*;

public class MyTests {
    @Test
    void testIsOperator() {
        Assertions.assertTrue(isOperator("+"));
        Assertions.assertTrue(isOperator("-"));
        Assertions.assertTrue(isOperator("*"));
        Assertions.assertTrue(isOperator("/"));
        Assertions.assertFalse(isOperator("5"));
        Assertions.assertFalse(isOperator("x"));
    }

    @Test
    void testPriority() {
        Assertions.assertEquals(1, priority("+"));
        Assertions.assertEquals(1, priority("-"));
        Assertions.assertEquals(2, priority("*"));
        Assertions.assertEquals(2, priority("/"));
        Assertions.assertEquals(3, priority("5"));
        Assertions.assertEquals(3, priority("x"));
    }

    @Test
    void testParse() {
        List<String> expected = Arrays.asList("2", "3", "4", "*", "+");
        Assertions.assertEquals(expected, parse("2 + 3 * 4"));

        expected = Arrays.asList("2", "3", "+", "4", "+", "5", "+");
        Assertions.assertEquals(expected, parse("2 + 3 + 4 + 5"));

        expected = Arrays.asList("10", "4", "-", "3", "-", "2", "-");
        Assertions.assertEquals(expected, parse("10 - 4 - 3 - 2"));

        expected = Arrays.asList("2", "3", "*", "4", "5", "*", "+");
        Assertions.assertEquals(expected, parse("2 * 3 + 4 * 5"));

        expected = Arrays.asList("12", "3", "/", "4", "*", "8", "2", "/", "+");
        Assertions.assertEquals(expected, parse("12 / 3 * 4 + 8 / 2"));
    }
}
