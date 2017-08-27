package service;

import dao.DaoFactory;
import entity.GTimeEntity;

import java.util.List;

public class TimeService {
    public static TimeService timeService;
    public static TimeService newInstance() {
        if(timeService==null)
            return new TimeService();
        else return timeService;
    }
    public boolean add(GTimeEntity gTimeEntity){
        DaoFactory<GTimeEntity> daoFactory=new DaoFactory<GTimeEntity>();
        return daoFactory.save(gTimeEntity);
    }
    public boolean delete(GTimeEntity gTimeEntity){
        DaoFactory<GTimeEntity> daoFactory=new DaoFactory<GTimeEntity>();
        return daoFactory.delete(gTimeEntity);
    }
    public boolean update(GTimeEntity gTimeEntity){
        DaoFactory<GTimeEntity>daoFactory=new DaoFactory<>();
        return daoFactory.update(gTimeEntity);
    }
    public boolean update(String sql){
        DaoFactory<GTimeEntity>daoFactory=new DaoFactory<>();
        return daoFactory.update(sql);
    }
    public List cursor(String sql, Class mClass){
        DaoFactory<GTimeEntity>daoFactory=new DaoFactory<>();
        List<GTimeEntity>timeList= null;
        try {
            timeList = daoFactory.cursor(null,sql,mClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeList;
    }

}
