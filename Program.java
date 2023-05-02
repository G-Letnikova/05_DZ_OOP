import models.TableModel;
import presenters.BookingPresenter;
import views.BookingView;

import java.util.Date;

public class Program {

    public static void main(String[] args) {
        TableModel tableModel = new TableModel();
        BookingView bookingView = new BookingView();
        BookingPresenter bookingPresenter = new BookingPresenter(tableModel, bookingView);
        bookingPresenter.loadTables();
        bookingPresenter.updateView();

        bookingView.reservationTable(new Date(), 3, "Станислав");

        bookingView.changeReservationTable(1001, new Date(), 2, "Станислав");

        bookingPresenter.updateView();

    }

}
