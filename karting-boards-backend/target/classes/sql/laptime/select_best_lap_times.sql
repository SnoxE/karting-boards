select
    laptime.id as laptime_id,
    EXTRACT(minute FROM laptime.time) AS laptime_minutes,
    FLOOR(EXTRACT(second FROM laptime.time)) AS laptime_seconds,
    FLOOR((EXTRACT(epoch FROM laptime.time) * 1000) % 1000) AS laptime_milliseconds,
    track.id as track_id,
    track.name as track_name,
    session.id as session_id,
    session.date as session_date,
    session.time as session_time,
    driver.id as driver_id,
    driver.first_name as driver_first_name,
    driver.last_name as driver_last_name,
    driver.nickname as driver_nickname
from
    laptime
        left join track ON laptime.track_id = track.id
        left join session ON laptime.session_id = session.id
        left join driver ON laptime.driver_id = driver.id
where
    track.id = :trackId
order by
    laptime_minutes,
    laptime_seconds,
    laptime_milliseconds
LIMIT
    10;

