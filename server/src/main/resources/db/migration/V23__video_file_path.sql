ALTER TABLE video
DROP COLUMN video_data;


ALTER TABLE video
ADD COLUMN video_file_path VARCHAR(255);