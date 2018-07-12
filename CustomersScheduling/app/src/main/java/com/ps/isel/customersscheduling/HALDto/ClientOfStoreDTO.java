package com.ps.isel.customersscheduling.HALDto;

import com.ps.isel.customersscheduling.HALDto.embeddeds.ClientEmbedded;
import com.ps.isel.customersscheduling.HALDto.embeddeds.PersonEmbedded;
import com.ps.isel.customersscheduling.HALDto.links.SelfLink;

import java.io.Serializable;

public class ClientOfStoreDTO implements Serializable {

    private ClientEmbedded _embedded;
    private SelfLink _links;

    public ClientOfStoreDTO() {
    }

    public ClientOfStoreDTO(ClientEmbedded _embedded, SelfLink _links) {
        this._embedded = _embedded;
        this._links = _links;
    }

    public ClientEmbedded get_embedded() {
        return _embedded;
    }

    public SelfLink get_links() {
        return _links;
    }

    public void set_embedded(ClientEmbedded _embedded) {
        this._embedded = _embedded;
    }

    public void set_links(SelfLink _links) {
        this._links = _links;
    }
}
