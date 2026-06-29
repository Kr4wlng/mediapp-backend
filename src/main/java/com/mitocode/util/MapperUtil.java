package com.mitocode.util;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
// Clase para refactorizar en genérico la conversión de entidades a DTO y viceversa
public class MapperUtil {

    // Sirve para traer los beans que se requieran en tiempo de ejecución
    private final ApplicationContext applicationContext;

    // Empezamos con la conversión en Lista
    public <S, T>List<T> mapList(List<S> source, Class<T> targetClass, String... mapperQualifier){
        ModelMapper modelMapper = getModelMapper(mapperQualifier);

        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .toList();
    }

    // Ahora la conversión para una unidad
    public <S, T> T map(S source, Class<T> targetClass, String... mapperQualifier){
        ModelMapper modelMapper = getModelMapper(mapperQualifier);
        return modelMapper.map(source, targetClass);
    }

    private ModelMapper getModelMapper(String... mapperQualifier){
        if (mapperQualifier.length == 0 || mapperQualifier[0] == null || mapperQualifier[0].isEmpty()){
            return applicationContext.getBean("defaultMapper", ModelMapper.class);
        } else {
            return applicationContext.getBean(mapperQualifier[0], ModelMapper.class);
        }
    }

}
