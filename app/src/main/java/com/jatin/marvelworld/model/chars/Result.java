package com.jatin.marvelworld.model.chars;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result implements Parcelable {

	@JsonProperty("thumbnail")
	private Thumbnail thumbnail;

	@JsonProperty("series")
	private Series series;

	@JsonProperty("comics")
	private Comics comics;

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	@JsonProperty("id")
	private int id;

	public void setThumbnail(Thumbnail thumbnail){
		this.thumbnail = thumbnail;
	}

	public Thumbnail getThumbnail(){
		return thumbnail;
	}

	public void setSeries(Series series){
		this.series = series;
	}

	public Series getSeries(){
		return series;
	}

	public void setComics(Comics comics){
		this.comics = comics;
	}

	public Comics getComics(){
		return comics;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this.thumbnail, flags);
		dest.writeParcelable(this.series, flags);
		dest.writeParcelable(this.comics, flags);
		dest.writeString(this.name);
		dest.writeString(this.description);
		dest.writeInt(this.id);
	}

	public Result() {
	}

	protected Result(Parcel in) {
		this.thumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
		this.series = in.readParcelable(Series.class.getClassLoader());
		this.comics = in.readParcelable(Comics.class.getClassLoader());
		this.name = in.readString();
		this.description = in.readString();
		this.id = in.readInt();
	}

	public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>() {
		@Override
		public Result createFromParcel(Parcel source) {
			return new Result(source);
		}

		@Override
		public Result[] newArray(int size) {
			return new Result[size];
		}
	};
}