package com.ps.isel.customersscheduling.java.dto;

/**
 * Created by Colapso on 16/05/18.
 */

public class BookingDto
{
    private int bookingID;
    private int servicesID;
    private String staffEmail;
    private String clientEmail;
    private String storeName;

    public BookingDto(int bookingID, int servicesID, String staffEmail, String clientEmail, String storeName)
    {
        this.bookingID = bookingID;
        this.servicesID = servicesID;
        this.staffEmail = staffEmail;
        this.clientEmail = clientEmail;
        this.storeName = storeName;
    }

    public int getBookingID() {
        return bookingID;
    }

    public int getServicesID() {
        return servicesID;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public String getStoreName() {
        return storeName;
    }
}
