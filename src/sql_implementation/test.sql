-- INSERT INTO Account (username, password_hash, email)
-- VALUES ("hehe", "password123", "example@test.com"); 

-- SELECT *
-- FROM Account;

-- INSERT INTO Game_Save (save_id, trainer_name, current_location, pokemon_team, gym_leaders_defeated, badges, last_saved) 
-- VALUES (1,'hehe', 'Pallet Town','[
--     {
--         "name": "Pikachu",
--         "level": 25
--     },
--     {
--         "name": "Charmander",
--         "level": 15
--     },
--     {
--         "name": "Bulbasaur",
--         "level": 20
--     }
-- ]', '["none","hehe"]','["badge1","badge2"]', CURRENT_TIMESTAMP);

SELECT *
FROM Game_Save;

-- INSERT INTO Account (username, password_hash, salt)
-- VALUES ("hehe2", "password123", "hehe"); 

-- INSERT INTO Account (username, password_hash, email)
-- VALUES ("hehe3", "password123", "example3@test.com"); 

-- DELETE FROM Account;
-- DELETE FROM Game_Save;

SELECT *
FROM Account;

-- SELECT COUNT(*) FROM Account;