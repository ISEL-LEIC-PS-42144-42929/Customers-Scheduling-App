package com.ps.isel.customersscheduling.HALDto.links;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ps.isel.customersscheduling.HALDto.Link;

import java.io.Serializable;


public class SelfLink implements Serializable {

    private Link self;

    public SelfLink() {}

    public SelfLink(Link self) {
        this.self = self;

    }

    public Link getSelf() {
        return self;
    }

    public void setSelf(Link link) {
        this.self = link;
    }


}
