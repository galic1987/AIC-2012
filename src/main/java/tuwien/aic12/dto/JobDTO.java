package tuwien.aic12.dto;

import java.io.Serializable;

/**
 *
 * @author galic1987
 */
public class JobDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String object;
    private String to;
    private String from;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
