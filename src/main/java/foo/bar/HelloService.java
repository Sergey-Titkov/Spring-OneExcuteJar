package foo.bar;

import org.springframework.stereotype.Component;

@Component
public class HelloService {
    public String sayHello() {
        return "Эта строка получена из бина. Проверяем корректность работы автосвязывания спринга.";
    }
}
