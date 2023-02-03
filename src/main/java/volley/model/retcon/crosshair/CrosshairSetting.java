package volley.model.retcon.crosshair;

import java.util.Map;
import java.util.function.Function;

public enum CrosshairSetting {
    SAME_ADS("p", SettingCategory.META, 1, v -> isBinary(v)),
    DIFFERENT_SNIPER("s", SettingCategory.META, 0, v -> isBinary(v)),

    COLOR("c", SettingCategory.GENERAL, 0xFFFFFF, v -> isIntegerWithin(v, 0x000000, 0xFFFFFF)),
    FADE_CROSSHAIR_WITH_FIRING_ERROR("f", SettingCategory.GENERAL, 1, v -> isBinary(v)),
    SHOW_SPECTATOR_CROSSHAIR("s", SettingCategory.GENERAL, 1, v -> isBinary(v)),
    OVERRIDE_FIRING_ERROR_WITH_CROSSHAIR("m", SettingCategory.GENERAL, 0, v -> isBinary(v)),

    SHOW_OUTLINES("h", SettingCategory.OUTLINES, 1, v -> isBinary(v)),
    OUTLINE_THICKNESS("t", SettingCategory.OUTLINES, 1, v -> isIntegerWithin(v, 1, 6),
            Map.of(SHOW_OUTLINES, 1)),
    OUTLINE_OPACITY("o", SettingCategory.OUTLINES, 0.5f, v -> isFloatWithin(v, 3, 0, 1),
            Map.of(SHOW_OUTLINES, 1)),

    SHOW_CENTER_DOT("d", SettingCategory.CENTER_DOT, 0, v -> isBinary(v)),
    CENTER_DOT_THICKNESS("z", SettingCategory.CENTER_DOT, 2, v -> isIntegerWithin(v, 1, 6),
            Map.of(SHOW_CENTER_DOT, 1)),
    CENTER_DOT_OPACITY("a", SettingCategory.CENTER_DOT, 1, v -> isFloatWithin(v, 3, 0, 1),
            Map.of(SHOW_CENTER_DOT, 1)),

    SHOW_INNER_LINES("0b", SettingCategory.INNER_LINES, 1, v -> isBinary(v)),
    INNER_LINE_LENGTH("0l", SettingCategory.INNER_LINES, 6, v -> isIntegerWithin(v, 0, 20),
            Map.of(SHOW_INNER_LINES, 1)),
    INNER_LINE_THICKNESS("0t", SettingCategory.INNER_LINES, 2, v -> isIntegerWithin(v, 0, 10),
            Map.of(SHOW_INNER_LINES, 1)),
    INNER_LINE_OPACITY("0a", SettingCategory.INNER_LINES, 0.8f, v -> isFloatWithin(v, 3, 0, 1),
            Map.of(SHOW_INNER_LINES, 1)),
    INNER_LINE_OFFSET("0o", SettingCategory.INNER_LINES, 3, v -> isIntegerWithin(v, 0, 20),
            Map.of(SHOW_INNER_LINES, 1)),
    INNER_LINE_MOVEMENT_ERROR("0m", SettingCategory.INNER_LINES, 0, v -> isBinary(v),
            Map.of(SHOW_INNER_LINES, 1)),
    INNER_LINE_MOVEMENT_ERROR_MULTIPLIER("0s", SettingCategory.INNER_LINES, 1, v -> isFloatWithin(v, 3, 0 ,3),
            Map.of(SHOW_INNER_LINES, 1, INNER_LINE_MOVEMENT_ERROR, 1)),
    INNER_LINE_FIRING_ERROR("0f", SettingCategory.INNER_LINES, 1, v -> isBinary(v),
            Map.of(SHOW_INNER_LINES, 1)),
    INNER_LINE_FIRING_ERROR_MULTIPLIER("0e", SettingCategory.INNER_LINES, 1, v -> isFloatWithin(v, 3, 0 ,3),
            Map.of(SHOW_INNER_LINES, 1, INNER_LINE_FIRING_ERROR, 1)),

