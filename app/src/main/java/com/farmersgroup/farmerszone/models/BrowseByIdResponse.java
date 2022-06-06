
package com.farmersgroup.farmerszone.models;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class BrowseByIdResponse implements Serializable {

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
    public BrowseByIdResponse() {
    }

    /**
     * 
     * @param tfvcount
     * @param results
     */
    public BrowseByIdResponse(List<ResultById> results, Integer tfvcount) {
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
