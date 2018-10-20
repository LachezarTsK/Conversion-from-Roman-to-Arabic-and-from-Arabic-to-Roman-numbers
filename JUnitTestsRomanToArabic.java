package conversionCalculator.JUnitTests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import conversionCalculator.ConvertNumbers.ConvertRomanToArabic;

/**
 * Implements tests for checking the proper conversion of a Roman to an Arabic
 * number.
 * 
 * @author Lachezar Kutsarov
 */
class JUnitTestsRomanToArabic {

	/**
	 * Maximum four X only if XXXIX.
	 * 
	 * Maximum four C only if CCCXC.
	 * 
	 * Maximum five M only if MMMMDM.
	 * 
	 */
	@Test
	public void romanToArabic_XXXIX_CCCXC_MMMMDM() throws Exception {
		ConvertRomanToArabic romanToArabic = new ConvertRomanToArabic();
		int roman_XXXIX = romanToArabic.convertRomanNumberToArabic("XXXIX");
		int roman_CCCXC = romanToArabic.convertRomanNumberToArabic("CCCXC");
		int roman_MMMMCM = romanToArabic.convertRomanNumberToArabic("MMMMCM");
		assertEquals(roman_XXXIX, 39);
		assertEquals(roman_CCCXC, 390);
		assertEquals(roman_MMMMCM, 4900);
	}

	/**
	 * Maximum three I in succession.
	 * 
	 * Maximum three X in succession.
	 * 
	 * Maximum three C in succession.
	 * 
	 * Maximum four M in succession.
	 */
	@Test
	public void romanToArabic_III_XXX_CCC_MMMM() throws Exception {
		ConvertRomanToArabic romanToArabic = new ConvertRomanToArabic();
		int roman_III = romanToArabic.convertRomanNumberToArabic("III");
		int roman_XXX = romanToArabic.convertRomanNumberToArabic("XXX");
		int roman_CCC = romanToArabic.convertRomanNumberToArabic("CCC");
		int roman_MMMM = romanToArabic.convertRomanNumberToArabic("MMMM");
		assertEquals(roman_III, 3);
		assertEquals(roman_XXX, 30);
		assertEquals(roman_CCC, 300);
		assertEquals(roman_MMMM, 4000);
	}

