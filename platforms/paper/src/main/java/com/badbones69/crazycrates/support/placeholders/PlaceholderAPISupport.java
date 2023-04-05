package com.badbones69.crazycrates.support.placeholders;

import com.badbones69.crazycrates.CrazyCrates;
import com.badbones69.crazycrates.api.CrazyManager;
import com.badbones69.crazycrates.api.objects.Crate;
import com.badbones69.crazycrates.enums.types.CrateType;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.text.NumberFormat;

public class PlaceholderAPISupport extends PlaceholderExpansion {

    private final CrazyCrates plugin = CrazyCrates.getPlugin();

    private final CrazyManager crazyManager = plugin.getStarter().getCrazyManager();

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String identifier) {
        if (player.isOnline()) {
            Player playerOnline = (Player) player;

            for (Crate crate : crazyManager.getCrates()) {
                if (crate.getCrateType() != CrateType.MENU) {
                    if (identifier.equalsIgnoreCase(crate.getName())) {
                        return NumberFormat.getNumberInstance().format(crazyManager.getVirtualKeys(playerOnline, crate));
                    } else if (identifier.equalsIgnoreCase(crate.getName() + "_physical")) {
                        return NumberFormat.getNumberInstance().format(crazyManager.getPhysicalKeys(playerOnline, crate));
                    } else if (identifier.equalsIgnoreCase(crate.getName() + "_total")) {
                        return NumberFormat.getNumberInstance().format(crazyManager.getTotalKeys(playerOnline, crate));
                    } else if (identifier.equalsIgnoreCase(crate.getName() + "_guaranteed_bonus_times")) {
                        return String.valueOf(crate.getGuaranteedBonusTimes());
                    } else if (identifier.equalsIgnoreCase(crate.getName() + "_guaranteed_bonus_times_now")) {
                        return String.valueOf(crate.getGuaranteedBonusTimes(playerOnline));
                    } else if (identifier.equalsIgnoreCase(crate.getName() + "_guaranteed_bonus_times_last")) {
                        return String.valueOf(crate.getGuaranteedBonusTimes() - crate.getGuaranteedBonusTimes(playerOnline));
                    }
                }
            }
        }

        return "";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "crazycrates";
    }

    @Override
    public @NotNull String getAuthor() {
        return "BadBones69";
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }
}