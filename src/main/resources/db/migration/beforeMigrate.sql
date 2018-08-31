declare
userexist integer;
begin
  select count(*) into userexist from dba_users where username='dbuser';
  if (userexist = 0) then
    execute immediate 'create tablespace dbuser_tabspace datafile ''dbuser_tabspace.dat'' size 10m autoextend on;';
    execute immediate 'create temporary tablespace dbuser_tabspace_temp tempfile ''dbuser_tabspace_temp.dat'' size 5m autoextend on;';
    execute immediate 'create user "dbuser" identified by "temp"  default tablespace dbuser_tabspace temporary tablespace dbuser_tabspace_temp;';
  end if;
end;
/