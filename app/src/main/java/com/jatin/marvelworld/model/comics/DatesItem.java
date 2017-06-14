package com.jatin.marvelworld.model.comics;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatesItem implements Parcelable {

	@JsonProperty("date")
	private String date;

	@JsonProperty("type")
	private String type;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
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
		dest.writeString(this.date);
		dest.writeString(this.type);
	}

	public DatesItem() {
	}

	protected DatesItem(Parcel in) {
		this.date = in.readString();
		this.type = in.readString();
	}

	public static final Parcelable.Creator<DatesItem> CREATOR = new Parcelable.Creator<DatesItem>() {
		@Override
		public DatesItem createFromParcel(Parcel source) {
			return new DatesItem(source);
		}

		@Override
		public DatesItem[] newArray(int size) {
			return new DatesItem[size];
		}
	};
}