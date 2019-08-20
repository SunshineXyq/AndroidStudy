package com.sunshinexu.mobilelearn.core.db;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/20
 */

import com.sunshinexu.mobilelearn.activity.main.bean.HistoryData;

import java.util.List;

public interface DbHelper {

    List<HistoryData> addHistoryData(String data);

    void clearHistoryData();

    void deleteHistoryDataId(Long id);

    List<HistoryData> loadAllHistoryData();
}
