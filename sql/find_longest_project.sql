USE MegaSoftDB;

SELECT
    p.name AS NAME,
    TIMESTAMPDIFF(MONTH, p.start_date, p.finish_date) AS MONTH_COUNT
FROM
    project p
WHERE
    TIMESTAMPDIFF(MONTH, p.start_date, p.finish_date) = (
        SELECT
            MAX(TIMESTAMPDIFF(MONTH, start_date, finish_date))
        FROM
            project
    );
