package com.jatin.marvelworld.model.comics;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Creators implements Parcelable {

	@JsonProperty("collectionURI")
	private String collectionURI;

	@JsonProperty("available")
	private int available;

	@JsonProperty("returned")
	private int returned;

	@JsonProperty("items")
	private ArrayList<ItemsItem> items = null;

	public void setCollectionURI(String collectionURI){
		this.collectionURI = collectionURI;
	}

	public String getCollectionURI(){
		return collectionURI;
	}

	public void setAvailable(int available){
		this.available = available;
	}

	public int getAvailable(){
		return available;
	}

	public void setReturned(int returned){
		this.returned = returned;
	}

	public int getReturned(){
		return returned;
	}

	public void setItems(ArrayList<ItemsItem> items){
		this.items = items;
	}

	public ArrayList<ItemsItem> getItems(){
		return items;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.collectionURI);
		dest.writeInt(this.available);
		dest.writeInt(this.returned);
		dest.writeList(this.items);
	}

	public Creators() {
	}

	protected Creators(Parcel in) {
		this.collectionURI = in.readString();
		this.available = in.readInt();
		this.returned = in.readInt();
		this.items = new ArrayList<ItemsItem>();
		in.readList(this.items, ItemsItem.class.getClassLoader());
	}

	public static final Parcelable.Creator<Creators> CREATOR = new Parcelable.Creator<Creators>() {
		@Override
		public Creators createFromParcel(Parcel source) {
			return new Creators(source);
		}

		@Override
		public Creators[] newArray(int size) {
			return new Creators[size];
		}
	};
}