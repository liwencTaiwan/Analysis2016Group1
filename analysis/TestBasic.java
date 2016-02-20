package analysis;
import junit.framework.TestCase;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestBasic {

    @Test
    public void testBasic() {
    	ShapeClassifier shapeClassifier = new ShapeClassifier(); 

		String result = shapeClassifier.evaluateGuess("Equilateral,Large,Yes,100,100,100");
        String expectedResult = "Yes";
        assertEquals(expectedResult, result);
    }
}
