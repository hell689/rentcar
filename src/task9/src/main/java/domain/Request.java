package domain;

import lombok.Data;

import java.util.Date;
@Data
public class Request extends Entity {

    private static final long serialVersionUID = 1L;

    private User user;
    private Mark mark;
    private Gearbox gearbox;
    private float volume;
    private Color color;
    private Date startDate;
    private Date endDate;
    private String comment;
    private boolean processed;
}
