package conversionCalculator.UserInterface;

import java.util.Scanner;

import conversionCalculator.Regex.RegexAndChecks;
import conversionCalculator.ConvertNumbers.ConvertArabicToRoman;
import conversionCalculator.ConvertNumbers.ConvertRomanToArabic;

/**
 * Implement an application for interaction with the user.
 * 
 * @author Lachezar Kutsarov
 */
public class UserInterface {
	RegexAndChecks regexAndChecks = new RegexAndChecks();
	ConvertArabicToRoman convertArabicToRoman = new ConvertArabicToRoman();
	ConvertRomanToArabic convertRomanToArabic = new ConvertRomanToArabic();

	public void conversionCalculator() throws Exception {
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter number: ");

		while (reader.hasNextLine()) {

			String inputNumber = reader.nextLine();

			if (regexAndChecks.validArabicNumber_from_1_to_4999(inputNumber)) {
				String result = convertArabicToRoman.convertArabicNumberToRoman(inputNumber);
				/**
				 * Replaces all leading zeroes.
				 */
				inputNumber = inputNumber.replaceFirst("^0+(?!$)", "");
				System.out.println(inputNumber + " = " + result);
			} else if (regexAndChecks.validRomanNumber_from_1_to_4999(inputNumber)) {
				int result = convertRomanToArabic.convertRomanNumberToArabic(inputNumber);
				System.out.println(inputNumber + " = " + result);
			} else {
				System.out.println("Input can be only an Arabic or a Roman number.");
				System.out.println("Values: from 1 to 4999.");
			}
			System.out.print("Enter number: ");
		}
		reader.close();
	}
}
