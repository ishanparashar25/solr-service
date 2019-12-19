package com.ishan.solr.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import com.ishan.solr.product.SolrProduct;
import com.ishan.solr.product.SolrProductUser;
import com.ishan.solr.service.SolrProductService;

@Service
public class SolrProductRepository {

	public SolrProduct addProduct(SolrProduct product, SolrProductService service) {

		HttpSolrClient solr = service.getSolr();

		SolrInputDocument document = new SolrInputDocument();
		document.addField("name", product.getName());
		document.addField("sku", product.getSku().toString());
		document.addField("id", product.getSku());
		document.addField("packagingQuantity", product.getPackagingQuantity());
		document.addField("description", product.getDescription());

		try {
			solr.add(document);
			solr.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return product;
	}

	public List<SolrProduct> getAllProducts(SolrProductService service) {
		HttpSolrClient solr = service.getSolr();
		SolrQuery params = new SolrQuery();
		params.setQuery("*:*");
		params.setSort("score ", ORDER.desc);
		params.setStart(Integer.getInteger("0"));
		params.setRows(Integer.getInteger("100"));

		ArrayList<SolrProduct> allProducts = new ArrayList<SolrProduct>();

		QueryResponse response = null;
		SolrDocumentList results = null;
		try {
			response = solr.query(params);
			results = response.getResults();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null != results) {
			for (int i = 0; i < results.size(); ++i) {
				System.out.println(results.get(i));
				SolrProduct solrProduct = new SolrProduct();
				solrProduct.setName((String) results.get(i).getFieldValue("name"));
				solrProduct.setSku((String) results.get(i).getFieldValue("sku"));
				solrProduct.setDescription((String) results.get(i).getFieldValue("description"));
				solrProduct.setPackagingQuantity((String) results.get(i).getFieldValue("packagingQuantity"));
				allProducts.add(solrProduct);
			}
		}
		return allProducts;

	}

	public SolrProduct getProductById(String id, SolrProductService service) {
		HttpSolrClient solr = service.getSolr();

		SolrDocument result;
		SolrProduct solrProduct = new SolrProduct();
		try {
			result = solr.getById(id);
			if (null != result) {
				solrProduct.setName((String) result.getFieldValue("name"));
				solrProduct.setSku((String) result.getFieldValue("sku"));
				solrProduct.setDescription((String) result.getFieldValue("description"));
				solrProduct.setPackagingQuantity((String) result.getFieldValue("packagingQuantity"));
			}
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return solrProduct;
	}

	public SolrProduct updateCustomerData(SolrProductUser solrProductUser, SolrProductService service) {
		HttpSolrClient solr = service.getSolr();

		SolrDocument result;
		SolrProduct solrProduct = new SolrProduct();
		try {
			result = solr.getById(solrProductUser.getSku());
			if (null != result) {

				SolrInputDocument solrDocToIndex = new SolrInputDocument(); // Create Document
				solrDocToIndex.addField("id", solrProductUser.getSku());
				Map<String, String> partialUpdate = new HashMap<>();
				partialUpdate.put("set", solrProductUser.getUsers());
				solrDocToIndex.addField("users", partialUpdate);
				solr.add(solrDocToIndex); // send it to solr
				solr.commit();

				solrProduct.setName((String) result.getFieldValue("name"));
				solrProduct.setSku((String) result.getFieldValue("sku"));
				solrProduct.setDescription((String) result.getFieldValue("description"));
				solrProduct.setPackagingQuantity((String) result.getFieldValue("packagingQuantity"));
			}
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return solrProduct;
	}

	public String deleteProductByID(String id, SolrProductService service) {
		HttpSolrClient solr = service.getSolr();
		try {
			solr.deleteById(id);
			solr.commit();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	public void deleteAllProducts(SolrProductService service) {
		HttpSolrClient solr = service.getSolr();

		SolrQuery params = new SolrQuery();
		params.setQuery("*:*");

		try {
			solr.deleteByQuery(params.getQuery());
			solr.commit();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
