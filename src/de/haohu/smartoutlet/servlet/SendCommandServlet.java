    package de.haohu.smartoutlet.servlet;

    import com.google.gson.Gson;
    import de.haohu.smartoutlet.devices.CommandException;
    import de.haohu.smartoutlet.devices.Device;
    import de.haohu.smartoutlet.manager.DeviceManager;
    import de.haohu.smartoutlet.model.Command;
    import de.haohu.smartoutlet.model.ResponseJsonData;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;

    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.io.InputStreamReader;

    /**
     * A servlet
     */
    @WebServlet("/api/command")
    public class SendCommandServlet extends HttpServlet {

        private static final Logger LOGGER = LoggerFactory.getLogger(SendCommandServlet.class);

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            InputStreamReader isr = new InputStreamReader(request.getInputStream());
            Gson gson = new Gson();
            Command command = gson.fromJson(isr, Command.class);
            if (command == null){
                response.sendError(400, "Bad Request");
                return;
            }
            String deviceId =command.getDeviceId();
            Device device = DeviceManager.getInstance().getDevice(deviceId);
            if (device == null){
                LOGGER.debug("{} cannot be found.", deviceId);
                String errMsg = String.format("Device ID %s not found.", deviceId);
                ResponseJsonData respData = new ResponseJsonData.Builder().
                        message(errMsg).
                        statusCode(400).
                        build();
                String json = respData.toJson();
                response.setStatus(400);
                response.setContentType("application/json");
                response.setContentLength(json.length());
                response.getWriter().println(json);
                return;
            }

            try {
                LOGGER.debug("Sending out command.");
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
                ResponseJsonData respData = new ResponseJsonData.Builder().
                        message(e.getMessage()).
                        statusCode(500).
                        build();
                String json = respData.toJson();
                response.setStatus(500);
                response.setContentType("application/json");
                response.setContentLength(json.length());
                response.getWriter().println(json);
            }

        }


        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.sendError(501, "not implemented");
        }


    }
