package service;

import dao.Dao;
import dao.DaoFactory;
import entity.GPersonEntity;

import java.util.List;

/**
 * Created by panyunyi on 2017/7/7.
 */
public class PersonService {

    public boolean add(GPersonEntity gPersonEntity){
        DaoFactory<GPersonEntity> daoFactory=new DaoFactory<GPersonEntity>();
        return daoFactory.save(gPersonEntity);
    }
    public boolean delete(GPersonEntity gPersonEntity){
        DaoFactory<GPersonEntity> daoFactory=new DaoFactory<GPersonEntity>();
        return daoFactory.delete(gPersonEntity);
    }
    public boolean update(GPersonEntity gPersonEntity){
        DaoFactory<GPersonEntity>daoFactory=new DaoFactory<>();
        return daoFactory.update(gPersonEntity);
    }
    public List cursor(String sql,Class mClass){
        DaoFactory<GPersonEntity>daoFactory=new DaoFactory<>();
        List<GPersonEntity>personList= null;
        try {
            personList = daoFactory.cursor(null,sql,mClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personList;
    }

}
