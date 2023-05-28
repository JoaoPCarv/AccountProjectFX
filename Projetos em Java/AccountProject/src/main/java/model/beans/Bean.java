package model.beans;

import java.io.Serializable;

public class Bean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String lastSerializationDateTime;

    public String getLastSerializationDateTime() {
        return this.lastSerializationDateTime;
    }

    public void setLastSerializationDateTime(String lastSerializationDate) {
        this.lastSerializationDateTime = lastSerializationDate;
    }



}
