package com.jhonjimenez.intergrupotest.data.remote.api;

import com.jhonjimenez.intergrupotest.models.Prospect;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

import java.util.List;

public interface ProspectsApi {

    @GET("sch/prospects.json")
    Observable<List<Prospect>> getProspects();
}
