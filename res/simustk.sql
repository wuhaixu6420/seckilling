CREATE DEFINER=`gmuser1`@`%` PROCEDURE `dealStock`()
BEGIN
  
  -- skposition
  DECLARE i_id bigint(20);
  DECLARE i_usablestock varchar(100);
  DECLARE i_freezenum varchar(100);
  
  
  	-- 遍历数据结束标志
	DECLARE done INT DEFAULT FALSE;
 
  
  
  -- 游标
	DECLARE cur CURSOR FOR
		SELECT 
      `id`
      ,`usablestock`
      ,`freezenum`
		FROM skposition where `freezenum` is not null;
	-- 将结束标志绑定到游标
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
  
  -- 手动提交
	set autocommit = 0;
  
  -- 打开游标
	OPEN cur;
 
  -- 开始循环
		read_loop: LOOP
    -- 提取游标里的数据，这里只有一个，多个的话也一样；
		FETCH cur INTO
      i_id
      ,i_usablestock
      ,i_freezenum
      ;
       	-- 声明结束的时候
			IF done THEN
				LEAVE read_loop;
			END IF;
   UPDATE  skposition set
      `usablestock`=i_usablestock + i_freezenum
      ,`freezenum`=null
      where id=i_id;
      
   	COMMIT;
    
   END LOOP;
  -- 提交
		COMMIT;
	
	-- 关闭游标
	CLOSE cur;
  
  -- 自动提交
	set autocommit = 1;
  
END;

create event event_dealStock
on schedule every 1 day 
STARTS TIMESTAMP '2016-02-16 15:30:00'
on completion preserve enable  
do  
begin  
    call dealStock();  
end;

CREATE DEFINER=`gmuser1`@`%` PROCEDURE `moveclinchdeal`()
BEGIN
  -- 需要定义接收游标数据的变量
  DECLARE i_id bigint(20);
  DECLARE i_accountid bigint(20);
  DECLARE i_crtdt timestamp;
  DECLARE i_upddt timestamp;
  
  -- skclinchdeal  skhistory_cli
  DECLARE i_stockcode varchar(100);
  DECLARE i_stockname varchar(100);
  DECLARE i_operation varchar(100);
  DECLARE i_clinchdealnum varchar(100);
  DECLARE i_clinchdealtm timestamp;
  DECLARE i_averageprice varchar(100);
  DECLARE i_clinchdealsum varchar(100);
  DECLARE i_contractno varchar(100);
  DECLARE i_clinchdealno varchar(100);
  DECLARE i_cancellationnum varchar(100);
  DECLARE i_stocknum varchar(100);
  DECLARE i_updymd date;
  DECLARE i_crtymd date;
  
  	-- 遍历数据结束标志
	DECLARE done INT DEFAULT FALSE;
 
  
  
  -- 游标
	DECLARE cur CURSOR FOR
		SELECT 
			`id`
      ,`accountid`
      ,`stockcode`
      ,`stockname`
      ,`operation`
      ,`clinchdealnum`
      ,`clinchdealtm`
      ,`averageprice`
      ,`clinchdealsum`
      ,`contractno`
      ,`clinchdealno`
      ,`cancellationnum`
      ,`stocknum`
      ,`crtymd`
      ,`updymd`
      ,`crtdt`
      ,`upddt`
		FROM skclinchdeal ;
	-- 将结束标志绑定到游标
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
  
  -- 手动提交
	set autocommit = 0;
  
  -- 打开游标
	OPEN cur;
 
  -- 开始循环
		read_loop: LOOP
 
    -- 提取游标里的数据，这里只有一个，多个的话也一样；
		FETCH cur INTO
      i_id
      ,i_accountid
      ,i_stockcode
      ,i_stockname
      ,i_operation
      ,i_clinchdealnum
      ,i_clinchdealtm
      ,i_averageprice
      ,i_clinchdealsum
      ,i_contractno
      ,i_clinchdealno
      ,i_cancellationnum
      ,i_stocknum
      ,i_crtymd
      ,i_updymd
      ,i_crtdt
      ,i_upddt
      ;
      
      	-- 声明结束的时候
			IF done THEN
				LEAVE read_loop;
			END IF;
      
   INSERT INTO skhistory_cli (
      `id`
      ,`accountid`
      ,`stockcode`
      ,`stockname`
      ,`operation`
      ,`clinchdealnum`
      ,`clinchdealtm`
      ,`averageprice`
      ,`clinchdealsum`
      ,`contractno`
      ,`clinchdealno`
      ,`cancellationnum`
      ,`stocknum`
      ,`crtymd`
      ,`updymd`
      ,`crtdt`
      ,`upddt`
   ) VALUES (
      i_id
      ,i_accountid
      ,i_stockcode
      ,i_stockname
      ,i_operation
      ,i_clinchdealnum
      ,i_clinchdealtm
      ,i_averageprice
      ,i_clinchdealsum
      ,i_contractno
      ,i_clinchdealno
      ,i_cancellationnum
      ,i_stocknum
      ,i_crtymd
      ,i_updymd
      ,i_crtdt
      ,i_upddt
   );
   
  	COMMIT;
   END LOOP;
   
   DELETE FROM skclinchdeal;
  -- 提交
		COMMIT;
	
	-- 关闭游标
	CLOSE cur;
  
  -- 自动提交
	set autocommit = 1;
  
