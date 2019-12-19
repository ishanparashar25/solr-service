package com.ishan.solr.product;

public class SolrProductUser {

	private String sku;
	private String users;

	SolrProductUser() {
		// Default Constructor
	}

	public SolrProductUser(String sku, String users) {
		super();
		this.sku = sku;
		this.users = users;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
	}

}
