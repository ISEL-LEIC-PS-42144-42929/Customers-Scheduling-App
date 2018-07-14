package com.ps.isel.customersscheduling.HALDto.embeddeds;

import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StaffResourceItem;
import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.StoreResourceItem;

public class StaffEmbedded {

    private StaffResourceItem[] staffResourceList;

    public StaffEmbedded() {
    }

    public StaffEmbedded(StaffResourceItem[] staffResourceList) {
        this.staffResourceList = staffResourceList;
    }

    public StaffResourceItem[] getStaffResourceList() {
        return staffResourceList;
    }

    public void setStaffResourceList(StaffResourceItem[] staffResourceList) {
        this.staffResourceList = staffResourceList;
    }
}
