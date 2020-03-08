package components.guide2;

import javax.ejb.Local;
import java.util.List;

@Local
public interface OrderRepository {

    void addOrder(List<String> order);

    List<List<String>> getOrders();

    int getOrderCount();
}
