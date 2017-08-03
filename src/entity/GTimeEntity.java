package entity;

/**
 * Created by panyunyi on 2017/8/4.
 * CUFE cs14
 */
public class GTimeEntity {
    private String teacherId;
    private String timeDetail;
    private int id;
    private String timeStatus;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTimeDetail() {
        return timeDetail;
    }

    public void setTimeDetail(String timeDetail) {
        this.timeDetail = timeDetail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimeStatus() {
        return timeStatus;
    }

    public void setTimeStatus(String timeStatus) {
        this.timeStatus = timeStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GTimeEntity that = (GTimeEntity) o;

        if (id != that.id) return false;
        if (teacherId != null ? !teacherId.equals(that.teacherId) : that.teacherId != null) return false;
        if (timeDetail != null ? !timeDetail.equals(that.timeDetail) : that.timeDetail != null) return false;
        if (timeStatus != null ? !timeStatus.equals(that.timeStatus) : that.timeStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teacherId != null ? teacherId.hashCode() : 0;
        result = 31 * result + (timeDetail != null ? timeDetail.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (timeStatus != null ? timeStatus.hashCode() : 0);
        return result;
    }
}
