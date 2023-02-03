package volley.model.retcon.crosshair;

public interface Crosshair {
    String getName();

    boolean isMisaligned();

    Crosshair adjustSetting(CrosshairSetting setting, int newValue);

    String getCode();

    CrosshairVersion getVersion();
}
