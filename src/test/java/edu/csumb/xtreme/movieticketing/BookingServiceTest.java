package edu.csumb.xtreme.movieticketing;

import edu.csumb.xtreme.movieticketing.dao.BookingDao;
import edu.csumb.xtreme.movieticketing.dao.BookingService;
import edu.csumb.xtreme.movieticketing.dao.ShowtimeDao;
import edu.csumb.xtreme.movieticketing.dao.ViewerDao;
import edu.csumb.xtreme.movieticketing.dao.ViewerService;
import edu.csumb.xtreme.movieticketing.entities.BookingEntity;
import edu.csumb.xtreme.movieticketing.entities.SeatEntity;
import edu.csumb.xtreme.movieticketing.entities.SeatEntity.SeatEntityBuilder;
import edu.csumb.xtreme.movieticketing.entities.SeatsEntity;
import edu.csumb.xtreme.movieticketing.entities.SeatsEntity.SeatsEntityBuilder;
import edu.csumb.xtreme.movieticketing.entities.ShowtimeEntity;
import edu.csumb.xtreme.movieticketing.entities.ViewerEntity;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceTest {

    @Autowired
    ViewerDao viewerDao;
    @Autowired
    ShowtimeDao showtimeDao;
    @Autowired
    BookingDao bookingDao;

    @Autowired
    BookingService bookingService;
    private final ViewerService viewerService = new ViewerService();

    @Test
    public void createBooking() {
        long before = bookingDao.count();
        ViewerEntity viewer = viewerService.createViewer("TestViewer");
        ShowtimeEntity showtimeEntity = showtimeDao.getOne(1);
        List<SeatsEntity> seatsEntity = new ArrayList<>();
        bookingService.createBooking(viewer, showtimeEntity, seatsEntity);
        Assert.assertEquals(before + 1L, bookingDao.count());
    }

    @Test
    public void createBookingWithSeat() {
        long before = bookingDao.count();
        ViewerEntity viewer = viewerService.createViewer("TestViewer");
        viewerDao.save(viewer);
        ShowtimeEntity showtimeEntity = showtimeDao.getOne(1);
        List<SeatsEntity> seatsEntityList = new ArrayList<>();

        SeatEntity seatEntity = SeatEntityBuilder.aSeatEntity().withNumber(1).withRow("A").build();
        SeatsEntity seatsEntity = SeatsEntityBuilder.aSeatsEntity().withSeat(seatEntity).build();
        seatsEntityList.add(seatsEntity);
        bookingService.createBooking(viewer, showtimeEntity, seatsEntityList);
        Assert.assertEquals(before + 1L, bookingDao.count());
    }

    @Test
    public void deleteBooking() {
        long before = bookingDao.count();
        ViewerEntity viewer = viewerService.createViewer("TestViewer");
        viewerDao.save(viewer);
        ShowtimeEntity showtimeEntity = showtimeDao.findById(1).get();
        List<SeatsEntity> seatsEntity = new ArrayList<>();
        BookingEntity booking = bookingService.createBooking(viewer, showtimeEntity, seatsEntity);
        Assert.assertEquals(before + 1L, bookingDao.count());
        bookingService.deleteBooking(booking.getId());
        Assert.assertEquals(before, bookingDao.count());

    }
}
