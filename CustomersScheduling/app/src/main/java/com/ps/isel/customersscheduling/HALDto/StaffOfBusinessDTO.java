package com.ps.isel.customersscheduling.HALDto;

import com.ps.isel.customersscheduling.HALDto.embeddeds.StaffEmbedded;
import com.ps.isel.customersscheduling.HALDto.embeddeds.StoresOfUserEmbedded;
import com.ps.isel.customersscheduling.HALDto.links.SelfLink;

public class StaffOfBusinessDTO {

    private StaffEmbedded _embedded;
    private SelfLink _links;

    public StaffOfBusinessDTO() {
    }

    public StaffOfBusinessDTO(StaffEmbedded _embedded, SelfLink _links) {
        this._embedded = _embedded;
        this._links = _links;
    }

    public StaffEmbedded get_embedded() {
        return _embedded;
    }

    public SelfLink get_links() {
        return _links;
    }

    public void set_embedded(StaffEmbedded _embedded) {
        this._embedded = _embedded;
    }

    public void set_links(SelfLink _links) {
        this._links = _links;
    }
}
