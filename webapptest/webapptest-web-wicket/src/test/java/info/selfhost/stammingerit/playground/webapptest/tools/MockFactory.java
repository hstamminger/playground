package info.selfhost.stammingerit.playground.webapptest.tools;

import org.springframework.beans.factory.FactoryBean;
import static org.mockito.Mockito.*;

public class MockFactory implements FactoryBean {

    // Typ des erstellten Objektes
    private Class type;

    public void setType(final Class type) {
        this.type = type;
    }

    @Override
    public Object getObject() throws Exception {
        return mock(type);
    }

    @Override
    public Class getObjectType() {
        return type;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
