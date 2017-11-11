package interview.jpm.mps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import interview.jpm.mps.SaleMessageProcessor;
import interview.jpm.mps.SaleRegister;

public class SaleMessageProcessorTest {
	SaleMessageProcessor messageProcessor;
	SaleRegister register;
	
	@Before
	public void setUp() throws Exception {
		register = new SaleRegister();
		messageProcessor = new SaleMessageProcessor(register);
	}

	@Test
	public void shouldNotProcessEmptyMessage() {
		//Given
		String message = null;
		String emptyMessage = "  ";
		
		//When
		boolean isNullSaleMessageProcessed = messageProcessor.process(message);
		boolean isEmptySaleMessageProcessed = messageProcessor.process(emptyMessage);
		
		//Then
		assertFalse(isNullSaleMessageProcessed);
		assertFalse(isEmptySaleMessageProcessed);
		
	}
	
	@Test
	public void shouldRegisterProductsInRegistry() {
		//Given
		String message1 = "Detail,Apple,10,p,,,";
		String message2= "Detail,Banana,25,p,,,";
		String message3= "DetailWithQuantity,Apple,10,p,20";
		String message4= "DetailWithQuantity,Orange,30,p,10";
		
		//When
		messageProcessor.process(message1);
		messageProcessor.process(message2);
		messageProcessor.process(message3);
		messageProcessor.process(message4);
		
		Set<String> productNames = register.getAvailableProducts().keySet();
		double priceForApple = register.getSalePerProduct().get("Apple");
		double priceForBanana = register.getSalePerProduct().get("Banana");
		double priceForOrange = register.getSalePerProduct().get("Orange");
		
		//Then
		assertTrue(productNames.contains("Apple"));
		assertTrue(productNames.contains("Banana"));
		assertTrue(productNames.contains("Orange"));
		assertEquals(210.0,priceForApple,.001);
		assertEquals(25.0,priceForBanana,.001);
		assertEquals(300.0,priceForOrange,.001);
		
	}
	
	@Test
	public void shouldPrintProductsInRegistry() {
		//Given
		String message1 = "Detail,Apple,10,p,,,";
		String message2= "Detail,Banana,25,p,,,";
		String message3= "DetailWithQuantity,Apple,10,p,20";
		String message4= "DetailWithQuantity,Orange,30,p,10";
		
		//When
		messageProcessor.process(message1);
		messageProcessor.process(message2);
		messageProcessor.process(message3);
		messageProcessor.process(message4);
		
		//Then
		register.logProcessedProducts();
		
	}
	
	@Test
	public void shouldPrintAdjustmentsMadeFromRegistry() {
		//Given
		String message1 = "Detail,Apple,10,p,,,";
		String message2= "Detail,Banana,25,p,,,";
		String message3= "DetailWithQuantity,Apple,10,p,20";
		String message4= "DetailWithQuantity,Orange,30,p,10";
		String message5= "Adjustment,Banana,,,,ADD,10";
		String message6= "Adjustment,Orange,,,,SUBTRACT,5";
		String message7= "Adjustment,Apple,,,,MULTIPLY,2";
		
		//When
		messageProcessor.process(message1);
		messageProcessor.process(message2);
		messageProcessor.process(message3);
		messageProcessor.process(message4);
		messageProcessor.process(message5);
		messageProcessor.process(message6);
		messageProcessor.process(message7);
		
		//Then
		register.logProcessedProducts();
		register.logProductAdjustments();
		
	}

}
