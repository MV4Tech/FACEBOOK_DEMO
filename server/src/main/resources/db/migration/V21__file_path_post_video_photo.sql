-- Remove the 'image_data' column from the 'product' table
ALTER TABLE post_photo
DROP COLUMN photo_data;

ALTER TABLE post_video
DROP COLUMN video_data;

-- Add a new column 'image_path' to the 'product' table
ALTER TABLE post_photo
ADD COLUMN photo_file_path VARCHAR(255);

ALTER TABLE post_video
ADD COLUMN video_file_path VARCHAR(255);