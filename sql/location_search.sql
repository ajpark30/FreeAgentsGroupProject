# find nearest object to coordinates using great circle distance
# after narrowing a range search with bounding boxes
# adapted from https://www.plumislandmedia.net/mysql/haversine-mysql-nearest-loc/

SELECT waterfall_id,
       latitude, longitude, distance
  FROM (
 SELECT w.waterfall_id,
        w.latitude, w.longitude,
        p.radius,
        p.distance_unit
                 * DEGREES(ACOS(COS(RADIANS(p.latpoint))
                 * COS(RADIANS(w.latitude))
                 * COS(RADIANS(p.longpoint - w.longitude))
                 + SIN(RADIANS(p.latpoint))
                 * SIN(RADIANS(w.latitude)))) AS distance
  FROM waterfall AS w
  JOIN (   /* these are the query parameters */
        SELECT  0.0/*input longitude*/  AS latpoint,
		0.0/* input latitude */ AS longpoint,
                50000.0/* no narrowing with large value */ AS radius,
		/*111.045 for km*/ 69.0/* for miles*/ AS distance_unit
    ) AS p ON 1=1
  WHERE w.latitude
     BETWEEN p.latpoint  - (p.radius / p.distance_unit)
         AND p.latpoint  + (p.radius / p.distance_unit)
    AND w.longitude
     BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
         AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint))))
 ) AS d
 WHERE distance <= radius
 ORDER BY distance
 LIMIT 15