END;


create event event_moveclinchdeal
on schedule every 1 day 
STARTS TIMESTAMP '2016-02-16 15:30:00'
on completion preserve enable  
do  
begin  
    call moveclinchdeal();  
end;


CREATE DEFINER=`gmuser1`@`%` PROCEDURE `moveskasset`()
BEGIN
  -- 需要定义接收游标数据的变量
  DECLARE i_id bigint(20);
  DECLARE i_accountid bigint(20);
  DECLARE i_crtdt timestamp;
  DECLARE i_upddt timestamp;
  
  -- skasset  skhistory_ass
  DECLARE i_usableasset varchar(100);
  DECLARE i_freezeasset varchar(100);
  DECLARE i_stockmarketvalue varchar(100);
  DECLARE i_totalcys varchar(100);
  DECLARE i_crtymd date;
  DECLARE i_updymd date;
  
	-- 遍历数据结束标志
	DECLARE done INT DEFAULT FALSE;
 
  -- 游标 skasset  skhistory_ass
	DECLARE cur CURSOR FOR
		SELECT 
			`id`
      ,`accountid`
      ,`usableasset`
      ,`freezeasset`
      ,`stockmarketvalue`
      ,`totalcys`
      ,`crtymd`
      ,`updymd`
      ,`crtdt`
      ,`upddt`
		FROM skasset ;
	-- 将结束标志绑定到游标
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
  
  
  -- 手动提交
	set autocommit = 0;
  
  -- 打开游标
	OPEN cur;
 
   -- 开始循环
		read_loop: LOOP
   
    -- 提取游标里的数据，这里只有一个，多个的话也一样；
		FETCH cur INTO
      i_id
      ,i_accountid
      ,i_usableasset
      ,i_freezeasset
      ,i_stockmarketvalue
      ,i_totalcys
      ,i_crtymd
      ,i_updymd
      ,i_crtdt
      ,i_upddt
      ;
      
       	-- 声明结束的时候
			IF done THEN
				LEAVE read_loop;
			END IF;
      
   INSERT INTO skhistory_ass (
      `id`
      ,`accountid`
      ,`usableasset`
      ,`freezeasset`
      ,`stockmarketvalue`
      ,`totalcys`
      ,`crtymd`
      ,`updymd`
      ,`crtdt`
      ,`upddt`
   ) VALUES (
        i_id
      ,i_accountid
      ,i_usableasset
      ,i_freezeasset
      ,i_stockmarketvalue
      ,i_totalcys
      ,i_crtymd
      ,i_updymd
      ,i_crtdt
      ,i_upddt
   );
   
   	COMMIT;
    
   END LOOP;
  -- 提交
		COMMIT;
	
	-- 关闭游标
	CLOSE cur;
	
	-- 自动提交
	set autocommit = 1;
  
END;


create event event_moveskasset
on schedule every 1 day 
STARTS TIMESTAMP '2016-02-16 15:30:00'
on completion preserve enable  
do  
begin  
    call moveskasset();  
end;


