package com.ps.isel.customersscheduling.HALDto;

public class ClientStores {
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
