create table product (
  id int not null primary key,
  name varchar(255) unique not null
);

grant select, insert, update, delete on product to "dbuser";

create sequence product_pk_seq start with 1 increment by 1;

drop procedure "dbuser".delete_all;

create procedure "dbuser".delete_all as
begin
  delete from "dbuser".shop;
  delete from "dbuser".product;
end;
/