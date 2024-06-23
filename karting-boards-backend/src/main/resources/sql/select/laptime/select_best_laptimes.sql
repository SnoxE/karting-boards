select
    laptime.id as laptime_id,
    laptime.time as laptime,
    track.id as track_id,
    track.name as track_name,
    session.id as session_id,
    driver.id as driver_id,
    driver.first_name as driver_first_name,
    driver.last_name as driver_last_name,
    driver.nickname as driver_nickname
from
    laptime
    left join track ON laptime.track_id = track.id
    left join session ON laptime.session_id = session.id
    left join driver ON laptime.driver_id = driver.id
order by
    laptime.time;

