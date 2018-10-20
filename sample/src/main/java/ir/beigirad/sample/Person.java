package ir.beigirad.sample;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Parameter;

public class Person{


	private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(String channel_id) {
        this.channel_id = channel_id;
    }

    public String getIsHD() {
        return isHD;
    }

    public void setIsHD(String isHD) {
        this.isHD = isHD;
    }

    public Person(String name, String link, String category, String language, String icon, String channel_id, String isHD) {
        this.name = name;
        this.link = link;
        this.category = category;
        this.language = language;
        this.icon = icon;
        this.channel_id = channel_id;
        this.isHD = isHD;
    }

    private String link;
    private String category;
    private String language;
    private String icon;
    private String channel_id;
    private String isHD;



}