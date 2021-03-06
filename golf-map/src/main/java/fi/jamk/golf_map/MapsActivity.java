package fi.jamk.golf_map;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
	private GoogleMap mMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);
		// Obtain the SupportMapFragment and get notified when the map is ready to be used.
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
			.findFragmentById(R.id.map);
		mapFragment.getMapAsync(this);
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		final Context context = this;
		mMap = googleMap;
		mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
			@Override
			public View getInfoWindow(Marker marker) {
				return null;
			}

			@Override
			public View getInfoContents(Marker marker) {
				LinearLayout info = new LinearLayout(context);
				info.setOrientation(LinearLayout.VERTICAL);

				TextView title = new TextView(context);
				title.setTextColor(Color.BLACK);
				title.setGravity(Gravity.CENTER);
				title.setTypeface(null, Typeface.BOLD);
				title.setText(marker.getTitle());

				TextView snippet = new TextView(context);
				snippet.setTextColor(Color.GRAY);
				snippet.setText(marker.getSnippet());

				info.addView(title);
				info.addView(snippet);

				return info;
			}
		});

		FetchDataTask task = new FetchDataTask();
		task.execute("http://ptm.fi/materials/golfcourses/golf_courses.json");
	}

	private class FetchDataTask extends AsyncTask<String, Void, JSONObject> {
		@Override
		protected JSONObject doInBackground(String... urls) {
			HttpURLConnection urlConnection = null;
			JSONObject json = null;
			try {
				URL url = new URL(urls[0]);
				urlConnection = (HttpURLConnection) url.openConnection();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
				StringBuilder stringBuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					stringBuilder.append(line).append("\n");
				}
				bufferedReader.close();
				json = new JSONObject(stringBuilder.toString());
			} catch (IOException | JSONException e) {
				e.printStackTrace();
			} finally {
				if (urlConnection != null) urlConnection.disconnect();
			}
			return json;
		}

		protected void onPostExecute(JSONObject json) {
			try {
				LatLng latLng = new LatLng(0, 0);
				JSONArray courses = json.getJSONArray("courses");
				for (int i = 0; i < courses.length(); i++) {
					JSONObject course = courses.getJSONObject(i);
					String title = course.getString("course");
					double lat = course.getDouble("lat");
					double lng = course.getDouble("lng");
					latLng = new LatLng(lat, lng);
					float hue = BitmapDescriptorFactory.HUE_BLUE;
					switch (course.getString("type")) {
						case "Kulta/Etu":
							hue = BitmapDescriptorFactory.HUE_RED;
							break;
						case "Kulta":
							hue = BitmapDescriptorFactory.HUE_YELLOW;
							break;
						case "Etu":
							hue = BitmapDescriptorFactory.HUE_GREEN;
							break;
					}

					String snippet =
						course.getString("address") + "\n"
						+ course.getString("phone") + "\n"
						+ course.getString("email") + "\n"
						+ course.getString("web") + "\n";

					mMap.addMarker(new MarkerOptions()
						.title(title)
						.icon(BitmapDescriptorFactory.defaultMarker(hue))
						.position(latLng)
						.snippet(snippet)
					);
				}

				mMap.moveCamera(CameraUpdateFactory.zoomTo(5f));
				mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
			} catch (JSONException e) {
				Log.e("JSON", "Error getting data.");
			}
		}
	}
}
