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
    public void testFlipLine33Case1() {
		result = shapeClassifier.evaluateGuess("Line,Large,Yes,300");
        assertEquals(YES, result);
    }

    @Test
    public void testFlipLine33Case3() {
		result = shapeClassifier.evaluateGuess("Equilateral,Large,Yes,300,300,300");
        assertEquals(YES, result);
    }

    @Test
    public void testFlipLine33Case4() {
		result = shapeClassifier.evaluateGuess("Rectangle,Large,Yes,200,100,100,200");
        assertEquals(YES, result);
    }

    @Test
    public void testFlipLine42() {
		result = shapeClassifier.evaluateGuess("Ellipse,Large,Yes,201,100");
        assertEquals(YES, result);
    }

    @Test
    public void testFlipLine68() {
		result = shapeClassifier.evaluateGuess("Circl,Large,Yes,102,102");
        assertEquals(NO, result);
    }

    @Test
    public void testFlipLine75() {
		result = shapeClassifier.evaluateGuess("Circle,Large,Yes,10,10");
        assertEquals(NO, result);
    }

    @Test
    public void testFlipLine35() {
		result = shapeClassifier.evaluateGuess(",Large,Yes,300");
        assertEquals(NO, result);
    }

    @Test
    public void testFlipLine55() {
		result = shapeClassifier.evaluateGuess("Square,Large,Yes,100,100,100,100");
        assertEquals(YES, result);
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
    
}

