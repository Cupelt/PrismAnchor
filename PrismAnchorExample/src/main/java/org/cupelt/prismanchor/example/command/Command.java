package org.cupelt.prismanchor.example.command;

import org.bukkit.command.CommandSender;
import org.cupelt.prismanchor.autoloader.AutoRegister;
import org.cupelt.prismanchor.autoloader.RegisterType;
import org.cupelt.prismanchor.command.AbstractCommand;
import org.cupelt.prismanchor.command.CommandBuilder;
import org.cupelt.prismanchor.command.CommandPerformer;

@AutoRegister(registerType = RegisterType.COMMAND)
public class Command extends AbstractCommand implements CommandPerformer {
    @Override
    public CommandBuilder getCommandOptions() {
        return new CommandBuilder("test", Command.class)
                .setDescription("This is TestCommand")
                .addSubCommand(new CommandBuilder("A", SubCommand.class)
                        .setTabCompletion(SubCommand.class)
                )
                .addSubCommand(new CommandBuilder("inventory", InventoryCommand.class));
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        sender.sendMessage("Test Command");
    }
}
