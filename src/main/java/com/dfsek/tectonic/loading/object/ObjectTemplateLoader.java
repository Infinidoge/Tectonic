package com.dfsek.tectonic.loading.object;

import com.dfsek.tectonic.abstraction.TemplateProvider;
import com.dfsek.tectonic.config.Configuration;
import com.dfsek.tectonic.exception.ConfigException;
import com.dfsek.tectonic.exception.LoadException;
import com.dfsek.tectonic.loading.ConfigLoader;
import com.dfsek.tectonic.loading.TypeLoader;

import java.lang.reflect.Type;
import java.util.Map;

@SuppressWarnings("unchecked")
public class ObjectTemplateLoader<T> implements TypeLoader<T> {
    private final TemplateProvider<ObjectTemplate<T>> provider;

    public ObjectTemplateLoader(TemplateProvider<ObjectTemplate<T>> provider) {
        this.provider = provider;
    }

    @Override
    public T load(Type t, Object c, ConfigLoader loader) throws LoadException {
        ObjectTemplate<T> template = provider.getInstance();
        try {
            loader.load(template, new Configuration((Map<String, Object>) c));
        } catch(ConfigException e) {
            throw new LoadException("Unable to load object.", e);
        }
        return template.get();
    }
}
