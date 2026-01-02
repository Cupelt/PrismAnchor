package org.cupelt.prismanchor;

import org.bukkit.command.CommandSender;
import org.cupelt.prismanchor.annotation.AutoRegister;
import org.cupelt.prismanchor.command.AbstractCommand;
import org.cupelt.prismanchor.command.CommandBuilder;
import org.cupelt.prismanchor.command.CommandPerformer;

@AutoRegister(moduleName = "common")
public class Test extends AbstractCommand implements CommandPerformer{
    @Override
    public CommandBuilder getCommandOptions() {
        return new CommandBuilder();
    }

    @Override
    public void perform(CommandSender sender, String[] args) {
        sender.sendMessage("Test Command");
    }
}
