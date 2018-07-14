package com.ps.isel.customersscheduling.HALDto;

import com.ps.isel.customersscheduling.HALDto.embeddeds.BookingsEmbedded;
import com.ps.isel.customersscheduling.HALDto.links.SelfLink;

import java.io.Serializable;

public class BookingsOfStoreDTO implements Serializable {

    private BookingsEmbedded _embedded;
    private SelfLink _links;

    public BookingsOfStoreDTO() {
    }

    public BookingsOfStoreDTO(BookingsEmbedded _embedded, SelfLink _links) {
        this._embedded = _embedded;
        this._links = _links;
    }

    public BookingsEmbedded get_embedded() {
        return _embedded;
    }

    public SelfLink get_links() {
        return _links;
    }

    public void set_embedded(BookingsEmbedded _embedded) {
        this._embedded = _embedded;
    }

    public void set_links(SelfLink _links) {
        this._links = _links;
    }
}
