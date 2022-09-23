SELECT `modelKey`, `modelValue`
FROM `cardetective-develop1`.`modelsMakes`
WHERE `providerId` = ?
AND `modelKey` <> ''
AND `makeValue` = ?