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
}
