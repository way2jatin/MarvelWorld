package com.jatin.marvelworld.model.comics;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PricesItem implements Parcelable {

	@JsonProperty("price")
	private double price;

	@JsonProperty("type")
	private String type;

	public void setPrice(double price){
		this.price = price;
	}

	public double getPrice(){
		return price;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeDouble(this.price);
		dest.writeString(this.type);
	}

	public PricesItem() {
	}

	protected PricesItem(Parcel in) {
		this.price = in.readDouble();
		this.type = in.readString();
	}

	public static final Parcelable.Creator<PricesItem> CREATOR = new Parcelable.Creator<PricesItem>() {
		@Override
		public PricesItem createFromParcel(Parcel source) {
			return new PricesItem(source);
		}

		@Override
		public PricesItem[] newArray(int size) {
			return new PricesItem[size];
		}
	};
}