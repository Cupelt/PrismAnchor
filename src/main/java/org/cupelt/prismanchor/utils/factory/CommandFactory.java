package org.cupelt.prismanchor.utils.factory;

import org.cupelt.prismanchor.command.CommandBuilder;
import org.cupelt.prismanchor.command.CommandPerformer;

public interface CommandFactory {
    CommandBuilder build(String name, Class<? extends CommandPerformer> performer);
}
