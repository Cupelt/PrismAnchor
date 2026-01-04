package org.cupelt.prismanchor.command;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.cupelt.prismanchor.exception.NotHavePermissionException;

import java.util.*;

public class CommandBuilder {

    protected String name;
    protected String description;

    protected String permission;
    protected boolean isAutoTabGenerate = true;

    protected Class<? extends CommandPerformer> performer;
    protected Class<? extends CommandTabCompleter> tabCompletion;

    protected Set<CommandBuilder> subCommands = new HashSet<>();

    @Inject
    private Injector injector;

    public CommandBuilder() {}

    public CommandBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public CommandBuilder setPerformer(Class<? extends CommandPerformer> performer) {
        this.performer = performer;
        return this;
    }

    public CommandBuilder setTabCompletion(Class<? extends CommandTabCompleter> tabCompletion) {
        this.tabCompletion = tabCompletion;
        return this;
    }

    public CommandBuilder setAutoTabGenerate(boolean isAutoTabGenerate) {
        this.isAutoTabGenerate = isAutoTabGenerate;
        return this;
    }

    public CommandBuilder setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public CommandBuilder addSubCommand(CommandBuilder subCommand) {
        this.subCommands.add(subCommand);
        return this;
    }

    private CommandBuilder findSubCommand(CommandSender sender, String name) {
        return subCommands.stream()
                .filter(option -> option.getName().equals(name))
                .findFirst().orElse(null);
    }

    protected void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            performCommand(sender, args);
            return;
        }

        CommandBuilder subCommand = findSubCommand(sender, args[0]);
        if (subCommand != null) {
            subCommand.execute(sender, Arrays.copyOfRange(args, 1, args.length));
        } else {
            performCommand(sender, args);
        }
    }

    protected void performCommand(CommandSender sender, String[] args) {
        try {
            if (permission != null && !sender.hasPermission(permission)) {
                throw new NotHavePermissionException();
            }

            CommandPerformer command = injector.getInstance(performer);
            command.perform(sender, args);
        } catch (NotHavePermissionException e) {
            sender.sendMessage(Bukkit.permissionMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> getAutoGenerateTab(CommandSender sender, String[] args) {
        List<CommandBuilder> tabs = subCommands.stream()
                .filter(option -> option.permission == null || sender.hasPermission(option.permission))
                .toList();

        if (tabs.isEmpty()) {
            return new ArrayList<>();
        }

        // found subCommands. then return only subCommands.
        return tabs.stream()
                .map(CommandBuilder::getName)
                .filter(subCommandName -> subCommandName.toLowerCase().startsWith(args[0].toLowerCase()))
                .toList();
    }

    protected List<String> getExecuteTabComplete(CommandSender sender, String[] args) {
        if (args.length == 0)
            return new ArrayList<>();

        // current Player Inputting Space
        if (args.length == 1) {
            if (tabCompletion != null) {
                try {
                    return injector.getInstance(tabCompletion)
                            .onTabComplete(sender, args);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            if (isAutoTabGenerate) {
                return getAutoGenerateTab(sender, args);
            }

            return new ArrayList<>();
        }

        // if args length is bigger than 1, this is not a Player Input Space. so method passes the responsibility.
        CommandBuilder subCommand = findSubCommand(sender, args[0]);
        if (subCommand != null)
            return subCommand.getExecuteTabComplete(sender, Arrays.copyOfRange(args, 1, args.length));

        return new ArrayList<>();
    }

}
