-- Create a new table called '[UserDetails]' in schema '[dbo]'

-- Create the table in the specified schema
CREATE TABLE [dbo].[UserDetails]
(
    [username] VARCHAR(50) NOT NULL PRIMARY KEY, -- Primary Key column
    [password] VARBINARY(max) NOT NULL,
    [email] VARCHAR(max) NOT NULL,
    [phone] INT NOT NULL, 
    [status] VARCHAR(50)
    -- Specify more columns here
);
GO