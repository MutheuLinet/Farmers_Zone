package com.farmersgroup.farmerszone.models;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BrowseAllResponse {

    @SerializedName("results")
    @Expose
    private List<ResultById> results = null;
    @SerializedName("tfvcount")
    @Expose
    private Integer tfvcount;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BrowseAllResponse() {
    }

    /**
     * 
     * @param tfvcount
     * @param results
     */
    public BrowseAllResponse(List<ResultById> results, Integer tfvcount) {
        super();
        this.results = results;
        this.tfvcount = tfvcount;
    }

    public List<ResultById> getResults() {
        return results;
    }

    public void setResults(List<ResultById> results) {
        this.results = results;
    }

    public Integer getTfvcount() {
        return tfvcount;
    }

    public void setTfvcount(Integer tfvcount) {
        this.tfvcount = tfvcount;
    }

}
