SELECT `modelKey`, `modelValue`
FROM `cardetective`.`modelsMakes`
WHERE `providerId` = ?
AND `modelKey` <> ''
AND `makeValue` = ?