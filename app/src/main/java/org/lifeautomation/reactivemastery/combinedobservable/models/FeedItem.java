package org.lifeautomation.reactivemastery.combinedobservable.models;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FeedItem implements Comparable<FeedItem> {

	@SerializedName("link")
	private String link;

	@SerializedName("title")
	private String title;

	@SerializedName("pubDate")
	private String pubDate;

	public String getLink(){
		return link;
	}

	public String getTitle(){
		return title;
	}

	public String getPubDate(){
		return pubDate;
	}

	@Override
	public int compareTo(FeedItem another) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		try {
			Date thisDate = dateFormat.parse(this.pubDate);
			Date anotherDate = dateFormat.parse(another.pubDate);

			if (thisDate.compareTo(anotherDate)>0){
				return -1;
			}else return 1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
}