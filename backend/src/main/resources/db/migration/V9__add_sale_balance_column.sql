ALTER TABLE sale
ADD COLUMN remaining_balance DECIMAL(10,2) NOT NULL DEFAULT 0
CHECK (remaining_balance >= 0);
