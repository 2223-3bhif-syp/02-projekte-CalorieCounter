CREATE TABLE CC_USER (
                         U_ID                    INTEGER NOT NULL CONSTRAINT U_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                         U_EMAIL                 VARCHAR(50),
                         U_USERNAME              VARCHAR(20),
                         U_PASSWORD              VARCHAR(20),
                         U_WEIGHT                DOUBLE,
                         U_HEIGHT                DOUBLE,
                         U_BIRTHDAY              DATE
);

CREATE TABLE CC_GOAL (
    G_ID                    INTEGER NOT NULL CONSTRAINT G_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    G_WEIGHT                DOUBLE,
    G_DEADLINE              DATE,
    G_U_ID                  INTEGER CONSTRAINT G_U_FK REFERENCES CC_USER(U_ID)
);

CREATE TABLE CC_WORKOUT (
    W_ID                    INTEGER NOT NULL CONSTRAINT W_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    W_NAME                  VARCHAR(20),
    W_CALORIES              DOUBLE,
    W_DURATION              DOUBLE,
    W_U_ID                  INTEGER NOT NULL CONSTRAINT W_U_FK REFERENCES CC_USER(U_ID)
);

CREATE TABLE CC_FOOD (
    F_ID                    INTEGER NOT NULL CONSTRAINT F_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    F_NAME                  VARCHAR(20),
    F_CALORIES              DOUBLE
);

CREATE TABLE CC_CONSUMPTION (
    C_ID                    INTEGER NOT NULL CONSTRAINT C_PK PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    C_U_ID                  INTEGER NOT NULL CONSTRAINT C_U_FK REFERENCES CC_USER(U_ID),
    C_F_ID                  INTEGER NOT NULL CONSTRAINT F_FK REFERENCES CC_FOOD(F_ID),
    C_AMOUNT                INTEGER
);