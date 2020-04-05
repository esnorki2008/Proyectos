LOAD DATA INFILE "/var/lib/mysql-files/transactions.csv"
INTO  TABLE  TemporalTransaction 
COLUMNS TERMINATED BY ','
OPTIONALLY ENCLOSED BY '"'
#ESCAPED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
#(Campo1,Campo2,Campo3,Campo4,Campo5,Campo6,Campo7,Campo8,Campo9,Campo10,
#Campo11,Campo12,Campo13,Campo14,Campo15,Campo16,Campo17,Campo18) 
;

#Drop table TemporalTransaction;