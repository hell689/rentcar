package domain;

import lombok.Data;

@Data
public class Car extends Entity {

    private static final long serialVersionUID = 1L;

    private Mark mark;
    private Gearbox gearbox;
    private float volume;
    private Color color;

}
