package ks.dev.hotel_book.service.bookedRoom;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ks.dev.hotel_book.exception.InvalidBookingRequestException;
import ks.dev.hotel_book.exception.ResourceNotFoundException;
import ks.dev.hotel_book.model.BookedRoom;
import ks.dev.hotel_book.model.Room;
import ks.dev.hotel_book.repository.BookingRepository;
import ks.dev.hotel_book.service.room.IRoomService;

import java.util.List;



@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService {
    private final BookingRepository bookingRepository ;
    private final IRoomService roomService;
    @Override
    public void cancelBooking(Long bookingId) {
     bookingRepository.deleteById(bookingId);
    }
    @Override
    public List<BookedRoom> getAllBookingsByRoomId(Long roomId) {
     return bookingRepository.findByRoomId(roomId);
    }
    @Override
    public String saveBooking(Long roomId, BookedRoom bookingRequest) {
     if(bookingRequest.getCheckOutDate().isBefore(bookingRequest.getCheckInDate())){
        throw new InvalidBookingRequestException("Check-in date must come before check-out date");

     }
     Room room = roomService.getRoomById(roomId).get();
     List<BookedRoom> existingBookings = room.getBookings();
     boolean roomIsAvailable = roomIsAvailable(bookingRequest, existingBookings);
          if (roomIsAvailable) {
             room.addBooking(bookingRequest);
             bookingRepository.save(bookingRequest);
          }else{
             throw new InvalidBookingRequestException("sorry, this room is not available for the selected dates;");
     
          }
          return bookingRequest.getBookingConfirmationCode();
     }

    @Override
    public BookedRoom findByBookingConfirmationCode(String confirmationCode) {
     return bookingRepository.findByBookingConfirmationCode(confirmationCode)
     .orElseThrow(()-> new ResourceNotFoundException("no booking found with booking code: " + confirmationCode));
    }
    @Override
    public List<BookedRoom> getAllBookings() {
       return bookingRepository.findAll();
    }
    @Override
    public List<BookedRoom> getBookingsByUserEmail(String email) {
       return bookingRepository.findByGuestEmail(email);
    }

    private boolean roomIsAvailable(BookedRoom bookingRequest, List<BookedRoom> existingBookings) {
    return existingBookings.stream()
    .noneMatch(existingBooking ->
    bookingRequest.getCheckInDate().equals(existingBooking.getCheckInDate())
            || bookingRequest.getCheckOutDate().isBefore(existingBooking.getCheckOutDate())
            || (bookingRequest.getCheckInDate().isAfter(existingBooking.getCheckInDate())
            && bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckOutDate()))
            || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())

            && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckOutDate()))
            || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())

            && bookingRequest.getCheckOutDate().isAfter(existingBooking.getCheckOutDate()))

            || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
            && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckInDate()))

            || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
            && bookingRequest.getCheckOutDate().equals(bookingRequest.getCheckInDate()))
);
}
  }



   
   
   
   
   


  


