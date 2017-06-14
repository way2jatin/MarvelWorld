package com.jatin.marvelworld.model.chars;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Thumbnail implements Parcelable {

	@JsonProperty("path")
	private String path;

	public void setPath(String path){
		this.path = path;
	}

	public String getPath(){
		return path;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.path);
	}

	public Thumbnail() {
	}

	protected Thumbnail(Parcel in) {
		this.path = in.readString();
	}

	public static final Parcelable.Creator<Thumbnail> CREATOR = new Parcelable.Creator<Thumbnail>() {
		@Override
		public Thumbnail createFromParcel(Parcel source) {
			return new Thumbnail(source);
		}

		@Override
		public Thumbnail[] newArray(int size) {
			return new Thumbnail[size];
		}
	};
}