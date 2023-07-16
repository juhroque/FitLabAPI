create table exercicios (
    id bigint not null auto_increment,
    nome varchar(100) not null,
    parte_do_corpo VARCHAR(255) NOT NULL,
    cardio tinyint NOT NULL,
    ativo tinyint,
    primary key(id)

   );
