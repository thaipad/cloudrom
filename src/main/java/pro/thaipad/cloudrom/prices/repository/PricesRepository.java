package pro.thaipad.cloudrom.prices.repository;

import pro.thaipad.cloudrom.prices.entity.PricesPlan;

import java.time.LocalDate;
import java.util.List;

public interface PricesRepository {

    void create(PricesPlan plan);

    void update(PricesPlan plan);

    boolean setStartDate(int id, LocalDate date);

    boolean publish(int id);

    boolean deactive(int id, LocalDate date);

    PricesPlan get(int id);

    List<PricesPlan> getAll();

}
