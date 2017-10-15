package xyz.placeholder.golf_courses_wish_list;

import android.content.Context;

class Place {
	public String name;
	String imageName;

	int getImageResourceId(Context context) {
		return context.getResources().getIdentifier(this.imageName, "drawable", context.getPackageName());
	}
}
