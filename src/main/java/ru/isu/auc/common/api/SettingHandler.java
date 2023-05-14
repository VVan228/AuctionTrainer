package ru.isu.auc.common.api;

import java.util.List;

public interface SettingHandler {
    <T> T resolveSetting(String fieldName, List<?> settingTree);
    <T> T resolveSetting(String fieldName, T defaultValue, List<?> settingTree);

    <T> T resolveSetting(List<String> fieldNames, List<?> settingTree);
    <T> T resolveSetting(List<String> fieldNames, T defaultValue, List<?> settingTree);

}
