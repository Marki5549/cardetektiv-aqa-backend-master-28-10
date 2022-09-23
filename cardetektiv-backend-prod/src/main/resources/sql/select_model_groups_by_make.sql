SELECT DISTINCT mg.groupKey, mg.mobilewKey, mg.autoscoutKey, mg.autoscoutchKey, mg.pkwKey, mg.comprschKey,
                mg.lacentralKey, mg.cochesKey, mg.automobileKey, mg.porscheFinderKey, mg.otomotoKey, mg.autotraderKey,
                mg.autoscoutchKey, mm.makeKey, mm.makeValue
FROM `cardetective`.modelGroups mg
INNER JOIN `cardetective`.modelsMakes mm
ON mg.makeKey = mm.makeKey
WHERE `providerId` = ?
AND mm.makeValue = ?