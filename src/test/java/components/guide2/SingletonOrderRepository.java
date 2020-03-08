package components.guide2;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Singleton
@Lock(LockType.READ)
public class SingletonOrderRepository implements OrderRepository {

    private List<List<String>> orders;

    @Lock(LockType.WRITE)
    public void addOrder(List<String> order) {
        orders.add(order);
    }

    public List<List<String>> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    public int getOrderCount() {
        return orders.size();
    }

    @PostConstruct
    void initialize() {
        orders = new ArrayList<List<String>>();
    }
}
