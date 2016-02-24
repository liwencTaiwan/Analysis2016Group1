package analysis;

public class WhiteBoxShapeClassifier {
	private int badGuesses; 
	private String[] threeParamGuesses = {"Equilateral", "Isosceles", "Scalene"};
	private String[] fourParamGuesses = {"Rectangle", "Square"};
	private String[] twoParamGuesses = {"Circle", "Ellipse", "Line"};

	public WhiteBoxShapeClassifier() {
		badGuesses = 0;
	}

	// return Yes if the guess is correct, No otherwise
	public String evaluateGuess(String arg) {

		String shapeGuessResult = "";
		Integer[] parameters = getParams(arg);
		String shapeGuess = getShapeGuess(arg);
		String sizeGuess = getSizeGuess(arg);
		String evenOddGuess = getEvenOddGuess(arg);
		int calcPerim = 0;

		if (shapeGuess == null) {
			System.out.println("Line 23: True");
			shapeGuess = "";
		}
		else {
			System.out.println("Line 23: False");
		}

		if (sizeGuess == null) {
			System.out.println("Line 26: True");
			sizeGuess = "";
		}
		else {
			System.out.println("Line 26: False");
		}

		if (evenOddGuess == null) {
			System.out.println("Line 29: True");
			evenOddGuess = "";
		}
		else {
			System.out.println("Line 29: False");
		}


		switch (parameters.length) {
		case 1:
			System.out.println("Line 33: Case 1");
			if (shapeGuess.equals("Line")) {
				System.out.println("Line 35: True");
				shapeGuessResult = shapeGuess;
				calcPerim = parameters[0];
			} else {
				System.out.println("Line 35: False");
			}
			break; 
		case 2: 
			System.out.println("Line 33: Case 2");
			shapeGuessResult = classify2Parameters(parameters[0], parameters[1]);
			if (shapeGuessResult.equals("Ellipse")) {
				System.out.println("Line 42: True");
				calcPerim = calculateEllipsePerimeter(parameters[0],parameters[1]);
			}
			else {
				System.out.println("Line 42: False");
				calcPerim = calculateCirclePerimeter(parameters[0]);
			}
			break;
		case 3:
			System.out.println("Line 33: Case 3");
			shapeGuessResult = classify3Parameters(parameters[0], parameters[1],parameters[2]);
			calcPerim = calculateTrianglePerimeter(parameters[1], parameters[1],parameters[2]);
			break;
		case 4:
			System.out.println("Line 33: Case 4");
			shapeGuessResult = classify4Parameters(parameters[0], parameters[1],parameters[2], parameters[3]);
			if (shapeGuessResult.equals("Rectangle")) {
				System.out.println("Line 55: True");
				// Bug fix: Incorrect order Params being passed into calculateRectanglePerimeter
				calcPerim = calculateRectanglePerimeter(parameters[0], parameters[2],parameters[1], parameters[3]);
			}
			else {
				System.out.println("Line 55: False");
				calcPerim = calculateRectanglePerimeter(parameters[0], parameters[1],parameters[2], parameters[3]);
			}
		}

		Boolean isShapeGuessCorrect = null;
		Boolean isSizeGuessCorrect = null;
		Boolean isEvenOddCorrect = null;

		// check the shape guess
		if (shapeGuessResult.equals(shapeGuess)) {
			System.out.println("Line 68: True");
			isShapeGuessCorrect = true;
		}
		else {
			System.out.println("Line 68: False");
			isShapeGuessCorrect = false;
		}

		// check the size guess

		if (calcPerim > 200 && sizeGuess.equals("Large")) {
			System.out.println("Line 75: True");
			isSizeGuessCorrect = true;
		}
		else {
			System.out.println("Line 75: False");
			if (calcPerim < 10 && sizeGuess.equals("Small")) {
				System.out.println("Line 78: True");
				isSizeGuessCorrect = true;	
			}
			else { 
				System.out.println("Line 78: False");
				isSizeGuessCorrect = false;
			}
		}

		if ( 0 == (calcPerim % 2) && evenOddGuess.equals("Yes")) {
			System.out.println("Line 85: True");
			isEvenOddCorrect = true;
		}
		else if ( 0 != (calcPerim % 2) && evenOddGuess.equals("No")) { 
			System.out.println("Line 85: False");
			System.out.println("Line 88: True");
			isEvenOddCorrect = true;
		}
		else { 
			System.out.println("Line 85: False");
			System.out.println("Line 88: False");
			isEvenOddCorrect = false;
		}
		
		if (isShapeGuessCorrect && isSizeGuessCorrect && isEvenOddCorrect) {
			System.out.println("Line 95: True");
			badGuesses=0;
			return "Yes";
		}
		else {
			System.out.println("Line 95: False");
			// too many bad guesses
			badGuesses++;
			if (badGuesses >= 3) {
				System.out.println("Line 102: True");
//				System.out.println("Bad guess limit Exceeded");
				System.exit(1);
			}
			System.out.println("Line 102: False");
			return "No";
		}
	}

