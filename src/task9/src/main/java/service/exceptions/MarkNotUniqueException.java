package service.exceptions;

public class MarkNotUniqueException extends ServiceException {
    private String mark;

    public MarkNotUniqueException(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }
}
