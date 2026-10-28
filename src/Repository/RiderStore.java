package Repository;

import model.Rider;

public interface RiderStore {
    //crud
    Rider getRider(String id);
    Rider updateRider(String id, Rider rider);
    Rider addRider(Rider rider);
    boolean deleteRider(Rider rider);
}
