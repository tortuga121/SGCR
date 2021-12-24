package Model;

import java.util.HashSet;

public interface IClient {
    String getMailAddress();
    String getNif();
    HashSet<Integer> getDeviceCodes();
}
