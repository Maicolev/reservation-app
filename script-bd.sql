-- Customer types table (Regular, VIP)
CREATE TABLE customer_type (
    customer_type_id INT PRIMARY KEY,
    type_name VARCHAR(50) NOT NULL,
    monthly_fee DECIMAL(10,2) NOT NULL,
    discount_percentage DECIMAL(5,2) NOT NULL CHECK (discount_percentage BETWEEN 0 AND 100)
);

-- Customer table
CREATE TABLE customer (
    customer_id varchar(250) NOT NULL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone_number VARCHAR(20),
    customer_type_id INT,
    CONSTRAINT fk_customer_type FOREIGN KEY (customer_type_id) REFERENCES customer_type(customer_type_id)
);

-- Schedule table
CREATE TABLE schedule (
    schedule_id INT PRIMARY KEY,
    schedule_date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    is_available BOOLEAN NOT NULL DEFAULT TRUE
);

-- Table for different types of reservation
CREATE TABLE reservation_type (
    reservation_type_id INT PRIMARY KEY,
    type_name VARCHAR(50) NOT NULL
);

-- Reservations table
CREATE TABLE reservation (
    reservation_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id varchar(250) NOT NULL,
    schedule_id INT NOT NULL,
    reservation_type_id INT NOT NULL,
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    number_of_people INT NOT NULL CHECK (number_of_people > 0),
    discount_applied DECIMAL(10,2) DEFAULT 0,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    CONSTRAINT fk_schedule FOREIGN KEY (schedule_id) REFERENCES schedule(schedule_id),
    CONSTRAINT fk_reservation_type FOREIGN KEY (reservation_type_id) REFERENCES reservation_type(reservation_type_id)
);

-- Table to store reservation logs
CREATE TABLE reservation_log (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    reservation_id INT,
    action VARCHAR(50),
    action_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_reservation_log FOREIGN KEY (reservation_id) REFERENCES reservation(reservation_id)
);

-- Indices for query optimization
CREATE INDEX idx_schedule_date ON schedule (schedule_date);
CREATE INDEX idx_customer_email ON customer (email);
CREATE INDEX idx_reservation_customer ON reservation (customer_id);
CREATE INDEX idx_customer_type_name ON customer_type (type_name);
CREATE INDEX idx_reservation_type_name ON reservation_type (type_name);

-- Stored procedure to create a reservation
CREATE PROCEDURE create_reservation_v2(
    IN p_customer_id VARCHAR(50),
    IN p_first_name VARCHAR(50),
    IN p_last_name VARCHAR(50),
    IN p_email VARCHAR(100),
    IN p_phone_number VARCHAR(20),
    IN p_customer_type_id INT,
    IN p_schedule_id INT,
    IN p_schedule_date DATE,
    IN p_start_time TIME,
    IN p_end_time TIME,
    IN p_reservation_type INT,
    IN p_number_of_people INT,
    OUT p_discount DECIMAL(5,2),
    OUT p_id_reservation INT
)
BEGIN
    DECLARE v_discount_percentage DECIMAL(5,2);
    DECLARE v_is_available BOOLEAN;
    DECLARE v_max_reservation_id INT;
    DECLARE v_customer_id INT;
    DECLARE v_schedule_id INT;

    -- Start the transaction
    START TRANSACTION;

    SET p_discount = 0;

    -- Check if schedule exists
    SELECT schedule_id, is_available
    INTO v_schedule_id, v_is_available
    FROM schedule
    WHERE schedule_id = p_schedule_id;

    IF v_schedule_id IS NULL THEN
        -- If schedule does not exist, create a new one
        INSERT INTO schedule (schedule_id, schedule_date, start_time, end_time, is_available)
        VALUES (p_schedule_id, p_schedule_date, p_start_time, p_end_time, TRUE);
        COMMIT;
        SET v_schedule_id = LAST_INSERT_ID();
    ELSE
        IF v_is_available = FALSE THEN
            -- If schedule is unavailable, rollback
            SET p_discount = -2;
            ROLLBACK;
        END IF;
    END IF;

    IF p_discount = 0 THEN
        -- Check if customer exists
        SELECT customer_id
        INTO v_customer_id
        FROM customer
        WHERE customer_id = p_customer_id
        OR email = p_email;

        IF v_customer_id IS NULL THEN
            -- If customer does not exist, create a new one
            INSERT INTO customer (customer_id, first_name, last_name, email, phone_number, customer_type_id)
            VALUES (p_customer_id, p_first_name, p_last_name, p_email, p_phone_number, p_customer_type_id);
        END IF;

        -- Get customer discount
        SELECT IFNULL(ct.discount_percentage, 0)
        INTO v_discount_percentage
        FROM customer_type ct
        WHERE ct.customer_type_id = p_customer_type_id;

        -- Get the next available reservation ID
        SELECT MAX(reservation_id)
        INTO v_max_reservation_id
        FROM reservation;

        SET v_max_reservation_id = v_max_reservation_id + 1;

        -- Create the reservation
        INSERT INTO reservation (reservation_id, customer_id, schedule_id, reservation_type_id, number_of_people, discount_applied)
        VALUES (v_max_reservation_id, v_customer_id, v_schedule_id, p_reservation_type, p_number_of_people, v_discount_percentage);

        SET p_id_reservation = v_max_reservation_id;

        -- Update the schedule availability
        UPDATE schedule
        SET is_available = FALSE
        WHERE schedule_id = v_schedule_id;

        -- Log the reservation creation
        INSERT INTO reservation_log (reservation_id, action)
        VALUES (v_max_reservation_id, 'CREATE RESERVATION');

        -- Set the discount output
        SET p_discount = v_discount_percentage;

        -- Commit the transaction
        COMMIT;
    END IF;

END;