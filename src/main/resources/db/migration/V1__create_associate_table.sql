CREATE TABLE associate (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    document VARCHAR(20) NOT NULL UNIQUE,
    create_date DATE NOT NULL DEFAULT CURRENT_DATE,
    delete_date DATE
);

CREATE TABLE agenda (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    describe TEXT,
    aproved BOOLEAN,
    create_date DATE NOT NULL DEFAULT CURRENT_DATE
);

CREATE TABLE session_agenda (
    id BIGSERIAL PRIMARY KEY,
    agenda_id BIGINT NOT NULL REFERENCES agenda(id) ON DELETE CASCADE,
    start_date TIMESTAMP NOT NULL,
    finish_date TIMESTAMP NOT NULL,
    open BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE vote_agenda (
    id BIGSERIAL PRIMARY KEY,
    agenda_id BIGINT NOT NULL REFERENCES agenda(id) ON DELETE CASCADE,
    associate_id BIGINT NOT NULL REFERENCES associate(id) ON DELETE CASCADE,
    vote BOOLEAN NOT NULL,
    create_date DATE NOT NULL DEFAULT CURRENT_DATE,
    UNIQUE(agenda_id, associate_id)
);

CREATE TABLE shedlock(
    name VARCHAR(64) NOT NULL,
    lock_until TIMESTAMP NOT NULL,
    locked_at TIMESTAMP NOT NULL,
    locked_by VARCHAR(255) NOT NULL,
    PRIMARY KEY(name)
);