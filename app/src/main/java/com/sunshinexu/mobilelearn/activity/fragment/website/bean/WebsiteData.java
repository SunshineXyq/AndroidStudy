package com.sunshinexu.mobilelearn.activity.fragment.website.bean;

import java.io.Serializable;

public class WebsiteData implements Serializable {
    private int id;
    private String name;
    private String link;
    private int visible;
    private int order;
    private String icon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "WebsiteData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", visible=" + visible +
                ", order=" + order +
                ", icon='" + icon + '\'' +
                '}';
    }
}
