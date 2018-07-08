package com.ps.isel.customersscheduling.HALDto;

import com.ps.isel.customersscheduling.HALDto.embeddeds.PersonEmbedded;
import com.ps.isel.customersscheduling.HALDto.links.SelfLink;

public class PersonOfStoreDTO {

    private PersonEmbedded _embedded;
    private SelfLink _links;

    public PersonOfStoreDTO() {
    }

    public PersonOfStoreDTO(PersonEmbedded _embedded, SelfLink _links)
    {
        this._embedded = _embedded;
        this._links = _links;
    }

    public PersonEmbedded get_embedded() {
        return _embedded;
    }

    public SelfLink get_links() {
        return _links;
    }

    public void set_embedded(PersonEmbedded _embedded) {
        this._embedded = _embedded;
    }

    public void set_links(SelfLink _links) {
        this._links = _links;
    }
}