package com.sofmethod.modchecker.client;

import com.sofmethod.modchecker.ModCheckerConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.minecraft.client.gui.screens.TitleScreen;

import java.util.List;

@EventBusSubscriber(modid = "modchecker", bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientSetup {

    private static boolean checkedAlready = false;

    public static void onClientSetup(FMLClientSetupEvent event) {}

    @SubscribeEvent
    public static void onScreenOpen(ScreenEvent.Opening event) {
        if (checkedAlready) return;
        if (!(event.getScreen() instanceof TitleScreen)) return;

        List<ModCheckerConfig.RequiredMod> missing = ModCheckerConfig.REQUIRED_MODS.stream()
            .filter(m -> !ModList.get().isLoaded(m.modId()))
            .toList();

        if (!missing.isEmpty()) {
            checkedAlready = true;
            event.setNewScreen(new ModCheckerScreen(missing));
        }
    }
}