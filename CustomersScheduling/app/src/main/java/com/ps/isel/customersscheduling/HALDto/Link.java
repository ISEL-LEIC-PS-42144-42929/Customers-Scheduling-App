package com.ps.isel.customersscheduling.HALDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Link implements Serializable {

    private String href;
    private boolean templated;

    public Link() {

    }

    public Link(String href, boolean templated) {
        this.href = href;
        this.templated = templated;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isTemplated() {
        return templated;
    }

    public void setTemplated(boolean template) {
        this.templated = template;
    }
}
