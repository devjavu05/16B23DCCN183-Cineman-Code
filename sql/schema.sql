-- CineMan3 - CSDL cineman3_db (MySQL 8 / MariaDB 10). 18 bang, ket qua Buoc 5 thiet ke CSDL.
-- Chay: mysql -u root -p < schema.sql   (sau do: mysql -u root -p cineman3_db < data.sql)
DROP DATABASE IF EXISTS cineman3_db;
CREATE DATABASE cineman3_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE cineman3_db;

CREATE TABLE tblUser3 (
  username VARCHAR(50) PRIMARY KEY,
  password VARCHAR(255) NOT NULL,
  status   VARCHAR(20)  NOT NULL DEFAULT 'ACTIVE'
) ENGINE=InnoDB;

CREATE TABLE tblMembershipTier3 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tierName VARCHAR(50) NOT NULL,
  description VARCHAR(255),
  pointThreshold INT NOT NULL DEFAULT 0
) ENGINE=InnoDB;

-- The thanh vien (1-1 voi khach hang) da duoc gop vao bang khach hang (cac cot card*)
CREATE TABLE tblCustomer3 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  fullName VARCHAR(100) NOT NULL,
  birthDate DATE,
  gender VARCHAR(10),
  phone VARCHAR(15) NOT NULL,
  email VARCHAR(100) NOT NULL,
  address VARCHAR(255),
  createdDate DATE NOT NULL,
  cardNumber VARCHAR(20) NULL,
  cardIssueDate DATE NULL,
  cardStatus VARCHAR(20) NULL,
  tblMembershipTier3id INT NULL,
  UNIQUE KEY uq_customer_phone (phone),
  UNIQUE KEY uq_customer_email (email),
  UNIQUE KEY uq_customer_card (cardNumber),
  CONSTRAINT fk_customer_tier FOREIGN KEY (tblMembershipTier3id) REFERENCES tblMembershipTier3(id)
) ENGINE=InnoDB;

CREATE TABLE tblMember3 (
  username VARCHAR(50) PRIMARY KEY,
  tblCustomer3id INT NOT NULL,
  loyaltyPoints INT NOT NULL DEFAULT 0,
  registerDate DATE NOT NULL,
  CONSTRAINT fk_member_user FOREIGN KEY (username) REFERENCES tblUser3(username),
  CONSTRAINT fk_member_customer FOREIGN KEY (tblCustomer3id) REFERENCES tblCustomer3(id)
) ENGINE=InnoDB;

CREATE TABLE tblBranch3 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  address VARCHAR(255),
  phone VARCHAR(15),
  openTime TIME,
  closeTime TIME
) ENGINE=InnoDB;

CREATE TABLE tblEmployee3 (
  username VARCHAR(50) PRIMARY KEY,
  tblBranch3id INT NOT NULL,
  fullName VARCHAR(100) NOT NULL,
  phone VARCHAR(15),
  email VARCHAR(100),
  hireDate DATE,
  CONSTRAINT fk_employee_user FOREIGN KEY (username) REFERENCES tblUser3(username),
  CONSTRAINT fk_employee_branch FOREIGN KEY (tblBranch3id) REFERENCES tblBranch3(id)
) ENGINE=InnoDB;

CREATE TABLE tblManager3 (
  username VARCHAR(50) PRIMARY KEY,
  position VARCHAR(50),
  scope VARCHAR(100),
  CONSTRAINT fk_manager_employee FOREIGN KEY (username) REFERENCES tblEmployee3(username)
) ENGINE=InnoDB;

CREATE TABLE tblSalesStaff3 (
  username VARCHAR(50) PRIMARY KEY,
  position VARCHAR(50),
  CONSTRAINT fk_sales_employee FOREIGN KEY (username) REFERENCES tblEmployee3(username)
) ENGINE=InnoDB;

CREATE TABLE tblAuditorium3 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tblBranch3id INT NOT NULL,
  name VARCHAR(50) NOT NULL,
  screenType VARCHAR(30),
  CONSTRAINT fk_auditorium_branch FOREIGN KEY (tblBranch3id) REFERENCES tblBranch3(id)
) ENGINE=InnoDB;

CREATE TABLE tblSeat3 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tblAuditorium3id INT NOT NULL,
  position VARCHAR(10) NOT NULL,
  seatType VARCHAR(20),
  CONSTRAINT fk_seat_auditorium FOREIGN KEY (tblAuditorium3id) REFERENCES tblAuditorium3(id)
) ENGINE=InnoDB;

