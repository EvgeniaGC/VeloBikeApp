package com.example.VeloBikeApp.service.impl;


import com.example.VeloBikeApp.dto.RouteBean;
import com.example.VeloBikeApp.dto.RouteResponse;
import com.example.VeloBikeApp.model.Route;
import com.example.VeloBikeApp.model.User;
import com.example.VeloBikeApp.repository.RouteRepository;
import com.example.VeloBikeApp.repository.UserRepository;
import com.example.VeloBikeApp.service.RouteService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public RouteResponse addRoute(User user, RouteBean routeBean) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate localDate = LocalDate.parse(routeBean.getDate(), formatter);


        Route routeToSave = new Route();
        routeToSave.setStartPoint(routeBean.getStartPoint());
        routeToSave.setEndPoint(routeBean.getEndPoint());
        routeToSave.setUserId(user.getId());
        routeToSave.setDate(localDate);

        //TODO:
        // Double distance = readHtml(route);
        Double distance = roundDoubleBy2(Math.random() * 100);
        routeToSave.setDistance(distance);


        //User userFromDB = userRepository.findUserByEmail(user.getEmail());
        User userFromDB = userRepository.findUserById(user.getId());
        routeToSave.setKcal(roundDoubleBy2(45.0 * userFromDB.getWeight() / 10 * distance / 10));
        routeToSave.setSavedFuel(roundDoubleBy2(distance / 100 * 7));

        Route savedRoute = routeRepository.save(routeToSave);

        RouteResponse response = new RouteResponse();
        response.setMessage("Route successfully added");
        response.setRoute(routeToSave);

        user.getListOfRout().add(savedRoute);
        userRepository.save(user);

        return response;
    }

    @Override
    public Route getRouteById(Integer id) {
        return routeRepository.findRouteByIdRoute(id);
    }

    public Double readHtml(Route route) {
        Double distance = 0.0;

        String startPoint = route.getStartPoint();
        String endPoint = route.getEndPoint();
        String urlPart = "https://www.google.by/maps/dir/";
        String url = urlPart + startPoint + "/" + endPoint; //<div jstcache="200">12,6&nbsp;км</div>

        url = "https://www.google.by/maps/dir/ул.+Филимонова+10,+Минск/пр.+Независимости+56,+Минск+220089/@53.9155934,27.555518,12.26z/data=!4m13!4m12!1m5!1m1!1s0x46dbce588f2745af:0xb842fe63e6722c40!2m2!1d27.6335594!2d53.9097202!1m5!1m1!1s0x46dbcfa6a749853d:0x893354e32f2683a8!2m2!1d27.5873915!2d53.9171199";
        //url = "https://stackoverflow.com/questions/21713280/find-div-element-by-multiple-class-names";
        Document doc = null;
//        try {
////            doc = Jsoup.connect(url).get();
//            String html = Jsoup.connect(url).get().html();
//            doc =Jsoup.parseBodyFragment(html);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        try {
            doc = Jsoup.parse(new URL(url), 3000);
        } catch (Exception ignored) {
            System.out.println("ERROR___");
        }

        Elements elementsByClass = doc.getElementsByClass("ita-kd-inputtools-div ita-kd-statusbar");
        Element first1 = elementsByClass.first();

        // FROM STAS
//        public static Document getPage(String ticker) {
//            String url = "https://finviz.com/quote.ashx?t=" + ticker;
//            Document page = null;
//            try {
//                page = Jsoup.parse(new URL(url), 3000);
//                return page;
//            } catch (Exception ignored) {
//                log.error("FINVIZ ERROR");
//                return null;
//            }
//        }
//
//        public CompanyDescription getCompanyDescription(String ticker) {
//            List<String> list = new ArrayList<>();
//
//            Document page = getPage(ticker);
//            if (page == null) {
//                return new CompanyDescription(0.0, 0.0, 0.0, 0.0);
//            }
//            Elements pageElement = page.select("table[class=snapshot-table2]")
//                    .select("tr[class=table-dark-row]").select("td[class=snapshot-td2]");
//            for (Element element : pageElement) {
//
//                if (element.text().equals("-")) {
//                    list.add("0.0");
//                } else {
//                    list.add(element.text());
//                }
//            }
//            if (list.size() == 0) {
//                return new CompanyDescription(0.0, 0.0, 0.0, 0.0);
//            }
//            return new CompanyDescription(
//                    Double.parseDouble(list.get(3).replace("%", "")),
//                    Double.parseDouble(list.get(15).replace("%", "")),
//                    Double.parseDouble(list.get(28)),
//                    Double.parseDouble(list.get(66))
//            );
//        }


        Elements body = doc.select("body");
        Element first = body.first();
        Elements div = first.select("div");
        String html = div.first().html();


//        Document doc = Jsoup.parse(html);
//        String textFromElement = doc.body().text();
//        String textDistance = textFromElement.substring(0, textFromElement.length() - 3).replace(",", ".");

//        Element elementWithDistance = doc.select("div jstcache=\"200\"").first();


        //       String textFromElement = elementWithDistance.html();
        //       String textDistance = textFromElement.substring(0, textFromElement.length() - 3).replace(",", ".");
//        distance = Double.parseDouble(textDistance);

        return distance;
    }

    double roundDoubleBy2(Double number) {
        number *= 100;
        Double num = Double.valueOf(Math.round(number));
        return num / 100;
    }

}
