package org.cupelt.prismanchor.example.command;

import org.bukkit.command.CommandSender;
import org.cupelt.prismanchor.autoloader.annotation.CommandRegister;
import org.cupelt.prismanchor.autoloader.annotation.Component;
import org.cupelt.prismanchor.command.AbstractCommand;
import org.cupelt.prismanchor.command.CommandBuilder;
import org.cupelt.prismanchor.command.CommandPerformer;
import org.cupelt.prismanchor.module.factory.command.CommandFactory;

@Component("example")
@CommandRegister
public class Command extends AbstractCommand implements CommandPerformer {
    @Override
    public CommandBuilder getCommandOptions() {
        return CommandBuilder.create("test", Command.class)
                .setDescription("This is TestCommand")
                .addSubCommand(CommandBuilder.create("A", SubCommand.class)
                        .setTabCompletion(SubCommand.class)
                )
                .addSubCommand(CommandBuilder.create("inventory", InventoryCommand.class));
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        sender.sendMessage("Test Command");
    }
}
