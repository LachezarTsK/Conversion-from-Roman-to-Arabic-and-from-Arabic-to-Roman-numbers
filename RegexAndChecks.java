package conversionCalculator.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implements regular expressions and application for checking validity Roman
 * and Arabic numbers from 1 to 4999.
 * 
 * @author Lachezar Lutsarov
 */
public class RegexAndChecks {

	/**
	 * Regular expression: Roman numbers from 1 to 4999.
	 */
	public String regexForValidRomanNumbers() {

		String inputOnlyOf_IVXLCDM = "[^IVXLCDM&&[^\\s]]";

		String onlyOnceEachOf_VLD = "(V.*V)|(L.*L)|(D.*D)";
		String maxThreeOf_I = "(IIII)";
		String maxThreeOf_X_withExceptionOf_XXXIX = "(XXX[^=I]*X)";
		String maxThreeOf_C_withExceptionOf_CCCXC = "(CCC[^=X]*C)";
		String maxFourOf_M_withExceptionOf_MMMMCM = "(MMMM[^=C]*M)";

		String subtractionOf_I_onlyFrom_VX = "(I[LCDM])";
		String subtractionOf_X_onlyFrom_LC = "(X[DM])";
		/**
		 * Implementing regex "subtractionOf_C_onlyFrom_DM" is not necessary.
		 * 
		 * The last three primary symbols by increasing value are C, D and M, i.e. when
		 * C is followed by a symbol with higher value, it can be only D or M.
		 */

		String noSubtractionOf_V = "(V[XLCDM])";
		String noSubtractionOf_L = "(L[CDM])";
		String noSubtractionOf_D = "(DM)";

		String subtractionOf_onlyOneSmallValueSymbol_FromAnyLargeValueSymbol = "(IV.*IX)|(IX.*IV)|(XC.*XL)|(XL.*XC)|(CM.*CD)|(CD.*CM)";

		/**
		 * Regex "onlyFrom_Large_to_SmallValues" complements the rest namely:
		 * 
		 * regex "onlyOnceEachOf_VLD"
		 * 
		 * regex "noSubtractionOf_V",
		 * 
		 * regex "noSubtractionOf_L"
		 * 
		 * regex "noSubtractionOf_D"
		 * 
		 * so that the Roman symbols, taking account of subtractions as well, occur only
		 * from larger to smaller values.
		 */
		String onlyFrom_Large_to_SmallValues = "(IX.*[VXLCDM])|(XC.*[LCDM])|(CM.*[DM])";

		String regex = inputOnlyOf_IVXLCDM + "|" + onlyOnceEachOf_VLD + "|" + maxThreeOf_I + "|"
				+ maxThreeOf_X_withExceptionOf_XXXIX + "|" + maxThreeOf_C_withExceptionOf_CCCXC + "|"
				+ maxFourOf_M_withExceptionOf_MMMMCM + "|" + subtractionOf_I_onlyFrom_VX + "|"
				+ subtractionOf_X_onlyFrom_LC + "|" + noSubtractionOf_V + "|" + noSubtractionOf_V + "|"
				+ noSubtractionOf_L + "|" + noSubtractionOf_D + "|"
				+ subtractionOf_onlyOneSmallValueSymbol_FromAnyLargeValueSymbol + "|" + onlyFrom_Large_to_SmallValues;

		return regex;
	}

	/**
	 * Checks validity of a Roman number from 1 to 4999.
	 * 
	 * @return true if the Roman number is valid.
	 */
	public boolean validRomanNumber_from_1_to_4999(String romanNumber) {
		String regexValidRomanNumbers = regexForValidRomanNumbers();
		Pattern p = Pattern.compile(regexValidRomanNumbers);
		Matcher m = p.matcher(romanNumber);
		boolean validNumber = true;
		if (m.find()) {
			validNumber = false;
		}
		return validNumber;
	}

	/**
	 * Checks validity of an Arabic number from 1 to 4999.
	 * 
	 * @return true if the Arabic number is valid.
	 */
	public boolean validArabicNumber_from_1_to_4999(String arabicNumber) {

		Pattern p = Pattern.compile("\\D");
		Matcher m = p.matcher(arabicNumber);
		boolean validNumber = true;
		if (m.find()) {
			validNumber = false;
		}
		if (validNumber) {
			int number = Integer.parseInt(arabicNumber);
			if (number < 1 || number > 4999) {
				validNumber = false;
			}
		}
		return validNumber;
	}
}
