package components.guide2;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class BasketTest {

    @Inject
    private Basket basket;

    @EJB
    private OrderRepository repo;

    @Test
    @InSequence(1)
    public void place_order_should_add_order() {

        basket.addItem("sunglasses");
        basket.addItem("suit");
        basket.placeOrder();
        assertEquals(1, repo.getOrderCount());
        assertEquals(0, basket.getItemCount());

        basket.addItem("raygun");
        basket.addItem("spaceship");
        basket.placeOrder();
        assertEquals(2, repo.getOrderCount());
        assertEquals(0, basket.getItemCount());
    }

    @Test
    @InSequence(2)
    public void order_should_be_persistent() {
        assertEquals(2, repo.getOrderCount());
    }


    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClasses(Basket.class, OrderRepository.class, SingletonOrderRepository.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
}