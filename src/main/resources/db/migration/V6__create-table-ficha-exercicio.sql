create table ficha_exercicio(
    id bigint not null auto_increment,
   ficha_id bigint,
    exercicio_id bigint,
    duracao integer,
    series integer,
    repeticoes integer,
    primary key(id)
  );
