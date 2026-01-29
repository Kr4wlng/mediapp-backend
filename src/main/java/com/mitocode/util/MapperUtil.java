package com.mitocode.util;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.core.ApplicationContext;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MapperUtil {

    private final ApplicationContext applicationContext;

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass, String... mapperQualifier) {
        ModelMapper modelMapper = getModelMapper(mapperQualifier);

        return source
                .stream()
                .map(element -> modelMapper.map(element, targetClass))
                .toList();
    }

    private ModelMapper getModelMapper(String... mapperQualifier) {
        if (mapperQualifier.length == 0 || mapperQualifier[0] == null || mapperQualifier[0].isEmpty()){
            return applicationContext.getBean("defaultMapper", ModelMapper.class);
        }else {
            return applicationContext.getBean(mapperQualifier[0], ModelMapper.class);
        }
    }

}
