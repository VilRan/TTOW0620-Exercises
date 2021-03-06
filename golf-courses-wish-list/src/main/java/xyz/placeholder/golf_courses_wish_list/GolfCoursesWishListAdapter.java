package xyz.placeholder.golf_courses_wish_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

class GolfCourseWishListAdapter extends RecyclerView.Adapter<GolfCourseWishListAdapter.ViewHolder> {
	private Context mContext;

	GolfCourseWishListAdapter(Context context) {
		this.mContext = context;
	}

	class ViewHolder extends RecyclerView.ViewHolder {
		LinearLayout placeNameHolder;
		TextView placeName;
		ImageView placeImage;

		ViewHolder(View itemView) {
			super(itemView);
			placeName = itemView.findViewById(R.id.placeName);
			placeNameHolder = itemView.findViewById(R.id.placeNameHolder);
			placeImage = itemView.findViewById(R.id.placeImage);
		}
	}

	@Override
	public int getItemCount() {
		return MainActivity.places.size();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_places, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(final ViewHolder holder, final int position) {
		final Place place = MainActivity.places.get(position);
		holder.placeName.setText(place.name);
		Glide.with(mContext).load(place.getImageResourceId(mContext)).into(holder.placeImage);
	}

}
