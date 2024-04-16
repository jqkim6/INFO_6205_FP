package com.example.myapplication;

import java.util.HashMap;
import java.util.Map;

public class WorkloadComparator {
    private Map<String, Double> workloadMap;

    public WorkloadComparator() {
        workloadMap = new HashMap<>();
        workloadMap.put("Light", -0.5);
        workloadMap.put("Medium", -1.5);
        workloadMap.put("Heavy", -3.0);
        workloadMap.put("", -1.5);  // 处理无工作量信息的情况
    }

    public int compare(String workload1, String workload2) {
        return Double.compare(workloadMap.getOrDefault(workload1, 0.0), workloadMap.getOrDefault(workload2, 0.0));
    }
}

