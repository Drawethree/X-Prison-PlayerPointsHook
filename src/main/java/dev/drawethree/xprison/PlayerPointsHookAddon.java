package dev.drawethree.xprison;

import dev.drawethree.xprison.api.XPrisonAPI;
import dev.drawethree.xprison.api.addons.XPrisonAddon;
import dev.drawethree.xprison.currency.PlayerPointsCurrency;
import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public final class PlayerPointsHookAddon implements XPrisonAddon, Listener {

    private static PlayerPointsHookAddon instance;
    private XPrisonAPI api;
	private PlayerPointsCurrency currency;

    @Override
    public void onEnable() {
        instance = this;
        api = XPrisonAPI.getInstance();

        if (!Bukkit.getPluginManager().isPluginEnabled("PlayerPoints")) {
            Bukkit.getLogger().warning("PlayerPoints plugin not found! No PlayerPoints currencies will be supported.");
            onDisable();
            return;
        }

		PlayerPointsAPI ppAPI = PlayerPoints.getInstance().getAPI();
		currency = new PlayerPointsCurrency(ppAPI);

		api.getCurrencyApi().registerCurrency(currency);

    }


    @Override
    public void onDisable() {
		api.getCurrencyApi().unregisterCurrency(currency);
    }

    public XPrisonAPI getApi() {
        return api;
    }
}
