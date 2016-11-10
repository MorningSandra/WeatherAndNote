package test.greenDAO.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import test.greenDAO.bean.Duty;

public class DutyDao extends AbstractDao<Duty, Long> {

    public static final String TABLENAME = "things";

    /**
     * Properties of entity Duty.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Title = new Property(1, String.class, "title", false, "TITLE");
        public final static Property Info = new Property(2, String.class, "info", false, "INFO");
        public final static Property Type = new Property(3, String.class, "type", false, "TYPE");
        public final static Property Status = new Property(4, Boolean.class, "status", false, "STATUS");
        public final static Property Date = new Property(5, java.util.Date.class, "date", false, "DATE");
    };


    public DutyDao(DaoConfig config) {
        super(config);
    }
    
    public DutyDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"things\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"TITLE\" TEXT NOT NULL ," + // 1: title
                "\"INFO\" TEXT," + // 2: info
                "\"TYPE\" TEXT," + // 3: type
                "\"STATUS\" INTEGER," + // 4: status
                "\"DATE\" INTEGER);"); // 5: date
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"things\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Duty entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getTitle());
 
        String info = entity.getInfo();
        if (info != null) {
            stmt.bindString(3, info);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(4, type);
        }
 
        Boolean status = entity.getStatus();
        if (status != null) {
            stmt.bindLong(5, status ? 1L: 0L);
        }
 
        java.util.Date date = entity.getDate();
        if (date != null) {
            stmt.bindLong(6, date.getTime());
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Duty readEntity(Cursor cursor, int offset) {
        Duty entity = new Duty( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // title
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // info
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // type
            cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0, // status
            cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)) // date
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Duty entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTitle(cursor.getString(offset + 1));
        entity.setInfo(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setType(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setStatus(cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0);
        entity.setDate(cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Duty entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Duty entity) {
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
