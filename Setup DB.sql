DROP TABLE IF EXISTS report;
DROP TABLE IF EXISTS serial_customer;
DROP TABLE IF EXISTS icon;
DROP TABLE IF EXISTS customer;

CREATE TABLE customer (
	customer_id int AUTO_INCREMENT NOT NULL,
	customer_name varchar(128) NOT NULL,
	address text,
	notes text,
	contact_number char(10),
	primary key (customer_id)
);

CREATE TABLE icon (
	serial_number INT AUTO_INCREMENT NOT NULL,
	part_number varchar(128) NOT NULL,
	status varchar(128) NOT NULL,
	production_date date NOT NULL,
	notes text,
	primary key (serial_number)
);

CREATE TABLE serial_customer (
	serial_number INT NOT NULL,
	customer_id INT NOT null,
    received_date date NOT null,
	foreign key (serial_number) references icon (serial_number) ON DELETE CASCADE,
	foreign key (customer_id) references customer (customer_id) ON DELETE CASCADE,
	unique key (customer_id, serial_number)
);

CREATE TABLE report (
	report_id INT AUTO_INCREMENT NOT NULL,
	serial_number INT NOT NULL,
	dc_value_min int,
	dc_value_max int,
	code_34 bool,
	battery bool,
	calibrated bool,
	notes text,
	primary key (report_id),
	foreign key (serial_number) references icon (serial_number) ON DELETE CASCADE
);

INSERT INTO `report_mgr_db`.`icon` (`serial_number`, `part_number`, `status`, `production_date`, `notes`) VALUES ('1', 'abc123', 'In-Use', '2021-05-06', 'Dropped');
INSERT INTO `report_mgr_db`.`icon` (`serial_number`, `part_number`, `status`, `production_date`, `notes`) VALUES ('2', '321cba', 'Retired', '2021-05-06', 'Returned with broken screen 4\/28');
INSERT INTO `report_mgr_db`.`report` (`report_id`, `serial_number`, `dc_value_min`, `dc_value_max`, `code_34`, `battery`, `calibrated`, `notes`) VALUES ('1', '1', '23', '24', '1', '0', '1', 'ICON was dropped.');
INSERT INTO `report_mgr_db`.`customer` (`customer_id`, `customer_name`, `address`, `notes`, `contact_number`) VALUES ('1', 'Widgets, Inc.', '123 Jolly Ln, North Pole', 'Ho-Ho-Ho', '9999999999');
INSERT INTO `report_mgr_db`.`report` (`report_id`, `serial_number`, `dc_value_min`, `dc_value_max`, `code_34`, `battery`, `calibrated`, `notes`) VALUES ('2', '2', '20', '26', '0', '0', '1', 'Broken screen replaced 5\/2');
