package com.jatin.marvelworld.model.comics;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result implements Parcelable {

	@JsonProperty("creators")
	private Creators creators;

	@JsonProperty("issueNumber")
	private int issueNumber;

	@JsonProperty("isbn")
	private String isbn;

	@JsonProperty("description")
	private String description;

	@JsonProperty("title")
	private String title;

	@JsonProperty("diamondCode")
	private String diamondCode;

	@JsonProperty("ean")
	private String ean;

	@JsonProperty("modified")
	private String modified;

	@JsonProperty("id")
	private int id;

	@JsonProperty("prices")
	private List<PricesItem> prices;

	@JsonProperty("pageCount")
	private int pageCount;

	@JsonProperty("thumbnail")
	private Thumbnail thumbnail;

	@JsonProperty("digitalId")
	private int digitalId;

	@JsonProperty("format")
	private String format;

	@JsonProperty("upc")
	private String upc;

	@JsonProperty("dates")
	private List<DatesItem> dates;

	@JsonProperty("resourceURI")
	private String resourceURI;

	@JsonProperty("variantDescription")
	private String variantDescription;

	@JsonProperty("issn")
	private String issn;

	public void setCreators(Creators creators){
		this.creators = creators;
	}

	public Creators getCreators(){
		return creators;
	}

	public void setIssueNumber(int issueNumber){
		this.issueNumber = issueNumber;
	}

	public int getIssueNumber(){
		return issueNumber;
	}

	public void setIsbn(String isbn){
		this.isbn = isbn;
	}

	public String getIsbn(){
		return isbn;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setDiamondCode(String diamondCode){
		this.diamondCode = diamondCode;
	}

	public String getDiamondCode(){
		return diamondCode;
	}

	public void setEan(String ean){
		this.ean = ean;
	}

	public String getEan(){
		return ean;
	}

	public void setModified(String modified){
		this.modified = modified;
	}

	public String getModified(){
		return modified;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setPrices(List<PricesItem> prices){
		this.prices = prices;
	}

	public List<PricesItem> getPrices(){
		return prices;
	}

	public void setPageCount(int pageCount){
		this.pageCount = pageCount;
	}

	public int getPageCount(){
		return pageCount;
	}

	public Thumbnail getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(Thumbnail thumbnail){
		this.thumbnail = thumbnail;
	}


	public void setDigitalId(int digitalId){
		this.digitalId = digitalId;
	}

	public int getDigitalId(){
		return digitalId;
	}

	public void setFormat(String format){
		this.format = format;
	}

	public String getFormat(){
		return format;
	}

	public void setUpc(String upc){
		this.upc = upc;
	}

	public String getUpc(){
		return upc;
	}

	public void setDates(List<DatesItem> dates){
		this.dates = dates;
	}

	public List<DatesItem> getDates(){
		return dates;
	}

	public void setResourceURI(String resourceURI){
		this.resourceURI = resourceURI;
	}

	public String getResourceURI(){
		return resourceURI;
	}

	public void setVariantDescription(String variantDescription){
		this.variantDescription = variantDescription;
	}

	public String getVariantDescription(){
		return variantDescription;
	}

	public void setIssn(String issn){
		this.issn = issn;
	}

	public String getIssn(){
		return issn;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this.creators, flags);
		dest.writeInt(this.issueNumber);
		dest.writeString(this.isbn);
		dest.writeString(this.description);
		dest.writeString(this.title);
		dest.writeString(this.diamondCode);
		dest.writeString(this.ean);
		dest.writeString(this.modified);
		dest.writeInt(this.id);
		dest.writeList(this.prices);
		dest.writeInt(this.pageCount);
		dest.writeParcelable(this.thumbnail, flags);
		dest.writeInt(this.digitalId);
		dest.writeString(this.format);
		dest.writeString(this.upc);
		dest.writeList(this.dates);
		dest.writeString(this.resourceURI);
		dest.writeString(this.variantDescription);
		dest.writeString(this.issn);
	}

	public Result() {
	}

	protected Result(Parcel in) {
		this.creators = in.readParcelable(Creators.class.getClassLoader());
		this.issueNumber = in.readInt();
		this.isbn = in.readString();
		this.description = in.readString();
		this.title = in.readString();
		this.diamondCode = in.readString();
		this.ean = in.readString();
		this.modified = in.readString();
		this.id = in.readInt();
		this.prices = new ArrayList<PricesItem>();
		in.readList(this.prices, PricesItem.class.getClassLoader());
		this.pageCount = in.readInt();
		this.thumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
		this.digitalId = in.readInt();
		this.format = in.readString();
		this.upc = in.readString();
		this.dates = new ArrayList<DatesItem>();
		in.readList(this.dates, DatesItem.class.getClassLoader());
		this.resourceURI = in.readString();
		this.variantDescription = in.readString();
		this.issn = in.readString();
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