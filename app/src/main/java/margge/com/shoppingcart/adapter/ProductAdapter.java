package margge.com.shoppingcart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import margge.com.shoppingcart.R;
import margge.com.shoppingcart.entities.Product;
import margge.com.shoppingcart.listener.BuyProductListener;

public class ProductAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Product> mProductsList;
    private BuyProductListener mListener;

    public ProductAdapter(Context context, ArrayList<Product> products) {
        mInflater = LayoutInflater.from(context);
        mProductsList = products;
    }

    @Override
    public int getCount() {
        return mProductsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.product_adapter, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Product product = mProductsList.get(position);
        holder.txtNameProduct.setText(product.getName());
        holder.txtPriceProduct.setText(String.valueOf(product.getPrice()));
        holder.txtStockProduct.setText(String.valueOf(product.getStock()));
        holder.btnBuyProduct.setTag(product);
        holder.btnBuyProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product p = (Product) v.getTag();
                int stock = p.getStock();
                if (stock > 0) {
                    stock--;
                    product.setStock(stock);
                    mListener.updateProduct(p);
                }
            }
        });

        return convertView;
    }

    public void setListener(BuyProductListener mListener) {
        this.mListener = mListener;
    }

    static class ViewHolder {

        @InjectView(R.id.txt_name_product) TextView txtNameProduct;
        @InjectView(R.id.txt_price_product) TextView txtPriceProduct;
        @InjectView(R.id.txt_stock_product) TextView txtStockProduct;
        @InjectView(R.id.btn_buy_product) TextView btnBuyProduct;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
