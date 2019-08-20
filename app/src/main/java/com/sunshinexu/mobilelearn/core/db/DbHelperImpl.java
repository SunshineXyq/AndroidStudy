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

import java.util.List;

import javax.inject.Inject;

public class DbHelperImpl implements DbHelper {

    private final DaoMaster daoMaster;
    private final DaoSession daoSession;

    @Inject
    public DbHelperImpl() {
        DaoMaster.DevOpenHelper devOpenHelper =
                new DaoMaster.DevOpenHelper(MobileLearnApp.getContext(), Constants.DB_NAME);
        SQLiteDatabase database = devOpenHelper.getWritableDatabase();
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    @Override
    public List<HistoryData> addHistoryData(String data) {
        return null;
    }

    @Override
    public void clearHistoryData() {

    }

    @Override
    public void deleteHistoryDataId(Long id) {

    }

    @Override
    public List<HistoryData> loadAllHistoryData() {
        return null;
    }
}
