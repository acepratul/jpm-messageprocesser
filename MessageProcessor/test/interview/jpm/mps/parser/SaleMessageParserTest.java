package interview.jpm.mps.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import interview.jpm.mps.model.SaleMessage;
import interview.jpm.mps.parser.SaleMessageParser;

public class SaleMessageParserTest {

	SaleMessageParser parser;

	@Before
	public void setUp() throws Exception {
		parser = new SaleMessageParser();
	}

	@Test(expected = NumberFormatException.class)
	public final void shouldThrowExceptionWhenPriceIsNotValid() throws Exception {
		// Given
		String messageToBeParsed = "Detail,Apple,price";

		// When
		try {
			parser.parse(messageToBeParsed);
		} catch (Exception e) {
			// Then
			throw new NumberFormatException();
		}

	}
	
	@Test
	public void shouldHaveNumberOfOccurancesInSaleMessage() throws Exception {
		//Given
		String messageToBeParsed = "DetailWithQuantity,Orange,30,p,25";
		
		//When
		SaleMessage message = null;
		try {
			message = parser.parse(messageToBeParsed);
		}catch(Exception e) {
			fail("Message is not parsed");
		}
		
		//Then
		assertEquals("Number of ooccurances matches", 25, message.getNumberOfOccurences());
				
	}

	@Test
	public void shouldHaveAdjustmentInSaleMessage() throws Exception {
		//Given
		String messageToBeParsed = "Adjustment,,,,,ADD,2";
		
		//When
		SaleMessage message = null;
		try {
			message = parser.parse(messageToBeParsed);
		}catch(Exception e) {
			fail("Message is not parsed");
		}
		
		//Then
		assertTrue(message.isHasAdjustments());
				
	}

}
