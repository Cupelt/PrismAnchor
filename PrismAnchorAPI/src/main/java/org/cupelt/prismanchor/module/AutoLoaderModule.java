package org.cupelt.prismanchor.module;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import org.cupelt.prismanchor.autoloader.RegisterType;
import org.cupelt.prismanchor.autoloader.register.CommandRegister;
import org.cupelt.prismanchor.autoloader.register.EventRegister;
import org.cupelt.prismanchor.autoloader.register.IRegister;

public class AutoLoaderModule extends AbstractModule {
    @Override
    protected void configure() {
        MapBinder<RegisterType, IRegister> mapBinder
                = MapBinder.newMapBinder(binder(), RegisterType.class, IRegister.class);

        mapBinder.addBinding(RegisterType.COMMAND).to(CommandRegister.class);
        mapBinder.addBinding(RegisterType.EVENT).to(EventRegister.class);
    }
}