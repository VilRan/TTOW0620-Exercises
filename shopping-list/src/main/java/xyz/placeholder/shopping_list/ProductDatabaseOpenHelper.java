package xyz.placeholder.shopping_list;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductDatabaseOpenHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "ProductDatabase";
	private final String DATABASE_TABLE = "products";
	private final String NAME = "name";
	private final String COUNT = "count";
	private final String PRICE = "price";

	public ProductDatabaseOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE "+DATABASE_TABLE+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" TEXT, "+COUNT+" INTEGER, "+PRICE+" REAL);");
		ContentValues values = new ContentValues();

		values.put(NAME, "test product");
		values.put(COUNT, 10);
		values.put(PRICE, 5);

		db.insert(DATABASE_TABLE, null, values);
		values.put(NAME, "test product 2");
		values.put(COUNT, 5);
		values.put(PRICE, 15);

		db.insert(DATABASE_TABLE, null, values);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
		onCreate(db);
	}
}

