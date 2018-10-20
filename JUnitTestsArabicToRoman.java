package conversionCalculator.JUnitTests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import conversionCalculator.ConvertNumbers.ConvertArabicToRoman;
import conversionCalculator.ConvertNumbers.ConvertRomanToArabic;

/**
 * Implements tests for checking the proper conversion of an Arabic to a Roman
 * number.
 * 
 * @author Lachezar Kutsarov
 */
class JUnitTestsArabicToRoman {

	/**
	 * If hitchhikersGuideToTheGalaxy.JUnitTests.JUnitTestsRomanToArabic.java covers
	 * all possible scenarios of the rules of the challenge for valid Roman numbers
	 * and passes all tests, as it does,
	 * 
	 * then
	 * 
	 * converting an Arabic number to a Roman one, and the resulting Roman number
	 * again to an Arabic number should reveal any errors in the application for
	 * conversion of Arabic to Roman numbers.
	 */
	@Test
	public void arabicToRomanFromMinToMax() throws Exception {
		ConvertArabicToRoman arabicToRoman = new ConvertArabicToRoman();
		ConvertRomanToArabic romanToArabic = new ConvertRomanToArabic();
		boolean convertionSuccessful = true;

		for (int i = 1; i <= 4999; i++) {
			String currentArabic = Integer.toString(i);
			String romanNumber = arabicToRoman.convertArabicNumberToRoman(currentArabic);
			int arabicNumber = romanToArabic.convertRomanNumberToArabic(romanNumber);
			if (arabicNumber != i) {
				convertionSuccessful = false;
				break;
			}
		}
		assertTrue(convertionSuccessful);
	}

	/**
	 * Checks whether the input contains only numbers from 1 to 3999.
	 * 
	 * @throwsException if condition is not fulfilled.
	 */
	@Test
	public void invalidArabic_maxValueIsExceeded() throws Exception {
		ConvertArabicToRoman arabicToRoman = new ConvertArabicToRoman();

		Throwable nonDigits = assertThrows(Exception.class, () -> arabicToRoman.convertArabicNumberToRoman("xyz"));
		Throwable arabic_0 = assertThrows(Exception.class, () -> arabicToRoman.convertArabicNumberToRoman("0"));
		Throwable arabic_5000 = assertThrows(Exception.class, () -> arabicToRoman.convertArabicNumberToRoman("5000"));

		assertEquals(
				"Input does not contain only digits, or"
						+ "\nArabic number is not in the range from 1 to 4999 (I to MMMMCMXCIX).",
				nonDigits.getMessage());
		assertEquals(
				"Input does not contain only digits, or"
						+ "\nArabic number is not in the range from 1 to 4999 (I to MMMMCMXCIX).",
				arabic_0.getMessage());
		assertEquals(
				"Input does not contain only digits, or"
						+ "\nArabic number is not in the range from 1 to 4999 (I to MMMMCMXCIX).",
				arabic_5000.getMessage());
	}

	/**
	 * Checks for numbers with leading zeros.
	 * 
	 * Arabic numbers with leading zeros are allowed.
	 */
	@Test
	public void convertNumbersWithLeadingZeros() throws Exception {

		ConvertArabicToRoman arabicToRoman = new ConvertArabicToRoman();
		String number_0001 = arabicToRoman.convertArabicNumberToRoman("0001");
		String number_01234 = arabicToRoman.convertArabicNumberToRoman("01234");
		String number_00100 = arabicToRoman.convertArabicNumberToRoman("00100");
		assertEquals(number_0001, "I");
		assertEquals(number_01234, "MCCXXXIV");
		assertEquals(number_00100, "C");
	}
}
