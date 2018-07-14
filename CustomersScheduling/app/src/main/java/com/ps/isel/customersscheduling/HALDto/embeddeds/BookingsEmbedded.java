package com.ps.isel.customersscheduling.HALDto.embeddeds;

import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.BookingResourceItem;

import java.io.Serializable;

public class BookingsEmbedded implements Serializable {

    private BookingResourceItem[] bookingResourceList;

    public BookingsEmbedded() {
    }

    public BookingsEmbedded(BookingResourceItem[] bookingResourceList) {
        this.bookingResourceList = bookingResourceList;
    }

    public BookingResourceItem[] getBookingResourceList() {
        return bookingResourceList;
    }

    public void setBookingResourceList(BookingResourceItem[] bookingResourceList) {
        this.bookingResourceList = bookingResourceList;
    }
}
