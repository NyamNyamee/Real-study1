-- User
CREATE TABLE users (
	no		 INT AUTO_INCREMENT,
	name	 VARCHAR(40) NOT NULL,
	email	 VARCHAR(80) NOT NULL,
	password VARCHAR(20) NOT NULL,
	gender	 VARCHAR(10),
	PRIMARY KEY (no)
);

-- Board
CREATE TABLE board (
	no		 INT AUTO_INCREMENT,
	title	 VARCHAR(200),
	content  VARCHAR(4000),
	reg_date TIMESTAMP, -- DEFAULT CURRENT_TIMESTAMP
	hit		 INT, -- DEFAULT 0,
	group_no INT,
	order_no INT,
	depth	 INT,
	users_no INT,
	PRIMARY KEY (no)
);

-- Gallery
CREATE TABLE gallery (
	no				 INT AUTO_INCREMENT,
	org_file_name	 VARCHAR(200),
	save_file_name	 VARCHAR(200),
	comments		 VARCHAR(4000),
	file_ext_name	 VARCHAR(10),
	file_size		 INT,
	reg_date		 TIMESTAMP,
	users_no		 INT,
	PRIMARY KEY (no)
);








