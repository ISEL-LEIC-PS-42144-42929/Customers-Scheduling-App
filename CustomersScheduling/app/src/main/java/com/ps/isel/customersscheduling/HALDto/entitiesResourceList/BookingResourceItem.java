package com.ps.isel.customersscheduling.HALDto.entitiesResourceList;

import com.ps.isel.customersscheduling.HALDto.BookDto;
import com.ps.isel.customersscheduling.HALDto.ServiceDto;
import com.ps.isel.customersscheduling.HALDto.StaffDto;
import com.ps.isel.customersscheduling.HALDto.StoreDto;
import com.ps.isel.customersscheduling.HALDto.links.BookLinks;
import com.ps.isel.customersscheduling.HALDto.links.ClientLinks;
import com.ps.isel.customersscheduling.HALDto.links.ServiceLink;

import java.io.Serializable;

public class BookingResourceItem implements Serializable {

    private BookDto book;
    private BookLinks _links;

    public BookingResourceItem() {
    }

    public BookingResourceItem(BookDto book) {
        this.book = book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }

    public BookDto getBook() {
        return book;
    }

    public BookLinks get_links() {
        return _links;
    }

    public void set_links(BookLinks _links) {
        this._links = _links;
    }
}
