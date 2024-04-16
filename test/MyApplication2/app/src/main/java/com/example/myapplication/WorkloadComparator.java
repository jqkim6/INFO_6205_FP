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
        workloadMap.put("", -1.5);  // 处理无工作量信息的情况
    }

    public int compare(String workload1, String workload2) {
        return Double.compare(
                workloadMap.getOrDefault(workload1, 0.0),
                workloadMap.getOrDefault(workload2, 0.0)
        );
    }

    // 键值对存储类
    static class Map {
        private static final int CAPACITY = 16; // 默认容量
        private Entry[] buckets = new Entry[CAPACITY];

        public void put(String key, double value) {
            int index = getIndex(key);
            Entry newEntry = new Entry(key, value);
            Entry current = buckets[index];

            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value; // 更新已存在的键
                    return;
                }
                if (current.next == null) {
                    current.next = newEntry; // 添加到链表的末尾
                    return;
                }
                current = current.next;
            }
            buckets[index] = newEntry; // 新插入
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
            return defaultValue; // 默认值
        }

        private int getIndex(String key) {
            int hashCode = toAscii(key);
            int index = hashCode % CAPACITY;
            return index < 0 ? -index : index; // 确保索引为正
        }

        private int toAscii(String s){
            String text = s;
            int res = 0;
            // 遍历字符串中的每个字符
            for (int i = 0; i < text.length(); i++) {
                char ch = text.charAt(i); // 获取字符
                int ascii = (int) ch; // 将字符转换为对应的ASCII码（其实就是转换成int类型）
                res += ascii;
            }
            return res;
        }

        // 存储元素的链表结构
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

