package com.ishan.solr.product;

public class SolrProduct {

	private String name;
	private String sku;
	private String packagingQuantity;
	private String description;
	
	public SolrProduct() {
		
		// Default Constructor
	}
	public SolrProduct(String name, String sku, String packagingQuantity, String description) {
		super();
		this.name = name;
		this.sku = sku;
		this.packagingQuantity = packagingQuantity;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getPackagingQuantity() {
		return packagingQuantity;
	}

	public void setPackagingQuantity(String packagingQuantity) {
		this.packagingQuantity = packagingQuantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
