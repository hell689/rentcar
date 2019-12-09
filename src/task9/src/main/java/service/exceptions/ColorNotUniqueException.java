package service.exceptions;

public class ColorNotUniqueException extends ServiceException {
    private String color;

    public ColorNotUniqueException(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
