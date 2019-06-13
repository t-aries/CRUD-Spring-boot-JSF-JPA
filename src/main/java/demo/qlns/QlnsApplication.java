package demo.qlns;

import demo.qlns.repository.EmpRepository;
import demo.qlns.repository.EmpRepositoryImpl;
import demo.qlns.services.EmpService;
import demo.qlns.services.EmpServiceImpl;
import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;
import java.util.EnumSet;

@EnableAutoConfiguration
@ComponentScan({"demo.qlns"})
public class QlnsApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(QlnsApplication.class, args);
    }
    @Bean
    public EmpRepository empRepository(){
        return new EmpRepositoryImpl();
    }

    @Bean
    public EmpService empService(){
        return new EmpServiceImpl(empRepository());
    }


    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        return new ServletRegistrationBean(servlet, "*.jsf");
    }

    @Bean
    public FilterRegistrationBean rewriteFilter() {
        FilterRegistrationBean rwFilter = new FilterRegistrationBean(new RewriteFilter());
        rwFilter.setDispatcherTypes(EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST,
                DispatcherType.ASYNC, DispatcherType.ERROR));
        rwFilter.addUrlPatterns("/*");
        return rwFilter;
    }
}
