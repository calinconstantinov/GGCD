# Neo4j Global Graph Celebration Day

Run these queries on the Global Graph Celebration Day attendee graph: https://globalgraphcelebrationday.com/

1. _Find all Events_  
MATCH (e:Event)  
RETURN e  

1. _Find all Events, order by Country_  
MATCH (e:Event)  
RETURN e.Country, e.City  
ORDER BY e.Country ASC  

1. _Find all persons attending in Romania_  
MATCH (p:Person)-[:ATTENDING]->(e:Event)  
WHERE e.Country = 'Romania'  
RETURN p, e  

1. _Find the Events with most Persons attending_  
MATCH (p:Person)-[:ATTENDING]->(e:Event)  
WITH e, count(p) as participants  
ORDER BY count(p) DESC  
RETURN e.name, e.Country, e.City, e.Description, participants  

1. _Find the hobbies of persons attending in Romania_  
MATCH (p:Person)-[:ATTENDING]->(e:Event)  
WHERE e.Country = 'Romania'  
WITH p  
MATCH (p)-[:ENJOYS]->(h:Hobby)  
RETURN p, h  

1. _Optionally find the Hobbies, Tools and Usecases for the Persons atteding in Romania_  
MATCH (p:Person)-[:ATTENDING]->(e:Event)  
WHERE e.Country = 'Romania'  
WITH p  
OPTIONAL MATCH (p)-[:ENJOYS]->(h:Hobby)  
OPTIONAL MATCH (p)-[:USES]->(t:Tool)  
OPTIONAL MATCH (p)-[:INTERESTED_IN]->(u:Usecase)  
RETURN p, h, t, u  

1. _The are multiple entries for Calin. Find all data for Calin, only returning the first match_  
MATCH (p:Person)-[:ATTENDING]->(e:Event)  
WHERE e.Country = 'Romania' and p.name = "Calin"  
WITH p   
LIMIT 1  
OPTIONAL MATCH (p)-[:ENJOYS]->(h:Hobby)  
OPTIONAL MATCH (p)-[:USES]->(t:Tool)  
OPTIONAL MATCH (p)-[:INTERESTED_IN]->(u:Usecase)  
RETURN p, h, t, u  

1. _Find the most popular Usecases for Persons attending in Romania_  
MATCH (p:Person)-[:ATTENDING]->(e:Event)  
WHERE e.Country = 'Romania'  
WITH p  
MATCH (p)-[:INTERESTED_IN]->(u:Usecase)  
RETURN u.name as usecase, count(p) as peopleInterested  
ORDER BY count(p) DESC  

1. _Use the trim() function for grouping duplicate Usecases_  
MATCH (p:Person)-[:ATTENDING]->(e:Event)  
WHERE e.Country = 'Romania'  
WITH p  
MATCH (p)-[:INTERESTED_IN]->(u:Usecase)  
RETURN trim(u.name) as usecase, count(p) as peopleInterested  
ORDER BY count(p) DESC  

1. _Find the most similar to Calin Persons on the planet_  
MATCH (p:Person)-[:ATTENDING]->(e:Event)  
WHERE e.Country = 'Romania' and p.name = "Calin"  
WITH p  
LIMIT 1  
MATCH (p)-[:ENJOYS]->(h:Hobby)  
WITH p, h  
MATCH (s:Person)-[:ENJOYS]->(h)  
WHERE s.name <> "Calin"  
RETURN s.name, s.id, count(h) as commonHobies  
ORDER BY count(h) DESC  
LIMIT 10  
