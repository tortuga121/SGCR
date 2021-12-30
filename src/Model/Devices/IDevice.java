package Model.Devices;

import java.time.LocalDateTime;

public interface IDevice {
    int getRegCode();
    String getNifOwner();
    String getDescription();
    String getName();
    IDevice clone();
}
