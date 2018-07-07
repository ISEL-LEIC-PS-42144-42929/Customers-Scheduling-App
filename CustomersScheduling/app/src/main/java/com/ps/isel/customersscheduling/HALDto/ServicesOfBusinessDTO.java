package com.ps.isel.customersscheduling.HALDto;


import com.ps.isel.customersscheduling.HALDto.embeddeds.ServicesOfBusinessEmbedded;
import com.ps.isel.customersscheduling.HALDto.embeddeds.StoresOfUserEmbedded;
import com.ps.isel.customersscheduling.HALDto.links.SelfLink;

public class ServicesOfBusinessDTO
{
    private ServicesOfBusinessEmbedded _embedded;
    private SelfLink _links;

    public ServicesOfBusinessDTO() {
    }

    public ServicesOfBusinessDTO(ServicesOfBusinessEmbedded _embedded, SelfLink _links) {
        this._embedded = _embedded;
        this._links = _links;
    }

    public ServicesOfBusinessEmbedded get_embedded() {
        return _embedded;
    }

    public SelfLink get_links() {
        return _links;
    }

    public void set_embedded(ServicesOfBusinessEmbedded _embedded) {
        this._embedded = _embedded;
    }

    public void set_links(SelfLink _links) {
        this._links = _links;
    }
}