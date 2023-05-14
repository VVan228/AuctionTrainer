package ru.isu.auc.common.impl;

import org.springframework.stereotype.Component;
import ru.isu.auc.common.api.SettingHandler;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Component
public class SettingHandlerImpl implements SettingHandler {
    @Override
    public <T> T resolveSetting(String fieldName, List<?> settingTree
    ) {
        return resolveSetting(Arrays.asList(fieldName), null, settingTree);
    }

    @Override
    public <T> T resolveSetting(String fieldName, T defaultValue, List<?> settingTree) {
        return resolveSetting(Arrays.asList(fieldName), defaultValue, settingTree);
    }

    @Override
    public <T> T resolveSetting(List<String> fieldNames, List<?> settingTree) {
        return resolveSetting(fieldNames, null, settingTree);
    }

    @Override
    public <T> T resolveSetting(List<String> fieldNames, T defaultValue, List<?> settingTree) {
        T res = defaultValue;
        for(Object setting: settingTree)
        {
            res = getValue(setting, defaultValue, fieldNames);
            if(res!=defaultValue) break;
        }

        return res;
    }

    private <T> T getValue(Object object, T defaultValue, List<String> fieldNames)
    {
        T res = defaultValue;
        for(String fieldName: fieldNames)
        {
            try {
                Field field = object.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                T tempRes = (T) field.get(object);
                if(tempRes!=null) res=tempRes;
                if(res!=defaultValue) break;
            } catch (Exception ignored) {}
        }
        return res;
    }
}
