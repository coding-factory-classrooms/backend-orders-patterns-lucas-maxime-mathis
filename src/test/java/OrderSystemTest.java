import org.example.models.Order;
import org.example.models.OrderSystem;
import org.example.models.LogSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderSystemTest {

    private OrderSystem orderSystem;
    private LogSystem logSystem;

    @Before
    public void before() {
        orderSystem = new OrderSystem(logSystem);
    }

    @Test
    public void addCommandSuccess(){
        orderSystem.addCommand(new Order());
        Assert.assertEquals(1, orderSystem.getOrders().size());
    }

    @Test
    public void addCommandNotNewFail(){
        Order command = new Order();
        command.setState(Order.State.CANCELED);

        orderSystem.addCommand(command);
        Assert.assertEquals(0, orderSystem.getOrders().size());
    }
}
