package dev.drawethree.xprison;

import dev.drawethree.xprison.api.XPrisonAPI;
import dev.drawethree.xprison.api.addons.XPrisonAddon;
import dev.drawethree.xprison.api.addons.XPrisonAddonContext;
import dev.drawethree.xprison.currency.PlayerPointsCurrency;
import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;

import java.util.logging.Logger;

public final class PlayerPointsHookAddon implements XPrisonAddon {

    private XPrisonAPI api;
    private Logger logger;
    private PlayerPointsCurrency currency;

    @Override
    public void onEnable(XPrisonAddonContext context) {
        this.api = context.getAPI();
        this.logger = context.getLogger();

        if (!Bukkit.getPluginManager().isPluginEnabled("PlayerPoints")) {
            logger.warning("PlayerPoints plugin not found! No PlayerPoints currency will be supported.");
            return;
        }

        PlayerPointsAPI ppAPI = PlayerPoints.getInstance().getAPI();
        currency = new PlayerPointsCurrency(ppAPI);
        api.getCurrencyApi().registerCurrency(currency);
    }

    @Override
    public void onDisable() {
        if (currency != null) {
            api.getCurrencyApi().unregisterCurrency(currency);
        }
    }
}
