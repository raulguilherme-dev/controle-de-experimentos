create database projeto;

use projeto;

create table funcionarioUniversidade(
	matricula int primary key,
    nome varchar(255),
    sexo char,
    nascimento date
);

create table feriado(
	id int primary key auto_increment,
	data_ date,
    descricao varchar(255)
);

create table especie(
	id int primary key auto_increment,
	nome varchar(255)
);

create table protocoloEspecie(
	id int primary key auto_increment,
	quantidade int
);

create table bioterio(
	id int primary key auto_increment,
	nome varchar(255)
);

create table protocolo(
	id int primary key auto_increment,
	dataEmissaoProtocolo date,
    dataInicioExperimento date,
    dataFimExperimento date,
    justificativaUsoAnimais varchar(255),
    resumoPortugues text,
    resumoIngles text,
    dataEnvioParecer date,
    dataEmissaoParecer date,
    descrissaoParecer varchar(255),
    statusParecer varchar(255),
    dataDeliberacaoProtocolo date,
    statusDeliberacao varchar(255),
    statusProtocolo varchar(255)
);

create table docente(
	titulacao int
);
