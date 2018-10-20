package conversionCalculator.ConvertNumbers;

import java.util.Map;

import conversionCalculator.DataProcessing.ValuesAndSymbols;
import conversionCalculator.Regex.RegexAndChecks;

/**
 * Implements an application for converting an Arabic number to an Roman one.
 * 
 * Input from 1 to 4999.
 * 
 * Leading zeros are allowed. Example: 0010, 0345, 00001000.
 * 
 * @author Lachezar Kutsarov
 */
public class ConvertArabicToRoman {
	private RegexAndChecks regexAndChecks = new RegexAndChecks();
	private ValuesAndSymbols valuesAndSymbols = new ValuesAndSymbols();
	private ConvertRomanToArabic convertRomanToArabic = new ConvertRomanToArabic();
	private Map<Integer, Character> arabicToRoman = valuesAndSymbols.getArabicToRoman();
	private Map<Integer, String> keyAdditions_ArabicToRoman = valuesAndSymbols
			.getKeyCombinationsForAddition_ArabicToRoman();
	private Map<Integer, String> subtractArabicToRoman = valuesAndSymbols.getSubtractArabicToRoman();

	/**
	 * Converts an Arabic number from 1 to 4999 to a Roman number.
	 * 
	 * @throws Exception
	 *             if the input is not within the range from 1 to 4999.
	 * 
	 *             if in the process of formation of the Roman number, two, three or
	 *             four identical Roman symbols can not be converted back to an
	 *             Arabic number.
	 * 
	 * @param String:
	 *            Arabic number.
	 * 
	 * @return String: Roman number.
	 */
	public String convertArabicNumberToRoman(String arabicNumber) throws Exception {
		checkValidInput(arabicNumber);
		StringBuilder result = new StringBuilder();
		StringBuilder valueToBeConverted = new StringBuilder();

		for (int i = 0; i < arabicNumber.length(); i++) {

			char currentDigit = arabicNumber.charAt(i);
			/**
			 * If current digit is different from 0, takes the current digit and adds 0 for
			 * every digit after it.
			 * 
			 * Example with an input number of 345
			 * 
			 * Current digit: 3, valueToBeConverted = 300
			 * 
			 * Current digit: 4, valueToBeConverted = 40
			 * 
			 * Current digit: 5, valueToBeConverted = 5
			 */
			if (currentDigit != '0') {
				valueToBeConverted = new StringBuilder(Character.toString(currentDigit));
				for (int j = i + 1; j < arabicNumber.length(); j++) {
					valueToBeConverted.append("0");
				}

				if (checkAndAddBasicRomanSymbol(result, valueToBeConverted))
					;
				else if (checkAndAddTwoTimesSameRomanSymbol(result, valueToBeConverted))
					;
				else if (checkAndAddThreeTimesSameRomanSymbol(result, valueToBeConverted))
					;
				else if (checkAndAddFourTimesSameRomanSymbol(result, valueToBeConverted))
					;
				else if (checkAndAddKeyCombinationsForAddition(result, valueToBeConverted))
					;
				else if (checkAndSubtractTwoRomanSymbols(result, valueToBeConverted))
					;
			}
		}

		return result.toString();
	}

	/**
	 * Checks if valueToBeConverted equals the value of a primary Roman symbols.
	 * 
	 * If true, adds one primary Roman symbol.
	 * 
	 * Primary Roman symbols: I, V, X, L, C, D, M.
	 * 
	 * @return Returns "true" if a basic Roman symbol is added. Otherwise returns
	 *         "false".
	 */
	private boolean checkAndAddBasicRomanSymbol(StringBuilder result, StringBuilder valueToBeConverted) {
		int integerValueToBeConverted = Integer.parseInt(valueToBeConverted.toString());
		String checkValidity = result + "" + arabicToRoman.get(Integer.parseInt(valueToBeConverted.toString()));
		if (regexAndChecks.validRomanNumber_from_1_to_4999(checkValidity)
				&& arabicToRoman.containsKey(integerValueToBeConverted)) {
			result.append(arabicToRoman.get(Integer.parseInt(valueToBeConverted.toString())));
			return true;
		}
		return false;
	}

	/**
	 * Checks if valueToBeConverted equals the value of two identical Roman symbols.
	 * 
	 * If true, adds two identical Roman symbols.
	 *
	 * @throws Exception
	 *             if the two identical Roman symbols can not be converted to an
	 *             Arabic number.
	 * 
	 * @return Returns "true" if two identical Roman symbols are added. Otherwise
	 *         returns "false".
	 */
	private boolean checkAndAddTwoTimesSameRomanSymbol(StringBuilder result, StringBuilder valueToBeConverted)
			throws Exception {
		int integerValueToBeConverted = Integer.parseInt(valueToBeConverted.toString());
		String oneAsLeadingDigit = "1" + valueToBeConverted.substring(1, valueToBeConverted.length());
		String symbol = "" + arabicToRoman.get(Integer.parseInt(oneAsLeadingDigit));
		String twoSymbols = symbol + symbol;
		String checkValidity = result + "" + twoSymbols;

		if (regexAndChecks.validRomanNumber_from_1_to_4999(checkValidity)
				&& convertRomanToArabic.convertRomanNumberToArabic(twoSymbols) == integerValueToBeConverted) {
			result.append(twoSymbols);
			return true;
		}
		return false;
	}

