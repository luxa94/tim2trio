package rs.isa.mrs.trio.iceipice.model.dto;

/**
 * Created by Nina on 22-Apr-16.
 */
public class CookDTO extends BaseUserDTO {

    private String dressSize;

    private String footwearSize;

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
