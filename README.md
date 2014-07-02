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



 


"c:\Program Files\Java\jdk1.7.0_25\bin\java.exe" -jar SpringOneExecuteJar.one-jar.jar
