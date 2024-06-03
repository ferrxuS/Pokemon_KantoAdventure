-- INSERT INTO Account (username, password_hash, email)
-- VALUES ("hehe", "password123", "example@test.com"); 

-- SELECT *
-- FROM Account;

INSERT INTO Game_Save (save_id, trainer_name, current_location, pokemon_team, gym_leaders_defeated, badges, last_saved) 
VALUES (5, 'trainerDog', 'Random Location','[
    {
        "name": "Squirtle",
        "level": 25,
        "hp": 90,
        "xp": 50,
        "moves": [
            {"name": "Water Gun", "damage": 50},
            {"name": "Tail Whip", "damage": 20}
        ]    
    },
    {
        "name": "Charmander",
        "level": 15,
        "hp": 70,
        "xp": 30,
        "moves": [
            {"name": "Flamethrower", "damage": 30},
            {"name": "Dragon Breath", "damage": 40}
        ]
    },
    {
        "name": "Bulbasaur",
        "level": 20,
        "hp": 80,
        "xp": 60,
        "moves": [
            {"name": "Vine Whip", "damage": 25},
            {"name": "Tackle", "damage": 36}
        ]
    }
]', '["none","hehe"]','["badge1","badge2"]', CURRENT_TIMESTAMP);

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