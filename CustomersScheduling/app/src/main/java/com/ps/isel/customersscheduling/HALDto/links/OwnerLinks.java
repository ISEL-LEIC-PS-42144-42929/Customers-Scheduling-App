package com.ps.isel.customersscheduling.HALDto.links;

import com.ps.isel.customersscheduling.HALDto.Link;

public class OwnerLinks {

    private Link insert;
    private Link stores;
    private Link self;

    public OwnerLinks() {
    }

    public OwnerLinks(Link insert, Link stores, Link self) {
        this.insert = insert;
        this.stores = stores;
        this.self = self;
    }

    public void setInsert(Link insert) {
        this.insert = insert;
    }

    public void setStores(Link stores) {
        this.stores = stores;
    }

    public void setSelf(Link self) {
        this.self = self;
    }

    public Link getInsert() {
        return insert;
    }

    public Link getStores() {
        return stores;
    }

    public Link getSelf() {
        return self;
    }
}
