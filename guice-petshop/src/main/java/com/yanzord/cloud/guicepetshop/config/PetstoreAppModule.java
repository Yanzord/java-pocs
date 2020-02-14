package com.yanzord.cloud.guicepetshop.config;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import com.yanzord.cloud.guicepetshop.domain.job.*;

public class PetstoreAppModule extends AbstractModule {

    @Override
    protected void configure() {
        MapBinder<String, PetstoreJob> mapBinder =
                MapBinder.newMapBinder(binder(), String.class, PetstoreJob.class);

        mapBinder.addBinding("bath").to(Bath.class);
        mapBinder.addBinding("bath with perfume").to(BathWithPerfume.class);
        mapBinder.addBinding("dry bath").to(DryBath.class);
        mapBinder.addBinding("dry bath with perfume").to(DryBathWithPerfume.class);
        mapBinder.addBinding("short haircut").to(ShortHaircut.class);
        mapBinder.addBinding("long haircut").to(LongHaircut.class);
    }
}
