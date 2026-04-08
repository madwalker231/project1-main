-- 1. Wipe the child first
DELETE FROM compliance_obligations;
-- 2. Wipe parents
DELETE FROM business_entities;
DELETE FROM regulatory_requirements;

-- 3. Insert Entities (Names match your logs)
INSERT INTO business_entities (id, name, entity_type, jurisdiction, status) VALUES 
(1, 'Goldman Sachs Group', 'Bank', 'US', 'Active'),
(2, 'Morgan Stanley', 'Broker-Dealer', 'US', 'Active'),
(3, 'JPMorgan Chase & Co.', 'Bank', 'US', 'Active'),
(4, 'BlackRock Inc.', 'Investment Firm', 'US', 'Active');

-- 4. Insert Requirements
INSERT INTO regulatory_requirements (id, name, governing_body, requirement_type, effective_date, status) VALUES 
(1, 'Anti-Money Laundering (AML) Check', 'FINRA', 'Reporting', '2026-05-10', 'Active'),
(2, 'CCAR Stress Testing', 'Federal Reserve', 'Capital Requirements', '2026-06-15', 'Active'),
(3, 'GDPR Data Privacy Audit', 'EU', 'Privacy', '2026-04-20', 'Active'),
(4, 'SEC Form 13F Disclosure', 'SEC', 'Reporting', '2026-04-15', 'Active');

-- 5. Insert Obligations (Notice: obligation_status instead of status)
INSERT INTO compliance_obligations (id, entity_id, requirement_id, obligation_status, last_review_date, notes) VALUES 
(1, 1, 4, 'Compliant', '2026-03-01', 'Q1 Filing completed.'),
(2, 2, 1, 'In Progress', '2026-03-15', 'Reviewing onboarding documents.'),
(3, 3, 2, 'Non-Compliant', '2026-01-10', 'Stress test failed; remediation required.');