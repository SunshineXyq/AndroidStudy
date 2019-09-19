package com.sunshinexu.mobilelearn.core.db;

/*
 * Created by Xu Yuanqiang
 * Created on 2019/8/20
 */

import android.database.sqlite.SQLiteDatabase;

import com.sunshinexu.mobilelearn.activity.main.bean.HistoryData;
import com.sunshinexu.mobilelearn.app.MobileLearnApp;
import com.sunshinexu.mobilelearn.core.constant.Constants;
import com.sunshinexu.mobilelearn.core.greendao.DaoMaster;
import com.sunshinexu.mobilelearn.core.greendao.DaoSession;
import com.sunshinexu.mobilelearn.core.greendao.HistoryDataDao;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

/**
 * 数据库实现类
 */
public class DbHelperImpl implements DbHelper {

    private final DaoMaster daoMaster;
    private final DaoSession daoSession;
    private String data;
    private HistoryData historyData;
    private List<HistoryData> historyDataList;
    private static final int HISTORY_LIST_SIZE = 10;

    @Inject
    public DbHelperImpl() {
        DaoMaster.DevOpenHelper devOpenHelper =
                new DaoMaster.DevOpenHelper(MobileLearnApp.getContext(), Constants.DB_NAME);
        SQLiteDatabase database = devOpenHelper.getWritableDatabase();
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    /**
     * 添加搜索历史
     * @param data
     * @return
     */

    @Override
    public List<HistoryData> addHistoryData(String data) {
        this.data = data;
        getHistoryList();
        createHistoryData();
        if (historyDataForward()) {
            return historyDataList;
        }

        if (historyDataList.size() < HISTORY_LIST_SIZE) {
            getHistoryDataDao().insert(historyData);
        } else {
            historyDataList.remove(0);
            historyDataList.add(historyData);
            getHistoryDataDao().deleteAll();
            getHistoryDataDao().insertInTx(historyDataList);
        }
        return historyDataList;
    }

    /**
     * 清空搜索历史
     */
    @Override
    public void clearHistoryData() {
        daoSession.getHistoryDataDao().deleteAll();
    }

    /**
     * 删除某一条搜索历史
     * @param id
     */
    @Override
    public void deleteHistoryDataId(Long id) {
        daoSession.getHistoryDataDao().deleteByKey(id);
    }

    /**
     * 加载搜索历史
     * @return
     */
    @Override
    public List<HistoryData> loadAllHistoryData() {
        return daoSession.getHistoryDataDao().loadAll();
    }

    /**
     * 获取历史搜索列表
     */
    private void getHistoryList() {
        historyDataList = getHistoryDataDao().loadAll();
    }

    private void createHistoryData() {
        historyData = new HistoryData();
        historyData.setDate(System.currentTimeMillis());
        historyData.setData(data);
    }

    private HistoryDataDao getHistoryDataDao() {
        return daoSession.getHistoryDataDao();
    }

    /**
     * 历史数据前移
     *
     * @return 返回true表示查询的数据已存在，只需将其前移到第一项历史记录，否则需要增加新的历史记录
     */
    private boolean historyDataForward() {
        //重复搜索时进行历史记录前移
        Iterator<HistoryData> iterator = historyDataList.iterator();
        //不要在foreach循环中进行元素的remove、add操作，使用Iterator模式
        while (iterator.hasNext()) {
            HistoryData historyData1 = iterator.next();
            if (historyData1.getData().equals(data)) {
                historyDataList.remove(historyData1);
                historyDataList.add(historyData);
                getHistoryDataDao().deleteAll();
                getHistoryDataDao().insertInTx(historyDataList);
                return true;
            }
        }
        return false;
    }
}
