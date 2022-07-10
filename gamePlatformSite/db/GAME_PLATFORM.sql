DROP SCHEMA IF EXISTS gameplatform;
CREATE SCHEMA IF NOT EXISTS gameplatform;
USE gameplatform;



/* ----------------------------- entità ----------------------------- */ 
CREATE TABLE IF NOT EXISTS administrators(
	codice_fiscale char(16) NOT NULL,
	nome varchar(20) NOT NULL,
	cognome varchar(20) NOT NULL,
    data_nascita DATE NOT NULL,
    ruolo char(8) DEFAULT "admin",
    email varchar(50) NOT NULL,
    pass_word char(40) NOT NULL,
    retribuzione_annuale int NOT NULL,
    PRIMARY KEY(codice_fiscale)
);

CREATE TABLE IF NOT EXISTS supervisore_videogiochi(
	codice_fiscale char(16) NOT NULL,
	nome varchar(20) NOT NULL,
	cognome varchar(20) NOT NULL,
    data_nascita DATE NOT NULL,
    ruolo char(8) DEFAULT "supVid",
    email varchar(50) NOT NULL,
    pass_word char(40) NOT NULL,
    retribuzione_annuale int NOT NULL,
    PRIMARY KEY(codice_fiscale)
);

CREATE TABLE IF NOT EXISTS assistente_clienti(
	codice_fiscale char(16) NOT NULL,
	nome varchar(20) NOT NULL,
	cognome varchar(20) NOT NULL,
    data_nascita DATE NOT NULL,
    ruolo char(8) DEFAULT "assCl",
    email varchar(50) NOT NULL,
    pass_word char(40) NOT NULL,
    retribuzione_annuale int NOT NULL,
    PRIMARY KEY(codice_fiscale)
);

CREATE TABLE IF NOT EXISTS software_house(
	nome_univoco varchar(20) NOT NULL,
    locazione varchar(15) NOT NULL,
    data_di_fondazione date NOT NULL,
    PRIMARY KEY(nome_univoco)
);

CREATE TABLE IF NOT EXISTS videogioco(
	codice varchar(15) NOT NULL,
    nome_software_house varchar(20) NOT NULL,
    nome_videogioco varchar(30) NOT NULL,
    dimensione int NOT NULL,
    pegi enum('tre', 'sette', 'dodici', 'sedici', 'diciotto') NOT NULL,
    anno_di_produzione int,
    costo int NOT NULL,
    eliminato boolean DEFAULT false,
    PRIMARY KEY(codice),
    FOREIGN KEY(nome_software_house) REFERENCES software_house(nome_univoco) ON UPDATE CASCADE ON DELETE CASCADE 
);

CREATE TABLE IF NOT EXISTS abbonamento(
	nome_univoco varchar(20) NOT NULL,
    costo int NOT NULL,
    durata tinyint NOT NULL,
    eliminato boolean DEFAULT false,
    PRIMARY KEY(nome_univoco)
);

CREATE TABLE IF NOT EXISTS clienti(
	codice_fiscale char(16) NOT NULL,
    nome varchar(20) NOT NULL,
	cognome varchar(20) NOT NULL,
    data_nascita DATE NOT NULL,
    ruolo varchar(8) DEFAULT "cliente",
    email varchar(50) NOT NULL,
    pass_word char(40) NOT NULL,
	username varchar(15) NOT NULL,
    ind_fatt varchar(50),
    PRIMARY KEY(codice_fiscale)
);

