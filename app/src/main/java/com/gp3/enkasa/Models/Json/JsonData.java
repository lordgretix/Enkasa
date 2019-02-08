package com.gp3.enkasa.Models.Json;

import com.google.gson.annotations.SerializedName;
import com.gp3.enkasa.Models.Json.Models.Data;
import com.gp3.enkasa.Models.Json.Models.User;

public class JsonData {

    @SerializedName("user")
    private User user;
    @SerializedName("data")
    private Data data;
    @SerializedName("error")
    private String error;

    public JsonData() {
    }

    public JsonData(User user, Data data) {
        this.user = user;
        this.data = data;
    }

    public JsonData(String error) {
        this.error = error;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean hashError() {
        return this.error != null;
    }
}
