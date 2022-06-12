package team2.mobileapp.gplx.Retrofit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoricalExam {
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("license")
    @Expose
    private String license;
    @SerializedName("setname")
    @Expose
    private String setname;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("correct")
    @Expose
    private int correct;
    @SerializedName("total")
    @Expose
    private int total;

    public HistoricalExam() {
    }

    public HistoricalExam(String userid, String license, String setname, String date, int correct, int total) {
        this.userid = userid;
        this.license = license;
        this.setname = setname;
        this.date = date;
        this.correct = correct;
        this.total = total;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getSetname() {
        return setname;
    }

    public void setSetname(String setname) {
        this.setname = setname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "HistoricalExam{" +
                "userid='" + userid + '\'' +
                ", license='" + license + '\'' +
                ", setname='" + setname + '\'' +
                ", date='" + date + '\'' +
                ", correct=" + correct +
                ", total=" + total +
                '}';
    }
}
