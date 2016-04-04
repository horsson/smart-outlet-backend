package de.haohu.smartoutlet.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.haohu.smartoutlet.devices.Device;
import de.haohu.smartoutlet.manager.DeviceManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/devices")

public class ListDeviceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {

        List<Device> devices = DeviceManager.getInstance().getDevices();
        Gson gson = new GsonBuilder().setDateFormat("MM-dd-yyyy, HH:mm:ss").create();
        String jsonString = gson.toJson(devices);
        response.setContentType("application/json");
        response.setContentLength(jsonString.length());
        response.getWriter().println(jsonString);
        response.getWriter().flush();
    }
}
