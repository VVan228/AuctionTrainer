package ru.isu.auc.scheduling.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import ru.isu.auc.scheduling.AutoWiringSpringBeanJobFactory;

import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableAutoConfiguration
public class QrtzConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${org.quartz.dataSource.quartzDataSource.URL}")
    String quartzDataSourceURL;

    @Value("${org.quartz.dataSource.quartzDataSource.user}")
    String quartzDataSourceUser;

    @Value("${org.quartz.dataSource.quartzDataSource.password}")
    String quartzDataSourcePassword;

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(springBeanJobFactory());
        factory.setQuartzProperties(quartzProperties());
        return factory;
    }

    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));

        //настройки подгружаются из параметров приложения, чтобы их можно было переписывать параметром.
        Properties props = new Properties();
        props.put("org.quartz.dataSource.quartzDataSource.URL", quartzDataSourceURL);
        props.put("org.quartz.dataSource.quartzDataSource.user", quartzDataSourceUser);
        props.put("org.quartz.dataSource.quartzDataSource.password", quartzDataSourcePassword);
        propertiesFactoryBean.setProperties(props);

        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

}
