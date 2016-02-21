package analysis_G2.analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import analysis.ShapeClassifier;

public class TestAll {

	// change this constant to the actuall file name. Put it under the same folder of this file.
	private static final String GENERATED_CSV = "BlackBox.csv";
	private static final Map<String, String> special = new HashMap<>();

	static {
		special.put("true", "Yes");
		special.put("True", "Yes");
		special.put("false", "No");
		special.put("False", "No");
		special.put("Even_2_TO_100", "4");
		special.put("Odd_2_TO_100", "3");
		special.put("Even_101_TO_4094", "102");
		special.put("Odd_101_TO_4094", "103");
	}

	@Test
	public void test() throws Exception {
		int indexShape = 0, indexSize = 0, indexEvenness = 0, indexP1 = 0, indexP2 = 0, indexP3 = 0, indexP4 = 0;
		final URL testResource = TestAll.class.getResource(GENERATED_CSV);
		BufferedReader reader = new BufferedReader(new FileReader(new File(testResource.getFile())));
		String[] header = reader.readLine().replace(" ", "").split(",");
		for (int i = 0; i < header.length; i++) {
			String cur = header[i];
			if (cur.equals("Param1"))
				indexP1 = i;
			if (cur.equals("Param2"))
				indexP2 = i;
			if (cur.equals("Param3"))
				indexP3 = i;
			if (cur.equals("Param4"))
				indexP4 = i;
			if (cur.equals("Eveness"))
				indexEvenness = i;
			if (cur.equals("Size"))
				indexSize = i;
			if (cur.equals("Shape"))
				indexShape = i;
		}

		int count = 1;
		String line;
		String arg = "";
		while ((line = reader.readLine()) != null) {
			try {
				// System.out.println(line);
				System.out.print(count + ":\t");
				String[] params = line.replace(" ", "").replace("_4095", "4095").split(",");

				arg += params[indexShape]; // shape
				arg += "," + params[indexSize]; // size
				arg += "," + map(params[indexEvenness]); // evenness
				for (int i = indexP1; i < indexP4 - indexP1 + 1; i++) {
					if (!params[i].equalsIgnoreCase("empty")) {
						arg += "," + map(params[i]);
					}
				}

				// System.out.println("arg = " + arg);

				System.out.print(sut().evaluateGuess(arg) + "\t");
			} catch (Throwable e) {
				System.out.print("Error\t");
			}

			count++;
			System.out.println(arg);
			arg = "";
		}

		reader.close();
	}

	private String map(String string) {
		if (special.containsKey(string))
			return special.get(string);
		else
			return string;
	}

	private ShapeClassifier sut() {
		return new ShapeClassifier();
	}

}
