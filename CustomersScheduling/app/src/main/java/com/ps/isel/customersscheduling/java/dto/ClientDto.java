package com.ps.isel.customersscheduling.java.dto;

/**
 * Created by Colapso on 16/05/18.
 */

public class ClientDto extends PersonDto
{
    private StoreDto[] registeredStores;
    private BookingDto[] bookings;

    public ClientDto(String name, String email, int contact, int gender, StoreDto[] registeredStores, BookingDto[] bookings)
    {
        super(name, email, contact, gender);
        this.registeredStores = registeredStores;
        this.bookings = bookings;
    }

    public StoreDto[] getRegisteredStores()
    {
        return registeredStores;
    }

    public BookingDto[] getBookings() {
        return bookings;
    }
}
