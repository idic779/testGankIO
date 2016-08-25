package com.example.administrator.testgankio.model;

import java.util.List;

/**
 * Created by Administrator on 2016/6/2.
 */
public class DataResults {
    private boolean error;
    private List<Results> results;
    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }




}
