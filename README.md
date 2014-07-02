Создание запускаемого jar файла со всеми необходимыми библиотеками
В репозитарии находится проект, демонстрирующий как создать запускаемые jar файлы со всеми необходимыми библиотеками внутри. Такой jar, крайне полезен в том случае если необходимо контролируемая среда выполнения (набор библиотек). 
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
Собираем проект мавеном: mvn package
В директории \target должны создастся два файла: SpringOneExecuteJar.jar, SpringOneExecuteJar.one-jar.jar 



 


"c:\Program Files\Java\jdk1.7.0_25\bin\java.exe" -jar SpringOneExecuteJar.one-jar.jar