CREATE DEFINER=`gmuser1`@`%` PROCEDURE `moveskentrust`()
BEGIN
  -- 需要定义接收游标数据的变量
  DECLARE i_id bigint(20);
  DECLARE i_accountid bigint(20);
  DECLARE i_crtdt timestamp;
  DECLARE i_upddt timestamp;
  -- skentrust  skhistory_ent
  DECLARE i_stockcode varchar(100);
  DECLARE i_stockname varchar(100);
  DECLARE i_description varchar(100);
  DECLARE i_entrustnum varchar(100);
  DECLARE i_clinchdealnum varchar(100);
  DECLARE i_entrustprice varchar(100);
  DECLARE i_averageprice varchar(100);
  DECLARE i_operation varchar(100);
  DECLARE i_entrusttm timestamp;
  DECLARE i_entrustdate timestamp;
  DECLARE i_contractno varchar(100);
  DECLARE i_market varchar(100);
  DECLARE i_shdact varchar(100);
  DECLARE i_indenttype varchar(100);
  DECLARE i_crtymd date;
  DECLARE i_updymd date;
 
	-- 遍历数据结束标志
	DECLARE done INT DEFAULT FALSE;

  -- 游标
	DECLARE cur CURSOR FOR
		SELECT 
			`id`
      ,`accountid`
      ,`stockcode`
      ,`stockname`
      ,`description`
      ,`entrustnum`
      ,`clinchdealnum`
      ,`entrustprice`
      ,`averageprice`
      ,`operation`
      ,`entrusttm`
      ,`entrustdate`
      ,`contractno`
      ,`market`
      ,`shdact`
      ,`indenttype`
      ,`crtymd`
      ,`updymd`
      ,`crtdt`
      ,`upddt`
		FROM skentrust ;
	-- 将结束标志绑定到游标
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
  
  -- 手动提交
	set autocommit = 0;
  
  -- 打开游标
	OPEN cur;
 
  -- 开始循环
		read_loop: LOOP
 
    -- 提取游标里的数据，这里只有一个，多个的话也一样；
		FETCH cur INTO
      i_id
      ,i_accountid
      ,i_stockcode
      ,i_stockname
      ,i_description
      ,i_entrustnum
      ,i_clinchdealnum
      ,i_entrustprice
      ,i_averageprice
      ,i_operation
      ,i_entrusttm
      ,i_entrustdate
      ,i_contractno
      ,i_market
      ,i_shdact
      ,i_indenttype
      ,i_crtymd
      ,i_updymd
      ,i_crtdt
      ,i_upddt
      ;
      
      	-- 声明结束的时候
			IF done THEN
				LEAVE read_loop;
			END IF;
      
   INSERT INTO skhistory_ent (
     	`id`
      ,`accountid`
      ,`stockcode`
      ,`stockname`
      ,`description`
      ,`entrustnum`
      ,`clinchdealnum`
      ,`entrustprice`
      ,`averageprice`
      ,`operation`
      ,`entrusttm`
      ,`entrustdate`
      ,`contractno`
      ,`market`
      ,`shdact`
      ,`indenttype`
      ,`crtymd`
      ,`updymd`
      ,`crtdt`
      ,`upddt`
   ) VALUES (
      i_id
      ,i_accountid
      ,i_stockcode
      ,i_stockname
      ,i_description
      ,i_entrustnum
      ,i_clinchdealnum
      ,i_entrustprice
      ,i_averageprice
      ,i_operation
      ,i_entrusttm
      ,i_entrustdate
      ,i_contractno
      ,i_market
      ,i_shdact
      ,i_indenttype
      ,i_crtymd
      ,i_updymd
      ,i_crtdt
      ,i_upddt
   );
  
  	COMMIT;
   END LOOP;
  
  	DELETE FROM skentrust;
  
  -- 提交
		COMMIT;
	
	-- 关闭游标
	CLOSE cur;
	
	-- 自动提交
	set autocommit = 1;
  
END;

create event event_moveskentrust
on schedule every 1 day 
STARTS TIMESTAMP '2016-02-16 15:30:00'
on completion preserve enable  
do  
begin  
    call moveskentrust();  
end;


CREATE DEFINER=`gmuser1`@`%` PROCEDURE `updateass`()
BEGIN
  -- 手动提交
	set autocommit = 0;
  
  
  
   update skasset d,
    (select e.`accountid`
    ,sum(e.`stockexcess`*100*b.`price`) g
     from skposition e,skprice b 
     where e.`stockcode` = b.`stkcd` 
     group by e.`accountid`) c set 
       d.`stockmarketvalue` = c.g
       ,d.`totalcys`=d.`usableasset`+c.g 
        where d.`accountid` = c.`accountid`;

  -- 提交
		COMMIT;
  
  -- 自动提交
	set autocommit = 1;
  
END;

create event event_updateass
on schedule every 1 day 
STARTS TIMESTAMP '2016-02-16 15:20:00'
on completion preserve enable  
do  
begin  
    call updateass();  
end;


SET GLOBAL event_scheduler = ON;















