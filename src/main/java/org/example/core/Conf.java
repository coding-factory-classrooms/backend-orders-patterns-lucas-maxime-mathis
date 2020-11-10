package org.example.core;

import java.io.File;

/**
 * This class centralizes all the configuration of the app (port, database, resources, etc)
 */
public class Conf {
    // General
    public static final boolean DEBUG_MODE = true;
    public static final int HTTP_PORT = 8080;

    // Resources
    public static final File RESOURCES_DIR = new File("src/main/resources");
    public static final File TEMPLATE_DIR = new File(RESOURCES_DIR, "templates");
    public static final File STATIC_DIR = new File(RESOURCES_DIR, "static");
}
