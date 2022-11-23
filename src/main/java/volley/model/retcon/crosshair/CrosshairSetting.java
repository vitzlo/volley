package volley.model.retcon.crosshair;

import java.util.function.Function;

public enum CrosshairSetting {
    COLOR("c", SettingCategory.GENERAL, v -> isIntegerWithin(v, 0, 7)),
    CENTER_DOT_THICKNESS("z", SettingCategory.CENTER_DOT, v -> isIntegerWithin(v, 1, 6)),
    INNER_LINE_LENGTH("0l", SettingCategory.INNER_LINES, v -> isIntegerWithin(v, 0, 20));

    private final String key;
    private final SettingCategory category;
    private final Function<Float, Boolean> validator;
    CrosshairSetting(String key, SettingCategory category, Function<Float, Boolean> validator) {
        this.key = key;
        this.category = category;
        this.validator = validator;
    }

    enum SettingCategory {
        GENERAL, OUTLINES, CENTER_DOT, INNER_LINES, OUTER_LINES
    }

    private static boolean isIntegerWithin(float x, int minInc, int maxInc) {
        return minInc <= x && x <= maxInc && isWithinPlaces(x, 0);
    }

    private static boolean isFloatWithin(float x, int decimalPlaces, int minInc, int maxInc) {
        return minInc <= x && x <= maxInc && isWithinPlaces(x, decimalPlaces);
    }

    private static boolean isWithinPlaces(float x, int decimalPlaces) {
        float adjustedX = x * (float)Math.pow(10, decimalPlaces);
        return Math.ceil(adjustedX) == adjustedX;
    }
}
