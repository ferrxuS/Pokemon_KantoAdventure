-- INSERT INTO Account (username, password_hash, email)
-- VALUES ("hehe", "password123", "example@test.com"); 

-- SELECT *
-- FROM Account;

-- INSERT INTO Game_State (account_id, current_location, current_level, pokemon_team, last_saved) 
-- VALUES (1, 'Pallet Town', 5, '[
--     {
--         "pokemon_id": 1,
--         "name": "Pikachu",
--         "type": "Electric",
--         "level": 25
--     },
--     {
--         "pokemon_id": 2,
--         "name": "Charmander",
--         "type": "Fire",
--         "level": 15
--     },
--     {
--         "pokemon_id": 3,
--         "name": "Bulbasaur",
--         "type": "Grass/Poison",
--         "level": 20
--     }
-- ]', CURRENT_TIMESTAMP);

-- SELECT *
-- FROM Game_State;

-- INSERT INTO Account (username, password_hash, salt)
-- VALUES ("hehe2", "password123", "hehe"); 

-- INSERT INTO Account (username, password_hash, email)
-- VALUES ("hehe3", "password123", "example3@test.com"); 

-- DELETE FROM Account;
-- DELETE FROM Game_Save;

SELECT *
FROM Account;

-- SELECT COUNT(*) FROM Account;