package xyz.placeholder.shopping_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductArrayAdapter extends ArrayAdapter<Product> {
	private Context context;
	private ArrayList<Product> products;

	public ProductArrayAdapter(Context context, ArrayList<Product> products) {
		super(context, R.layout.row_product, products);
		this.context = context;
		this.products = products;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.row_product, parent, false);

		((TextView)rowView.findViewById(R.id.productRowName))
			.setText(products.get(position).getName());
		((TextView)rowView.findViewById(R.id.productRowCount))
			.setText("" + products.get(position).getCount());
		((TextView)rowView.findViewById(R.id.productRowPrice))
			.setText("" + products.get(position).getPrice());

		return rowView;
	}

}
