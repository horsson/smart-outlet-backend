package de.haohu.smartoutlet.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.haohu.smartoutlet.annotations.CleanResource;
import de.haohu.smartoutlet.devices.Device;
import de.haohu.smartoutlet.manager.DeviceManager;
import de.haohu.smartoutlet.model.ResponseJsonData;

import org.reflections.Reflections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet("/api/devices")

public class ListDeviceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {

        List<Device> devices = DeviceManager.getInstance().getDevices();
        Gson gson = new GsonBuilder().setDateFormat("MM-dd-yyyy, HH:mm:ss").create();
        String jsonString = gson.toJson(devices);
        response.setContentType("application/json");
        response.setContentLength(jsonString.length());
        response.getWriter().println(jsonString);
        response.getWriter().flush();


//        Reflections reflections = new Reflections("de.haohu.smartoutlet");
//        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(CleanResource.class);
//
//        for (Class clazz: annotated){
//            response.getWriter().println(clazz.getCanonicalName());
//        }
    }
}
