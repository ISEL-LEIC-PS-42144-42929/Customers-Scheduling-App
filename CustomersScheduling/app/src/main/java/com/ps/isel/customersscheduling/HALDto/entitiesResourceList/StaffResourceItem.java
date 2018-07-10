package com.ps.isel.customersscheduling.HALDto.entitiesResourceList;

import com.ps.isel.customersscheduling.HALDto.StaffDto;
import com.ps.isel.customersscheduling.HALDto.links.StaffLinks;

import java.io.Serializable;

public class StaffResourceItem implements Serializable {

    private StaffDto person;
    private StaffLinks _links;

    public StaffResourceItem() {
    }

    public StaffResourceItem(StaffDto person, StaffLinks _links) {
        this.person = person;
        this._links = _links;
    }

    public void setPerson(StaffDto person) {
        this.person = person;
    }

    public void set_links(StaffLinks _links) {
        this._links = _links;
    }

    public StaffDto getPerson() {
        return person;
    }

    public StaffLinks get_links() {
        return _links;
    }
}
