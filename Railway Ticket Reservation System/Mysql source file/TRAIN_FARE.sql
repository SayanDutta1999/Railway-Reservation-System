CREATE TABLE T_TRAIN_FARE(
	FIRST_CLASS_PRICE varchar(6) NOT NULL,
    ECONOMY_CLASS_PRICE varchar(6) NOT NULL,
    TRAIN_NO varchar(20) not null,
    CONSTRAINT FK_TRAIN_NO FOREIGN KEY(TRAIN_NO) REFERENCES T_TRAIN(TRAIN_NO)
);
INSERT INTO T_TRAIN_FARE VALUES ('380', '105', "12857"),
				('405', '125', "22321"),
                                ('430', '120', "22387"),
                                ('2130', '330', "18005"),
                                ('3045', '495', "22817"),
                                ('3460', '980', "22863"),
                                ('2750', '445', "22894"),
                                ('2700', '435', "22912"),
                                ('1635', '1070', "12041"),
                                ('365', '100', "03047"),
                                ('1085', '180', "12317"),
                                ('1035', '185', "58011"),
                                ('575', '145', "58013");
