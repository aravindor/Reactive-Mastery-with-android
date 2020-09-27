package org.lifeautomation.reactivemastery.combinedobservable.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RSSEntry{

	@SerializedName("items")
	private List<FeedItem> items;

	public List<FeedItem> getItems(){
		return items;
	}
}