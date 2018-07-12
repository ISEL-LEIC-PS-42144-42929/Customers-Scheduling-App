package com.ps.isel.customersscheduling.HALDto.entitiesResourceList;

import com.ps.isel.customersscheduling.HALDto.ClientDto;
import com.ps.isel.customersscheduling.HALDto.links.ClientLinks;

public class ClientResourceItem {

    private ClientDto person;
    private boolean accepted;
    private ClientLinks _links;

    public ClientResourceItem() {
    }

    public ClientResourceItem(ClientDto person, boolean accepted, ClientLinks _links) {
        this.person = person;
        this.accepted = accepted;
        this._links = _links;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public void setPerson(ClientDto person) {
        this.person = person;
    }

    public void set_links(ClientLinks _links) {
        this._links = _links;
    }

    public ClientDto getPerson() {
        return person;
    }

    public ClientLinks get_links() {
        return _links;
    }
}
