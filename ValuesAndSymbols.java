package conversionCalculator.DataProcessing;

import java.util.HashMap;
import java.util.Map;

/**
 * Implements an application that stores key Roman numerals and their value as
 * Arabic numbers.
 * 
 * Applied in:
 * 
 * conversionCalculator.ConvertNumbers.ConvertArabicToRoman.java
 * conversionCalculator.ConvertNumbers.ConvertRomanToArabic.java
 * 
 * @author Lachezar Kutsarov
 */
public class ValuesAndSymbols {

	/**
	 * romanSymbols[i]=arabicValues[i]
	 * 
	 * X=1, V=5, X=10; L=50; C=100, D=500, M=1000
	 */
	private char[] romanSymbolsSorted = { 'I', 'V', 'X', 'L', 'C', 'D', 'M' };
	private int[] arabicValuesSorted = { 1, 5, 10, 50, 100, 500, 1000 };
	private Map<Character, Integer> romanToArabic;
	private Map<Integer, Character> arabicToRoman;

	/**
	 * ValidCouplesForSubtraction[i]=valueOfSubtraction[i]
	 * 
	 * IV=4, IX=9, XL=40, XC=90, CD=400, CM=900
	 */
	private String[] ValidCouplesForSubtraction = { "IV", "IX", "XL", "XC", "CD", "CM" };
	private int[] valueOfSubtractionSorted = { 4, 9, 40, 90, 400, 900 };
	private Map<String, Integer> subtractRomanToArabic;
	private Map<Integer, String> subtractArabicToRoman;

	/**
	 * keyCombinationsForAddition[i]=valueOfKeyCombinationsForAddition[i]
	 * 
	 * 6, 7, 8 followed by one or two 0, or nothing at all.
	 * 
	 * VI=6, VII=7, VIII=8; LX=60, LXX=70, LXXX=80, DC=600, DCC=700, DCCC=800
	 */
	private String[] keyCombinationsForAddition = { "VI", "VII", "VIII", "LX", "LXX", "LXXX", "DC", "DCC", "DCCC" };
	private int[] valueOfKeyCombinationsForAddition = { 6, 7, 8, 60, 70, 80, 600, 700, 800 };
	private Map<Integer, String> keyCombinationsForAddition_ArabicToRoman;

	private void initializeKeyCombinationsForAddition_ArabicToRoman() {
		keyCombinationsForAddition_ArabicToRoman = new HashMap<Integer, String>();
		for (int i = 0; i < keyCombinationsForAddition.length; i++) {
			keyCombinationsForAddition_ArabicToRoman.put(valueOfKeyCombinationsForAddition[i],
					keyCombinationsForAddition[i]);
		}
	}

	private void initializeArabicToRoman() {
		arabicToRoman = new HashMap<Integer, Character>();
		for (int i = 0; i < arabicValuesSorted.length; i++) {
			arabicToRoman.put(arabicValuesSorted[i], romanSymbolsSorted[i]);
		}
	}

	private void initializeRomanToArabic() {
		romanToArabic = new HashMap<Character, Integer>();
		for (int i = 0; i < romanSymbolsSorted.length; i++) {
			romanToArabic.put(romanSymbolsSorted[i], arabicValuesSorted[i]);
		}
	}

	private void initializeSubtractArabicToRoman() {
		subtractArabicToRoman = new HashMap<Integer, String>();
		for (int i = 0; i < valueOfSubtractionSorted.length; i++) {
			subtractArabicToRoman.put(valueOfSubtractionSorted[i], ValidCouplesForSubtraction[i]);
		}
	}

	private void initializeSubtractRomanToArabic() {
		subtractRomanToArabic = new HashMap<String, Integer>();
		for (int i = 0; i < valueOfSubtractionSorted.length; i++) {
			subtractRomanToArabic.put(ValidCouplesForSubtraction[i], valueOfSubtractionSorted[i]);
		}
	}

	/**
	 * A copy of Map "romanToArabic".
	 * 
	 * Prevents meddling with the actual Map outside the class.
	 */
	public Map<Character, Integer> getRomanToArabic() {
		initializeRomanToArabic();
		Map<Character, Integer> copy = romanToArabic;
		return copy;
	}

	/**
	 * A copy of Map "arabicToRonam".
	 * 
	 * Prevents meddling with the actual Map outside the class.
	 */
	public Map<Integer, Character> getArabicToRoman() {
		initializeArabicToRoman();
		Map<Integer, Character> copy = arabicToRoman;
		return copy;
	}

	/**
	 * A copy of Map "subtractRomanToArabic".
	 * 
	 * Prevents meddling with the actual Map outside the class.
	 */
	public Map<String, Integer> getSubtractRomanToArabic() {
		initializeSubtractRomanToArabic();
		Map<String, Integer> copy = subtractRomanToArabic;
		return copy;
	}

	/**
	 * A copy of Map "subtractArabicToRoman".
	 * 
	 * Prevents meddling with the actual Map outside the class.
	 */
	public Map<Integer, String> getSubtractArabicToRoman() {
		initializeSubtractArabicToRoman();
		Map<Integer, String> copy = subtractArabicToRoman;
		return copy;
	}

	/**
	 * A copy of Map "keyCombinationsForAddition_ArabicToRoman".
	 * 
	 * Prevents meddling with the actual Map outside the class.
	 */
	public Map<Integer, String> getKeyCombinationsForAddition_ArabicToRoman() {
		initializeKeyCombinationsForAddition_ArabicToRoman();
		Map<Integer, String> copy = keyCombinationsForAddition_ArabicToRoman;
		return copy;
	}
}
