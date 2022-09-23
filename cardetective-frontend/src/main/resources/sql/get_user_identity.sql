SELECT `requestId`
FROM `cardetective-stage`.`pendingPasswordChangeData`
WHERE `userIdentityId` IN (SELECT `id`
                           FROM `cardetective-stage`.`userIdentity`
                           WHERE `userIdentity`.`userName` = ?) LIMIT 1;