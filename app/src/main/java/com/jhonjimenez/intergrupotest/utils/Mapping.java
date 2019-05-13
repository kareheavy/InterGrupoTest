package com.jhonjimenez.intergrupotest.utils;

import com.jhonjimenez.intergrupotest.models.Prospect;
import com.jhonjimenez.intergrupotest.models.ProspectBackup;

public class Mapping {

    public static ProspectBackup convertProspectToProspectBackup(Prospect prospect){
        ProspectBackup prospectBackup = new ProspectBackup();
        prospectBackup.setId(prospect.getId());
        prospectBackup.setName(prospect.getName());
        prospectBackup.setSurname(prospect.getSurname());
        prospectBackup.setTelephone(prospect.getTelephone());
        prospectBackup.setSchProspectIdentification(prospect.getSchProspectIdentification());
        prospectBackup.setStatusCd(prospect.getStatusCd());

        return prospectBackup;
    }
}
