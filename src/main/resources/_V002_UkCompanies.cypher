CREATE CONSTRAINT FOR (c:Company) REQUIRE c.companyNumber IS UNIQUE;
//Constraint for a node key is a Neo4j Enterprise feature only - run on an instance with enterprise
//CREATE CONSTRAINT ON (p:Person) ASSERT (p.birthMonth, p.birthYear, p.name) IS NODE KEY
CREATE CONSTRAINT FOR (p:Property) REQUIRE p.titleNumber IS UNIQUE;
LOAD CSV WITH HEADERS FROM "https://guides.neo4j.com/ukcompanies/data/PSCAmericans.csv" AS row
MERGE (c:Company {companyNumber: row.company_number})
RETURN COUNT(*);
LOAD CSV WITH HEADERS FROM "https://guides.neo4j.com/ukcompanies/data/PSCAmericans.csv" AS row
MERGE (p:Person {name: row.`data.name`, birthYear: row.`data.date_of_birth.year`, birthMonth: row.`data.date_of_birth.month`})
ON CREATE SET p.nationality = row.`data.nationality`,
p.countryOfResidence = row.`data.country_of_residence`
// TODO: Address
RETURN COUNT(*);
LOAD CSV WITH HEADERS FROM "https://guides.neo4j.com/ukcompanies/data/PSCAmericans.csv" AS row
MATCH (c:Company {companyNumber: row.company_number})
MATCH (p:Person {name: row.`data.name`, birthYear: row.`data.date_of_birth.year`, birthMonth: row.`data.date_of_birth.month`})
MERGE (p)-[r:HAS_CONTROL]->(c)
SET r.nature = split(replace(replace(replace(row.`data.natures_of_control`, "[",""),"]",""),  '"', ""), ",")
RETURN COUNT(*);
LOAD CSV WITH HEADERS FROM "https://guides.neo4j.com/ukcompanies/data/CompanyDataAmericans.csv" AS row
MATCH (c:Company {companyNumber: row.` CompanyNumber`})
SET c.name = row.CompanyName,
c.mortgagesOutstanding = toInteger(row.`Mortgages.NumMortOutstanding`),
c.incorporationDate = Date(Datetime({epochSeconds: apoc.date.parse(row.IncorporationDate,'s','dd/MM/yyyy')})),
c.SIC = row.`SICCode.SicText_1`,
c.countryOfOrigin = row.CountryOfOrigin,
c.status = row.CompanyStatus,
c.category = row.CompanyCategory;
LOAD CSV WITH HEADERS FROM "https://guides.neo4j.com/ukcompanies/data/ElectionDonationsAmericans.csv" AS row
MATCH (c:Company) WHERE c.companyNumber = row.CompanyRegistrationNumber
MERGE (p:Recipient {name: row.RegulatedEntityName})
SET p.entityType = row.RegulatedEntityType
MERGE (c)-[r:DONATED {ref: row.ECRef}]->(p)
SET r.date  = Date(Datetime({epochSeconds: apoc.date.parse(row.ReceivedDate,'s','dd/MM/yyyy')})),
r.value = toFloat(replace(replace(row.Value, "£", ""), ",", ""));
LOAD CSV WITH HEADERS FROM "https://guides.neo4j.com/ukcompanies/data/LandOwnershipAmericans.csv" AS row
MATCH (c:Company {companyNumber: row.`Company Registration No. (1)`})
MERGE (p:Property {titleNumber: row.`Title Number`})
SET p.address = row.`Property Address`,
p.county  = row.County,
p.price   = toInteger(row.`Price Paid`),
p.district = row.District
MERGE (c)-[r:OWNS]->(p)
WITH row, c,r,p WHERE row.`Date Proprietor Added` IS NOT NULL
SET r.date = Date(Datetime({epochSeconds: apoc.date.parse(row.`Date Proprietor Added`,'s','dd-MM-yyyy')}));
CREATE INDEX FOR (c:Company) ON c.incorporationDate;
