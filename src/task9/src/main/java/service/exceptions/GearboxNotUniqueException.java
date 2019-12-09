package service.exceptions;

public class GearboxNotUniqueException extends ServiceException {
    private String gearbox;

    public GearboxNotUniqueException(String gearbox) {
        this.gearbox = gearbox;
    }

    public String getGearbox() {
        return gearbox;
    }
}