	/**
	 * Checks if valueToBeConverted equals the value of three identical Roman
	 * symbols.
	 * 
	 * If true, adds three identical Roman symbols.
	 *
	 * @throws Exception
	 *             if the three identical Roman symbols can not be converted to an
	 *             Arabic number.
	 * 
	 * @return Returns "true" if three identical Roman symbols are added. Otherwise
	 *         returns "false".
	 */
	private boolean checkAndAddThreeTimesSameRomanSymbol(StringBuilder result, StringBuilder valueToBeConverted)
			throws Exception {
		int integerValueToBeConverted = Integer.parseInt(valueToBeConverted.toString());
		String oneAsLeadingDigit = "1" + valueToBeConverted.substring(1, valueToBeConverted.length());
		String symbol = "" + arabicToRoman.get(Integer.parseInt(oneAsLeadingDigit));
		String threeSymbols = symbol + symbol + symbol;
		String checkValidity = result + "" + threeSymbols;

		if (regexAndChecks.validRomanNumber_from_1_to_4999(checkValidity)
				&& convertRomanToArabic.convertRomanNumberToArabic(threeSymbols) == integerValueToBeConverted) {
			result.append(threeSymbols);
			return true;
		}
		return false;
	}

	/**
	 * Checks if valueToBeConverted equals the value of four identical Roman
	 * symbols.
	 * 
	 * If true, adds three identical Roman symbols.
	 *
	 * @throws Exception
	 *             if the four identical Roman symbols can not be converted to an
	 *             Arabic number.
	 * 
	 * @return Returns "true" if three identical Roman symbols are added. Otherwise
	 *         returns "false".
	 */
	private boolean checkAndAddFourTimesSameRomanSymbol(StringBuilder result, StringBuilder valueToBeConverted)
			throws Exception {
		int integerValueToBeConverted = Integer.parseInt(valueToBeConverted.toString());
		String oneAsLeadingDigit = "1" + valueToBeConverted.substring(1, valueToBeConverted.length());
		String symbol = "" + arabicToRoman.get(Integer.parseInt(oneAsLeadingDigit));
		String fourSymbols = symbol + symbol + symbol + symbol;
		String checkValidity = result + "" + fourSymbols;

		if (regexAndChecks.validRomanNumber_from_1_to_4999(checkValidity)
				&& convertRomanToArabic.convertRomanNumberToArabic(fourSymbols) == integerValueToBeConverted) {
			result.append(fourSymbols);
			return true;
		}
		return false;
	}

	/**
	 * Checks if valueToBeConverted equals the value of a Roman symbol(s), which
	 * Arabic equivalent is a number with a digit of 6,7 or 8 followed by one or two
	 * 0, or nothing at all.
	 * 
	 * If true, adds the Roman symbol(s).
	 * 
	 * @return Returns "true" if such Roman symbols are added. Otherwise returns
	 *         "false".
	 */
	private boolean checkAndAddKeyCombinationsForAddition(StringBuilder result, StringBuilder valueToBeConverted) {
		int integerValueToBeConverted = Integer.parseInt(valueToBeConverted.toString());
		if (keyAdditions_ArabicToRoman.containsKey(integerValueToBeConverted)) {
			String additionSymbols = keyAdditions_ArabicToRoman.get(integerValueToBeConverted);
			String checkValidity = result + additionSymbols;
			if (regexAndChecks.validRomanNumber_from_1_to_4999(checkValidity)) {
				result.append(additionSymbols);
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if valueToBeConverted is equal to the subtraction value of two Roman
	 * symbols that form a valid subtraction couple.
	 * 
	 * If true, adds the two Roman symbols.
	 *
	 * @return Returns "true" if such Roman symbols are added. Otherwise returns
	 *         "false".
	 */
	private boolean checkAndSubtractTwoRomanSymbols(StringBuilder result, StringBuilder valueToBeConverted) {
		int integerValueToBeConverted = Integer.parseInt(valueToBeConverted.toString());
		if (subtractArabicToRoman.containsKey(integerValueToBeConverted)) {
			String subtractionSymbols = subtractArabicToRoman.get(integerValueToBeConverted);
			String checkValidity = result + subtractionSymbols;
			if (regexAndChecks.validRomanNumber_from_1_to_4999(checkValidity)) {
				result.append(subtractionSymbols);
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks whether the input contains only a number from 1 to 3999.
	 * 
	 * @throws Exception
	 *             if condition is not fulfilled.
	 */
	private void checkValidInput(String arabicNumber) throws Exception {
		if (!regexAndChecks.validArabicNumber_from_1_to_4999(arabicNumber)) {
			throw new Exception("Input does not contain only digits, or"
					+ "\nArabic number is not in the range from 1 to 4999 (I to MMMMCMXCIX).");
		}
	}
}
