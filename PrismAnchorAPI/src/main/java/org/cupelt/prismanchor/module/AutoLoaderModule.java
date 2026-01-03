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

        // Enum 값에 따라 실제 구현체를 연결 (이 구현체들은 Guice가 생성하므로 주입 가능!)
        mapBinder.addBinding(RegisterType.COMMAND).to(CommandRegister.class);
        mapBinder.addBinding(RegisterType.EVENT).to(EventRegister.class);
        // ... 나머지도 동일하게 등록
    }
}