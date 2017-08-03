package service;

import dao.DaoFactory;
import entity.GTeacherEntity;

import java.util.List;

/**
 * Created by panyunyi on 2017/8/1.
 * CUFE cs14
 */
public class TeacherService {
    public boolean add(GTeacherEntity GTeacherEntity){
        DaoFactory<GTeacherEntity> daoFactory=new DaoFactory<GTeacherEntity>();
        return daoFactory.save(GTeacherEntity);
    }
    public boolean delete(GTeacherEntity gTeacherEntity){
        DaoFactory<GTeacherEntity> daoFactory=new DaoFactory<GTeacherEntity>();
        return daoFactory.delete(gTeacherEntity);
    }
    public boolean update(GTeacherEntity gTeacherEntity){
        DaoFactory<GTeacherEntity>daoFactory=new DaoFactory<>();
        return daoFactory.update(gTeacherEntity);
    }
    public List cursor(String sql, Class mClass){
        DaoFactory<GTeacherEntity>daoFactory=new DaoFactory<>();
        List<GTeacherEntity>teacherList=daoFactory.cursor(null,sql,mClass);
        return teacherList;
    }

}



