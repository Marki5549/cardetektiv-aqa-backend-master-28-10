SELECT DISTINCT makeKey, makeValue
FROM `cardetective`.modelsMakes
WHERE providerId = ? AND modelKey <> '';