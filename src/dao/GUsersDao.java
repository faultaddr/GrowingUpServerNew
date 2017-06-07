package dao;



import entity.GUserEntity;

import java.util.List;

/**
 * Created by panyunyi on 2017/5/2.
 * use for adding/deleting/updating members
 */



public class GUsersDao {

    public boolean add(GUserEntity GUserEntity) {
        DaoFactory<GUserEntity> daoFactory = new DaoFactory<>();
        return daoFactory.save(GUserEntity);
    }

    public boolean update(GUserEntity GUserEntity) {
        DaoFactory<GUserEntity> daoFactory = new DaoFactory<>();
        return daoFactory.update(GUserEntity);
    }

    public boolean delete(GUserEntity hUserEntity) {
        DaoFactory<GUserEntity> daoFactory = new DaoFactory<>();
        return daoFactory.delete(hUserEntity);
    }

    /*
    * @Param:实体类对象
    * @return：布尔值
    *
    * */
    public GUserEntity login(GUserEntity GUserEntity) {
        String userId = GUserEntity.getUserId();
        String passWord = GUserEntity.getUserPassword();
        DaoFactory<GUserEntity> daoFactory = new DaoFactory<>();
        List<GUserEntity>list = daoFactory.cursor(GUserEntity, "select * from g_user as u where u.user_Id='" + userId + "'", GUserEntity.class);
//        for (int i = 0; i < list.size(); i++) {
//            GUserEntity h = (GUserEntity) list.get(i);
//            System.out.print(h.getHaidaxueSn());
//        }
//        if (list.size() != 0) return true;
        if(list.size()!=0){
            if(list.get(0).getUserPassword().equals(passWord)){
                return list.get(0);
            }
        }
        return null;
}

    /*
    * @Param1:实体类对象
    * @Param2:待查询的haidaxue_sn
    * @return 实体类对象（带有相关属性值）
    * */
    public GUserEntity getInforByHaidaxue_sn(GUserEntity GUserEntity, String haidaxueSn) {
        DaoFactory<GUserEntity> daoFactory = new DaoFactory<>();
        List result = daoFactory.cursor(GUserEntity, "select * from h_users where haidaxue_sn='" + haidaxueSn + "'", GUserEntity.class);
        GUserEntity h = (GUserEntity) result.get(0);
        return h;
    }

    public List<GUserEntity> list() {
        GUserEntity GUserEntity = new GUserEntity();
        DaoFactory<GUserEntity> daoFactory = new DaoFactory<>();
        List<GUserEntity> result = daoFactory.cursor(GUserEntity, "select * from h_users", GUserEntity.class);

        return result;

    }


    /*
    * 获取所有的嘉宾信息
    * */
    public List getSpecialProfile() {
        DaoFactory<GUserEntity> daoFactory = new DaoFactory<>();
        GUserEntity GUserEntity = new GUserEntity();
        List<GUserEntity> list = daoFactory.cursor(GUserEntity, "select * from h_users limit 10", GUserEntity.class);
        return list;
    }


}
