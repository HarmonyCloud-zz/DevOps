package com.harmony.devops.common.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageableUtil {

    public static final Pageable get(Integer page, Integer size) {
        if (null == page || page<0) page = 0;
        if (null == size) size = 10;
        return new PageRequest(page, size);
    }
}
