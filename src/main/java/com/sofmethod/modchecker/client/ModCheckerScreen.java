package com.sofmethod.modchecker.client;

import com.sofmethod.modchecker.ModCheckerConfig;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.Util;

import java.net.URI;
import java.util.List;

public class ModCheckerScreen extends Screen {

    private final List<ModCheckerConfig.RequiredMod> missingMods;

    public ModCheckerScreen(List<ModCheckerConfig.RequiredMod> missingMods) {
        super(Component.literal("Required Mods Missing"));
        this.missingMods = missingMods;
    }

    @Override
    protected void init() {
        int startY = this.height / 2 - (missingMods.size() * 28) / 2;

        for (int i = 0; i < missingMods.size(); i++) {
            ModCheckerConfig.RequiredMod mod = missingMods.get(i);
            int y = startY + i * 28;
            this.addRenderableWidget(Button.builder(
                Component.literal("Download: " + mod.displayName()),
                btn -> Util.getPlatform().openUri(URI.create(mod.downloadUrl()))
            ).bounds(this.width / 2 - 150, y, 300, 20).build());
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics, mouseX, mouseY, partialTick);

        graphics.drawCenteredString(this.font,
            "Some required mods are missing!",
            this.width / 2, this.height / 2 - (missingMods.size() * 28) / 2 - 30,
            0xFF5555);

        graphics.drawCenteredString(this.font,
            "Install them and restart the game.",
            this.width / 2, this.height / 2 - (missingMods.size() * 28) / 2 - 15,
            0xAAAAAA);

        super.render(graphics, mouseX, mouseY, partialTick);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }
}