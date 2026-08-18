CREATE DATABASE plataforma_ambiental;
USE plataforma_ambiental;

CREATE TABLE Bioma(
 id_bioma INT PRIMARY KEY,
 nome VARCHAR(50),
 descricao TEXT
);

CREATE TABLE Rio(
 id_rio INT PRIMARY KEY,
 id_bioma INT,
 nome VARCHAR(80),
 extensao_km DECIMAL(8,2),
 FOREIGN KEY(id_bioma) REFERENCES Bioma(id_bioma)
);

CREATE TABLE Trecho_Rio(
 id_trecho INT PRIMARY KEY,
 id_rio INT,
 estado CHAR(2),
 cidade VARCHAR(60),
 FOREIGN KEY(id_rio) REFERENCES Rio(id_rio)
);

CREATE TABLE Especie(
 id_especie INT PRIMARY KEY,
 nome_popular VARCHAR(80),
 categoria VARCHAR(30)
);

CREATE TABLE Trecho_Especie(
 id_trecho INT,
 id_especie INT,
 PRIMARY KEY(id_trecho,id_especie),
 FOREIGN KEY(id_trecho) REFERENCES Trecho_Rio(id_trecho),
 FOREIGN KEY(id_especie) REFERENCES Especie(id_especie)
);

CREATE TABLE Ponto_Monitoramento(
 id_ponto INT PRIMARY KEY,
 id_trecho INT,
 latitude DECIMAL(9,6),
 longitude DECIMAL(9,6),
 FOREIGN KEY(id_trecho) REFERENCES Trecho_Rio(id_trecho)
);

CREATE TABLE Monitoramento(
 id_monitoramento INT PRIMARY KEY,
 id_ponto INT,
 data_coleta DATE,
 ph DECIMAL(3,1),
 qualidade VARCHAR(20),
 FOREIGN KEY(id_ponto) REFERENCES Ponto_Monitoramento(id_ponto)
);
