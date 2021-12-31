package Model.Devices;


public interface IDevice {
    int getRegCode();
    String getNifOwner();
    String getDescription();
    String getName();
    IDevice clone();
}
