package com.nest5.businessClient;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ComboDataSource {
	
	// Database fields
	  private SQLiteDatabase database;
	  private MySQLiteHelper dbHelper;
	  private String[] allColumns = {Setup.COLUMN_ID,Setup.COLUMN_NAME,Setup.COLUMN_COMBO_AUTOMATIC,Setup.COLUMN_COMBO_COST,Setup.COLUMN_COMBO_PRICE,Setup.COLUMN_COMBO_TAX_ID};
	  private Context mContext;

	  public ComboDataSource(Context context) {
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

	  public Combo createCombo(String name,/*ProductCategory category,*/int automatic,double cost, double price,Tax tax) {
	    ContentValues values = new ContentValues();
	    values.put(Setup.COLUMN_NAME, name);
	    values.put(Setup.COLUMN_COMBO_AUTOMATIC, automatic);
	    values.put(Setup.COLUMN_COMBO_COST, cost);
	    values.put(Setup.COLUMN_COMBO_PRICE, price);
	    //values.put(Setup.COLUMN_COMBO_CATEGORY_ID, category.getId());
	    values.put(Setup.COLUMN_COMBO_TAX_ID, tax.getId());
	    long insertId = database.insert(Setup.TABLE_COMBOS, null,
	        values);
	    Cursor cursor = database.query(Setup.TABLE_COMBOS,
	        allColumns, Setup.COLUMN_ID + " = " + insertId, null,
	        null, null, null);
	    cursor.moveToFirst();
	    Combo newCombo = cursorToCombo(cursor);
	    cursor.close();
	    return newCombo;
	  }

	  public void deleteProduct(Product product) {
	    long id = product.getId();
	    System.out.println("Combo deleted with id: " + id);
	    database.delete(Setup.TABLE_COMBOS, Setup.COLUMN_ID
	        + " = " + id, null);
	  }

	  public List<Combo> getAllCombos() {
	    List<Combo> combos = new ArrayList<Combo>();

	    Cursor cursor = database.query(Setup.TABLE_COMBOS,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Combo combo = cursorToCombo(cursor);
	      combos.add(combo);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return combos;
	  }
	  
	  public Combo getCombo(long id) {
		  Combo combo = null;
		   Cursor cursor = database.rawQuery("select * from " + Setup.TABLE_COMBOS + " where " + Setup.COLUMN_ID + "=" + id  , null);
	        if (cursor != null) 
	        	{
	        		cursor.moveToFirst();
	        		
	      		      combo = cursorToCombo(cursor);
	      		      
	      		    // Make sure to close the cursor
	      		    cursor.close();
	        	}
	        return combo;
		  }
	  
	  

	  private Combo cursorToCombo(Cursor cursor) {
	    Combo combo = new Combo();
	    combo.setId(cursor.getLong(0));
	    combo.setName(cursor.getString(1));
	    //aqui en vez de poner en el objeto un id, poner un objeto de tipo categoryIngredient
	    //ingredient.setCategoryId(cursor.getLong(2));
	    //ProductCategoryDataSource productCategoryDatasource = new ProductCategoryDataSource(mContext);
	    //productCategoryDatasource.open();
	    //ProductCategory productCategory = productCategoryDatasource.getProductCategory(cursor.getLong(2));
	    //productCategoryDatasource.close();
	    //product.setCategory(productCategory);
	    combo.setCost(cursor.getDouble(3));
	    combo.setPrice(cursor.getDouble(4));
	    TaxDataSource taxDatasource = new TaxDataSource(mContext);
	    taxDatasource.open();
	    Tax tax = taxDatasource.getTax(cursor.getLong(5));
	    taxDatasource.close();
	    combo.setTax(tax);
	    ComboIngredientDataSource comboIngredientDatasource = new ComboIngredientDataSource(mContext);
		comboIngredientDatasource.open();
		List<Ingredient> allIngredients = comboIngredientDatasource.getAllIngredients(cursor.getLong(0));
		comboIngredientDatasource.close();
		combo.setIngredients(allIngredients);
		ComboProductDataSource comboProductDatasource = new ComboProductDataSource(mContext);
		comboProductDatasource.open();
		List<Product> allProducts = comboProductDatasource.getAllProducts(cursor.getLong(0));
		comboProductDatasource.close();
		combo.setProducts(allProducts);
	    		
	    return combo;
	  }

}