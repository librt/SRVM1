package srvm.lab1;

import org.jbehave.core.steps.ParameterConverters;

import java.lang.reflect.Type;

public class BigNumberConverter implements ParameterConverters.ParameterConverter {
    @Override
    public boolean accept(Type type) {
        return type instanceof Class<?> && BigNumber.class.isAssignableFrom((Class<?>) type);
    }

    @Override
    public Object convertValue(String value, Type type) {
        return new BigNumber(value);
    }
}
