package com.radosti.app.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.radosti.app.config.AppConfig;
import com.radosti.app.domain.Apartment;
import com.radosti.app.domain.Client;
import com.radosti.app.service.ApartmentService;
import com.radosti.app.service.ClientService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class HotelServlet extends HttpServlet{
    private final ObjectMapper mapper = new ObjectMapper();
    AppConfig appConfig = new AppConfig();
    private ClientService clientService;
    private ApartmentService apartmentService;
    @Override
    public void init() throws ServletException {
        super.init();

        //App environment building and dependency injecting, like main() in CLII app
        clientService = new ClientService();
        apartmentService = new ApartmentService(clientService, appConfig);
        System.out.println("Servlet init processed");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getPathInfo();                  //Get a request URL, if you need to route request

        if ("/clientFind".equalsIgnoreCase(path)){
            handleClientFind(req, resp);
            return;
        }
        if ("/apartmentFind".equalsIgnoreCase(path)){
            handleApartmentFind(req, resp);
            return;
        }

        if ("/printListOfApartments".equalsIgnoreCase(path)){
            handlePrintApartmentList(req, resp);
            return;
        }
        resp.sendError(404, "Unknown command");    //Set response body with serialization to JSON
    }

    private  void handleApartmentFind(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        Apartment a = apartmentService.findById(id);

        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(a));
    }

        private void handleClientFind(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            String passportID = req.getParameter("passportID");

            Client a = clientService.findById(passportID);

            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(a));
    }

        private void handlePrintApartmentList(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            int page = Integer.parseInt(req.getParameter("page"));
            int size = Integer.parseInt(req.getParameter("size"));
            String sortBy = req.getParameter("sortBy");

            List<Apartment> list = apartmentService.listApartments(page, size, sortBy);
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(list));
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String path = req.getPathInfo();

        if ("/apartmentRegister".equalsIgnoreCase(path)){
            handleRegisterApartment(req, resp);
            return;
        }
        if ("/apartmentRelease".equalsIgnoreCase(path)){
            handleReleaseApartment(req, resp);
            return;
        }
        if ("/apartmentReserve".equalsIgnoreCase(path)) {
            handleReserveApartment(req, resp);
            return;
        }
        if ("/clientRegister".equalsIgnoreCase(path)) {
            handleClientRegister(req, resp);
            return;
        }

        resp.sendError(404, "Unknown command");

    }
    private void handleRegisterApartment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int price = Integer.parseInt(req.getParameter("price"));

        apartmentService.registerApartment(id, price);

        resp.getWriter().write("OK");
    }
    private void handleReleaseApartment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        apartmentService.releaseApartment(id);

        resp.getWriter().write("OK");
    }
    private void handleReserveApartment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String passportID = req.getParameter("passportID");

        apartmentService.reserveApartment(id, passportID);

        resp.getWriter().write("OK");
    }
    private void handleClientRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String passportID = req.getParameter("passportID");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        clientService.registerClient(passportID, name, surname);

        resp.getWriter().write("OK");
    }

}
