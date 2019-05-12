package com.jhonjimenez.intergrupotest.data.remote;

import com.jhonjimenez.intergrupotest.data.remote.api.ProspectsApi;
import com.jhonjimenez.intergrupotest.data.remote.client.RetrofitClient;
import com.jhonjimenez.intergrupotest.models.Prospect;
import com.jhonjimenez.intergrupotest.utils.Constants;
import io.reactivex.Observable;

import java.util.List;

public class ProspectRemoteDataSourceImpl extends RetrofitClient implements ProspectRemoteDataSource {
    @Override
    public Observable<List<Prospect>> getProspects(String token) {
        return create(ProspectsApi.class, Constants.BASE_URL, token).getProspects();
    }
}
