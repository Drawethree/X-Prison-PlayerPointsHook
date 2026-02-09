package dev.drawethree.xprison.currency.handler;

import dev.drawethree.xprison.api.currency.enums.LostCause;
import dev.drawethree.xprison.api.currency.enums.ReceiveCause;
import dev.drawethree.xprison.api.currency.model.XPrisonCurrencyHandler;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.OfflinePlayer;

public final class PlayerPointsCurrencyHandler implements XPrisonCurrencyHandler {

	private final PlayerPointsAPI api;

    public PlayerPointsCurrencyHandler(PlayerPointsAPI api) {
        this.api = api;
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
		return api.look(offlinePlayer.getUniqueId());
    }

    @Override
    public boolean setBalance(OfflinePlayer offlinePlayer, double v) {
		api.take(offlinePlayer.getUniqueId(), (int) getBalance(offlinePlayer));
		api.give(offlinePlayer.getUniqueId(), (int) v);
		return true;
    }

    @Override
    public boolean addBalance(OfflinePlayer offlinePlayer, double v, ReceiveCause receiveCause) {
		return api.give(offlinePlayer.getUniqueId(),(int) v);
    }

    @Override
    public boolean removeBalance(OfflinePlayer offlinePlayer, double v, LostCause lostCause) {
        return api.take(offlinePlayer.getUniqueId(), (int) v);
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double v) {
        return getBalance(offlinePlayer) >= v;

    }
}