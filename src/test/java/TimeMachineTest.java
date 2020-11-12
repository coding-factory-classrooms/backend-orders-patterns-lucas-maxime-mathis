import org.example.TimeMachine;
import org.example.models.Foot;
import org.example.models.LogSystem;
import org.example.models.Order;
import org.example.models.OrderSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        Assert.assertNotNull(timeMachine.getSnapshots().get(timeMachine.getIndex()));
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
        timeMachine.makeBackup();
        Assert.assertEquals(0,timeMachine.getIndex());
        Assert.assertFalse(timeMachine.undo());
    }

    @Test
    public void UndoSuccess(){
        timeMachine.makeBackup();

        Order order = new Order();
        order.addOrgan(new Foot());
        orderSystem.addOrder(order);

        timeMachine.makeBackup();
        Assert.assertTrue(timeMachine.undo());

        Assert.assertEquals(0, orderSystem.getOrders().size());
    }

}
