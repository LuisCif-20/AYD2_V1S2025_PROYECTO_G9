ALTER TABLE inventory
ADD COLUMN is_low_stock_alert_sent BOOLEAN NOT NULL DEFAULT FALSE,
ADD COLUMN low_stock_threshold INT NOT NULL DEFAULT 5;
