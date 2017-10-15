package xyz.placeholder.golf_courses_wish_list;

import java.util.ArrayList;

class Places {
	private static String[] placeNameArray = {
		"Black Mountain", "Chambers Bay", "Clear Water", "Harbour Town",
		"Muirfield", "Old Course", "Pebble Beach", "Spy Class", "Turtle Bay"
	};

	static ArrayList<Place> placeList() {
		ArrayList<Place> list = new ArrayList<>();
		for (String placeName : placeNameArray) {
			Place place = new Place();
			place.name = placeName;
			place.imageName = placeName.replaceAll("\\s+", "").toLowerCase();
			list.add(place);
		}
		return list;
	}
}
