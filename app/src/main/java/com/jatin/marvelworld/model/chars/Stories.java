package com.jatin.marvelworld.model.chars;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stories implements Parcelable {

	@JsonProperty("collectionURI")
	private String collectionURI;

	@JsonProperty("available")
	private int available;

	@JsonProperty("returned")
	private int returned;

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



	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.available);
	}

	public Stories() {
	}

	protected Stories(Parcel in) {
		this.available = in.readInt();
	}

	public static final Parcelable.Creator<Stories> CREATOR = new Parcelable.Creator<Stories>() {
		@Override
		public Stories createFromParcel(Parcel source) {
			return new Stories(source);
		}

		@Override
		public Stories[] newArray(int size) {
			return new Stories[size];
		}
	};
}