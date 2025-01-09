package org.cupelt.prismanchor.example;

import org.cupelt.prismanchor.AbstractPlugin;

public final class PrismAnchorExample extends AbstractPlugin<PrismAnchorExample> {
    @Override
    public void initPlugin() {
        getLogger().info("Plugin allowed!");
    }
}
