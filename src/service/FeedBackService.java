package service;

import dao.DaoFactory;
import entity.GFeedbackEntity;

import java.util.List;

/**
 * Created by panyunyi on 2017/8/9.
 * CUFE cs14
 */
public class FeedBackService {
    public boolean add(GFeedbackEntity gFeedbackEntity){
        DaoFactory<GFeedbackEntity> daoFactory=new DaoFactory<GFeedbackEntity>();
        return daoFactory.save(gFeedbackEntity);
    }
    public boolean delete(GFeedbackEntity gFeedbackEntity){
        DaoFactory<GFeedbackEntity> daoFactory=new DaoFactory<GFeedbackEntity>();
        return daoFactory.delete(gFeedbackEntity);
    }
    public boolean update(GFeedbackEntity gFeedbackEntity){
        DaoFactory<GFeedbackEntity>daoFactory=new DaoFactory<>();
        return daoFactory.update(gFeedbackEntity);
    }
    public List cursor(String sql, Class mClass){
        DaoFactory<GFeedbackEntity>daoFactory=new DaoFactory<>();
        List<GFeedbackEntity>feedBackList=daoFactory.cursor(null,sql,mClass);
        return feedBackList;
    }
}