    SHOW_OUTER_LINES("1b", SettingCategory.OUTER_LINES, 1, v -> isBinary(v)),
    OUTER_LINE_LENGTH("1l", SettingCategory.OUTER_LINES, 2, v -> isIntegerWithin(v, 0, 20),
            Map.of(SHOW_OUTER_LINES, 1)),
    OUTER_LINE_THICKNESS("1t", SettingCategory.OUTER_LINES, 2, v -> isIntegerWithin(v, 0, 10),
            Map.of(SHOW_OUTER_LINES, 1)),
    OUTER_LINE_OPACITY("1a", SettingCategory.OUTER_LINES, 0.35f, v -> isFloatWithin(v, 3, 0, 1),
            Map.of(SHOW_OUTER_LINES, 1)),
    OUTER_LINE_OFFSET("1o", SettingCategory.OUTER_LINES, 10, v -> isIntegerWithin(v, 0, 20),
            Map.of(SHOW_OUTER_LINES, 1)),
    OUTER_LINE_MOVEMENT_ERROR("1m", SettingCategory.OUTER_LINES, 1, v -> isBinary(v),
            Map.of(SHOW_OUTER_LINES, 1)),
    OUTER_LINE_MOVEMENT_ERROR_MULTIPLIER("1s", SettingCategory.OUTER_LINES, 1, v -> isFloatWithin(v, 3, 0 ,3),
            Map.of(SHOW_OUTER_LINES, 1, OUTER_LINE_MOVEMENT_ERROR, 1)),
    OUTER_LINE_FIRING_ERROR("1f", SettingCategory.OUTER_LINES, 1, v -> isBinary(v),
            Map.of(SHOW_OUTER_LINES, 1)),
    OUTER_LINE_FIRING_ERROR_MULTIPLIER("1e", SettingCategory.OUTER_LINES, 1, v -> isFloatWithin(v, 3, 0 ,3),
            Map.of(SHOW_OUTER_LINES, 1, OUTER_LINE_FIRING_ERROR, 1)),

    SNIPER_SHOW_CENTER_DOT("d", SettingCategory.SNIPER, 1, v -> isBinary(v)),
    SNIPER_CENTER_DOT_COLOR("c", SettingCategory.SNIPER, 0xFF0000, v -> isIntegerWithin(v, 0x000000, 0xFFFFFF),
            Map.of(SNIPER_SHOW_CENTER_DOT, 1)),
    SNIPER_CENTER_DOT_THICKNESS("s", SettingCategory.INNER_LINES, 2, v -> isIntegerWithin(v, 0, 10),
            Map.of(SNIPER_SHOW_CENTER_DOT, 1)),
    SNIPER_CENTER_DOT_OPACITY("o", SettingCategory.INNER_LINES, 0.8f, v -> isFloatWithin(v, 3, 0, 1),
            Map.of(SNIPER_SHOW_CENTER_DOT, 1));

    private final String key;
    private final SettingCategory category;
    private final float def;
    private final Function<Float, Boolean> validator;
    private final Map<CrosshairSetting, Integer> prerequisites;

    CrosshairSetting(String key, SettingCategory category, float def, Function<Float, Boolean> validator, Map<CrosshairSetting, Integer> prerequisites) {
        this.key = key;
        this.category = category;
        this.def = def;
        this.validator = validator;
        this.prerequisites = prerequisites;
    }

    CrosshairSetting(String key, SettingCategory category, float def, Function<Float, Boolean> validator) {
        this.key = key;
        this.category = category;
        this.def = def;
        this.validator = validator;
        this.prerequisites = Map.of();
    }

    public String getKey() {
        return this.key;
    }

    public SettingCategory getCategory() {
        return this.category;
    }

    public float getDef() {
        return this.def;
    }

    public Function<Float, Boolean> getValidator() {
        return this.validator;
    }

    public Map<CrosshairSetting, Integer> getPrerequisites() {
        return this.prerequisites;
    }

    enum SettingCategory {
        META, GENERAL, OUTLINES, CENTER_DOT, INNER_LINES, OUTER_LINES, SNIPER
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

    private static boolean isBinary(float x) {
        return x == 0 || x == 1;
    }
}
