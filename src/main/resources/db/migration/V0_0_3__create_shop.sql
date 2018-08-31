create table shop (
  id int not null primary key,
  name varchar(255) unique not null
);

grant select, insert, update, delete on shop to "dbuser";

create sequence shop_pk_seq start with 1 increment by 1;

create procedure "dbuser".delete_all as
begin
  delete from "dbuser".shop;
end;
/