package analysis;
import junit.framework.TestCase;
import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;

public class TestShapeClassifier {

	private final String YES = "Yes";
	private final String NO = "No";

	private ShapeClassifier shapeClassifier; 
	private String result;

	@Before
    public void setUp() {
    	// Spawn a new ShapeClassifier instance
        shapeClassifier = new ShapeClassifier();
    }

    @After
    public void tearDown() {
    	// Reset ShapeClassifier
        shapeClassifier = null;
    }

    @Test
    public void testBaselinePath() {
		result = shapeClassifier.evaluateGuess("Rectangle,Large,Yes,100,20,100,20");
        assertEquals(YES, result);
    }

    @Test
    public void testFlipLine23() {
		result = shapeClassifier.evaluateGuess(",Large,Yes,100,20,100,20");
        assertEquals(NO, result);
    }

    @Test
    public void testFlipLine26() {
		result = shapeClassifier.evaluateGuess("Rectangle,,Yes,100,20,100,20");
        assertEquals(NO, result);
    }

    @Test
    public void testFlipLine29() {
		result = shapeClassifier.evaluateGuess("Rectangle,Large,,100,20,100,20");
        assertEquals(NO, result);
    }

    @Test
    public void testFlipLine85() {
        result = shapeClassifier.evaluateGuess("Rectangle,Large,No,101,21,101,21");
        assertEquals(NO, result);
    }

    @Test
    public void testFlipLine88() {
        result = shapeClassifier.evaluateGuess("Rectangle,Large,Yes,101,21,101,21");
        assertEquals(NO, result);
    }

    @Test
    public void testFlipLine95() {
        result = shapeClassifier.evaluateGuess("Rectangle,Large,Yes,101,21,101,21");
        assertEquals(NO, result);
    }

    // StackOverflow Reference: http://stackoverflow.com/questions/309396/java-how-to-test-methods-that-call-system-exit
    // @Test
    // public void testFlipLine102() {
    //    for (int i=0; i<3;i++) {
    //        result = shapeClassifier.evaluateGuess("Rectangle,Large,Yes,101,21,101,21");
    //    }
        // TODO: Figure out a way to check System Exit in JUnit
    //}
}
