//package group5.SE1863.DPSS_backend.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//import java.util.Properties;
//
//@Configuration
//public class MailConfiguration {
//
//    @Autowired
//    private Environment environment;
//
//    @Bean
//    public JavaMailSender getMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost(environment.getProperty("spring.mail.host"));
//        mailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
//        mailSender.setUsername(environment.getProperty("spring.mail.username"));
//        mailSender.setPassword(environment.getProperty("spring.mail.password"));
//
//        Properties javaMailProperties = new Properties();
//        javaMailProperties.put("mail.smtp.starttls.enable", "true");
//        javaMailProperties.put("mail.smtp.auth", "true");
//        javaMailProperties.put("mail.transport.protocol", "smtp");
//        javaMailProperties.put("mail.debug", "true");
//        javaMailProperties.put("mail.smtp.ssl.trust", "*");
//
//        mailSender.setJavaMailProperties(javaMailProperties);
//        return mailSender;
//    }
//}
