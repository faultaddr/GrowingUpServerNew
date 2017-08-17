package entity;

/**
 * Created by panyunyi on 2017/8/5.
 * CUFE cs14
 */
public class GTeacherEntity {
    private String teacherId;
    private String teacherName;
    private String teacherMajor;
    private String teacherMobileNumber;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherMajor() {
        return teacherMajor;
    }

    public void setTeacherMajor(String teacherMajor) {
        this.teacherMajor = teacherMajor;
    }

    public String getTeacherMobileNumber() {
        return teacherMobileNumber;
    }

    public void setTeacherMobileNumber(String teacherMobileNumber) {
        this.teacherMobileNumber = teacherMobileNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GTeacherEntity that = (GTeacherEntity) o;

        if (teacherId != null ? !teacherId.equals(that.teacherId) : that.teacherId != null) return false;
        if (teacherName != null ? !teacherName.equals(that.teacherName) : that.teacherName != null) return false;
        if (teacherMajor != null ? !teacherMajor.equals(that.teacherMajor) : that.teacherMajor != null) return false;
        if (teacherMobileNumber != null ? !teacherMobileNumber.equals(that.teacherMobileNumber) : that.teacherMobileNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teacherId != null ? teacherId.hashCode() : 0;
        result = 31 * result + (teacherName != null ? teacherName.hashCode() : 0);
        result = 31 * result + (teacherMajor != null ? teacherMajor.hashCode() : 0);
        result = 31 * result + (teacherMobileNumber != null ? teacherMobileNumber.hashCode() : 0);
        return result;
    }
}
