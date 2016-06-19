package rs.isa.mrs.trio.iceipice.model.dto;

import java.util.Date;

/**
 * Created by Sandra on 16.6.2016.
 */
public class ShiftDTO {
    private long workerId;
    private String workerType;
    private Date startDate;
    private Date endDate;
    private String startHour;
    private String endHour;
    private long areaId;

    public ShiftDTO(long workerId, String workerType, Date startDate, Date endDate, String startHour, String endHour, long areaId) {
        this.workerId = workerId;
        this.workerType = workerType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startHour = startHour;
        this.endHour = endHour;
        this.areaId = areaId;
    }

    public ShiftDTO() {
    }

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public String getWorkerType() {
        return workerType;
    }

    public void setWorkerType(String workerType) {
        this.workerType = workerType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }

    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }
}
