package xyz.placeholder.golf_courses_wish_list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	private StaggeredGridLayoutManager mStaggeredLayoutManager;
	private boolean isListView = true;
	// hard coded data
	public static ArrayList<Place> places = Places.placeList();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
		mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
		mRecyclerView.setLayoutManager(mStaggeredLayoutManager);

		GolfCourseWishListAdapter mAdapter = new GolfCourseWishListAdapter(this);
		mRecyclerView.setAdapter(mAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_toggle) {
			if (isListView) {
				item.setIcon(R.drawable.ic_view_stream_black_24dp);
				item.setTitle(R.string.showList);
				isListView = false;
				mStaggeredLayoutManager.setSpanCount(2);
			} else {
				item.setIcon(R.drawable.ic_view_column_black_24dp);
				item.setTitle(R.string.showGrid);
				isListView = true;
				mStaggeredLayoutManager.setSpanCount(1);
			}
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
