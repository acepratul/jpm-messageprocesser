/**
 * 
 */
package interview.jpm.mps;

import java.util.List;

import interview.jpm.mps.model.Operator;
import interview.jpm.mps.model.Product;
import interview.jpm.mps.model.SaleMessage;
import interview.jpm.mps.parser.MessageParser;
import interview.jpm.mps.parser.SaleMessageParser;

/**
 * @author Pratul Shukla
 *
 */
public class SaleMessageProcessor implements MessageProcessor {

	private SaleRegister registry;
	private MessageParser<SaleMessage> parser = new SaleMessageParser();

	public SaleMessageProcessor(SaleRegister register) {
		super();
		this.registry = register;
	}

	@Override
	public boolean process(String message) {

		if (message == null || message.trim().isEmpty()) {
			System.out.println(
					"There is no message to process, it is either not a valid message or is without any content");
			return false;
		}

		try {
			SaleMessage saleMessage = parser.parse(message);
			if (saleMessage == null) {
				System.out.println(String
						.format("Message [%s] could not be parsed, Please review logs for more information", message));
				return false;
			}
			processAndRegisterMessage(saleMessage);
		} catch (Exception e) {
			System.out.println(String.format("Unexpected [%s] while processing message [%s], Message not processed ",
					e.getMessage(), message));
			e.printStackTrace();
			return false;
		}

		return true;

	}

	private void processAndRegisterMessage(SaleMessage saleMessage) {

		if (saleMessage.isHasAdjustments()) {
			if (registry.getAvailableProducts().containsKey(saleMessage.getProductName())) {
				List<Product> products = registry.getAvailableProducts().get(saleMessage.getProductName());
				for (Product product : products) {
					performAdjustments(product, saleMessage.getAdjustmentValue(), saleMessage.getAdjustmentOperator());
				}
				registry.registerProductAdjustment(saleMessage.getProductName(), saleMessage.getAdjustmentOperator(), saleMessage.getAdjustmentValue());
			}else {
				System.out.println(String.format("No adjustments made for message [%s] as product was not found in registry", saleMessage));
			}
			
		} else {
			Product product = transformToProduct(saleMessage);
			registry.registerProcessedProducts(product.getProductType(), product);
		}

	}

	private Product transformToProduct(SaleMessage saleMessage) {
		double totalProductPrice = saleMessage.getPrice();
		if (saleMessage.getNumberOfOccurences() > 1) {
			totalProductPrice = totalProductPrice * saleMessage.getNumberOfOccurences();
		}
		Product product = new Product(saleMessage.getProductName(), saleMessage.getPrice(),
				saleMessage.getNumberOfOccurences(), totalProductPrice);
		return product;
	}

	private void performAdjustments(Product product, double adjustmentValue, Operator adjustmentOperator) {
		Double adjustedValue = calculateAdjustedValue(product.getProductTotalPrice(), adjustmentValue,
				adjustmentOperator);
		if (adjustedValue != null) {
			product.setProductTotalPrice(adjustedValue);
		}

	}

	private Double calculateAdjustedValue(double productTotalPrice, double adjustmentValue,
			Operator adjustmentOperator) {
		Double adjustedValue = null;
		switch (adjustmentOperator) {
		case ADD:
			adjustedValue = productTotalPrice + adjustmentValue;
			break;
		case SUBTRACT:
			adjustedValue = productTotalPrice - adjustmentValue;
			break;
		case MULTIPLY:
			adjustedValue = productTotalPrice * adjustmentValue;
			break;
		default:
			System.out.println(String.format(
					"Not a valid operator [%s] for adjustment, no adjustment will be performed", adjustmentOperator));
			break;
		}
		return adjustedValue;
	}

}
