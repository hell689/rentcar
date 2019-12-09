package domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
}
