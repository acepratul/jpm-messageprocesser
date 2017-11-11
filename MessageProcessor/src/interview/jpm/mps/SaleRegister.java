/**
 * 
 */
package interview.jpm.mps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interview.jpm.mps.model.Operator;
import interview.jpm.mps.model.Product;
import interview.jpm.mps.model.ProductAdjustment;

/**
 * @author Pratul Shukla
 *
 */
public class SaleRegister {

	private Map<String, List<Product>> availableProducts = new HashMap<>();
	private Map<String, Double> salePerProduct = new HashMap<>();
	private List<ProductAdjustment> productAdjustments = new ArrayList<>();

	public void registerProcessedProducts(String productName, Product product) {
		List<Product> products = null;
		if (availableProducts.containsKey(productName)) {
			products = availableProducts.get(productName);
			products.add(product);
			
		} else {
			products = new ArrayList<>();
			products.add(product);
		}
		availableProducts.put(productName, products);
		
		double productTotalSaleValue = product.getProductTotalPrice();
		if (salePerProduct.containsKey(productName)) {
			productTotalSaleValue = productTotalSaleValue + salePerProduct.get(productName);
		}

		salePerProduct.put(productName, productTotalSaleValue);
		
	}

	public void registerProductAdjustment(String productType, Operator operationApplied, double adjustmentValue) {
		productAdjustments.add(new ProductAdjustment(productType, operationApplied, adjustmentValue));
	}

	public void logProcessedProducts() {
		System.out.println(String.format("----------Processed following %d Product messages----------",
				availableProducts.keySet().size()));
		System.out.println(String.format("%-20s %-20s %-20s", "ProductType", "NumberOfSales", "TotalSaleValue"));
		for (String productName : availableProducts.keySet()) {
			System.out.println(String.format("%-20s %-20s %-20f", productName, availableProducts.get(productName).size(),
					salePerProduct.get(productName)));
		}
	}

	public void logProductAdjustments() {
		System.out
				.println(String.format("----------Following %s adjustments were made based on sale messages----------",
						productAdjustments.size()));
		System.out.println(String.format("%-20s %-20s %-20s", "ProductType", "AdjustmentType", "AdjustmentValue"));
		for (ProductAdjustment adjustment : productAdjustments) {
			System.out.println(String.format("%-20s %-20s %-20f", adjustment.getProductType(),
					adjustment.getAdjustmentOperator(), adjustment.getAdjustmentValue()));
		}
	}

	public Map<String, List<Product>> getAvailableProducts() {
		return availableProducts;
	}

	public void setAvailableProducts(Map<String, List<Product>> availableProducts) {
		this.availableProducts = availableProducts;
	}

	public Map<String, Double> getSalePerProduct() {
		return salePerProduct;
	}

	public void setSalePerProduct(Map<String, Double> salePerProduct) {
		this.salePerProduct = salePerProduct;
	}

	public List<ProductAdjustment> getProductAdjustments() {
		return productAdjustments;
	}

	public void setProductAdjustments(List<ProductAdjustment> productAdjustments) {
		this.productAdjustments = productAdjustments;
	}

}
