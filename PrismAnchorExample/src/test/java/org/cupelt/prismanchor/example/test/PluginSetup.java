package org.cupelt.prismanchor.example.test;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import org.cupelt.prismanchor.example.PrismAnchorExample;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class PluginSetup {
    private ServerMock server;
    private PrismAnchorExample plugin;

    @BeforeEach
    public void setUp()
    {
        server = MockBukkit.mock();
        plugin = MockBukkit.load(PrismAnchorExample.class);
    }

    @AfterEach
    public void tearDown()
    {
        MockBukkit.unmock();
    }
}
