
USE MegaSoftDB;

SELECT
    c.name AS NAME,
    COUNT(p.id) AS PROJECT_COUNT
FROM
    client c
        JOIN
    project p
    ON c.id = p.client_id
GROUP BY
    c.name
HAVING
    COUNT(p.id) = (
        SELECT
            MAX(project_count)
        FROM (
                 SELECT
                     COUNT(p2.id) AS project_count
                 FROM
                     client c2
                         JOIN
                     project p2
                     ON
                         c2.id = p2.client_id
                 GROUP BY
                     c2.name
             ) AS counts
    );
