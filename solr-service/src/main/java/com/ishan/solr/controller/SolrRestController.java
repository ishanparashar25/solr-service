package com.ishan.solr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ishan.solr.data.SolrProductRepository;
import com.ishan.solr.product.SolrProduct;
import com.ishan.solr.product.SolrProductUser;
import com.ishan.solr.service.SolrProductService;

@RestController
@RequestMapping(value = "/solr-products")
public class SolrRestController {

	@Autowired
	SolrProductService service;
	
	@Autowired
	SolrProductRepository solrProductRepository;
	
	@GetMapping(path = "/", produces = "application/json")
	public List<SolrProduct> getAllProducts() {
		return this.solrProductRepository.getAllProducts(service);
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	public SolrProduct getProductById(@PathVariable String id) {
		return this.solrProductRepository.getProductById(id, service);
	}

	@PostMapping(path = "/",  consumes = "application/json", produces = "application/json")
	public SolrProduct addProduct(@RequestBody SolrProduct product) {

		return this.solrProductRepository.addProduct(product, service);
	}

	@DeleteMapping(path = "/{id}")
	public String deleteProductById(@PathVariable String id) {
		return this.solrProductRepository.deleteProductByID(id, service);
	}

	@DeleteMapping(path = "/", produces = "application/json")
	public void deleteAllProducts() {
		this.solrProductRepository.deleteAllProducts(service);
	}

	@PutMapping(path = "/", consumes = "application/json", produces = "application/json")
	public SolrProduct updateUserData(@RequestBody SolrProductUser productUserData) {
		return this.solrProductRepository.updateCustomerData(productUserData, service);
	}
}