CREATE TABLE tblMovie3 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(200) NOT NULL,
  originalTitle VARCHAR(200),
  genre VARCHAR(100),
  duration INT,
  director VARCHAR(100),
  mainCast VARCHAR(255),
  ageRating VARCHAR(10),
  subtitleLanguage VARCHAR(50),
  description TEXT,
  posterUrl VARCHAR(255),
  trailerUrl VARCHAR(255),
  releaseDate DATE,
  status VARCHAR(20)
) ENGINE=InnoDB;

CREATE TABLE tblShowtime3 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tblMovie3id INT NOT NULL,
  tblAuditorium3id INT NOT NULL,
  showDate DATE NOT NULL,
  startTime TIME NOT NULL,
  basePrice DECIMAL(12,0) NOT NULL,
  status VARCHAR(20),
  CONSTRAINT fk_showtime_movie FOREIGN KEY (tblMovie3id) REFERENCES tblMovie3(id),
  CONSTRAINT fk_showtime_auditorium FOREIGN KEY (tblAuditorium3id) REFERENCES tblAuditorium3(id)
) ENGINE=InnoDB;

CREATE TABLE tblPaymentMethod3 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL
) ENGINE=InnoDB;

CREATE TABLE tblVoucher3 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  voucherCode VARCHAR(30) NOT NULL,
  discountType VARCHAR(20),
  discountValue DECIMAL(12,2),
  `condition` VARCHAR(255),
  startDate DATE,
  endDate DATE,
  maxUses INT,
  usedCount INT NOT NULL DEFAULT 0
) ENGINE=InnoDB;

CREATE TABLE tblCombo3 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(255),
  price DECIMAL(12,0) NOT NULL,
  status VARCHAR(20)
) ENGINE=InnoDB;

CREATE TABLE tblInvoice3 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tblCustomer3id INT NULL,
  salesStaffUsername VARCHAR(50) NULL,
  tblPaymentMethod3id INT NOT NULL,
  tblVoucher3id INT NULL,
  invoiceCode VARCHAR(20) NOT NULL,
  discountAmount DECIMAL(12,0) NOT NULL DEFAULT 0,
  createdAt DATETIME NOT NULL,
  status VARCHAR(20) NOT NULL,
  UNIQUE KEY uq_invoice_code (invoiceCode),
  CONSTRAINT fk_invoice_customer FOREIGN KEY (tblCustomer3id) REFERENCES tblCustomer3(id),
  CONSTRAINT fk_invoice_staff FOREIGN KEY (salesStaffUsername) REFERENCES tblSalesStaff3(username),
  CONSTRAINT fk_invoice_payment FOREIGN KEY (tblPaymentMethod3id) REFERENCES tblPaymentMethod3(id),
  CONSTRAINT fk_invoice_voucher FOREIGN KEY (tblVoucher3id) REFERENCES tblVoucher3(id)
) ENGINE=InnoDB;

CREATE TABLE tblTicket3 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tblInvoice3id INT NOT NULL,
  tblShowtime3id INT NOT NULL,
  tblSeat3id INT NOT NULL,
  actualPrice DECIMAL(12,0) NOT NULL,
  qrCode VARCHAR(100),
  status VARCHAR(20),
  UNIQUE KEY uq_ticket_showtime_seat (tblShowtime3id, tblSeat3id),
  CONSTRAINT fk_ticket_invoice FOREIGN KEY (tblInvoice3id) REFERENCES tblInvoice3(id),
  CONSTRAINT fk_ticket_showtime FOREIGN KEY (tblShowtime3id) REFERENCES tblShowtime3(id),
  CONSTRAINT fk_ticket_seat FOREIGN KEY (tblSeat3id) REFERENCES tblSeat3(id)
) ENGINE=InnoDB;

CREATE TABLE tblInvoiceCombo3 (
  id INT AUTO_INCREMENT PRIMARY KEY,
  tblInvoice3id INT NOT NULL,
  tblCombo3id INT NOT NULL,
  quantity INT NOT NULL,
  CONSTRAINT fk_ic_invoice FOREIGN KEY (tblInvoice3id) REFERENCES tblInvoice3(id),
  CONSTRAINT fk_ic_combo FOREIGN KEY (tblCombo3id) REFERENCES tblCombo3(id)
) ENGINE=InnoDB;
