package com.mygitgor.ecommerce_multivendor.mapper;

public interface ConfigMapper <T, E>{
    E toEntity(T t);
    T toDomain(E e);
}