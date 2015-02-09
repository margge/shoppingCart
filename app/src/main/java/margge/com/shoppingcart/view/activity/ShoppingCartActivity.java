package margge.com.shoppingcart.view.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import margge.com.shoppingcart.R;
import margge.com.shoppingcart.adapter.ShoppingCartAdapter;
import margge.com.shoppingcart.entities.Product;
import margge.com.shoppingcart.listener.BuyProductListener;
import margge.com.shoppingcart.persistence.PersistenceHelper;

public class ShoppingCartActivity extends Activity implements BuyProductListener {

    private ListView mLvProducts;
    private ArrayList<Product> mProductsList;
    private ShoppingCartAdapter mAdapter;
    private PersistenceHelper mPersistenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart_activity);

        initData();
        initViewComponents();

        mAdapter = new ShoppingCartAdapter(this, mProductsList);
        mAdapter.setListener(this);
        mLvProducts.setAdapter(mAdapter);
    }

    private void initData() {
        mProductsList = new ArrayList<Product>();
        mProductsList = (ArrayList<Product>)getIntent().getSerializableExtra("stock_list");

        mPersistenceHelper = new PersistenceHelper(this, "shoppingcart", null, 1);
    }

    private void initViewComponents() {
        mLvProducts = (ListView) findViewById(R.id.ltv_shopping_cart);
    }

    @Override
    public void updateProduct(Product product) {
        for (int i = 0; i < mProductsList.size(); i++) {
            Product productOnList = mProductsList.get(i);

            if (productOnList.getName().equalsIgnoreCase(product.getName())) {
                mProductsList.remove(i);
            }
        }
        int stock = product.getStock();
        stock = stock +1;
        product.setStock(stock);
        updateDatabase(product);
        mAdapter.notifyDataSetChanged();
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
}