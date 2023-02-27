-- Ziele
INSERT INTO CC_GOAL (G_WEIGHT, G_DEADLINE) VALUES (60.9, DATE(2023100));

INSERT INTO CC_GOAL (G_WEIGHT, G_DEADLINE) VALUES (118.3, DATE(2023100));

-- Benutzer
INSERT INTO CC_USER (U_EMAIL, U_USERNAME, U_PASSWORD, U_WEIGHT, U_HEIGHT, U_G_ID)
VALUES ('m.mustermann@students.htl-leonding.ac.at', 'Max', 'MAXL123', 63.5, 176.3, 1);

INSERT INTO CC_USER (U_EMAIL, U_USERNAME, U_PASSWORD, U_WEIGHT, U_HEIGHT, U_G_ID)
VALUES ('m.musterfrau@students.htl-leonding.ac.at', 'Marie', 'M4r1!', 60.1, 169.7, NULL);

INSERT INTO CC_USER (U_EMAIL, U_USERNAME, U_PASSWORD, U_WEIGHT, U_HEIGHT, U_G_ID)
VALUES ('t.testinger@students.htl-leonding.ac.at', 'theodore', 'th3od?0r!', 120.1, 193.0, 2);

-- Workout
INSERT INTO CC_WORKOUT (W_NAME, W_CALORIES, W_DURATION, W_U_ID)
VALUES ('sport', 20.0, 5.30, 1);

-- Essenstypen

INSERT INTO CC_FOOD (F_NAME, F_CALORIES) VALUES ('APPLE', 95.0);
INSERT INTO CC_FOOD (F_NAME, F_CALORIES) VALUES ('RICE', 205.0);
INSERT INTO CC_FOOD (F_NAME, F_CALORIES) VALUES ('HAM', 186.0);

-- Konsumationen

INSERT INTO CC_CONSUMPTION (C_U_ID, C_F_ID, AMOUNT) VALUES (1, 3, 2);
INSERT INTO CC_CONSUMPTION (C_U_ID, C_F_ID, AMOUNT) VALUES (1, 1, 1);
INSERT INTO CC_CONSUMPTION (C_U_ID, C_F_ID, AMOUNT) VALUES (2, 1, 2);
INSERT INTO CC_CONSUMPTION (C_U_ID, C_F_ID, AMOUNT) VALUES (3, 2, 4);