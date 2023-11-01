--Добавьте процедуру которая будет удалять записи.

create or replace procedure delete_data(u_id integer)
language 'plpgsql'
as $$
    BEGIN
		delete from products
		where id = u_id;
    END;
$$;

call delete_date(1);

--Добавьте функцию, которая будет удалять записи.
create or replace function delete_row(u_id integer)
returns varchar
language 'plpgsql'
as
$$
    declare
        result varchar;
		u_count integer;
    begin
		select into u_count count from products
		where id = u_id;
        if u_count = 0 THEN
            select into result name from products where id = u_id;
            delete from products where id = u_id;
        end if;
        return result;
    end;
$$;

select delete_row(1);