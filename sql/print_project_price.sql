
USE MegaSoftDB;

SELECT
    p.name AS NAME,
    TIMESTAMPDIFF(MONTH, p.start_date, p.finish_date)
        * SUM(w.salary) AS PRICE
FROM
    project p
        JOIN
    project_workers pw ON p.id = pw.project_id
        JOIN
    worker w ON pw.worker_id = w.id
GROUP BY
    p.name, p.start_date, p.finish_date
ORDER BY
    PRICE DESC;
