    package de.haohu.smartoutlet.servlet;

    import com.google.gson.Gson;
    import de.haohu.smartoutlet.devices.CommandException;
    import de.haohu.smartoutlet.devices.Device;
    import de.haohu.smartoutlet.manager.DeviceManager;
    import de.haohu.smartoutlet.model.Command;
    import de.haohu.smartoutlet.model.ResponseJsonData;

    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.io.InputStreamReader;

    /**
     * Created by d058856 on 29/03/16.
     */
    @WebServlet(name = "/api/command")
    public class SendCommandServlet extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            InputStreamReader isr = new InputStreamReader(request.getInputStream());
            Gson gson = new Gson();
            Command command = gson.fromJson(isr, Command.class);
            String deviceId =command.getDeviceId();
            Device device = DeviceManager.getInstance().getDevice(deviceId);
            try {
                device.sendCommand(command);
                ResponseJsonData respData = new ResponseJsonData.Builder().
                        message("Ok").
                        statusCode(200).
                        build();
                String json = respData.toJson();
                response.setStatus(200);
                response.setContentType("application/json");
                response.setContentLength(json.length());
                response.getWriter().println(json);

            } catch (CommandException e) {
                response.sendError(500,"Error to send command");
            }


        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.sendError(405, "Not");
        }
    }
