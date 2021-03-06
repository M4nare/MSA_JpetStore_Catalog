/**
 *    Copyright 2010-2017 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.jpetstore.web.actions;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.SessionScope;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.mybatis.jpetstore.domain.Account;
import org.mybatis.jpetstore.domain.Category;
import org.mybatis.jpetstore.domain.Item;
import org.mybatis.jpetstore.domain.Product;
import org.mybatis.jpetstore.mapper.ItemMapper;
import org.mybatis.jpetstore.service.CatalogService;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

/**
 * The Class CatalogActionBean.
 *
 * @author Eduardo Macarron
 */
@SessionScope
public class CatalogActionBean extends AbstractActionBean {

  private static final long serialVersionUID = 5849523372175050635L;

  @SpringBean
  private transient CatalogService catalogService;

  private String keyword;

  private String categoryId;
  private Category category;
  private List<Category> categoryList;

  private String productId;
  private Product product;
  private List<Product> product2;
  private List<Product> productList;

  private String itemId;
  private Item item;
  private List<Item> itemList;
  
  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public String getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
  }

  public List<Category> getCategoryList() {
    return categoryList;
  }

  public void setCategoryList(List<Category> categoryList) {
    this.categoryList = categoryList;
  }

  public List<Product> getProductList() {
    return productList;
  }

  public void setProductList(List<Product> productList) {
    this.productList = productList;
  }

  public List<Item> getItemList() {
    return itemList;
  }

  public void setItemList(List<Item> itemList) {
    this.itemList = itemList;
  }



  /**
   * Search products.
   *
   * @return the forward resolution
   */
  public Resolution searchProducts() {
	  
	  
	  
	  return new Resolution()
		{

			@Override
			public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				// TODO Auto-generated method stub
				String search = request.getParameter("search");
				productList = catalogService.searchProductList(search);

				
							
				response.setCharacterEncoding("utf-8");
		        response.setContentType("application/json");

				Gson gson = new Gson();
				
				String jsonlist = gson.toJson(productList);
				PrintWriter out = response.getWriter();
				out.write(jsonlist);
		        out.flush();
		        out.close();
		        productList = null;

			}

		};

  }
  

  
  public Resolution getProductListByCategory()
  {
	  
	  return new Resolution()
		{

			@Override
			public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				// TODO Auto-generated method stub
				String productId = request.getParameter("id");
				
				product2 = catalogService.getProductListByCategory(productId);
				
							
				response.setCharacterEncoding("utf-8");
		        response.setContentType("application/json");

				Gson gson = new Gson();
				
				String jsonlist = gson.toJson(product2);
				PrintWriter out = response.getWriter();
				out.write(jsonlist);
		        out.flush();
		        out.close();
		        product2 = null;

			}

		};
  }
  
  public Resolution getItemListByProduct()
  {
	  
	  return new Resolution()
		{

			@Override
			public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				// TODO Auto-generated method stub
				String productId = request.getParameter("id");
				
				itemList = catalogService.getItemListByProduct(productId);
				
							
				response.setCharacterEncoding("utf-8");
		        response.setContentType("application/json");

				Gson gson = new Gson();
				
				String jsonlist = gson.toJson(itemList);
				PrintWriter out = response.getWriter();
				out.write(jsonlist);
		        out.flush();
		        out.close();
		        itemList = null;

			}

		};
  }
  
  public Resolution getproduct()
  {
	  
	  return new Resolution()
		{

			@Override
			public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				// TODO Auto-generated method stub
				String productId = request.getParameter("id");
				
				product = catalogService.getProduct(productId);
				
							
				response.setCharacterEncoding("utf-8");
		        response.setContentType("application/json");

				Gson gson = new Gson();
				
				String jsonlist = gson.toJson(product);
				PrintWriter out = response.getWriter();
				out.write(jsonlist);
		        out.flush();
		        out.close();
		        product = null;

			}

		};
  }
  
  
  
  public Resolution getcategory()
  {
	  
	  return new Resolution()
		{

			@Override
			public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				// TODO Auto-generated method stub
				String productId = request.getParameter("id");
				
				category = catalogService.getCategory(productId);
				
							
				response.setCharacterEncoding("utf-8");
		        response.setContentType("application/json");

				Gson gson = new Gson();
				
				String jsonlist = gson.toJson(category);
				PrintWriter out = response.getWriter();
				out.write(jsonlist);
		        out.flush();
		        out.close();
		        category = null;

			}

		};
  }
  
  
  
  public Resolution isItemInStock()
  {
	  
	  return new Resolution()
		{

			@Override
			public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				// TODO Auto-generated method stub
				String isItemInStock = request.getParameter("item");
				
				boolean isInStock = catalogService.isItemInStock(isItemInStock);
				
							
				response.setCharacterEncoding("utf-8");
		        response.setContentType("application/json");

				Gson gson = new Gson();
				
				String jsonlist = gson.toJson(isInStock);
				PrintWriter out = response.getWriter();
				out.write(jsonlist);
		        out.flush();
		        out.close();
		        
		        
		     
			}

		};
  }
  
  public Resolution getitem()
  {
	  
	  return new Resolution()
		{

			@Override
			public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				// TODO Auto-generated method stub
				String getitem = request.getParameter("item");
				
				Item item = catalogService.getItem(getitem);
				
							
				response.setCharacterEncoding("utf-8");
		        response.setContentType("application/json");

				Gson gson = new Gson();
				
				String jsonlist = gson.toJson(item);
				
				
				PrintWriter out = response.getWriter();
				out.write(jsonlist);
		        out.flush();
		        out.close();
		        
		       
			}

		};
  }
  
  public Resolution getProductid()
  {
	  
	  return new Resolution()
		{

			@Override
			public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				// TODO Auto-generated method stub
				String getid = request.getParameter("item");
				
				
				Item item = catalogService.getItem(getid);
				
				Product product3 = item.getProduct();
				

				Gson gson = new Gson();
				
				String jsonlist = gson.toJson(product3);
			
							
							
				response.setCharacterEncoding("utf-8");
		        response.setContentType("application/json");


				PrintWriter out = response.getWriter();
				out.write(jsonlist);
		        out.flush();
		        out.close();
		        
		       
			}

		};
  }
  

  public Resolution updateInventoryQuantity()
  {
	  
	  return new Resolution()
		{

			@Override
			public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				// TODO Auto-generated method stub
				String itemId = request.getParameter("itemId");
				String increment = request.getParameter("increment");			
				Integer amount = Integer.parseInt(String.valueOf(increment));
				catalogService.updateInventoryQuantity(itemId, amount);				 
			    int a = catalogService.getInventoryQuantity(itemId);
			    System.out.println("QTY: " + a);
				response.setCharacterEncoding("utf-8");
		        response.setContentType("application/json");

		        Gson gson = new Gson();
				Map<String, String> json_result= new HashMap();
				json_result.put("result", "success");
				String jsonlist = gson.toJson(json_result);
				PrintWriter out = response.getWriter();
				out.write(jsonlist);
		        out.flush();
		        out.close();
		        
		       
			}

		};
  }
  
  
  

  /**
   * Clear.
   */
  public void clear() {
    keyword = null;

    categoryId = null;
    category = null;
    categoryList = null;

    productId = null;
    product = null;
    productList = null;

    itemId = null;
    item = null;
    itemList = null;
  }

}
