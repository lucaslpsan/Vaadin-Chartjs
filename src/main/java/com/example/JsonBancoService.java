package com.example;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by Lucas on 30-May-17.
 */
@Mapper
public interface JsonBancoService {

    @Select("SELECT T_JSON FROM TESTE_JSON WHERE T_ID = 1")
    String findJson();

}
