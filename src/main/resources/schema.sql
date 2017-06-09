/*DROP TABLE IF EXISTS company;

CREATE TABLE company (
    id SERIAL,
    name VARCHAR(255) NOT NULL,
    website VARCHAR(255)
);

INSERT INTO company(name, website) VALUES ('Vaadin', 'https://vaadin.com');
INSERT INTO company(name, website) VALUES ('Red Hat', 'https://www.redhat.com');
INSERT INTO company(name, website) VALUES ('Canonical', 'http://www.canonical.com');
INSERT INTO company(name, website) VALUES ('Sonatype', 'http://www.sonatype.com');
INSERT INTO company(name, website) VALUES ('Alfresco', 'https://www.alfresco.com');
*/

DROP TABLE IF EXISTS public.TB_CONJUNTO_DADOS;
DROP TABLE IF EXISTS public.TB_ORGAO;


CREATE TABLE public.tb_orgao
(
    torg_seq serial NOT NULL,
    torg_nome character varying(100) ,
    torg_sigla character varying(100),
    torg_descricao text ,
    torg_tag character varying(100),

    CONSTRAINT tb_orgao_pkey PRIMARY KEY (torg_seq)
);

CREATE TABLE public.tb_conjunto_dados
(
    tcod_seq serial NOT NULL,
    tcod_torg_seq integer NOT NULL,
    tcod_serie integer NOT NULL,
    tcod_titulo character varying(100) NOT NULL,
    tcod_dataset character varying(100) NOT NULL,
    tcod_data_modif date NOT NULL,
    tcod_data_ini date NOT NULL,
    tcod_data_final date NOT NULL,
    tcod_json text NOT NULL,
    CONSTRAINT tb_conjunto_dados_pkey PRIMARY KEY (tcod_seq),
    CONSTRAINT tb_conjunto_dados_fkey FOREIGN KEY (tcod_torg_seq)
        REFERENCES public.tb_orgao (torg_seq) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

insert into public.tb_orgao
	values(1, 'Banco Central do Brasil', 'BCB', 'O Banco Central do Brasil é uma autarquia federal integrante do Sistema Financeiro Nacional, sendo vinculado ao Ministério da Fazenda do Brasil.',
           'Instituição Financeira');

insert into public.tb_orgao
values(2, 'Ministério da Fazenda', 'MF', 'O Ministério da Fazenda, órgão da administração federal direta.',
       'Ministério');

INSERT INTO public.tb_conjunto_dados
VALUES (1, 1, 25226, 'Quantidade de transações com débito direto', 'Quantidade em milhares', '04-06-2017', '01-01-2010', '01-01-2005', '[{"data":"01/01/2010","valor":"3585"},{"data":"01/01/2011","valor":"4135"},{"data":"01/01/2012","valor":"4358"},{"data":"01/01/2013","valor":"5083"},{"data":"01/01/2014","valor":"5686"},{"data":"01/01/2015","valor":"5427"}]');

INSERT INTO public.tb_conjunto_dados
VALUES (2, 1, 24930, 'Quantidade de sedes de instituições autorizadas a funcionar do segmento bancário no Brasil', 'Quantidade em unidades', '08-06-2017', '01-01-2016', '01-01-2005', '[{"data":"01/01/2005","valor":"137"},{"data":"01/01/2006","valor":"143"},{"data":"01/01/2007","valor":"144"},{"data":"01/01/2008","valor":"152"},{"data":"01/01/2009","valor":"155"},{"data":"01/01/2010","valor":"155"},{"data":"01/01/2011","valor":"159"},{"data":"01/01/2012","valor":"159"},{"data":"01/01/2013","valor":"156"},{"data":"01/01/2014","valor":"153"},{"data":"01/01/2015","valor":"153"},{"data":"01/01/2016","valor":"154"}]');
