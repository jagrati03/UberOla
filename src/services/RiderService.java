package services;

import Repository.RiderStore;
import model.Rider;

import java.util.Map;

public class RiderService {
    RiderStore riderStore;

    public RiderService(RiderStore store) {
        this.riderStore = store;
    }

    public Rider registerRider(String id, String name) {
        Rider rider = new Rider(id, name);
        riderStore.addRider( rider);
        return rider;
    }
}
