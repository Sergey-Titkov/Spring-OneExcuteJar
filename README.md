Создание запускаемого jar файла со всеми необходимыми библиотеками
В этом репозитарии находится проект, демонстрирующий как создать исполняемый jar файл со всеми библиотеками внутри. Такой подход очень удобен. Достаточно, что бы на целевой машине была только java, все необходимые библиотеки есть внутри jar, и нет необходимости ничего качать и конфигурировать.
Для решения такой задачи используется мавен артефакт: onejar
Для подключения его к проекту необходимо добавить следующее описание в секцию: plugins, build.
<!-- Позволяет утрамбовать все библиотеки в один jar -->
      <plugin>
        <groupId>org.dstovall</groupId>
        <artifactId>onejar-maven-plugin</artifactId>
        <version>1.4.4</version>
        <executions>
          <execution>
            <goals>
              <goal>one-jar</goal>
            </goals>
          </execution>
        </executions>
      </plugin>

Так же необходимо подключить дополнительный maven репозиторий:
    <pluginRepository>
      <id>onejar-maven-plugin.googlecode.com</id>
      <url>http://onejar-maven-plugin.googlecode.com/svn/mavenrepo</url>
    </pluginRepository>

Важно! Для получения исполняемого jar необходимо подключить maven-jar-plugin артефакт.
Важно! Для нормальной работы примера необходимо подключение к Ораклу. Для этого в папке откуда производится запуск программы, в случае с запуском jar это папка target, необходимо создать файл connection.properties со следующим содержанием:
oracle.url=jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=<хост БД>)(PORT=<порт>)))(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=<имя базы>)))
oracle.user=<логин>
oracle.password=<пароль>

Да, да его нет в репозитарии!
Собираем проект мавеном: mvn -DconsoleEncoding=<Кодировка консоли. Для windows 866, для nix обычно UTF8> package
В директории \target должны создастся два файла: SpringOneExecuteJar.jar, SpringOneExecuteJar.one-jar.jar 
Нам нужен второй. Проверим работоспособность. 
java.exe -DconsoleEncoding=<Кодировка консоли> -jar SpringOneExecuteJar.one-jar.jar
Получаем в консоли:
JarClassLoader: Warning: org/apache/commons/logging/impl/NoOpLog.class in lib/jcl-over-slf4j-1.6.6.jar is hidden by lib/commons-logging-1.1.1.jar (with different bytecode)
JarClassLoader: Warning: org/apache/commons/logging/impl/SimpleLog$1.class in lib/jcl-over-slf4j-1.6.6.jar is hidden by lib/commons-logging-1.1.1.jar (with different bytecode)
JarClassLoader: Warning: org/apache/commons/logging/impl/SimpleLog.class in lib/jcl-over-slf4j-1.6.6.jar is hidden by lib/commons-logging-1.1.1.jar (with different bytecode)
JarClassLoader: Warning: org/apache/commons/logging/Log.class in lib/jcl-over-slf4j-1.6.6.jar is hidden by lib/commons-logging-1.1.1.jar (with different bytecode)
JarClassLoader: Warning: org/apache/commons/logging/LogConfigurationException.class in lib/jcl-over-slf4j-1.6.6.jar is hidden by lib/commons-logging-1.1.1.jar (with different bytecode)
JarClassLoader: Warning: org/apache/commons/logging/LogFactory.class in lib/jcl-over-slf4j-1.6.6.jar is hidden by lib/commons-logging-1.1.1.jar (with different bytecode)
Прямой вывод!
17:03:10.170 [main] INFO  foo.bar.HelloApp - Выводим через логер.
17:03:10.224 [main] INFO  o.s.c.s.ClassPathXmlApplicationContext - Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@6335d858: startup date [Wed Jul 02 17:03:10 MSK 2014]; root of context hierarchy
17:03:10.262 [main] INFO  o.s.b.f.xml.XmlBeanDefinitionReader - Loading XML bean definitions from class path resource [spring-config.xml]
17:03:10.904 [main] INFO  o.s.b.f.xml.XmlBeanDefinitionReader - Loading XML bean definitions from class path resource [myBatis-context.xml]
17:03:11.203 [main] INFO  o.s.c.s.PropertySourcesPlaceholderConfigurer - Loading properties file from URL [file:./connection.properties]
17:03:11.217 [main] INFO  o.s.b.f.s.DefaultListableBeanFactory - Pre-instantiating singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@16bf08c7: defining beans [org.springframework.context.annotation.internalConfigurationAnnotationPro
cessor,org.springframework.context.annotation.internalAutowiredAnnotationProcessor,org.springframework.context.annotation.internalRequiredAnnotationProcessor,org.springframework.context.annotation.internalCommonAnnotationProcessor,helloService,org.springframework.c
ontext.support.PropertySourcesPlaceholderConfigurer#0,oracleThinDataSource,oracleSessionFactory,org.mybatis.spring.mapper.MapperScannerConfigurer#0,org.springframework.context.annotation.ConfigurationClassPostProcessor.importAwareProcessor,helloMaper]; root of fact
ory hierarchy
Эта строка получена из бина. Проверяем корректность работы автосвязывания спринга.
Получаем данные из Оракла
NLS_LANGUAGE = RUSSIAN
NLS_TERRITORY = CIS
NLS_CURRENCY = р.
NLS_ISO_CURRENCY = CIS
NLS_NUMERIC_CHARACTERS = ,
NLS_CALENDAR = GREGORIAN
NLS_DATE_FORMAT = DD.MM.RR
NLS_DATE_LANGUAGE = RUSSIAN
NLS_SORT = RUSSIAN
NLS_TIME_FORMAT = HH24:MI:SSXFF
NLS_TIMESTAMP_FORMAT = DD.MM.RR HH24:MI:SSXFF
NLS_TIME_TZ_FORMAT = HH24:MI:SSXFF TZR
NLS_TIMESTAMP_TZ_FORMAT = DD.MM.RR HH24:MI:SSXFF TZR
NLS_DUAL_CURRENCY = р.
NLS_COMP = BINARY
NLS_LENGTH_SEMANTICS = BYTE
