create database booking_hotel;
use booking_hotel;

-- Bảng Customers
CREATE TABLE Customers (
    UserID INT PRIMARY KEY AUTO_INCREMENT,
    FullName VARCHAR(255),
    Address VARCHAR(255),
    Phone VARCHAR(20),
    IdentifyNumber VARCHAR(50),
    Email VARCHAR(100),
    Gender VARCHAR(10),
    BirthDate DATE,
    Password VARCHAR(255),
    Image VARCHAR(255)
);

-- Bảng Promotion
CREATE TABLE Promotion (
    PromotionID INT PRIMARY KEY AUTO_INCREMENT,
    CustomerID INT,
    PromotionName VARCHAR(255),
    StartDate DATE,
    EndDate DATE,
    Descriptions TEXT,
    PromotionCode VARCHAR(50),
    Conditions TEXT,
    FOREIGN KEY (CustomerID) REFERENCES Customers(UserID)
);

-- Bảng Rooms
CREATE TABLE Rooms (
    RoomID INT PRIMARY KEY AUTO_INCREMENT,
    RoomTypeID INT,
    RoomNumber VARCHAR(50),
    Bed INT,
    Bath INT,
    Descriptions TEXT,
    Price DECIMAL(10, 2),
    Image VARCHAR(255),
    Status VARCHAR(20),
    FOREIGN KEY (RoomTypeID) REFERENCES RoomTypes(RoomTypeID)
);

-- Bảng RoomTypes
CREATE TABLE RoomTypes (
    RoomTypeID INT PRIMARY KEY AUTO_INCREMENT,
    RoomTypeName VARCHAR(100),
    Descriptions TEXT
);

-- Bảng Services
CREATE TABLE Services (
    ServiceID INT PRIMARY KEY AUTO_INCREMENT,
    ServiceTypeID INT,
    ServiceName VARCHAR(255),
    Price DECIMAL(10, 2),
    Image VARCHAR(255),
    Descriptions TEXT,
    FOREIGN KEY (ServiceTypeID) REFERENCES ServiceTypes(ServiceTypeID)
);

-- Bảng ServiceTypes
CREATE TABLE ServiceTypes (
    ServiceTypeID INT PRIMARY KEY AUTO_INCREMENT,
    ServiceTypeName VARCHAR(100),
    Descriptions TEXT
);

-- Bảng Bookings
CREATE TABLE Bookings (
    BookingID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT,
    RoomID INT,
    ServiceID INT,
    PromotionID INT,
    BookingDate DATE,
    CheckInDate DATE,
    CheckOutDate DATE,
    Adult INT,
    Child INT,
    PaymentStatus VARCHAR(50),
    FOREIGN KEY (UserID) REFERENCES Customers(UserID),
    FOREIGN KEY (RoomID) REFERENCES Rooms(RoomID),
    FOREIGN KEY (ServiceID) REFERENCES Services(ServiceID),
    FOREIGN KEY (PromotionID) REFERENCES Promotion(PromotionID)
);

-- Bảng InvoicePayments
CREATE TABLE InvoicePayments (
    InvoicePaymentID INT PRIMARY KEY AUTO_INCREMENT,
    BookingID INT,
    EmployeeID INT,
    InvoiceDate DATE,
    TotalAmount DECIMAL(10, 2),
    InvoiceName VARCHAR(255),
    Status VARCHAR(50),
    FOREIGN KEY (BookingID) REFERENCES Bookings(BookingID),
    FOREIGN KEY (EmployeeID) REFERENCES Employees(EmployeeID)
);

-- Bảng Employees
CREATE TABLE Employees (
    EmployeeID INT PRIMARY KEY AUTO_INCREMENT,
    PositionID INT,
    FullName VARCHAR(255),
    Address VARCHAR(255),
    Phone VARCHAR(20),
    Email VARCHAR(100),
    Gender VARCHAR(10),
    BirthDate DATE,
    FOREIGN KEY (PositionID) REFERENCES Positions(PositionID)
);

-- Bảng Position
CREATE TABLE Positions (
    PositionID INT PRIMARY KEY AUTO_INCREMENT,
    PositionName VARCHAR(100),
    Salary DECIMAL(10, 2),
    Bonus DECIMAL(10, 2),
    Description TEXT
);


-- Bảng InvoiceTypes
CREATE TABLE InvoiceTypes (
    InvoiceTypeID INT PRIMARY KEY AUTO_INCREMENT,
    InvoiceTypeName VARCHAR(100),
    Description TEXT
);

-- Bảng Managers
CREATE TABLE Managers (
    ManagerID INT PRIMARY KEY AUTO_INCREMENT,
    Username VARCHAR(50),
    Password VARCHAR(255),
    FullName VARCHAR(255),
    Email VARCHAR(100),
    Phone VARCHAR(20),
    Gender VARCHAR(10),
    Image VARCHAR(255)
);

-- Bảng News
CREATE TABLE News (
    NewsID INT PRIMARY KEY AUTO_INCREMENT,
    NewsTitle VARCHAR(255),
    Author VARCHAR(100),
    PublishDate DATE,
    NewsContent TEXT
);
