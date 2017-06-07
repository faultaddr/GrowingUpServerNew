package dao;

import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * Created by panyunyi on 2017/5/3.
 */
class DaoFactory<T> extends Dao<T>{

    /*
* function：增加
* param：实体类对象
* */
    @Override
    public boolean save(T u) {
        String i;

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        // 2. 根据服务注册类创建一个元数据资源集，同时构建元数据并生成应用一般唯一的的session工厂
        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction t = null;
        try {
            // 从会话工厂获取一个session
            t = session.beginTransaction();
            i = (String) session.save(u);
            t.commit();
        } catch (HibernateException herror) {
            t.rollback();
            herror.printStackTrace();
            return false;

        } finally {
            session.close();

        }


        //System.out.println(u.getCurrentLocation());
        return true;
    }

    /*
    * function：删除
    * param：实体类对象
    * */
    @Override
    public boolean delete(T u) {
        String info;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        // 2. 根据服务注册类创建一个元数据资源集，同时构建元数据并生成应用一般唯一的的session工厂
        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();

        Transaction t = null;
        Session session = sessionFactory.openSession();// 从会话工厂获取一个session

        try {
            t = session.beginTransaction();

            session.delete(u);
            t.commit();
        } catch (HibernateException herror) {
            t.rollback();
            herror.printStackTrace();
            return false;

        } finally {
            session.close();

        }
        return true;

    }

    /*
    * function：更新
    * param：实体类对象
    * */
    @Override
    public boolean update(T u) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        // 2. 根据服务注册类创建一个元数据资源集，同时构建元数据并生成应用一般唯一的的session工厂
        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();

        Transaction t = null;
        Session session = sessionFactory.openSession();// 从会话工厂获取一个session
        try {
             t= session.beginTransaction();
            session.update(u);
            t.commit();
        } catch (HibernateException herror) {
            t.rollback();
            herror.printStackTrace();
            return false;

        } finally {
            session.close();
        }
        return true;
    }
@Override
    public List cursor(T u, String s, Class aclass) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        // 2. 根据服务注册类创建一个元数据资源集，同时构建元数据并生成应用一般唯一的的session工厂
        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory();


        Session session = sessionFactory.openSession();// 从会话工厂获取一个session
        Transaction t = session.beginTransaction();
        SQLQuery sqlQuery = session.createSQLQuery(s);
        List list;
        //if 做简单的查询
        //else 做统计
        if (aclass != null) {
            sqlQuery.addEntity(aclass);
            list = sqlQuery.getResultList();
        } else {
//            int count = ((Number) sqlQuery.uniqueResult()).intValue();
//            list = new ArrayList<Integer>();
//            list.add(count);
            list=null;
            //sqlQuery.getQueryReturns();
            //sqlQuery.executeUpdate();

        }

        t.commit();
        session.close();
        return list;
    }
/*    public boolean generateSn(){
        Date date=Date.valueOf(LocalDate.now());
    }*/

}
