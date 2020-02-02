/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary.task.utils;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Common {

	private static DecimalFormat formatter = new DecimalFormat("#.##");

	/**
	 * Fix number of decimal numbers in a double.
	 * 
	 * @param number
	 * @return double
	 */
	public static double getDoubleWithFixedDecimals(double number) {
		formatter.setRoundingMode(RoundingMode.DOWN);
		String numberWithFormat = formatter.format(number);
		double fixedNumber = Double.parseDouble(numberWithFormat);
		return fixedNumber;
	}

}
