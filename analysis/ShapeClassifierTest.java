package analysis;

import static org.junit.Assert.*;

import org.junit.Test;

public class ShapeClassifierTest {

	private static final String TODO = "TODO"; // Zhong will fix it!
	private static final String YES = "Yes";
	private static final String NO = "No";
	
	private void testEvaluateGuess(String expectedResult, String input) {
    	ShapeClassifier shapeClassifier = new ShapeClassifier(); 
		String result = shapeClassifier.evaluateGuess("Rectangle,Large,Yes,200,100,200,100");
        assertEquals(expectedResult, result);
	}
	
	@Test
	public void baseline() {
		testEvaluateGuess(YES,"Rectangle,Large,Yes,300,250,300,250");
	}
	
	@Test
	public void line33case1_line35True() {
		testEvaluateGuess(TODO,"Line,Large,Yes,300");
	}
	
	@Test
	public void line33case1_line35False() {
		testEvaluateGuess(TODO,",Large,Yes,300");
	}
	
	@Test
	public void line33case2_line42True() {
		testEvaluateGuess(TODO,"Ellipse,Large,Yes,300,250");
	}
	
	@Test
	public void line33case2_line42False() {
		// Can be anything that's not Ellipse.
		testEvaluateGuess(TODO,"Circle,Large,Yes,300,300");
	}
	
	@Test
	public void line33case3() {
		testEvaluateGuess(TODO,"Triangle,Large,Yes,300,300,300");
	}

	@Test
	public void line33case4_line55False() {
		testEvaluateGuess(TODO,"Square,Large,Yes,300,300,300,300");
	}
	
	@Test
	public void line68False() {
		testEvaluateGuess(TODO,"Asdf,Large,Yes,300");
	}
	
	@Test
	public void line75False_line78True() {
		testEvaluateGuess(TODO,"Line,Small,Yes,4");
	}

	@Test
	public void line75False_line78False() {
		testEvaluateGuess(TODO,"Line,Large,Yes,50");	
	}

}
