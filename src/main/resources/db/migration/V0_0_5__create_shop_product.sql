create table "dbuser".purchase(
  id int not null primary key,
  shop_id int not null,
  product_id int not null,
  price number(10, 4) not null,
  count int not null,
  purchase_date timestamp not null,
  constraint unique_item unique (shop_id, product_id, purchase_date),
  constraint fk_shop_id
    foreign key (shop_id)
    references "dbuser".shop(id)
    on delete cascade,
  constraint fk_product_id
    foreign key (product_id)
    references "dbuser".product(id)
    on delete cascade
);

create sequence purchase_pk_seq start with 1 increment by 1;