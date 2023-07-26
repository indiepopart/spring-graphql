LOAD CSV WITH HEADERS FROM "file:///ElectionDonationsAmericans.csv" AS row
MATCH (c:Company) WHERE c.companyNumber = row.CompanyRegistrationNumber
MERGE (p:Recipient {name: row.RegulatedEntityName})
SET p.entityType = row.RegulatedEntityType
MERGE (c)-[r:DONATED {ref: row.ECRef}]->(p)
SET r.date  = Date(Datetime({epochSeconds: apoc.date.parse(row.ReceivedDate,'s','dd/MM/yyyy')})),
r.value = toFloat(replace(replace(row.Value, "£", ""), ",", ""));
