package org.cupelt.prismanchor.example.command;

import org.bukkit.command.CommandSender;
import org.cupelt.prismanchor.command.AbstractCommand;
import org.cupelt.prismanchor.command.CommandBuilder;
import org.cupelt.prismanchor.command.CommandPerformer;

public class Command extends AbstractCommand implements CommandPerformer{
    @Override
    public CommandBuilder getCommandOptions() {
        return new CommandBuilder()
                .setName("test")
                .setPerformer(Command.class)
                .addSubCommand(new CommandBuilder()
                        .setName("A")
                        .setPerformer(SubCommand.class)
                        .setTabCompletion(SubCommand.class)
                )
                .addSubCommand(new CommandBuilder()
                        .setName("inventory")
                        .setPerformer(InventoryCommand.class)
                );
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        sender.sendMessage("Test Command");
    }
}
