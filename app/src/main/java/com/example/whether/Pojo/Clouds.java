package com.example.whether.Pojo;

/**
 * Created by Полина on 17.11.2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clouds {

    @SerializedName("all")
    @Expose
    private Integer all;

    /**
     *
     * @return
     *     The all
     */
    public Integer getAll() {
        return all;
    }

    /**
     *
     * @param all
     *     The all
     */
    public void setAll(Integer all) {
        this.all = all;
    }

}
