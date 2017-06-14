package com.jatin.marvelworld.model.comics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicResponse{

	@JsonProperty("copyright")
	private String copyright;

	@JsonProperty("code")
	private int code;

	@JsonProperty("data")
	private Data data;

	@JsonProperty("attributionHTML")
	private String attributionHTML;

	@JsonProperty("attributionText")
	private String attributionText;

	@JsonProperty("etag")
	private String etag;

	@JsonProperty("status")
	private String status;

	public void setCopyright(String copyright){
		this.copyright = copyright;
	}

	public String getCopyright(){
		return copyright;
	}

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setAttributionHTML(String attributionHTML){
		this.attributionHTML = attributionHTML;
	}

	public String getAttributionHTML(){
		return attributionHTML;
	}

	public void setAttributionText(String attributionText){
		this.attributionText = attributionText;
	}

	public String getAttributionText(){
		return attributionText;
	}

	public void setEtag(String etag){
		this.etag = etag;
	}

	public String getEtag(){
		return etag;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}