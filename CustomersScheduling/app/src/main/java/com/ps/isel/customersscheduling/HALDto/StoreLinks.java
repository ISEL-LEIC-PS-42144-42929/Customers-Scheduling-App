package com.ps.isel.customersscheduling.HALDto;

/**
 * Created by Colapso on 23/05/18.
 */

public class StoreLinks {
    private StoreDto store;
    private Link[] links;

    public StoreLinks(StoreDto store, Link[] links) {
        this.store = store;
        this.links = links;
    }

    public StoreLinks() {

    }

    public void setStore(StoreDto store) {
        this.store = store;
    }

    public void setLinks(Link[] links) {
        this.links = links;
    }

    public StoreDto getStore() {
        return store;
    }

    public Link[] getLinks() {
        return links;
    }
}
