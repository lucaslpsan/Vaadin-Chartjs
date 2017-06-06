package com.example;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BancoService {

    @Select("SELECT TCOD_JSON FROM TB_CONJUNTO_DADOS WHERE TCOD_SEQ = 1")
    String findJson();

    @Select("SELECT TCOD_TITULO FROM TB_CONJUNTO_DADOS WHERE TCOD_SEQ = 1")
    String findTitulo();

    @Select("SELECT TCOD_DATASET FROM TB_CONJUNTO_DADOS WHERE TCOD_SEQ = 1")
    String findDataset();

}
