package com.ps.isel.customersscheduling.HALDto.entitiesResourceList;

import com.ps.isel.customersscheduling.HALDto.PersonDto;
import com.ps.isel.customersscheduling.HALDto.links.PersonLink;
import com.ps.isel.customersscheduling.HALDto.links.ServiceLink;

public class StaffResourceItem {

    private PersonDto person;
    private PersonLink _links;


    public StaffResourceItem() {
    }

    public StaffResourceItem(PersonDto person, PersonLink _links) {
        this.person = person;
        this._links = _links;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public void set_links(PersonLink _links) {
        this._links = _links;
    }

    public PersonDto getPerson() {
        return person;
    }

    public PersonLink get_links() {
        return _links;
    }
}
