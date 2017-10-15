package xyz.placeholder.shopping_list;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity
	extends AppCompatActivity
	implements ProductFragment.OnFragmentInteractionListener {

	private ArrayList<Product> products = new ArrayList<>();
	private final String DATABASE_TABLE = "products";
	private final int DELETE_ID = 0;
	private SQLiteDatabase db;
	private Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		db = (new ProductDatabaseOpenHelper(this)).getWritableDatabase();

		Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
		setSupportActionBar(myToolbar);

		queryData();

		((ListView) findViewById(R.id.list)).setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

				return false;
			}
		});

		//ProductArrayAdapter adapter = new ProductArrayAdapter(this, products);
		//((ListView) findViewById(R.id.list)).setAdapter(adapter);
	}

	public void queryData() {
		String[] resultColumns = new String[]{"_id","name","count","price"};
		cursor = db.query(DATABASE_TABLE,resultColumns,null,null,null,null,"name DESC",null);

		ListAdapter adapter = new SimpleCursorAdapter(this,
			R.layout.row_product, cursor,
			new String[] {"name", "count", "price"},
			new int[] {R.id.productRowName, R.id.productRowCount, R.id.productRowPrice}
			,0
		);
		((ListView) findViewById(R.id.list)).setAdapter(adapter);

		float total = 0f;
		if (cursor.moveToFirst()) {
			do {
				float price = cursor.getFloat(2) * cursor.getFloat(3);
				total += price;
			} while(cursor.moveToNext());
			Toast.makeText(getBaseContext(), "Total price: " + total, Toast.LENGTH_SHORT).show();
		}
	}

	public void onDialogPositiveClick(DialogFragment dialog, String product, int count, double price) {
		//products.add(new Product(product, count, price));
		ContentValues values=new ContentValues(3);
		values.put("name", product);
		values.put("count", count);
		values.put("price", price);
		db.insert(DATABASE_TABLE, null, values);
		queryData();
	}

	public void onDialogNegativeClick(DialogFragment dialog) {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.toolbar, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.add:
				ProductFragment eDialog = new ProductFragment();
				eDialog.show(getFragmentManager(), "Text Dialog");
				return true;
		}
		return false;
	}
}
