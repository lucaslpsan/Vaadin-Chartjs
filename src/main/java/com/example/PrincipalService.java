package com.example;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PrincipalService {

    @Select("SELECT TORG_NOME FROM TB_ORGAO")
    List<String> findNomeOrgao();

    @Select("SELECT TORG_SEQ FROM TB_ORGAO")
    List<String> findIdOrgao();

    @Select("SELECT TCOD_NOME FROM TB_CONJUNTO_DADOS WHERE TORG_SEQ = 1")
    List<String> findConjunto();
}