	// P = 2 * PI *r
	private int calculateCirclePerimeter(int r) {
		return (int) (2 * Math.PI * r);
	}

	// P = 2l + 2w)
	private int calculateRectanglePerimeter(int side1, int side2, int side3, int side4) {
		if (side1 == side2) {

			return (2 * side1) + (2 * side3);
		} 

		else if (side2 == side3) {
			return (2 * side1) + (2 * side2);
		}

		return 0;
	}

	// P = a + b + c
	private int calculateTrianglePerimeter(int side1, int side2 , int side3) {
		return side1 + side2 + side3;
	}

	// This is an approximation
	// PI(3(a+b) - sqrt((3a+b)(a+3b))
	private int calculateEllipsePerimeter(int a, int b) {
		double da = a, db = b;
		return (int) ((int) Math.PI * (3 * (da+db) - Math.sqrt((3*da+db)*(da+3*db)))); 
	}

	// Transform a string argument into an array of numbers
	private Integer[] getParams(String args) {
		String[] params = args.split(",");
		Integer[] numParams = null;

		numParams = new Integer[params.length-3];
		for (int i=3; i<params.length; i++) {
			numParams[i-3] = Integer.parseInt(params[i]);
		}
		return numParams;		
	}

	// extract the Even/Odd guess
	private String getEvenOddGuess(String args) {
		String[] params = args.split(",");
		return params[2];		
	}

	// extract the size guess
	private String getSizeGuess(String args) {
		String[] params = args.split(",");
		return params[1];		
	}

	// extract the shape guess 
	private String getShapeGuess(String args) {
		String[] params = args.split(",");
		return params[0];
	}

	// classify an two sides 
	private String classify2Parameters(int a, int b) {
		if (a == b) {
			return twoParamGuesses[0];
		}
		else if (a == 0) {
			if (b > 0) {
				return twoParamGuesses[1];
			}
		}
		else if (a > 1) {
			if (b != 0) {
				return twoParamGuesses[1]; 
			}
		}
		return "";
	}

	// Classify four sides
	private String classify4Parameters(int a, int b, int c, int d) {
		if (a == b && c == d) {
			if (a != c) {
				return fourParamGuesses[1];
			}
			else 
				return fourParamGuesses[0];
		}		
		else if (b == d && c == a) {
			return fourParamGuesses[0];
		}
		else if (b == c && a == d) {
			return fourParamGuesses[0];
		}
		return  "";
	}

	// Classify a triangle based on the length of its sides
	private String classify3Parameters(int a, int b, int c) {

		if ( (a < (b+c)) && (b < (a + c)) && (c < (a+b))) {
			if (a == b && b == c) {
				return threeParamGuesses[0]; // Equilateral
			} else if (a!=b && a!=c && b!=c) {
				return threeParamGuesses[2]; // Scalene
			} else {
				return threeParamGuesses[1]; // Isosceles
			}  
		}
		return "";
	}
	
	private static void testEvaluateGuess(String input) {
    	WhiteBoxShapeClassifier shapeClassifier = new WhiteBoxShapeClassifier(); 
		shapeClassifier.evaluateGuess(input);
	}

	public static void main(String[] args) {
		testEvaluateGuess("Circle,Large,Yes,100,100");
	}
	
}

