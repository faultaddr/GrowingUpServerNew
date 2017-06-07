package entity;

import javax.persistence.*;

/**
 * Created by panyu on 2017/6/7.
 */
@Entity
@Table(name = "g_user", schema = "growingup", catalog = "")
public class GUserEntity {
    private String userName;
    private String userPassword;
    private String userId;
    private String userWechat;
    private String userAlipay;
    private String userTelephoneNumber;
    private String userGrade;
    private String userClass;
    private String userInstitution;
    private String userRole;

    @Basic
    @Column(name = "user_name", nullable = true, length = 30)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_password", nullable = true, length = 30)
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Id
    @Column(name = "user_Id", nullable = false, length = 15)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_wechat", nullable = true, length = 30)
    public String getUserWechat() {
        return userWechat;
    }

    public void setUserWechat(String userWechat) {
        this.userWechat = userWechat;
    }

    @Basic
    @Column(name = "user_alipay", nullable = true, length = 30)
    public String getUserAlipay() {
        return userAlipay;
    }

    public void setUserAlipay(String userAlipay) {
        this.userAlipay = userAlipay;
    }

    @Basic
    @Column(name = "user_telephone_number", nullable = true, length = 11)
    public String getUserTelephoneNumber() {
        return userTelephoneNumber;
    }

    public void setUserTelephoneNumber(String userTelephoneNumber) {
        this.userTelephoneNumber = userTelephoneNumber;
    }

    @Basic
    @Column(name = "user_grade", nullable = true, length = 4)
    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade;
    }

    @Basic
    @Column(name = "user_class", nullable = true, length = 20)
    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass;
    }

    @Basic
    @Column(name = "user_institution", nullable = true, length = 30)
    public String getUserInstitution() {
        return userInstitution;
    }

    public void setUserInstitution(String userInstitution) {
        this.userInstitution = userInstitution;
    }

    @Basic
    @Column(name = "user_role", nullable = true, length = 4)
    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GUserEntity that = (GUserEntity) o;

        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userPassword != null ? !userPassword.equals(that.userPassword) : that.userPassword != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (userWechat != null ? !userWechat.equals(that.userWechat) : that.userWechat != null) return false;
        if (userAlipay != null ? !userAlipay.equals(that.userAlipay) : that.userAlipay != null) return false;
        if (userTelephoneNumber != null ? !userTelephoneNumber.equals(that.userTelephoneNumber) : that.userTelephoneNumber != null)
            return false;
        if (userGrade != null ? !userGrade.equals(that.userGrade) : that.userGrade != null) return false;
        if (userClass != null ? !userClass.equals(that.userClass) : that.userClass != null) return false;
        if (userInstitution != null ? !userInstitution.equals(that.userInstitution) : that.userInstitution != null)
            return false;
        if (userRole != null ? !userRole.equals(that.userRole) : that.userRole != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (userWechat != null ? userWechat.hashCode() : 0);
        result = 31 * result + (userAlipay != null ? userAlipay.hashCode() : 0);
        result = 31 * result + (userTelephoneNumber != null ? userTelephoneNumber.hashCode() : 0);
        result = 31 * result + (userGrade != null ? userGrade.hashCode() : 0);
        result = 31 * result + (userClass != null ? userClass.hashCode() : 0);
        result = 31 * result + (userInstitution != null ? userInstitution.hashCode() : 0);
        result = 31 * result + (userRole != null ? userRole.hashCode() : 0);
        return result;
    }
}
