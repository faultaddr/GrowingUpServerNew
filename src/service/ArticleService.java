package service;

import dao.DaoFactory;
import entity.GArticleEntity;

import java.util.List;


/**
 * Created by panyu on 2017/7/6.
 */
public class ArticleService {
    public boolean add(GArticleEntity gArticleEntity){
        DaoFactory<GArticleEntity> daoFactory = new DaoFactory<>();
        return daoFactory.save(gArticleEntity);
    }
    public boolean delete(GArticleEntity gArticleEntity){
        DaoFactory<GArticleEntity> daoFactory=new DaoFactory<>();
        return daoFactory.delete(gArticleEntity);
    }
    public boolean update(GArticleEntity gArticleEntity){
        DaoFactory<GArticleEntity>daoFactory=new DaoFactory<>();
        return daoFactory.update(gArticleEntity);
    }
    public List cursor(){
        DaoFactory<GArticleEntity>daoFactory=new DaoFactory<>();
        List list = null;
        try {
            list= daoFactory.cursor(null,"select * from g_article",GArticleEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //目前业务逻辑只有这三个方法
}
