package ks.dev.hotel_book.service.bookedRoom;

import java.util.List;

import ks.dev.hotel_book.model.BookedRoom;

public interface IBookingService {

     void cancelBooking(Long bookingId);

    List<BookedRoom> getAllBookingsByRoomId(Long roomId);

    String saveBooking(Long roomId, BookedRoom bookingRequest);

    BookedRoom findByBookingConfirmationCode(String confirmationCode);

    List<BookedRoom> getAllBookings();

    List<BookedRoom> getBookingsByUserEmail(String email);
}
