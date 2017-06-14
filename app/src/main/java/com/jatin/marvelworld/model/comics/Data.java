package com.jatin.marvelworld.model.comics;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data{

	@JsonProperty("total")
	private int total;

	@JsonProperty("offset")
	private int offset;

	@JsonProperty("limit")
	private int limit;

	@JsonProperty("count")
	private int count;

	@JsonProperty("results")
	private List<Result> results;

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setOffset(int offset){
		this.offset = offset;
	}

	public int getOffset(){
		return offset;
	}

	public void setLimit(int limit){
		this.limit = limit;
	}

	public int getLimit(){
		return limit;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	public void setResults(List<Result> results){
		this.results = results;
	}

	public List<Result> getResults(){
		return results;
	}
}