package com.ps.isel.customersscheduling.HALDto.entitiesResourceList;

import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.HALDto.StoreLinks;

public class StoreResourceItem {

    private StoreDto store;
    private StoreLinks _links;

    public StoreResourceItem() {
    }

    public StoreResourceItem(StoreDto store, StoreLinks _links) {
        this.store = store;
        this._links = _links;
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
