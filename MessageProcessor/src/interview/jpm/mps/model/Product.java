/**
 * 
 */
package interview.jpm.mps.model;

/**
 * @author Pratul Shukla
 *
 */
public class Product {
    
    private String productType;
    private double productUnitPrice;
    private int productQuantity;
    private double productTotalPrice;
    
	public Product(String productType, double productUnitPrice, int productQuantity,
			double productTotalPrice) {
		super();
		this.productType = productType;
		this.productUnitPrice = productUnitPrice;
		this.productQuantity = productQuantity;
		this.productTotalPrice = productTotalPrice;
	}
	
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public double getProductUnitPrice() {
		return productUnitPrice;
	}
	public void setProductUnitPrice(double productUnitPrice) {
		this.productUnitPrice = productUnitPrice;
	}
	public int getProuctQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public double getProductTotalPrice() {
		return productTotalPrice;
	}
	public void setProductTotalPrice(double productTotalPrice) {
		this.productTotalPrice = productTotalPrice;
	}

	@Override
	public String toString() {
		return "Product [" + (productType != null ? "productType=" + productType + ", " : "") + "productUnitPrice="
				+ productUnitPrice + ", productQuantity=" + productQuantity + ", productTotalPrice=" + productTotalPrice
				+ "]";
	}

	
	
}
