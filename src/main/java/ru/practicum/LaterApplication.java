package ru.practicum;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class LaterApplication {
    private static final int PORT = 8080;

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();

        // connector — это компонент, который отвечает за «сеть»
        tomcat.getConnector().setPort(PORT);

        // то самое «приложение» или «контекст» с пустым путём
        Context tomcatContext = tomcat.addContext("", null);

        // создаём контекст
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        // .setServletContext передаёт контексту приложения, как и следует из названия, контекст сервлетов — ServletContext.
        applicationContext.setServletContext(tomcatContext.getServletContext());
        // контекст будет искать бины в пакете ru.practicum
        applicationContext.scan("ru.practicum");
        // загружаем Spring-контекст.
        applicationContext.refresh();

        // добавляем диспетчер запросов
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
        Wrapper dispatcherWrapper = Tomcat.addServlet(tomcatContext, "dispatcher", dispatcherServlet);
        // метод addMapping("/") отвечает за то, что сервлет будет обрабатывать все пути, начиная с корневого;
        dispatcherWrapper.addMapping("/");
        // сервлет будет инициализирован при запуске контейнера, а не при первом запросе.
        dispatcherWrapper.setLoadOnStartup(1);
//
//        // класс Wrapper позволяет задать дополнительные настройки для сервлета
//        Wrapper testServletWrapper = Tomcat.addServlet(tomcatContext, "testServlet", new TestServlet());
//
//        // addMapping() сопоставляет URL-путь с сервлетом
//        testServletWrapper.addMapping("/test");

        tomcat.start();
    }
}
