package domain;

import lombok.Data;

import java.util.Date;
@Data
public class Rent extends Entity {

    private static final long serialVersionUID = 1L;

    private Request request;
    private Car car;
    private Date startDate;
    private Date endDate;
}
