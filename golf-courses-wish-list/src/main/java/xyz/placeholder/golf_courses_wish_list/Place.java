package xyz.placeholder.golf_courses_wish_list;

import android.content.Context;

public class Place {
	public String name;
	public String imageName;

	public int getImageResourceId(Context context) {
		return context.getResources().getIdentifier(this.imageName, "drawable", context.getPackageName());
	}
}
