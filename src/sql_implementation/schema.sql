CREATE TABLE Account (
    account_id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL UNIQUE,
    password_hash TEXT NOT NULL,
    salt TEXT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Game_Save (
    save_id INTEGER PRIMARY KEY,
    trainer_name TEXT,
    current_location TEXT,
    pokemon_team JSON,
    gym_leaders_defeated JSON,
    badges JSON,
    last_saved DATETIME
);