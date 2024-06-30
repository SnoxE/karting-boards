select
    s.id as id,
    s.track_id as track_id,
    s.date as date,
    s.time as time
from
    session as s
where
    s.track_id = :trackId;

