package com.jatin.marvelworld.model.comics;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemsItem implements Parcelable {

	@JsonProperty("name")
	private String name;

	@JsonProperty("resourceURI")
	private String resourceURI;

	@JsonProperty("type")
	private String type;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setResourceURI(String resourceURI){
		this.resourceURI = resourceURI;
	}

	public String getResourceURI(){
		return resourceURI;
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
		dest.writeString(this.name);
		dest.writeString(this.resourceURI);
		dest.writeString(this.type);
	}

	public ItemsItem() {
	}

	protected ItemsItem(Parcel in) {
		this.name = in.readString();
		this.resourceURI = in.readString();
		this.type = in.readString();
	}

	public static final Parcelable.Creator<ItemsItem> CREATOR = new Parcelable.Creator<ItemsItem>() {
		@Override
		public ItemsItem createFromParcel(Parcel source) {
			return new ItemsItem(source);
		}

		@Override
		public ItemsItem[] newArray(int size) {
			return new ItemsItem[size];
		}
	};
}