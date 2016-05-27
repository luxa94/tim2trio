package rs.isa.mrs.trio.iceipice.model.dto;

import rs.isa.mrs.trio.iceipice.model.RestaurantTable;

/**
 * Created by nikolalukic on 5/22/16.
 */
public class RestaurantTableDTO {

    private long id;

    private String name;

    private int capacity;

    private long area;

    private String fabricTable;

    public RestaurantTableDTO(RestaurantTable table) {
        id = table.getId();
        name = table.getName();
        fabricTable = table.getFabricTable();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public long getArea() {
        return area;
    }

    public void setArea(long areaId) {
        this.area = areaId;
    }

    public String getFabricTable() {
        return fabricTable;
    }

    public void setFabricTable(String fabricTable) {
        this.fabricTable = fabricTable;
    }

}
