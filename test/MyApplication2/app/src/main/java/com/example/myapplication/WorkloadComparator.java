package com.example.myapplication;

//import java.util.HashMap;
//import java.util.Map;

public class WorkloadComparator {
    private Map workloadMap;
    public WorkloadComparator() {
        workloadMap = new Map();
        workloadMap.put("Light", -0.5);
        workloadMap.put("Medium", -1.5);
        workloadMap.put("Heavy", -3.0);
        workloadMap.put("", -1.5);
    }

    public int compare(String workload1, String workload2) {
        return Double.compare(
                workloadMap.getOrDefault(workload1, 0.0),
                workloadMap.getOrDefault(workload2, 0.0)
        );
    }
    static class Map {
        private static final int CAPACITY = 16; // 默认容量
        private Entry[] buckets = new Entry[CAPACITY];

        public void put(String key, double value) {
            int index = getIndex(key);
            Entry newEntry = new Entry(key, value);
            Entry current = buckets[index];

            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                if (current.next == null) {
                    current.next = newEntry;
                    return;
                }
                current = current.next;
            }
            buckets[index] = newEntry;
        }
        public double getOrDefault(String key, double defaultValue) {
            int index = getIndex(key);
            Entry current = buckets[index];
            while (current != null) {
                if (current.key.equals(key)) {
                    return current.value;
                }
                current = current.next;
            }
            return defaultValue;
        }
        private int getIndex(String key) {
            int hashCode = toAscii(key);
            int index = hashCode % CAPACITY;
            return index < 0 ? -index : index;
        }
        private int toAscii(String s){
            String text = s;
            int res = 0;
            for (int i = 0; i < text.length(); i++) {
                char ch = text.charAt(i);
                int ascii = (int) ch;
                res += ascii;
            }
            return res;
        }
        private static class Entry {
            String key;
            double value;
            Entry next;
            Entry(String key, double value) {
                this.key = key;
                this.value = value;
                this.next = null;
            }
        }
    }
}

