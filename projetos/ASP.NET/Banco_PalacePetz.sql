create database db_PalacePetz;

use db_PalacePetz;

create table tbl_func(
	id_func int primary key auto_increment,
    cpf_func varchar(14) not null,
    nm_func varchar(50) not null,
    cg_func varchar(50) not null,
    end_func varchar(50) not null,
    tel_func varchar(15) not null
);
create table tbl_gerente(
	nm_ger varchar(50) not null,
    cpf_ger varchar(14) not null,
    senha_ger varchar(12)  primary key not null
);	

create table tbl_cliente(
	id_cli int primary key auto_increment,
    cpf_cli varchar(14) not null,
    nm_cli varchar(50) not null,
    end_cli varchar(50) not null,
    tel_cli varchar(15) not null
);
alter table tbl_cliente add comple_cli varchar(100);


create table tbl_produto(
	id_prod int primary key auto_increment,
    nm_prod varchar(100) not null,
    qntd_prod int not null,
    cat_prod varchar(50) not null,
    data_prod date
);
alter table tbl_produto add preco_prod decimal(6, 2) not null;

create table tbl_veterinario(
	id_vet int primary key auto_increment,
    cpf_vet varchar(14) not null,
    crmv_vet int,
    nm_vet varchar(100) not null,
    tel_vet varchar(15) not null
);

create table tbl_consulta(
	cd_animal int primary key auto_increment,
    nm_cli varchar(50) not null,
    cpf_cli varchar(14) not null,
    end_cli varchar(50) not null,
    tel_cli varchar(15) not null,
    nm_vet varchar(100) not null,
    dt_consulta date not null,
    hr_consulta time not null
);
alter table tbl_consulta add forma_paga varchar(100) not null;
alter table tbl_consulta add raca_animal varchar(100) not null;

create table tbl_banhoTosa(
	cd_animal int not null , constraint foreign key(cd_animal) references tbl_consulta(cd_animal),
    raca_animal varchar(100) not null,
    nm_cli varchar(50) not null,
    cpf_cli varchar(14) not null,
    end_cli varchar(50) not null,
    tel_cli varchar(15) not null,
    nm_func varchar(50) not null,
    dt_serv date not null,
    hr_serv time not null,
    forma_paga varchar(100) not null
);
drop table tbl_banhoTosa;

create table tbl_BanhoTosa(
    cd_Animal int primary key auto_increment,
	raca_animal varchar(100) not null,
    nm_cli varchar(50) not null,
    cpf_cli varchar(14) not null,
    end_cli varchar(50) not null,
    tel_cli varchar(15) not null,
    nm_func varchar(50) not null,
    dt_serv date not null,
    hr_serv time not null,
    forma_paga varchar(100) not null
);
create table tbl_VendaProd(
	cd_venda int not null primary key auto_increment,
    Nm_cli varchar(50) not null,
    Cpf_cli varchar(14) not null,
    End_cli varchar(50) not null,
    Comple_cli varchar(100),
    Tel_cli varchar(15) not null,
	nm_prod varchar(100) not null references tbl_produto(nm_prod),
    Qntd_prod int not null,
    Cat_prod varchar(50) not null,
    Forma_paga varchar(100) not null
);
create table tbl_login(
	user_login varchar(14) not null,
    senha_login varchar(14) not null primary key
);
select * from tbl_gerente;