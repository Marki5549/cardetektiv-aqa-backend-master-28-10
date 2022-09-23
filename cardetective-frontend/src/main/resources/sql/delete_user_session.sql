DELETE
FROM `cardetective-stage`.sessionData
WHERE sessionData.userIdentityId = (SELECT userIdentityId
                                    FROM `cardetective-stage`.userProfile
                                    WHERE userProfile.email = ?);