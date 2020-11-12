package org.example;

import org.example.controllers.OrderController;
import org.example.controllers.DashboardController;
import org.example.core.Conf;
import org.example.core.Template;
import org.example.middlewares.LoggerMiddleware;
import org.example.models.*;
import spark.Spark;

public class App {
    public static void main(String[] args) {
        initialize();

        LogSystem log = new LogSystem();
        OrderSystem orderSystem = new OrderSystem(log);

        Order order = new Order();
        Lung lung = new Lung();
        order.addOrgan(lung);
        Foot foot = new Foot();
        order.addOrgan(foot);
        orderSystem.addOrder(order);

        DashboardController dashboardController = new DashboardController(orderSystem);
        OrderController orderController = new OrderController(orderSystem);

        Spark.get("/", dashboardController::detail);
        Spark.get("/orders/:id/info", orderController::infoOrder);

        Spark.get("/orders/:id", orderController::detail);
        Spark.post("/orders/:id", orderController::changeOrderState);
        Spark.get("/new_order", orderController::addOrder);
        Spark.post("/placeOrder", orderController::placeOrder);
    }

    static void initialize() {
        Template.initialize();

        // Display exceptions in logs
        Spark.exception(Exception.class, (e, req, res) -> e.printStackTrace());

        // Serve static files (img/css/js)
        Spark.staticFiles.externalLocation(Conf.STATIC_DIR.getPath());

        // Configure server port
        Spark.port(Conf.HTTP_PORT);
        final LoggerMiddleware log = new LoggerMiddleware();
        Spark.before(log::process);
    }
}
