package Model.Devices;

import java.time.LocalDateTime;

public interface IDevice {
    int getRegCode();
    String getNifOwner();
    String getDescription();
    IDevice clone();
}
