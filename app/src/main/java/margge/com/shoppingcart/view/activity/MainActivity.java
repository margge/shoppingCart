package margge.com.shoppingcart.view.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import margge.com.shoppingcart.R;
import margge.com.shoppingcart.adapter.ProductAdapter;
import margge.com.shoppingcart.entities.Product;
import margge.com.shoppingcart.listener.BuyProductListener;
import margge.com.shoppingcart.persistence.PersistenceHelper;

public class MainActivity extends Activity implements BuyProductListener, View.OnClickListener {

    private ListView mLvProducts;
    private TextView txtCounter;
    private TextView mTxtItems;
    private TextView mTxtTotal;
    private ArrayList<Product> mProductsList;
    private ArrayList<Product> mCartProductsList;
    private PersistenceHelper mPersistenceHelper;
    private ProductAdapter mAdapter;
    private int mBoughtItems;
    private int mPriceItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        initData();
        initViewComponents();

        mAdapter = new ProductAdapter(this, mProductsList);
        mAdapter.setListener(this);
        mLvProducts.setAdapter(mAdapter);
    }

    private void initData() {
        mProductsList = new ArrayList<Product>();
        mCartProductsList = new ArrayList<Product>();
        mBoughtItems = 0;
        mPriceItems = 0;

        mPersistenceHelper = new PersistenceHelper(this, "shoppingcart", null, 1);
        setDataFromDatabase();
    }

    private void initViewComponents() {
        mLvProducts = (ListView) findViewById(R.id.ltv_products);
        txtCounter = (TextView) findViewById(R.id.txt_counter);
        txtCounter.setOnClickListener(this);
        mTxtItems = (TextView) findViewById(R.id.txt_items);
        mTxtTotal = (TextView) findViewById(R.id.txt_total);
    }

    private void setDataFromDatabase() {
        SQLiteDatabase db = mPersistenceHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT name, price, stock FROM Data", null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setName(cursor.getString(0));
                product.setPrice(cursor.getInt(1));
                product.setStock(cursor.getInt(2));

                mProductsList.add(product);

            } while (cursor.moveToNext());
        }
    }

    private void updateDatabase(Product product) {
        SQLiteDatabase db = mPersistenceHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", product.getName());
        values.put("price", product.getPrice());
        values.put("stock", product.getStock());
        String[] strings = new String[]{product.getName()};
        db.update("Data", values, "name = ?", strings);
        db.close();
    }

    @Override
    public void updateProduct(Product product) {
        for (int i = 0; i < mProductsList.size(); i++) {
            Product productOnList = mProductsList.get(i);

            if (productOnList.getName().equalsIgnoreCase(product.getName())) {
                mProductsList.set(i, product);
            }
        }

        mCartProductsList.add(product);

        mBoughtItems++;
        mTxtItems.setText(String.valueOf(mBoughtItems));
        mPriceItems = mPriceItems + product.getPrice();
        mTxtTotal.setText(String.valueOf(mPriceItems));

        mAdapter.notifyDataSetChanged();

        updateDatabase(product);
    }

    @Override
    public void onClick(View v) {
        if (mCartProductsList.size() > 0) {
            Intent intent = new Intent(this, ShoppingCartActivity.class);
            intent.putParcelableArrayListExtra("stock_list", mCartProductsList);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Aún no ha realizado compras", Toast.LENGTH_SHORT).show();
        }
    }
}