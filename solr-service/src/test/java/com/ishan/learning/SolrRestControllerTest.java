package com.ishan.learning;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ishan.solr.data.SolrProductRepository;
import com.ishan.solr.product.SolrProduct;
import com.ishan.solr.service.SolrProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SolrRestControllerTest {

	@Autowired
	private SolrProductService solrProductService;

	@MockBean
	private SolrProductRepository solrProductDao;

	@Test
	public void getProductsTest() {
		when(solrProductDao.getAllProducts(solrProductService)).thenReturn(Stream.of(new SolrProduct("test", "1", "20", "test description")).collect(Collectors.toList()));
		assertEquals("1", solrProductDao.getAllProducts(solrProductService).get(0).getSku());
	}
}
