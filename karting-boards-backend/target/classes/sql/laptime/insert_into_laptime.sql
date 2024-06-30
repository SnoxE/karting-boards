WITH lap_time_counter AS (SELECT COUNT(*) AS entry_count FROM laptime WHERE laptime.id LIKE :counter_id)
INSERT
INTO laptime (id, track_id, session_id, driver_id, time)
Values (CONCAT(:id, '.', (select ltc.entry_count FROM lap_time_counter as ltc)),
        :track_id,
        :session_id,
        :driver_id,
        CAST(:time AS interval));

