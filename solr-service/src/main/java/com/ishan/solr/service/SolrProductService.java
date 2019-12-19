package com.ishan.solr.service;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.springframework.stereotype.Service;

@Service
public class SolrProductService {
private HttpSolrClient solr;


	public HttpSolrClient getSolr() {
		String urlString = "http://localhost:8983/solr/solr_products_indx";
		this.solr = new HttpSolrClient.Builder(urlString).build();
		solr.setParser(new XMLResponseParser());
		return solr;
	}
	
	
}
