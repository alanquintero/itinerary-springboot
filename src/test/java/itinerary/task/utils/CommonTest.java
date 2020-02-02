/**
* Copyright (C) 2020
* @author Alan Quintero
*/

package itinerary.task.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

public class CommonTest {

	@Test
	void testGetDoubleWithTwoFixedDecimals() {
		double number = 12.12345;
		double numberWithFixedDecimals = Common.getDoubleWithFixedDecimals(number);
		assertEquals(12.12, numberWithFixedDecimals);
		assertNotEquals(number, numberWithFixedDecimals);
	}

}
