CREATE TABLE Users (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    role VARCHAR(10) NOT NULL, -- 'customer' or 'officer'
    full_name VARCHAR(100),
    address VARCHAR(255),
    contact_number VARCHAR(20)
);

CREATE TABLE Bookings (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id INT REFERENCES Users(id),
    rec_name VARCHAR(100),
    rec_address VARCHAR(255),
    rec_pin VARCHAR(10),
    rec_mobile VARCHAR(20),
    par_weight_gram INT,
    par_contents_description VARCHAR(255),
    par_delivery_type VARCHAR(50),
    par_packing_preference VARCHAR(50),
    par_pickup_time TIMESTAMP,
    par_dropoff_time TIMESTAMP,
    par_service_cost DECIMAL(10, 2),
    par_payment_time TIMESTAMP,
    par_status VARCHAR(50)
);

CREATE TABLE TrackingInfo (
    booking_id INT REFERENCES Bookings(id),
    tracking_status VARCHAR(50)
);
