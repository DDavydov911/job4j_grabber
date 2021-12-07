CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company(id, name) VALUES (1, 'Aple'),
(2, 'Google'), (3, 'Amazon'), (4, 'Meta'), (5, 'Uber');

INSERT INTO person (id, name, company_id)
VALUES (1, 'Jhon Dho', 1), (2, 'Jain O''Brain', 1),
(3, 'Sarah Conor', 2), (4, 'Mike Salivan', 2), (5, 'Jein Flor', 3),
(6, 'Alex Grey', 3), (7, 'Amy Brangston', 4), (8, 'Tomy Mur', 4),
(9, 'Freddy Green', 5), (10, 'Martin Smith', 5), (11, 'Danial Bloh', 5)
(12, 'Anny Oster', 4);

select p.name, c.name
from person p join company c
on c.id = p.company_id
where company_id != 5;

-- Мой метод решения
select tab2.name, tab2.employes
from (
	select c.name, count(*) as employes
	from person p join company c
	ON c.id = p.company_id
	group by c.name)
	as tab2
where tab2.employes = (
select max(tab3.employes) from (
	select c.name, count(*) as employes
	from person p join company c
	ON c.id = p.company_id
	group by c.name)
	as tab3
);

-- Метод от ментора
SELECT c.name, count(*) as employes
from person p JOIN company c
ON c.id = p.company_id
GROUP BY c.name
having count(*) = (SELECT count(*)
from person p GROUP BY p.company_id
ORDER BY count(*) DESC
LIMIT 1);

