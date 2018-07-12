package com.ps.isel.customersscheduling.HALDto.embeddeds;

import com.ps.isel.customersscheduling.HALDto.entitiesResourceList.ClientResourceItem;

public class ClientEmbedded {

    private ClientResourceItem[] clientResourceList;

    public ClientEmbedded() {
    }

    public ClientEmbedded(ClientResourceItem[] clientResourceList) {
        this.clientResourceList = clientResourceList;
    }

    public void setClientResourceList(ClientResourceItem[] clientResourceList) {
        this.clientResourceList = clientResourceList;
    }

    public ClientResourceItem[] getClientResourceList() {
        return clientResourceList;
    }
}
