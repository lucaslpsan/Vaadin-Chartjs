package com.example;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BancoService {

    @Results({
            @Result(property = "nome", column = "torg_nome"),
            @Result(property = "id", column = "torg_seq"),
            @Result(property = "nomeConjunto", column = "tcod_nome"),
            @Result(property = "idConjunto", column = "tcod_seq")
    })

    @Select("SELECT TORG_NOME FROM TB_ORGAO")
    List<String> findNomeOrgao();

    @Select("SELECT TORG_SEQ FROM TB_ORGAO")
    List<String> findIdOrgao();

    @Select("SELECT TORG_SEQ FROM TB_ORGAO WHERE TORG_NOME = #{nome}")
    int findIdOrgaoByName(String nome);

    @Select("SELECT TCOD_TITULO FROM TB_CONJUNTO_DADOS WHERE TCOD_TORG_SEQ = #{id}")
    List<String> findNameConjunto(int id);

    @Select("SELECT TCOD_SEQ FROM TB_CONJUNTO_DADOS WHERE TCOD_TITULO = #{nomeConjunto}")
    int findIdConjuntoByName(String nomeConjunto);

    @Select("SELECT TCOD_TITULO FROM TB_CONJUNTO_DADOS WHERE TCOD_SEQ = #{idConjunto}")
    String findTitulo(int idConjunto);

    @Select("SELECT TCOD_JSON FROM TB_CONJUNTO_DADOS WHERE TCOD_SEQ = #{idConjunto}")
    String findJson(int idConjunto);

    @Select("SELECT TCOD_DATASET FROM TB_CONJUNTO_DADOS WHERE TCOD_SEQ = #{idConjunto}")
    String findDataset(int idConjunto);

}
