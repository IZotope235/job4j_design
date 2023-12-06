/*
1. В одном запросе получить

- имена всех person, которые не состоят в компании с id = 5;

- название компании для каждого человека.
*/

SELECT p.name, c.name AS company
FROM person AS p
LEFT JOIN company AS c
ON company_id = c.id
WHERE company_id != 5;

/*
Необходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании.
Нужно учесть, что таких компаний может быть несколько.
*/

WITH company_persons AS
        (SELECT count(p.name) AS persons,
        c.name AS company
        FROM person AS p
        LEFT JOIN company AS c
        ON company_id = c.id
        GROUP BY c.name)
SELECT company, persons AS persons_max
FROM company_persons
WHERE persons = (SELECT MAX(persons) FROM company_persons)
GROUP BY company, persons
