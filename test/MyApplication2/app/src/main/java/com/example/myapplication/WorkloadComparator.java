package com.example.myapplication;

//import java.util.HashMap;
//import java.util.Map;

public class WorkloadComparator {
    private SimpleMap workloadMap;

    public WorkloadComparator() {
        workloadMap = new SimpleMap();
        workloadMap.put("Light", -0.5);
        workloadMap.put("Medium", -1.5);
        workloadMap.put("Heavy", -3.0);
        workloadMap.put("", -1.5);  // 处理无工作量信息的情况
    }

    public int compare(String workload1, String workload2) {
        return Double.compare(
                workloadMap.getOrDefault(workload1, 0.0),
                workloadMap.getOrDefault(workload2, 0.0)
        );
    }

    // 键值对存储类
    static class SimpleMap {
        private Entry[] entries = new Entry[100]; // 假设我们最多处理100个不同的键
        private int size = 0;

        public void put(String key, double value) {
            // 检查已有键并更新值
            for (int i = 0; i < size; i++) {
                if (entries[i].key.equals(key)) {
                    entries[i].value = value;
                    return;
                }
            }
            // 添加新键值对
            if (size < entries.length) {
                entries[size++] = new Entry(key, value);
            }
        }

        public double getOrDefault(String key, double defaultValue) {
            for (int i = 0; i < size; i++) {
                if (entries[i].key.equals(key)) {
                    return entries[i].value;
                }
            }
            return defaultValue;
        }

        // 键值对存储结构
        private static class Entry {
            String key;
            double value;

            Entry(String key, double value) {
                this.key = key;
                this.value = value;
            }
        }
    }
}

