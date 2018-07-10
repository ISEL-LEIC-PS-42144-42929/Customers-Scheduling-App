package com.ps.isel.customersscheduling.HALDto.entitiesResourceList;

import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.HALDto.links.StoreLinks;

import java.io.Serializable;

public class StoreResourceItem implements Serializable {

    private StoreDto store;
    private double score;
    private StoreLinks _links;

    public StoreResourceItem() {
    }

    public StoreResourceItem(StoreDto store, double score, StoreLinks _links) {
        this.store = store;
        this.score = score;
        this._links = _links;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public StoreDto getStore() {
        return store;
    }

    public StoreLinks get_links() {
        return _links;
    }

    public void setStore(StoreDto store) {
        this.store = store;
    }

    public void set_links(StoreLinks _links) {
        this._links = _links;
    }
}
