package interview.jpm.mps.parser;

import interview.jpm.mps.model.Operator;
import interview.jpm.mps.model.SaleMessage;

public class SaleMessageParser implements MessageParser<SaleMessage> {

	private static final String COMMA_DELIMITER = ",";

	@Override
	public SaleMessage parse(String messageToBeParsed) throws Exception {
		SaleMessage saleMessage = null;

		try {
			saleMessage = getSaleMessage(messageToBeParsed);
		//	System.out.println("Parsed sale message: " + saleMessage);
		} catch (Exception e) {
			System.out.println(String.format(
					"Could not parse the message [%s], please review and resend the message in correct format [%s]",
					messageToBeParsed,
					"Type,Product,Price,Currency,NumberOfOccurences,AdjustmentOperator,AdjustmentValue"));
			e.printStackTrace();
			throw new Exception();
		}

		return saleMessage;
	}

	private SaleMessage getSaleMessage(String messageToBeParsed) {
		SaleMessage saleMessage = null;
		String[] messageTokens = messageToBeParsed.trim().split(COMMA_DELIMITER);
		
		if (messageTokens[0].equalsIgnoreCase("DETAIL")) {
			saleMessage = new SaleMessage();
			saleMessage.setProductName(messageTokens[1]);
			saleMessage.setPrice(Double.parseDouble(messageTokens[2]));
		} else if (messageTokens[0].equalsIgnoreCase("DETAILWITHQUANTITY")) {
			saleMessage = new SaleMessage();
			saleMessage.setProductName(messageTokens[1]);
			saleMessage.setPrice(Double.parseDouble(messageTokens[2]));
			saleMessage.setNumberOfOccurences(Integer.valueOf(messageTokens[4]));
		} else if (messageTokens[0].equalsIgnoreCase("ADJUSTMENT")) {
			saleMessage = new SaleMessage();
			saleMessage.setProductName(messageTokens[1]);
			saleMessage.setAdjustmentOperator(Operator.valueOf(messageTokens[5]));
			saleMessage.setAdjustmentValue(Double.parseDouble(messageTokens[6]));
			saleMessage.setHasAdjustments(true);
		}
		
		return saleMessage;
	}
}
