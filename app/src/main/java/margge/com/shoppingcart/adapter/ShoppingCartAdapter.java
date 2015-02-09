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

public class ShoppingCartAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Product> mProductsList;
    private BuyProductListener mListener;

    public ShoppingCartAdapter(Context context, ArrayList<Product> products) {
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
            convertView = mInflater.inflate(R.layout.shopping_cart_adapter, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Product product = mProductsList.get(position);
        holder.txtNameProduct.setText(product.getName());
        holder.btnDeleteProduct.setTag(product);
        holder.btnDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product p = (Product) v.getTag();
                mListener.updateProduct(p);
            }
        });

        return convertView;
    }

    public void setListener(BuyProductListener mListener) {
        this.mListener = mListener;
    }

    static class ViewHolder {

        @InjectView(R.id.txt_name_product) TextView txtNameProduct;
        @InjectView(R.id.btn_delete_product) TextView btnDeleteProduct;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