CREATE TABLE IF NOT EXISTS tickets(
	id int NOT NULL,
    codice_fiscale_assist_cl char(16) NOT NULL,
    codice_fiscale_cliente char(16) NOT NULL,
    resolved boolean DEFAULT false NOT NULL,
    categoria_probl enum('account', 'pagamenti', 'abbonamenti', 'rimborso', 'fattura'),
    messaggio varchar(500) NOT NULL,
    data_ora DATETIME NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(codice_fiscale_assist_cl) REFERENCES assistente_clienti(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(codice_fiscale_cliente) REFERENCES clienti(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS telefono(
	numero bigint NOT NULL,
    codice_fiscale_cliente char(16) NOT NULL,
    PRIMARY KEY(numero),
    FOREIGN KEY(codice_fiscale_cliente) REFERENCES clienti(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS recensione(
	codice_fiscale_cliente char(16) NOT NULL,
    codice varchar(15) NOT NULL,
    data_ora_ins DATETIME NOT NULL,
    descrizione varchar(1000),
    grado_di_apprezzamento enum('uno', 'due', 'tre', 'quattro', 'cinque') NOT NULL,
    PRIMARY KEY(codice_fiscale_cliente, codice),
    FOREIGN KEY(codice_fiscale_cliente) REFERENCES clienti(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(codice) REFERENCES videogioco(codice) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS azienda(
	p_iva varchar(16) NOT NULL,
    codice_fiscale_cliente char(16) NOT NULL,
    sdi varchar(10) NOT NULL,
    pec varchar(50) NOT NULL,
    PRIMARY KEY(p_iva),
    FOREIGN KEY(codice_fiscale_cliente) REFERENCES clienti(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS acquisti(
	id int NOT NULL,
    codice_riscatto varchar(10),
    codice_fiscale_cliente char(16) NOT NULL,
    costo_iva int NOT NULL,
    costo_netto int NOT NULL,
    data_ora DATETIME,
    ind_fatt varchar(50),
    numero_carta_pagam bigint,
    PRIMARY KEY(id),
    FOREIGN KEY(codice_fiscale_cliente) REFERENCES clienti(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS fattura(
	id int NOT NULL,
    numero int AUTO_INCREMENT NOT NULL,
    costo_iva int NOT NULL,
    costo_netto int NOT NULL,
    data_ora DATETIME,
    ind_fatt varchar(50),
    PRIMARY KEY(numero, id),
    FOREIGN KEY(id) REFERENCES acquisti(id) ON UPDATE CASCADE ON DELETE CASCADE
);




/* ----------------------------- relazioni ----------------------------- */ 
CREATE TABLE IF NOT EXISTS add_sup_vid(
	codice_fiscale_sup_vid char(16) NOT NULL,
 	codice_fiscale_admin char(16) NOT NULL,   
    PRIMARY KEY(codice_fiscale_sup_vid, codice_fiscale_admin),
    FOREIGN KEY(codice_fiscale_sup_vid) REFERENCES supervisore_videogiochi(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(codice_fiscale_admin) REFERENCES administrators(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS rem_sup_vid(
	codice_fiscale_sup_vid char(16) NOT NULL,
 	codice_fiscale_admin char(16) NOT NULL,   
    PRIMARY KEY(codice_fiscale_sup_vid, codice_fiscale_admin),
    FOREIGN KEY(codice_fiscale_sup_vid) REFERENCES supervisore_videogiochi(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(codice_fiscale_admin) REFERENCES administrators(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS add_assist_cl(
	codice_fiscale_assist_cl char(16) NOT NULL,
 	codice_fiscale_admin char(16) NOT NULL,   
    PRIMARY KEY(codice_fiscale_assist_cl, codice_fiscale_admin),
    FOREIGN KEY(codice_fiscale_assist_cl) REFERENCES assistente_clienti(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(codice_fiscale_admin) REFERENCES administrators(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS rem_assist_cl(
	codice_fiscale_assist_cl char(16) NOT NULL,
 	codice_fiscale_admin char(16) NOT NULL,   
    PRIMARY KEY(codice_fiscale_assist_cl, codice_fiscale_admin),
    FOREIGN KEY(codice_fiscale_assist_cl) REFERENCES assistente_clienti(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(codice_fiscale_admin) REFERENCES administrators(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS add_videog(
	codice_fiscale_sup_vid char(16) NOT NULL,
    codice_videogioco varchar(15) NOT NULL,
    PRIMARY KEY(codice_fiscale_sup_vid, codice_videogioco),
    FOREIGN KEY(codice_fiscale_sup_vid) REFERENCES supervisore_videogiochi(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(codice_videogioco) REFERENCES videogioco(codice) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS rem_videog(
	codice_fiscale_sup_vid char(16) NOT NULL,
    codice_videogioco varchar(15) NOT NULL,
    PRIMARY KEY(codice_fiscale_sup_vid, codice_videogioco),
    FOREIGN KEY(codice_fiscale_sup_vid) REFERENCES supervisore_videogiochi(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(codice_videogioco) REFERENCES videogioco(codice) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS mod_videog(
	codice_fiscale_sup_vid char(16) NOT NULL,
    codice_videogioco varchar(15) NOT NULL,
    PRIMARY KEY(codice_fiscale_sup_vid, codice_videogioco),
    FOREIGN KEY(codice_fiscale_sup_vid) REFERENCES supervisore_videogiochi(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(codice_videogioco) REFERENCES videogioco(codice) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS rem_in_abb(
	codice_fiscale_sup_vid char(16) NOT NULL,
    codice_videogioco varchar(15) NOT NULL,
    nome_univoco_abb varchar(20) NOT NULL,
    PRIMARY KEY(codice_fiscale_sup_vid, codice_videogioco, nome_univoco_abb),
    FOREIGN KEY(codice_fiscale_sup_vid) REFERENCES supervisore_videogiochi(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(codice_videogioco) REFERENCES videogioco(codice) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(nome_univoco_abb) REFERENCES abbonamento(nome_univoco) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS add_in_abb(
	codice_fiscale_sup_vid char(16),
    codice_videogioco varchar(15) NOT NULL,
    nome_univoco_abb varchar(20) NOT NULL,
    PRIMARY KEY(codice_videogioco, nome_univoco_abb),
    FOREIGN KEY(codice_fiscale_sup_vid) REFERENCES supervisore_videogiochi(codice_fiscale) ON UPDATE CASCADE ON DELETE SET NULL,
    FOREIGN KEY(codice_videogioco) REFERENCES videogioco(codice) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(nome_univoco_abb) REFERENCES abbonamento(nome_univoco) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS mod_abb(
	codice_fiscale_sup_vid char(16) NOT NULL,
    nome_univoco_abb varchar(20) NOT NULL,
    PRIMARY KEY(codice_fiscale_sup_vid, nome_univoco_abb),
    FOREIGN KEY(codice_fiscale_sup_vid) REFERENCES supervisore_videogiochi(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(nome_univoco_abb) REFERENCES abbonamento(nome_univoco) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS add_abb(
	codice_fiscale_sup_vid char(16) NOT NULL,
    nome_univoco_abb varchar(20) NOT NULL,
    PRIMARY KEY(codice_fiscale_sup_vid, nome_univoco_abb),
    FOREIGN KEY(codice_fiscale_sup_vid) REFERENCES supervisore_videogiochi(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(nome_univoco_abb) REFERENCES abbonamento(nome_univoco) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS rem_abb(
	codice_fiscale_sup_vid char(16) NOT NULL,
    nome_univoco_abb varchar(20) NOT NULL,
    PRIMARY KEY(codice_fiscale_sup_vid, nome_univoco_abb),
    FOREIGN KEY(codice_fiscale_sup_vid) REFERENCES supervisore_videogiochi(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(nome_univoco_abb) REFERENCES abbonamento(nome_univoco) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS acq_contiene_vid(
	id int NOT NULL,
    codice_videogioco varchar(15) NOT NULL,
    costo int NOT NULL,
    PRIMARY KEY(id, codice_videogioco),
    FOREIGN KEY(codice_videogioco) REFERENCES videogioco(codice) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(id) REFERENCES acquisti(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS acq_contiene_abb(
	id int NOT NULL,
    nome_univoco_abb varchar(20) NOT NULL,
    costo int NOT NULL,
    PRIMARY KEY(id, nome_univoco_abb),
    FOREIGN KEY(nome_univoco_abb) REFERENCES abbonamento(nome_univoco) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(id) REFERENCES acquisti(id) ON UPDATE CASCADE ON DELETE CASCADE
);