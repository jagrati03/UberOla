package Repository;

import Exceptions.BookingServiceException;
import enums.BookingServiceErrorCode;
import model.Rider;

import java.util.HashMap;
import java.util.Map;

public class MockRiderStore implements RiderStore {
    private final Map<String, Rider> riders = new HashMap<>();


    @Override
    public Rider getRider(String id) {
        return riders.get(id);
    }

    @Override
    public Rider updateRider(String id, Rider rider) {
        if(!riders.containsKey(id)){
            throw new BookingServiceException(BookingServiceErrorCode.RIDER_NOT_FOUND, "RIDER DOES NOT EXIST");
        }
        return null;
    }

    @Override
    public Rider addRider(Rider rider) {
        return false;
    }

    @Override
    public boolean deleteRider(Rider rider) {

    }
}
