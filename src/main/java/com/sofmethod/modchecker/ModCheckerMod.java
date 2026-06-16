package com.sofmethod.modchecker;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(ModCheckerMod.MOD_ID)
public class ModCheckerMod {
    public static final String MOD_ID = "modchecker";

    public ModCheckerMod(IEventBus modEventBus) {
        modEventBus.addListener(com.sofmethod.modchecker.client.ClientSetup::onClientSetup);
    }
}