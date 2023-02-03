package volley.model.retcon.crosshair;

import java.util.*;

import static volley.model.retcon.crosshair.CrosshairSetting.SettingCategory.*;

public class CrosshairV6_01 implements Crosshair {
    private final String name;
    private final Map<CrosshairSetting, Float> metaSettings, primarySettings, adsSettings, sniperSettings;

    private final static String DEFAULT_CROSSHAIR = "0";

//    public CrosshairV6_01(String name, Map<CrosshairSetting, Float> settings) {
//        this.name = name;
//        this.primarySettings = settings;
//    }

    public CrosshairV6_01(String name, String code) {
        this.name = name;
        this.metaSettings = new HashMap<>();
        this.primarySettings = new HashMap<>();
        this.adsSettings = new HashMap<>();
        this.sniperSettings = new HashMap<>();
        this.parseCode(code);
    }

    private void parseCode(String code) {
        String[] items = code.split(";");
        Map<String, Set<CrosshairSetting.SettingCategory>> allowedCategories = Map.of(
                "P", new HashSet<>(List.of(GENERAL, OUTLINES, CENTER_DOT, INNER_LINES, OUTER_LINES)),
                "A", new HashSet<>(List.of(GENERAL, OUTLINES, CENTER_DOT, INNER_LINES, OUTER_LINES)),
                "S", new HashSet<>(List.of(SNIPER)),
                "M", new HashSet<>(List.of(META))
        );
        String currentCategory = "M";

        int pointer = 0;
        while (pointer < items.length) {
            String item = items[pointer];
            if (pointer == 0) {
                if (!"0".equals(item)) {
                    throw new IllegalArgumentException("Crosshair code contains bad first item.");
                }
                pointer++;
            } else if (allowedCategories.containsKey(item)) {
                currentCategory = item;
                pointer++;
            }

            CrosshairSetting currentSetting = this.retrieveSettingFromCode(item, allowedCategories.get(currentCategory));
        }

    }

    private CrosshairSetting retrieveSettingFromCode(String key, Set<CrosshairSetting.SettingCategory> categories) {
        for (CrosshairSetting setting : CrosshairSetting.values()) {
            if (key.equals(setting.getKey()) && categories.contains(setting.getCategory())) {
                return setting;
            }
        }

        throw new IllegalArgumentException("Crosshair code contains unknown key declaration.");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isMisaligned() {
        return false;
    }

    @Override
    public Crosshair adjustSetting(CrosshairSetting setting, int newValue) {
        return null;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public CrosshairVersion getVersion() {
        return CrosshairVersion.v6_01;
    }
}
