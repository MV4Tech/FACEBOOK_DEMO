-- Remove the 'image_data' column from the 'product' table
ALTER TABLE photo
DROP COLUMN photo_data;

-- Add a new column 'image_path' to the 'product' table
ALTER TABLE photo
ADD COLUMN file_path VARCHAR(255);