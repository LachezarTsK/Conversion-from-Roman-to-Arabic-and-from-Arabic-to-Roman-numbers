package conversionCalculator.ConvertNumbers;

import java.util.Map;

import conversionCalculator.DataProcessing.ValuesAndSymbols;
import conversionCalculator.Regex.RegexAndChecks;

/**
 * Implements an application for converting a Roman number to an Arabic one.
 * 
 * Input from I to MMMMCMXCIX.
 * 
 * @author Lachezar Kutsarov
 */
public class ConvertRomanToArabic {
	private RegexAndChecks regexAndChecks = new RegexAndChecks();
	private ValuesAndSymbols valuesAndSymbols = new ValuesAndSymbols();
	private Map<Character, Integer> romanToArabic = valuesAndSymbols.getRomanToArabic();
	private Map<String, Integer> validCouplesForSubtraction = valuesAndSymbols.getSubtractRomanToArabic();

	/**
	 * Calculates the Arabic value of a Roman number.
	 * 
	 * @throws Exception
	 *             if Roman number s not valid.
	 * 
	 * @return Arabic number as an integer.
	 */
	public int convertRomanNumberToArabic(String romanNumber) throws Exception {

		int result = 0;
		if (!regexAndChecks.validRomanNumber_from_1_to_4999(romanNumber)) {
			throw new Exception("Invalid Input for Roman Numerals!");
		}

		/**
		 * If Roman number consists of one symbol.
		 */
		int numberOfSymbols = romanNumber.length();
		if (numberOfSymbols == 1) {
			result = romanToArabic.get(romanNumber.charAt(0));
			return result;
		}

		/**
		 * If Roman number consists of more than one symbol.
		 *
		 * "i <= numberOfSymbols - 2" for taking account that the last two symbols could
		 * form a subtraction.
		 */
		for (int i = 0; i <= numberOfSymbols - 2; i++) {

			char currentSymbol = romanNumber.charAt(i);
			char nextSymbol = romanNumber.charAt(i + 1);
			int currentValue = romanToArabic.get(currentSymbol);
			int nextValue = romanToArabic.get(nextSymbol);

			/**
			 * Checks for possible subtraction.
			 * 
			 * If true, adds the subtraction value to the result.
			 */
			if (validCouplesForSubtraction.containsKey(currentSymbol + "" + nextSymbol)) {
				result += nextValue - currentValue;
				i++;

				/**
				 * If the two subtraction symbols immediately precedes the last Roman symbol.
				 * 
				 * Then:
				 * 
				 * Adds the value of the last Roman symbol.
				 */
				if (i == numberOfSymbols - 2) {
					char endSymbol = romanNumber.charAt(i + 1);
					int endValue = romanToArabic.get(endSymbol);
					result += endValue;
				}

			} /**
				 * If no subtraction is possible, adds value of current symbol.
				 */
			else {
				result += currentValue;

				/**
				 * If the current symbol immediately precedes the last Roman symbol.
				 * 
				 * Then:
				 * 
				 * Adds the value of the last Roman symbol.
				 */
				if (i == numberOfSymbols - 2) {
					int endValue = nextValue;
					result += endValue;
				}
			}
		}
		return result;
	}
}
