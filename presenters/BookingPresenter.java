package presenters;

import models.Table;

import java.util.Collection;
import java.util.Date;

public class BookingPresenter implements ViewObserver {

    private final Model model;
    private final View view;
    private Collection<Table> tables;

    public BookingPresenter(Model model, View view){
        this.model = model;
        this.view = view;
        this.view.setObserver(this);
    }

    /**
     * Загрузить список всех столиков
     */
    public void loadTables(){
        if (tables == null){
            tables = model.loadTables();
        }
    }

    /**
     * Отобразить список столиков
     */
    public void updateView(){
        view.showTables(tables);
    }

    /**
     * Отобразить результат бронирования столика
     * @param reservationNo номер брони
     */
    private void updateReservationStatusView(int reservationNo){
        view.showReservationStatus(reservationNo);
    }
    /**
     * Отобразить отмену брони
     * @param oldReservationNo
     */
    private void deletereservationView(int oldReservationNo){
        view.showDeletereservationView(oldReservationNo);
    }

    /**
     * Получили уведомление о попытке бронирования столика
     * @param orderDate дата бронирования
     * @param tableNo номер столика
     * @param name имя клиента
     */
    @Override
    public void onReservationTable(Date orderDate, int tableNo, String name) {
        int reservationNo = model.reservationTable(orderDate, tableNo, name);
        updateReservationStatusView(reservationNo);
    }
    @Override
    public void onChangeReservationTable(int oldReservation, Date reservationDate, int tableNo, String name) {
        int delReservation = model.deleteReservationTable(oldReservation);
        int reservationNo = model.reservationTable(reservationDate, tableNo, name);

        deletereservationView(delReservation);
        updateReservationStatusView(reservationNo);

    }
    @Override
    public void onDeleteReservationTable(int oldReservation) {
        int delReservation = model.deleteReservationTable(oldReservation);
        deletereservationView(delReservation);
    }
}