	/**
	 * @throws Exception
	 *             if more than three I in succession.
	 * 
	 *             if more than three X in succession.
	 * 
	 *             if more than three C in succession.
	 * 
	 *             if more than four M in succession.
	 * 
	 */
	@Test
	public void invalidRoman_IIII_XXXX_CCCC_MMMMM() throws Exception {
		ConvertRomanToArabic romanToArabic = new ConvertRomanToArabic();
		Throwable roman_IIII = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("IIII"));
		Throwable roman_XXXX = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("XXXX"));
		Throwable roman_CCCC = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("CCCC"));
		Throwable roman_MMMMM = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("MMMMM"));
		assertEquals("Invalid Input for Roman Numerals!", roman_IIII.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_XXXX.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_CCCC.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_MMMMM.getMessage());
	}

	/**
	 * Checks for valid subtraction groups:
	 * 
	 * IV, IX, XL, XC, CD, CM
	 * 
	 * Tests for CD and CM not included, as it is not necessary.
	 * 
	 * The last three primary symbols by increasing value are C, D and M, i.e. when
	 * C is followed by a symbol with higher value, it can be only D or M.
	 * 
	 */
	@Test
	public void romanToArabic_IV_IX_XL_XC() throws Exception {
		ConvertRomanToArabic romanToArabic = new ConvertRomanToArabic();
		int roman_IV = romanToArabic.convertRomanNumberToArabic("IV");
		int roman_IX = romanToArabic.convertRomanNumberToArabic("IX");
		int roman_XL = romanToArabic.convertRomanNumberToArabic("XL");
		int roman_XC = romanToArabic.convertRomanNumberToArabic("XC");
		assertEquals(roman_IV, 4);
		assertEquals(roman_IX, 9);
		assertEquals(roman_XL, 40);
		assertEquals(roman_XC, 90);
	}

	/**
	 * @throws Exception
	 *             if more than one V.
	 * 
	 *             if more than one L.
	 * 
	 *             if more than one D.
	 */
	@Test
	public void invalidRoman_VV_LL_DD() throws Exception {
		ConvertRomanToArabic romanToArabic = new ConvertRomanToArabic();
		Throwable roman_VV = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("VV"));
		Throwable roman_LL = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("LL"));
		Throwable roman_DD = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("DD"));
		assertEquals("Invalid Input for Roman Numerals!", roman_VV.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_LL.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_DD.getMessage());
	}

	/**
	 * @throws Exception
	 *             if invalid subtraction of I, namely IL, IC, ID, IM.
	 */
	@Test
	public void invalidRoman_IL_IC_ID_IM() throws Exception {
		ConvertRomanToArabic romanToArabic = new ConvertRomanToArabic();
		Throwable roman_IL = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("IL"));
		Throwable roman_IC = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("IC"));
		Throwable roman_ID = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("ID"));
		Throwable roman_IM = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("IM"));
		assertEquals("Invalid Input for Roman Numerals!", roman_IL.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_IC.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_ID.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_IM.getMessage());
	}

	/**
	 * No subtraction of V.
	 * 
	 * @throws Exception
	 *             if V is followed by a symbol of higher value.
	 */
	@Test
	public void invalidRoman_VX_VL_VC_VD_VM() throws Exception {
		ConvertRomanToArabic romanToArabic = new ConvertRomanToArabic();
		Throwable roman_VX = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("VX"));
		Throwable roman_VL = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("VL"));
		Throwable roman_VC = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("VC"));
		Throwable roman_VD = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("VD"));
		Throwable roman_VM = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("VM"));
		assertEquals("Invalid Input for Roman Numerals!", roman_VX.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_VL.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_VC.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_VD.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_VM.getMessage());
	}

	/**
	 * @throws Exception
	 *             for an invalid subtraction of X, namely XD, XM.
	 */
	@Test
	public void invalidRoman_XD_XM() throws Exception {
		ConvertRomanToArabic romanToArabic = new ConvertRomanToArabic();
		Throwable roman_XD = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("IL"));
		Throwable roman_XM = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("IC"));
		assertEquals("Invalid Input for Roman Numerals!", roman_XM.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_XD.getMessage());
	}

	/**
	 * No subtraction of L.
	 * 
	 * @throws Exception
	 *             if L is followed by a symbol of higher value.
	 */
	@Test
	public void invalidRoman_LC_LD_LM() throws Exception {
		ConvertRomanToArabic romanToArabic = new ConvertRomanToArabic();
		Throwable roman_LC = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("LC"));
		Throwable roman_LD = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("LD"));
		Throwable roman_LM = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("LM"));
		assertEquals("Invalid Input for Roman Numerals!", roman_LC.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_LD.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_LM.getMessage());
	}

	/**
	 * No subtraction of D.
	 * 
	 * @throws Exception
	 *             if D is followed by a symbol of higher value.
	 */
	@Test
	public void invalidRoman_DM() throws Exception {
		ConvertRomanToArabic romanToArabic = new ConvertRomanToArabic();
		Throwable roman_DM = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("DM"));
		assertEquals("Invalid Input for Roman Numerals!", roman_DM.getMessage());
	}

	/**
	 * Subtraction of only one small-value symbol from any large-value symbol.
	 * 
	 * @throws Exception
	 *             if this rule is not observed.
	 */
	@Test
	public void invalidRoman_IVIX_IXIV_XCXL_XLXC_CMCD_CDCM() throws Exception {
		ConvertRomanToArabic romanToArabic = new ConvertRomanToArabic();
		Throwable roman_IVIX = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("IVIX"));
		Throwable roman_IXIV = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("IXIV"));
		Throwable roman_XCXL = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("XCXL"));
		Throwable roman_XLXC = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("XLXC"));
		Throwable roman_CMCD = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("CMCD"));
		Throwable roman_CDCM = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("CMCD"));
		assertEquals("Invalid Input for Roman Numerals!", roman_IVIX.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_IXIV.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_XCXL.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_XLXC.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_CMCD.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_CDCM.getMessage());
	}

	/**
	 * Roman symbols, taking account of the cases of subtractions, can occur only
	 * from large to small values.
	 * 
	 * @throws Exception
	 *             if this rule is not observed for I.
	 */
	@Test
	public void invalidRoman_IXV_IXX_IXL_IXC_IXD_IXM() throws Exception {
		ConvertRomanToArabic romanToArabic = new ConvertRomanToArabic();
		Throwable roman_IXV = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("IXV"));
		Throwable roman_IXX = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("IXX"));
		Throwable roman_IXL = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("IXL"));
		Throwable roman_IXC = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("IXC"));
		Throwable roman_IXD = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("IXD"));
		Throwable roman_IXM = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("IXM"));
		assertEquals("Invalid Input for Roman Numerals!", roman_IXV.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_IXX.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_IXL.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_IXC.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_IXD.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_IXM.getMessage());
	}

	/**
	 * Roman symbols, taking account of the cases of subtractions, can occur only
	 * from large to small values.
	 * 
	 * @throws Exception
	 *             if this rule is not observed for X.
	 */
	@Test
	public void invalidRoman_XCL_XCC_XCD_XCM() throws Exception {
		ConvertRomanToArabic romanToArabic = new ConvertRomanToArabic();
		Throwable roman_XCL = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("XCL"));
		Throwable roman_XCC = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("XCC"));
		Throwable roman_XCD = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("XCD"));
		Throwable roman_XCM = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("XCM"));
		assertEquals("Invalid Input for Roman Numerals!", roman_XCL.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_XCC.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_XCD.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_XCM.getMessage());
	}

	/**
	 * Roman symbols, taking account of the cases of subtractions, can occur only
	 * from large to small values.
	 * 
	 * @throws Exception
	 *             if this rule is not observed for C.
	 */
	@Test
	public void invalidRoman_CMD_CMM() throws Exception {
		ConvertRomanToArabic romanToArabic = new ConvertRomanToArabic();
		Throwable roman_CMD = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("CMD"));
		Throwable roman_CMM = assertThrows(Exception.class, () -> romanToArabic.convertRomanNumberToArabic("CMM"));
		assertEquals("Invalid Input for Roman Numerals!", roman_CMD.getMessage());
		assertEquals("Invalid Input for Roman Numerals!", roman_CMM.getMessage());
	}

	/**
	 * Only I, V, X, L, C, D, M are valid Roman symbols.
	 */
	@Test
	public void invalidRoman_composedOnlyOf_IVXLCDM() throws Exception {
		ConvertRomanToArabic romanToArabic = new ConvertRomanToArabic();
		boolean containsOnlyValidChatacters = false;
		String validCharacters = "IVXLCDM";
		String punctuation = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
		String space = " ";
		String nullString = null;
		for (char i = 'a'; i <= 'z'; i++) {
			String currentChar = Character.toString(i);
			try {
				romanToArabic.convertRomanNumberToArabic(currentChar);
			} catch (Exception e) {
				containsOnlyValidChatacters = true;
			}
		}

		for (char i = 'A'; i <= 'Z'; i++) {
			String currentChar = Character.toString(i);
			if (!validCharacters.contains(Character.toString(i))) {
				try {
					romanToArabic.convertRomanNumberToArabic(currentChar);
				} catch (Exception e) {
					containsOnlyValidChatacters = true;
				}
			}
		}

		for (int i = 0; i < punctuation.length(); i++) {
			String currentChar = Character.toString(punctuation.charAt(i));
			try {
				romanToArabic.convertRomanNumberToArabic(currentChar);
			} catch (Exception e) {
				containsOnlyValidChatacters = true;
			}

		}
		try {
			romanToArabic.convertRomanNumberToArabic(space);
		} catch (Exception e) {
			containsOnlyValidChatacters = true;
		}

		try {
			romanToArabic.convertRomanNumberToArabic(nullString);
		} catch (Exception e) {
			containsOnlyValidChatacters = true;
		}
		assertTrue(containsOnlyValidChatacters);
	}
}
