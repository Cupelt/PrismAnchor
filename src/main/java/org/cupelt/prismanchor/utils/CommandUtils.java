package org.cupelt.prismanchor.utils;

import com.google.inject.Injector;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import org.cupelt.prismanchor.AbstractPlugin;
import org.cupelt.prismanchor.command.AbstractCommand;
import org.cupelt.prismanchor.command.CommandBuilder;

public class CommandUtils {
    private static Method syncMethod = null;
    private static boolean notFound = false;
    private static JavaPlugin plugin;
    private static Logger logger;


    // origin commands
    private static final Map<String, Command> overridens = new HashMap<>();

    public static void register(AbstractCommand command) {
        Injector injector = AbstractPlugin.getInjector();
        
        plugin = injector.getInstance(JavaPlugin.class);
        logger = injector.getInstance(Logger.class);

        CommandBuilder options = command.getCommandOptions();
        logger.info("registering '"+ options.getName() +"' command...");

        Map<String, Command> rawCommandMap = getCommandMap();

        if (rawCommandMap.containsKey(options.getName()) && overridens.containsKey(options.getName()))
            return;


        Command overridden = rawCommandMap.get(options.getName());
        Optional.ofNullable(overridden)
                .ifPresent(c -> overridens.put(options.getName(), c));

        rawCommandMap.put(options.getName(), command);
        // register aliases manually here
        for (String alias : options.getAliases()) {
            Optional.ofNullable(rawCommandMap.get(alias))
                    .ifPresent(c -> overridens.put(alias, c));
            rawCommandMap.put(alias, command);
        }

        synchronizeCommandMap();
    }


    private static Map<String, Command> getCommandMap() {
        try {
            Server server = Bukkit.getServer();

            Field f = server.getClass().getDeclaredField("commandMap");
            f.setAccessible(true);

            CommandMap scm = (CommandMap) f.get(server);

            Method knownCommands = scm.getClass().getDeclaredMethod("getKnownCommands");
            return (Map<String, Command>) knownCommands.invoke(scm);
        } catch (Exception ex) {
            logger.warning("Couldn't find 'commandMap'. This may indicate that you are using very very old" +
                    " version of Bukkit. Please report this to TR team, so we can work on it.");
            logger.warning("Use /trg debug to see more details.");
            return null;
        }
    }

    private static void synchronizeCommandMap() {
        if (notFound) // in case of the syncCommands method doesn't exist, just skip it
            return; // command still works without synchronization anyway

        Server server = Bukkit.getServer();
        if (syncMethod == null) {
            try {
                syncMethod = server.getClass().getDeclaredMethod("syncCommands");
                syncMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                logger.warning("Couldn't find syncCommands(). This is not an error! Though, tab-completer" +
                        " may not work with this error. Report to us if you believe this version has "
                        + "to support it.");
                logger.warning("Use /trg debug to see more details.");
                notFound = true;

                return;
            }
        }

        try {
            syncMethod.invoke(server);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
