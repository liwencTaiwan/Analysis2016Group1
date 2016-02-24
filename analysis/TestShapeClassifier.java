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
        System.setSecurityManager(new ExitDeniedSecurityManager());
    }

    @After
    public void tearDown() {
    	// Reset ShapeClassifier
        shapeClassifier = null;
        System.setSecurityManager(null);
    }

    @Test
    public void testBaselinePath() {
		result = shapeClassifier.evaluateGuess("Circle,Large,Yes,100,100");
        assertEquals(YES, result);
    }

    @Test
    public void testFlipLine23() {
        // Infeasible Path
		result = shapeClassifier.evaluateGuess(",Large,Yes,100,100");
        assertEquals(NO, result);
    }

    @Test
    public void testFlipLine26() {
        // Infeasible Path
		result = shapeClassifier.evaluateGuess("Circle,,Yes,100,100");
        assertEquals(NO, result);
    }

    @Test
    public void testFlipLine29() {
        // Infeasible Path
		result = shapeClassifier.evaluateGuess("Circle,Large,,100,100");
        assertEquals(NO, result);
    }

    @Test
    public void testFlipLine85() {
        result = shapeClassifier.evaluateGuess("Circle,Large,No,113,113");
        assertEquals(YES, result);
    }

    @Test
    public void testFlipLine88() {
        result = shapeClassifier.evaluateGuess("Circle,Large,No,100,100");
        assertEquals(NO, result);
    }

    @Test
    public void testFlipLine95() {
        result = shapeClassifier.evaluateGuess("Rectangle,Large,Yes,100,100");
        assertEquals(NO, result);
    }

    @Test
    public void testFlipLine95and102() {
        try {
            for (int i=0; i<4; i++) {
                result = shapeClassifier.evaluateGuess("Rectangle,Large,Yes,100,100");
            }
            Assert.fail("Exit was expected");
        } catch (ExitDeniedSecurityManager.ExitSecurityException e) {
            int status = e.getStatus();
            assertEquals(1, status);
        }
    }

    @Test
    public void testFlipLine88and102() {
        try {
            for (int i=0; i<4; i++) {
                result = shapeClassifier.evaluateGuess("Circle,Large,No,100,100");
            }
            Assert.fail("Exit was expected");
        } catch (ExitDeniedSecurityManager.ExitSecurityException e) {
            int status = e.getStatus();
            assertEquals(1, status);
        }
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

