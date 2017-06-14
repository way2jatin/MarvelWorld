package com.jatin.marvelworld.model.comics;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Characters implements Parcelable {

	@JsonProperty("collectionURI")
	private String collectionURI;

	@JsonProperty("available")
	private int available;

	@JsonProperty("returned")
	private int returned;

	@JsonProperty("items")
	private List<Object> items = null;

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

	public void setItems(List<Object> items){
		this.items = items;
	}

	public List<Object> getItems(){
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

	public Characters() {
	}

	protected Characters(Parcel in) {
		this.collectionURI = in.readString();
		this.available = in.readInt();
		this.returned = in.readInt();
		this.items = new ArrayList<Object>();
		in.readList(this.items, Object.class.getClassLoader());
	}

	public static final Parcelable.Creator<Characters> CREATOR = new Parcelable.Creator<Characters>() {
		@Override
		public Characters createFromParcel(Parcel source) {
			return new Characters(source);
		}

		@Override
		public Characters[] newArray(int size) {
			return new Characters[size];
		}
	};
}