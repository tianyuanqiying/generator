package com.chinaclear.sz.component.common;

import java.util.List;

public interface ICache {
    void readCache();
    String get(String key);
    List<String> getWithPrefix(String prefix);
}
