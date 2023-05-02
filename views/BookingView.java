package views;

import models.Reservation;
import models.Table;
import presenters.View;
import presenters.ViewObserver;

import java.util.Collection;
import java.util.Date;

public class BookingView implements View {


    private ViewObserver observer;


    /**
     * Отобразить список столиков
     * @param tables список столиков
     */
    public void showTables(Collection<Table> tables){
        for (Table table: tables){
            System.out.println(table);
            for (Reservation reserv : table.getReservations()){
                System.out.printf("--- Бронь #: %d,  Гость: %s,  Дата: ", reserv.getId(), reserv.getName());
                System.out.println(reserv.getDate());

            }
        }
    }


    /**
     * Отобразить результат бронирования
     * @param reservationNo номер брони
     */
    @Override
    public void showReservationStatus(int reservationNo) {
        if (reservationNo > 0){
            System.out.printf("Столик успешно забронирован. Номер брони: #%d\n", reservationNo);
        }
        else {
            System.out.println("Ошибка бронирования столика.");
        }
    }

    public void showDeletereservationView(int oldReservationNo){
        if  (oldReservationNo > 0){
        System.out.printf("Бронирование #%d  отменено.\n", oldReservationNo);
        }
        else {
            System.out.println("Ошибка отмены бронирования.");

        }
    }

    /**
     * Установить наблюдателя
     * @param observer наблюдатель (презентер)
     */
    @Override
    public void setObserver(ViewObserver observer) {
        this.observer = observer;
    }

    /**
     * Действие клиента (пользователь нажал на кнопку бронирования),
     * бронирование столика
     * @param orderDate дата бронирования
     * @param tableNo номер столика
     * @param name имя клиента
     */
    public void reservationTable(Date orderDate, int tableNo, String name){
        observer.onReservationTable(orderDate, tableNo, name);
    }

    /**
     * Действие клиента (пользователь нажал на кнопку бронирования), бронирование столика
     * @param oldReservation идентификатор бронирования (старый)
     * @param reservationDate дата бронирования
     * @param tableNo номер столика
     * @param name Имя
     */
    public void changeReservationTable(int oldReservation, Date reservationDate, int tableNo, String name){
        observer.onChangeReservationTable(oldReservation, reservationDate, tableNo, name);

    }



}
