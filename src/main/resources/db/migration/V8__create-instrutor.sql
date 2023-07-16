create table instrutores(

    id bigint not null auto_increment,
    login varchar(100) not null unique,
    senha varchar(255) not null,

    primary key(id)

);

create table instrutor_aluno(
    id bigint not null auto_increment,
    instrutor_id bigint,
    aluno_id bigint,
    primary key(id)
  );
