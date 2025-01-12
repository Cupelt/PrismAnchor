package org.cupelt.prismanchor.example;

import org.cupelt.prismanchor.AbstractPlugin;

public final class PrismAnchorExample extends AbstractPlugin<PrismAnchorExample> {

    @Override
    public void onPluginEnable() {
        getLogger().info("Plugin allowed!");
    }

    @Override
    public void onPluginDisable() {

    }
}
