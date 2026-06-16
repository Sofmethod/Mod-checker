package com.sofmethod.modchecker;

import java.util.List;

public class ModCheckerConfig {

    public record RequiredMod(String modId, String displayName, String downloadUrl) {}

    public static final List<RequiredMod> REQUIRED_MODS = List.of(
        new RequiredMod("ftbteams", "FTB Teams", "https://www.curseforge.com/minecraft/mc-mods/ftb-teams-forge"),
        new RequiredMod("ftbchunks", "FTB Chunks", "https://www.curseforge.com/minecraft/mc-mods/ftb-chunks-forge")
        // Add more here
    );
}