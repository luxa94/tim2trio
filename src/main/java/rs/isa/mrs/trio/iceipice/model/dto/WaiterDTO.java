package rs.isa.mrs.trio.iceipice.model.dto;

/**
 * Created by Nina on 10-May-16.
 */
public class WaiterDTO extends BaseUserDTO {

    private String dressSize;

    private  String footwearSize;

    public String getDressSize() {
        return dressSize;
    }

    public void setDressSize(String dressSize) {
        this.dressSize = dressSize;
    }

    public String getFootwearSize() {
        return footwearSize;
    }

    public void setFootwearSize(String footwearSize) {
        this.footwearSize = footwearSize;
    }
}
