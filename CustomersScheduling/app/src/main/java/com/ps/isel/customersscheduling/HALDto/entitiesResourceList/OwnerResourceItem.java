package com.ps.isel.customersscheduling.HALDto.entitiesResourceList;

import com.ps.isel.customersscheduling.HALDto.OwnerDto;
import com.ps.isel.customersscheduling.HALDto.PersonDto;
import com.ps.isel.customersscheduling.HALDto.links.OwnerLinks;
import com.ps.isel.customersscheduling.HALDto.links.PersonLink;

import java.io.Serializable;

public class OwnerResourceItem implements Serializable {

    private OwnerDto person;
    private OwnerLinks _links;

    public OwnerResourceItem() {
    }

    public OwnerResourceItem(OwnerDto person, OwnerLinks _links) {
        this.person = person;
        this._links = _links;
    }

    public void setPerson(OwnerDto person) {
        this.person = person;
    }

    public void set_links(OwnerLinks _links) {
        this._links = _links;
    }

    public OwnerDto getPerson() {
        return person;
    }

    public OwnerLinks get_links() {
        return _links;
    }
}
