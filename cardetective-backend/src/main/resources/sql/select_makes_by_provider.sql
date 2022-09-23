SELECT DISTINCT makeKey, makeValue
FROM `cardetective-stage`.modelsMakes
WHERE providerId = ? AND modelKey <> '';