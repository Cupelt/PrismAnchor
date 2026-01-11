package org.cupelt.prismanchor.utils;

// import com.google.inject.Inject;
// import com.google.inject.Injector;
// import com.google.inject.assistedinject.Assisted;
// import org.bukkit.plugin.java.JavaPlugin;
// import org.reflections.Reflections;
// import org.reflections.util.ConfigurationBuilder;
// 
// import java.util.Set;
// import java.util.function.Consumer;
// import java.util.logging.Logger;

public class ReflectionInitializer<T> {

    // private final Class<T> type;
    // private final JavaPlugin plugin;
    // private final Injector injector; // @Inject 필드 주입 제거
    // private final Set<Class<? extends T>> classes;
// 
    // @Inject
    // public ReflectionInitializer(
    //     Injector injector, // Injector를 생성자로 직접 받습니다.
    //     JavaPlugin plugin, 
    //     Logger logger, 
    //     @Assisted Class<T> type
    // ) {
    //     this.injector = injector; // 필드 주입 대신 여기서 할당
    //     this.type = type;
    //     this.plugin = plugin;
// 
    //     // 리플렉션 설정 최적화 및 클래스 로더 지정
    //     ConfigurationBuilder config = new ConfigurationBuilder()
    //             .forPackages(plugin.getClass().getPackageName())
    //             .addClassLoaders(plugin.getClass().getClassLoader());
// 
    //     Reflections reflections = new Reflections(config);
// 
    //     this.classes = reflections.getSubTypesOf(type);
    //     
    //     // 디버깅용 로그
    //     logger.info("Scanning package: " + plugin.getClass().getPackageName());
    //     logger.info("Found " + this.classes.size() + " subclasses of " + type.getSimpleName());
    // }
// 
    // public void reflectionForEach(Consumer<T> initializer) {
    //     for (Class<? extends T> clazz : this.classes) {
    //         try {
    //             T instance = injector.getInstance(clazz);
    //             initializer.accept(instance);
    //         } catch (Exception e) {
    //             plugin.getLogger().severe("Failed to initialize: " + clazz.getName());
    //             e.printStackTrace();
    //         }
    //     }
    // }
}