package Model.Client;

import java.time.LocalDateTime;

public interface IDevice extends Comparable {
    int getRegCode();
    String getNifOwner();
    String getDescription();
    LocalDateTime getdate();
    IDevice clone();
}
