package com.jeremy.jarch;

import java.util.LinkedHashMap;
import java.util.Map;

public class MockDiaries {
    private static final String DESCRIPTION = "今天，天气晴朗，在第九巷大道上，我遇到了一群年轻人，他们优雅地弹奏着手风情，围观的人大多是少男少女，他们目不转睛。";

    public static Map<String, Diary> mock() {
        return mock(new LinkedHashMap<String, Diary>());
    }

    private static Map<String, Diary> mock(Map<String, Diary> data) {
        Diary diary = getDiary("2021-02-06 周六");
        data.put(diary.getId(), diary);
        Diary diary1 = getDiary("2021-02-07 周天");
        data.put(diary1.getId(), diary1);
        Diary diary2 = getDiary("2021-02-08 周一");
        data.put(diary2.getId(), diary2);
        Diary diary3 = getDiary("2021-02-09 周二");
        data.put(diary3.getId(), diary3);
        Diary diary4 = getDiary("2021-02-10 周三");
        data.put(diary4.getId(), diary4);
        Diary diary5 = getDiary("2021-02-11 周四");
        data.put(diary5.getId(), diary5);
        Diary diary6 = getDiary("2021-02-12 周五");
        data.put(diary6.getId(), diary6);
        return data;
    }

    private static Diary getDiary(String title) {
        return new Diary(title, DESCRIPTION);
    }
}
