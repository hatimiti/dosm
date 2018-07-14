package com.github.hatimiti.dosm.ad.master.cmshain;

import com.github.hatimiti.dosm.repository.entity.CmShain;

public interface CmShainService {
    void search(CmShainListForm form);
    CmShain register(CmShainForm form);
    void prepareUpdate(CmShainForm form);
    CmShain update(CmShainForm form);
    void confirmDelete(CmShainForm form);
    CmShain delete(CmShainForm form);
}
