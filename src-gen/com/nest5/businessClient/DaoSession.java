package com.nest5.businessClient;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.nest5.businessClient.DailySale;

import com.nest5.businessClient.DailySaleDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig dailySaleDaoConfig;

    private final DailySaleDao dailySaleDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        dailySaleDaoConfig = daoConfigMap.get(DailySaleDao.class).clone();
        dailySaleDaoConfig.initIdentityScope(type);

        dailySaleDao = new DailySaleDao(dailySaleDaoConfig, this);

        registerDao(DailySale.class, dailySaleDao);
    }
    
    public void clear() {
        dailySaleDaoConfig.getIdentityScope().clear();
    }

    public DailySaleDao getDailySaleDao() {
        return dailySaleDao;
    }

}
