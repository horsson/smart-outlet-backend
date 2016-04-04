package de.haohu.smartoutlet.model;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by d058856 on 29/03/16.
 */
public class ResponseJsonData implements Serializable {

    private int statusCode;
    private String message;

    protected transient Gson gson;

    public String toJson(){
        return gson.toJson(this);
    }


    private ResponseJsonData(){}

    private ResponseJsonData(Builder builder){
        this.gson = new Gson();
        this.statusCode = builder.statusCode;
        this.message = builder.message;
    }


    //Builder class
    public static class Builder{
        private  int statusCode;
        private  String message;

        public Builder statusCode(int statusCode){
            this.statusCode = statusCode;
            return this;
        }

        public Builder  message(String message){
            this.message = message;
            return this;
        }

        public ResponseJsonData build(){
            return  new ResponseJsonData(this);
        }
    }

}
