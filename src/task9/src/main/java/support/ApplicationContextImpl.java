package support;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class ApplicationContextImpl implements ApplicationContext {

    private static final String CONTEXT_PROPERTIES_PATH = "context.properties";
    private static ApplicationContextImpl instance;
    private Map<Class, Object> beans = new HashMap<>();
    private Properties props;

    private ApplicationContextImpl() {
        props = new Properties();
        try (InputStream is = ApplicationContextImpl.class.getClassLoader().getResourceAsStream(CONTEXT_PROPERTIES_PATH)) {
            props.load(is);
            for (String name : props.stringPropertyNames()) {
                Class clazz = Class.forName(props.get(name).toString());
                Object obj = clazz.newInstance();
                beans.put(clazz, obj);
                for (Class interfaze : clazz.getInterfaces()) {
                    beans.put(interfaze, obj);
                }
            }

            for (Map.Entry<Class, Object> bean : beans.entrySet()) {
                inject(bean.getKey(), bean.getValue());
            }
        } catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ApplicationContextImpl getInstance() {
        return Objects.isNull(instance) ? (instance = new ApplicationContextImpl()) : instance;
    }

    private void inject(final Class clazz, final Object obj) throws IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Object instance = beans.get(field.getType());
            if (Objects.nonNull(instance)) {
                boolean accessible = field.isAccessible();
                field.setAccessible(true);
                field.set(obj, instance);
                field.setAccessible(accessible);
            }
        }
        if (Objects.nonNull(clazz.getSuperclass())) {
            inject(clazz.getSuperclass(), obj);
        }
    }

    @Override
    public Object getBean(final String name) {
        Class clazz = null;
        try {
            clazz = Class.forName(props.getProperty(name));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return beans.get(clazz);
    }

    @Override
    public <T> T getBean(final String name, final Class<T> clazz) {
        return clazz.cast(getBean(name));
    }

    @Override
    public <T> T getBean(final Class<T> clazz) {
        return (T) beans.get(clazz);
    }
}
