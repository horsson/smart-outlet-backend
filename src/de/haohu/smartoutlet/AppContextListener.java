package de.haohu.smartoutlet;

import de.haohu.smartoutlet.manager.DeviceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class AppContextListener implements ServletContextListener {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(AppContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOGGER.debug("App started.");
        DeviceManager.getInstance();

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.debug("App destoryed.");
        DeviceManager.getInstance().cleanup();
    }
}
