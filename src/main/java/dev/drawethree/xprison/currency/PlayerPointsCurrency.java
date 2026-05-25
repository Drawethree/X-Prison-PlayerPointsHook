package dev.drawethree.xprison.currency;

import dev.drawethree.xprison.api.currency.model.XPrisonCurrency;
import dev.drawethree.xprison.api.currency.model.XPrisonCurrencyHandler;
import dev.drawethree.xprison.currency.handler.PlayerPointsCurrencyHandler;
import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.black_ixx.playerpoints.config.SettingKey;
import org.black_ixx.playerpoints.manager.LocaleManager;
import org.black_ixx.playerpoints.util.PointsUtils;

public final class PlayerPointsCurrency implements XPrisonCurrency {

    private final PlayerPointsAPI api;

    public PlayerPointsCurrency(PlayerPointsAPI api) {
        this.api = api;
    }

    @Override
    public String getName() {
        return "playerpoints";
    }

    @Override
    public double getMaxAmount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public String getDisplayName() {
        return PlayerPoints.getInstance().getName();
    }

    @Override
    public String getPrefix() {
		return getMessageFromLocale("currency-prefix");
    }

    @Override
    public String getSuffix() {
		return getMessageFromLocale("currency-suffix");
    }

    @Override
    public double getStartingAmount() {
        return SettingKey.STARTING_BALANCE.get();
    }

    @Override
    public String getFormatPattern() {
        return "#.##";
    }

    @Override
    public boolean isShortFormat() {
        return false;
    }

    @Override
    public boolean isTrimZeros() {
        return false;
    }

    @Override
    public String format(double v) {
        String formatted = isShortFormat()
                ? PointsUtils.formatPointsShorthand((long) v)
                : PointsUtils.formatPoints((long) v);
        return formatted + " " + (v == 1.0 ? this.currencyNameSingular() : this.currencyNamePlural());
    }

    @Override
    public XPrisonCurrencyHandler getHandler() {
        return new PlayerPointsCurrencyHandler(api);
    }

	private String getMessageFromLocale(String key) {
		LocaleManager localeManager = PlayerPoints.getInstance().getManager(LocaleManager.class);
		return localeManager.getLocaleMessage(key);
	}

	private String currencyNamePlural() {
		return getMessageFromLocale("currency-plural");
	}

	private String currencyNameSingular() {
		return getMessageFromLocale("currency-singular");
	}
}