SELECT
    d.id,
    d.first_name,
    d.last_name,
    d.nickname,
    d.sex,
    d.email,
    d.password,
    d.role
FROM
    driver AS d
WHERE
    d.email = :email;

