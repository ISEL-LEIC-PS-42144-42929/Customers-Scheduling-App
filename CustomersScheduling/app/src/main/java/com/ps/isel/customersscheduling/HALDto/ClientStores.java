package com.ps.isel.customersscheduling.HALDto;

import com.ps.isel.customersscheduling.HALDto.links.StoreLinks;

import java.io.Serializable;

public class ClientStores implements Serializable {
    private Link[] links;
    private StoreLinks[] content;

    public ClientStores(){}

    public ClientStores(Link[] links, StoreLinks[] content) {
        this.links = links;
        this.content = content;
    }

    public Link[] getLinks() {
        return links;
    }

    public void setLinks(Link[] links) {
        this.links = links;
    }

    public StoreLinks[] getContent() {
        return content;
    }

    public void setContent(StoreLinks[] content) {
        this.content = content;
    }
}
