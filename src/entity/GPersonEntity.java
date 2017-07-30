package entity;

/**
 * Created by panyu on 2017/7/7.
 */
public class GPersonEntity {
    private String personName;
    private String personSex;
    private String personInfo;
    private String personId;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonSex() {
        return personSex;
    }

    public void setPersonSex(String personSex) {
        this.personSex = personSex;
    }

    public String getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(String personInfo) {
        this.personInfo = personInfo;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GPersonEntity that = (GPersonEntity) o;

        if (personName != null ? !personName.equals(that.personName) : that.personName != null) return false;
        if (personSex != null ? !personSex.equals(that.personSex) : that.personSex != null) return false;
        if (personInfo != null ? !personInfo.equals(that.personInfo) : that.personInfo != null) return false;
        if (personId != null ? !personId.equals(that.personId) : that.personId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = personName != null ? personName.hashCode() : 0;
        result = 31 * result + (personSex != null ? personSex.hashCode() : 0);
        result = 31 * result + (personInfo != null ? personInfo.hashCode() : 0);
        result = 31 * result + (personId != null ? personId.hashCode() : 0);
        return result;
    }
}
