
USE MegaSoftDB;

SELECT
    'YOUNGEST' AS TYPE,
    w.name AS NAME,
    w.birthday AS BIRTHDAY
FROM worker w
WHERE w.birthday = (
    SELECT MAX(birthday)
    FROM worker
)
UNION ALL
SELECT
    'ELDEST' AS TYPE,
    w.name AS NAME,
    w.birthday AS BIRTHDAY
FROM worker w
WHERE w.birthday = (
    SELECT MIN(birthday)
    FROM worker
);