SELECT
    d.id AS id,
    d.first_name AS first_name,
    d.last_name AS last_name,
    d.nickname AS nickname,
    d.sex AS sex,
    d.email AS email,
    d.role AS role
FROM
    driver AS d
WHERE
    d.id = ?;

