import org.example.TimeMachine;
import org.example.models.Foot;
import org.example.models.LogSystem;
import org.example.models.Order;
import org.example.models.OrderSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.Time;
import java.util.List;

public class TimeMachineTest {

    private OrderSystem orderSystem;
    private LogSystem logSystem;
    private TimeMachine timeMachine;

    @Before
    public void before(){
        logSystem = new LogSystem();
        orderSystem = new OrderSystem(logSystem);
        timeMachine = new TimeMachine(orderSystem);
    }

    @Test
    public void CreateSnapshotSuccess(){
        timeMachine.makeBackup();
        Assert.assertEquals(1,timeMachine.getSnapshots().size());
        Assert.assertEquals(0,timeMachine.getIndex());
    }

    @Test
    public void UndoInRangeSuccess(){
        timeMachine.makeBackup();
        timeMachine.makeBackup();
        Assert.assertEquals(1,timeMachine.getIndex());
        Assert.assertTrue(timeMachine.undo());
        Assert.assertEquals(0,timeMachine.getIndex());
    }

    @Test
    public void UndoNotInRangeFail(){
        Order order = new Order();
        orderSystem.addOrder(order);
        Assert.assertFalse(timeMachine.undo());
    }

    @Test
    public void UndoSuccess(){

        timeMachine.makeBackup();

        Order order = new Order();
        order.addOrgan(new Foot());
        orderSystem.addOrder(order);


        Assert.assertTrue(timeMachine.undo());

        Assert.assertEquals(0, orderSystem.getOrders().size());
    }

    @Test
    public void RedoSuccess(){
        Order order = new Order();
        order.addOrgan(new Foot());
        orderSystem.addOrder(order);

        order.setState(Order.State.CANCELED);

        Assert.assertTrue(timeMachine.undo());
        Assert.assertEquals(2, timeMachine.getSnapshots().size());
        Assert.assertEquals(0, timeMachine.getIndex());
        Assert.assertTrue(timeMachine.redo());

        Assert.assertEquals(1, timeMachine.getIndex());

    }

    @Test
    public void RedoNotInRangeFail(){
        Order order = new Order();
        order.addOrgan(new Foot());
        orderSystem.addOrder(order);

        Assert.assertFalse(timeMachine.redo());
    }

    @Test
    public void UndoIfNoBackUpFail(){
        Assert.assertFalse(timeMachine.undo());
    }

    @Test
    public void UpdateCommandShouldTriggerListener() {
        Order.OnOrderStateChange onOrderStateChange = Mockito.mock(Order.OnOrderStateChange.class);

        Order order = new Order();
        orderSystem.addOrder(order);
        order.addOnOrderStateChangeListener(onOrderStateChange);
        order.setState(Order.State.FINISHED);

        Mockito.verify(onOrderStateChange).onOrderStateChange(order);
    }

    @Test
    public void UpdateCommandShouldUpdateHistory() {
        OrderSystem.OnSystemOrderChange onSystemOrderChange = Mockito.mock(OrderSystem.OnSystemOrderChange.class);
        orderSystem.addOnOnSystemOrderChangeListener(onSystemOrderChange);

        Order order = new Order();
        orderSystem.addOrder(order);
        order.setState(Order.State.FINISHED);

        Mockito.verify(onSystemOrderChange).onOrderChange(order);
    }

}
