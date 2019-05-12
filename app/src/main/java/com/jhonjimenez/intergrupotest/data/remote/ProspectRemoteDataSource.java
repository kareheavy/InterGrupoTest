package com.jhonjimenez.intergrupotest.data.remote;

import com.jhonjimenez.intergrupotest.models.Prospect;
import io.reactivex.Observable;

import java.util.List;

public interface ProspectRemoteDataSource {
    Observable<List<Prospect>> getProspects(String token);
}
