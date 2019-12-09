package support;

public interface ApplicationContext {

    Object getBean(String name);

    <T> T getBean(String name, Class<T> clazz);

    <T> T getBean(Class<T> clazz);
}
