UPDATE `cardetective-stage`.`userIdentity`
SET `status`                = 'ACTIVE',
    `accountExpirationDate` = '1924902000000'
WHERE `userName` = ?;