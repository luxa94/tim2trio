package rs.isa.mrs.trio.iceipice.model.dto;

/**
 * Created by nikolalukic on 5/22/16.
 */
public class AreaDTO {

    private long managerId;

    private String name;

    private String color;

    public long getManagerId() {
        return managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
