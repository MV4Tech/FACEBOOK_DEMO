-- Add a new column 'image_path' to the 'product' table
ALTER TABLE page
ADD COLUMN profilePicturePath VARCHAR(255);

ALTER TABLE page
ADD COLUMN coverPicturePath VARCHAR(255);