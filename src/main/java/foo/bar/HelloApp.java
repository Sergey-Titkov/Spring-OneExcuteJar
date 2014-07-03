package foo.bar;

import foo.bar.mapper.HelloMaper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class HelloApp {
  private final static Logger logger = LoggerFactory.getLogger(HelloApp.class);

  public static void main(String[] args) {
    // Значение читается из системной переменной java, ее можно установить например добави ключ-DconsoleEncoding=<кодировка>
    // в параметры запуска java.
    // По умолчанию кодировка консоли у нас 866
    String consoleEncoding = System.getProperty("consoleEncoding");
    consoleEncoding = (consoleEncoding == null || consoleEncoding.equals("")) ? "CP866" : consoleEncoding;
    try {
      System.setOut(new PrintStream(System.out, true, consoleEncoding));
    } catch (java.io.UnsupportedEncodingException ex) {
      System.err.println("Unsupported encoding set for stdout: " + consoleEncoding);
      return;
    }
    try {
      System.setErr((new PrintStream(System.err, true, consoleEncoding)));
    } catch (UnsupportedEncodingException ex) {
      System.err.println("Unsupported encoding set for stderr: " + consoleEncoding);
      return;
    }



    //lc.
    //logger.a
      //log4j.appender.ConsoleAppender.encoding = Cp866
    // Проверям что все работает.
    System.out.println("Прямой вывод!");
    // Проверям, что библиотека логирования подключилась.
    logger.info("Выводим через логер.");

    // Проверяем, что контекст спринга работает.
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
    HelloService helloService = context.getBean(HelloService.class);
    System.out.println(helloService.sayHello());

    // Проверям работу с Ораклом.
    HelloMaper helloMaper = context.getBean(HelloMaper.class);
    List<String> result = new ArrayList<>(1);
    result = helloMaper.getSome();
    System.out.println("Получаем данные из Оракла");
    for (String item : result) {
      System.out.println(item);
    }
  }
}
