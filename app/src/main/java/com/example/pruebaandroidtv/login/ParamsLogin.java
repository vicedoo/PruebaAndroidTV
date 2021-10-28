
package com.example.pruebaandroidtv.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ParamsLogin {

    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("pass")
    @Expose
    private String pass;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ParamsLogin() {
    }

    /**
     * 
     * @param pass
     * @param user
     */
    public ParamsLogin(String user, String pass) {
        super();
        this.user = user;
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
