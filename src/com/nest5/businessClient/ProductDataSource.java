package com.nest5.businessClient;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ProductDataSource {
	
	// Database fields
	  private SQLiteDatabase database;
	  private MySQLiteHelper dbHelper;
	  private String[] allColumns = {Setup.COLUMN_ID,Setup.COLUMN_NAME,Setup.COLUMN_PRODUCT_CATEGORY_ID,Setup.COLUMN_PRODUCT_AUTOMATIC,Setup.COLUMN_PRODUCT_COST,Setup.COLUMN_PRODUCT_PRICE,Setup.COLUMN_PRODUCT_TAX_ID};
	  private Context mContext;

	  public ProductDataSource(Context context) {
	    dbHelper = new MySQLiteHelper(context);
	    mContext = context;
	  }

	  public SQLiteDatabase open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	    return database;
	  }
	  
	  public void open(SQLiteDatabase _database)
	  {
		  database = _database;
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public Product createProduct(String name,ProductCategory category,int automatic,double cost, double price,Tax tax) {
	    ContentValues values = new ContentValues();
	    values.put(Setup.COLUMN_NAME, name);
	    values.put(Setup.COLUMN_PRODUCT_AUTOMATIC, automatic);
	    values.put(Setup.COLUMN_PRODUCT_COST, cost);
	    values.put(Setup.COLUMN_PRODUCT_PRICE, price);
	    values.put(Setup.COLUMN_PRODUCT_CATEGORY_ID, category.getId());
	    values.put(Setup.COLUMN_PRODUCT_TAX_ID, tax.getId());
	    long insertId = database.insert(Setup.TABLE_PRODUCTS, null,
	        values);
	    Cursor cursor = database.query(Setup.TABLE_PRODUCTS,
	        allColumns, Setup.COLUMN_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Product newProduct = cursorToProduct(cursor);
	    cursor.close();
	    return newProduct;
	  }

	  public void deleteProduct(Product product) {
	    long id = product.getId();
	    System.out.println("Product deleted with id: " + id);
	    database.delete(Setup.TABLE_PRODUCTS, Setup.COLUMN_ID
	        + " = " + id, null);
	  }

	  public List<Product> getAllProduct() {
	    List<Product> products = new ArrayList<Product>();

	    Cursor cursor = database.query(Setup.TABLE_PRODUCTS,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Product product = cursorToProduct(cursor);
	      products.add(product);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return products;
	  }
	  
	  public Product getProduct(long id) {
		  Product product = null;
		   Cursor cursor = database.rawQuery("select * from " + Setup.TABLE_PRODUCTS + " where " + Setup.COLUMN_ID + "=" + id  , null);
	        if (cursor != null) 
	        	{
	        		cursor.moveToFirst();
	        		
	      		      product = cursorToProduct(cursor);
	      		      
	      		    // Make sure to close the cursor
	      		    cursor.close();
	        	}
	        return product;
		  }
	  
	  

	  private Product cursorToProduct(Cursor cursor) {
	    Product product = new Product();
	    product.setId(cursor.getLong(0));
	    product.setName(cursor.getString(1));
	    //aqui en vez de poner en el objeto un id, poner un objeto de tipo categoryIngredient
	    //ingredient.setCategoryId(cursor.getLong(2));
	    ProductCategoryDataSource productCategoryDatasource = new ProductCategoryDataSource(mContext);
	    productCategoryDatasource.open();
	    ProductCategory productCategory = productCategoryDatasource.getProductCategory(cursor.getLong(2));
	    productCategoryDatasource.close();
	    product.setCategory(productCategory);
	    product.setCost(cursor.getDouble(4));
	    product.setPrice(cursor.getDouble(5));
	    TaxDataSource taxDatasource = new TaxDataSource(mContext);
	    taxDatasource.open();
	    Tax tax = taxDatasource.getTax(cursor.getLong(6));
	    taxDatasource.close();
	    product.setTax(tax);
	    ProductIngredientDataSource productIngredientDatasource = new ProductIngredientDataSource(mContext);
		productIngredientDatasource.open();
		List<Ingredient> allIngredients = productIngredientDatasource.getAllIngredients(cursor.getLong(0));
		productIngredientDatasource.close();
		product.setIngredients(allIngredients);
	    		
	    return product;
	  }

}