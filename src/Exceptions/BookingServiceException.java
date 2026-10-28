package Exceptions;

import enums.BookingServiceErrorCode;

public class BookingServiceException extends RuntimeException {
    private final BookingServiceErrorCode errorCode;
    public BookingServiceException(BookingServiceErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }


}
