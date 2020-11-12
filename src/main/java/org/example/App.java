package org.example;

import org.example.controllers.CommandController;
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
        CommandSystem commandSystem = new CommandSystem(log);

        Command command = new Command();
        Lung lung = new Lung();
        command.addOrgan(lung);
        Foot foot = new Foot();
        command.addOrgan(foot);
        commandSystem.addCommand(command);

        DashboardController dashboardController = new DashboardController(commandSystem);
        CommandController commandController = new CommandController(commandSystem);

        Spark.get("/", dashboardController::detail);
        Spark.get("/commands/:id/info", commandController::infoCommand);

        Spark.get("/commands/:id", commandController::detail);
        Spark.post("/commands/:id", commandController::changeCommandState);
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
