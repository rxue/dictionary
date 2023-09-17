package rx.dictionary.rest;

import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;
import rx.dictionary.rest.paramconverter.AcceptLanguagesConverter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Locale;
@Provider
public class ParamConverterProviderImpl implements ParamConverterProvider {
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> aClass, Type type, Annotation[] annotations) {
        if (aClass.equals(Locale.class)) {
            return (ParamConverter<T>) new ParamConverter<Locale> () {

                @Override
                public Locale fromString(String languageTag) {
                    return Locale.forLanguageTag(languageTag);
                }

                @Override
                public String toString(Locale locale) {
                    throw new UnsupportedOperationException();
                }
            };
        }
        else if (aClass.equals(List.class)) {
            return (ParamConverter<T>) new AcceptLanguagesConverter();
        }
        return null;
    }
}
