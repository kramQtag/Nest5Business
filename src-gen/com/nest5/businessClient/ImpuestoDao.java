package com.nest5.businessClient;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.nest5.businessClient.Impuesto;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table IMPUESTO.
*/
public class ImpuestoDao extends AbstractDao<Impuesto, Long> {

    public static final String TABLENAME = "IMPUESTO";

    /**
     * Properties of entity Impuesto.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property SyncId = new Property(1, long.class, "syncId", false, "SYNC_ID");
        public final static Property Percentage = new Property(2, double.class, "percentage", false, "PERCENTAGE");
        public final static Property Name = new Property(3, String.class, "name", false, "NAME");
    };


    public ImpuestoDao(DaoConfig config) {
        super(config);
    }
    
    public ImpuestoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'IMPUESTO' (" + //
                "'_id' INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "'SYNC_ID' INTEGER NOT NULL ," + // 1: syncId
                "'PERCENTAGE' REAL NOT NULL ," + // 2: percentage
                "'NAME' TEXT NOT NULL );"); // 3: name
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'IMPUESTO'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Impuesto entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getSyncId());
        stmt.bindDouble(3, entity.getPercentage());
        stmt.bindString(4, entity.getName());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Impuesto readEntity(Cursor cursor, int offset) {
        Impuesto entity = new Impuesto( //
            cursor.getLong(offset + 0), // id
            cursor.getLong(offset + 1), // syncId
            cursor.getDouble(offset + 2), // percentage
            cursor.getString(offset + 3) // name
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Impuesto entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setSyncId(cursor.getLong(offset + 1));
        entity.setPercentage(cursor.getDouble(offset + 2));
        entity.setName(cursor.getString(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Impuesto entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Impuesto entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
